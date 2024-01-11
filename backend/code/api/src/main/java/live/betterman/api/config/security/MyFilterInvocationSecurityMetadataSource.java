package live.betterman.api.config.security;

import live.betterman.system.model.SysPermission;
import live.betterman.system.service.SysPermissionService;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author: zhudawei
 * @date: 2024/1/10
 * @description: 自定义权限配置加载类
 */
public class MyFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private SysPermissionService permissionService;
    private Map<String, Collection<ConfigAttribute>> config = null;

    public MyFilterInvocationSecurityMetadataSource(SysPermissionService permissionService){
        this.permissionService = permissionService;
    }

    /**
     * http请求时，通过此方法返回指定url需要的权限
     * @param object
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        if(config == null){
            initConfig();
        }
        //object 中包含用户请求的request 信息
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        AntPathRequestMatcher matcher;
        String resUrl;
        for(Iterator<String> iter = config.keySet().iterator(); iter.hasNext(); ) {
            resUrl = iter.next();
            matcher = new AntPathRequestMatcher(resUrl);
            if(matcher.matches(request)) {
                return config.get(resUrl);
            }
        }

        return Collections.emptyList();
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    /**
     * 加载所有权限
     */
    private void initConfig() {
        this.config = new HashMap<>();
        List<SysPermission> permissions = permissionService.getAll();

        for (SysPermission permission : permissions) {
            ConfigAttribute configAttribute = new SecurityConfig(permission.getPermissionName());

            String key = permission.getUrl();
            if (config.containsKey(key)) {
                config.get(key).add(configAttribute);
            } else {
                Collection<ConfigAttribute> configAttributes = new ArrayList<>();
                configAttributes.add(configAttribute);
                config.put(key, configAttributes);
            }
        }
    }
}
