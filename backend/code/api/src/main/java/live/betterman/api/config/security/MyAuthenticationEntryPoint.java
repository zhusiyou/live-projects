package live.betterman.api.config.security;

import cn.hutool.http.HttpStatus;
import com.alibaba.fastjson.JSON;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: zhudawei
 * @date: 2024/1/10
 * @description: 当用户请求一个受保护的资源，又没登录时触发
 */
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        Map<String, Object> result = new HashMap<>();
        result.put("message", "没有登录");
        result.put("code", HttpStatus.HTTP_UNAUTHORIZED);

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(JSON.toJSONString(result));
        response.getWriter().flush();
    }
}
