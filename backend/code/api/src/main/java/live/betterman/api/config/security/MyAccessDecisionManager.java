package live.betterman.api.config.security;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @author: zhudawei
 * @date: 2024/1/10
 * @description: 自定义权限判断决策类
 */
public class MyAccessDecisionManager implements AccessDecisionManager {
    /**
     * 判定是否拥有权限的决策方法
     * @param authentication
     * @param object 包含客户端发起的请求的request信息，可转换为HttpServletRequest ((FilterInvocation) object).getHttpRequest()
     * @param configAttributes MyInvocationSecurityMetadataSource#getAttributes返回的结果
     * @throws AccessDeniedException
     * @throws InsufficientAuthenticationException
     */
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        if(configAttributes == null || configAttributes.size() == 0){
            return;
        }

        for (ConfigAttribute attribute : configAttributes){
            boolean matched = authentication.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals(attribute.getAttribute()));
            if(matched){
                return;
            }
        }

        throw new AccessDeniedException("没有权限访问此功能");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
