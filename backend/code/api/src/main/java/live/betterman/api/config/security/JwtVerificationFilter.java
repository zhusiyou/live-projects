package live.betterman.api.config.security;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Claims;
import live.betterman.common.util.JwtUtils;
import live.betterman.system.model.SysUser;
import live.betterman.system.service.SysUserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * @author: zhudawei
 * @date: 2024/1/16
 * @description:
 */
public class JwtVerificationFilter extends OncePerRequestFilter {
    private final static String TOKEN_HEADER = "Authorization";
    private final static String BEARER = "Bearer";
    private final UserDetailsService userDetailsService;

    public JwtVerificationFilter(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = obtainToken(request);
        if (!StrUtil.isEmpty(token)) {
            String username = JwtUtils.parseToken(token).getSubject();
            if (!StrUtil.isEmpty(username)
                    && SecurityContextHolder.getContext().getAuthentication() == null
                    && !JwtUtils.expired(token)) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                // 将请求中的详细信息（即：IP、SessionId 等）封装到 UsernamePasswordAuthenticationToken 对象中方便后续校验
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
//                    // token将要过期签发新token, 防止突然退出登录
//                    if ((claims.getExpiration().getTime() - new Date().getTime()) / 1000 / 60 < JwtUtil.TOKEN_WILL_EXPIRE) {
//                        String access_token_new = JwtUtil.buildToken(username, userDetails.getName(), userDetails.getUserId());
//                        response.addHeader(JwtUtil.TOKEN_HEADER_NAME, access_token_new);
//                    }
            }

        }
        filterChain.doFilter(request, response);
    }

    private String obtainToken(HttpServletRequest request) {
        String tokenHeader = request.getHeader(TOKEN_HEADER);
        if (StrUtil.isEmpty(tokenHeader)) {
            return tokenHeader;
        }

        if (!tokenHeader.startsWith(BEARER)) {
            return null;
        }
        return tokenHeader.substring(BEARER.length()).trim();
    }
}
