package live.betterman.api.config.security;

import cn.hutool.http.HttpStatus;
import com.alibaba.fastjson.JSON;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: zhudawei
 * @date: 2024/1/4
 * @description:
 */
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Map<String, Object> result = new HashMap<>();
        result.put("message", "登录成功");
        result.put("code", HttpStatus.HTTP_OK);

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(JSON.toJSONString(result));
    }
}
