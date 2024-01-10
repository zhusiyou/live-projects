package live.betterman.api.config.security;

import cn.hutool.http.HttpStatus;
import com.alibaba.fastjson.JSON;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: zhudawei
 * @date: 2024/1/10
 * @description: 用户访问无权限资源 时的异常（用户登录后，请求一个受保护的资源，又没权限时触发）
 */
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        Map<String, Object> result = new HashMap<>();
        result.put("message", "没有权限");
        result.put("code", HttpStatus.HTTP_FORBIDDEN);

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(JSON.toJSONString(result));
        response.getWriter().flush();
    }
}
