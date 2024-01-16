package live.betterman.api.config.security;

import com.alibaba.fastjson.JSON;
import live.betterman.system.model.SysUser;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StreamUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @author: zhudawei
 * @date: 2024/1/16
 * @description: jwt认证过滤器
 */
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final static String POST = "POST";
    public JwtAuthenticationFilter(String loginUrl){
        super.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(loginUrl, POST));
    }
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        if (!request.getMethod().equals(POST)) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        SysUser sysUser = readBody(request);
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(sysUser.getUsername(), sysUser.getPassword());
        setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    private SysUser readBody(HttpServletRequest request){
        try {
            String requestBody = StreamUtils.copyToString(request.getInputStream(), Charset.defaultCharset());
            return JSON.parseObject(requestBody, SysUser.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
