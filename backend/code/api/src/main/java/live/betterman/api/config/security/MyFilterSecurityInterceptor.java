package live.betterman.api.config.security;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author: zhudawei
 * @date: 2024/1/10
 * @description: 自定义的授权过滤器
 * 可以在MySecurityConfig中直接new FilterSecurityInterceptor()
 */
@Deprecated
public class MyFilterSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {

    private FilterInvocationSecurityMetadataSource metadataSource;

    public MyFilterSecurityInterceptor(FilterInvocationSecurityMetadataSource metadataSource, AccessDecisionManager accessDecisionManager){
        this.metadataSource = metadataSource;
        super.setAccessDecisionManager(accessDecisionManager);
    }

    @Override
    public void setRejectPublicInvocations(boolean rejectPublicInvocations){
        //rejectPublicInvocations属性含义为拒绝公共请求， 默认为false。
        // 如果从配置好的SecurityMetadataSource中获取不到当前 request 所对应的ConfigAttribute 时，即认为当前请求为公共请求。
        // 如配置rejectPublicInvocations属性为true，则系统会抛出IllegalArgumentException异常。即当前请求需要配置权限信息。
        // AbstractSecurityInterceptor#beforeInvocation
        super.setRejectPublicInvocations(rejectPublicInvocations);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        FilterInvocation filterInvocation = new FilterInvocation(servletRequest, servletResponse, filterChain);
        invoke(filterInvocation);
    }

    /**
     * fi里面有一个被拦截的url
     * 里面调用MyInvocationSecurityMetadataSource的getAttributes(Object object)这个方法获取fi对应的所有权限
     * 再调用MyAccessDecisionManager的decide方法来校验用户的权限是否足够
     * @param fi
     * @throws ServletException
     * @throws IOException
     */
    private void invoke(FilterInvocation fi) throws ServletException, IOException {
        InterceptorStatusToken token = super.beforeInvocation(fi);
        try {
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        }finally {
            super.afterInvocation(token, null);
        }
    }

    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return this.metadataSource;
    }
}
