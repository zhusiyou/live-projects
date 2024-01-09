package live.betterman.api.config.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: zhudawei
 * @date: 2024/1/9
 * @description: 手机号验证码方式认证
 */
public class MobileCodeAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    protected MobileCodeAuthenticationFilter(String defaultFilterProcessesUrl) {
        super(new AntPathRequestMatcher(defaultFilterProcessesUrl, "GET"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String mobile = request.getParameter("mobile");
        String code = request.getParameter("code");
        String companyId = request.getParameter("companyId");

        MobileCodeAuthenticationToken authRequest = new MobileCodeAuthenticationToken(mobile, code, companyId);
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
        return this.getAuthenticationManager().authenticate(authRequest);
    }
}
