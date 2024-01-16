package live.betterman.api.config.security;

import cn.hutool.http.HttpStatus;
import com.alibaba.fastjson.JSON;
import live.betterman.common.util.JwtUtils;
import live.betterman.system.model.SysUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: zhudawei
 * @date: 2024/1/16
 * @description: Jwt认证成成handler
 */
public class JwtAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        SysUser principal = (SysUser) authentication.getPrincipal();
        SysUser sysUser = new SysUser();
        sysUser.setUserId(principal.getUserId());
        sysUser.setUserName(principal.getUsername());
        sysUser.setPermissions(principal.getPermissions());
        String token = JwtUtils.generateToken(sysUser.getUsername(), sysUser);

        Map<String, Object> result = new HashMap<>();
        result.put("message", "登录成功");
        result.put("code", HttpStatus.HTTP_OK);
        result.put("token", token);

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(JSON.toJSONString(result));
        response.getWriter().flush();
    }
}
