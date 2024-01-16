package live.betterman.common.util;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.UUID;

/**
 * @author: zhudawei
 * @date: 2024/1/16
 * @description:
 */
public class JwtUtils {
    private final static String SECRET_KEY = "aGFoYSUyMzEyMy5ANjY2"; // haha#123.@666
    public final static String USER_CLAIM = "user";
    private final static int EXPIRATION = 1200 * 3600 * 1000;

    public static String generateToken(String subject, Object user){
        return Jwts.builder()
                .setSubject(subject)
                .claim(USER_CLAIM, JSON.toJSONString(user))
                .setId(UUID.randomUUID().toString())
                .setExpiration(DateUtil.offsetSecond(DateUtil.date(), EXPIRATION))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public static Claims parseToken(String token) {
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
        return claimsJws.getBody();
    }
    public static boolean expired(String token){
        Date expiration = parseToken(token).getExpiration();
        return expiration.before(new Date());
    }

    /**
     * 在token中获取用户对象
     * @param token
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getUser(String token, Class<T> clazz){
        Claims claims = JwtUtils.parseToken(token);
        String s = claims.get(JwtUtils.USER_CLAIM, String.class);
        return JSON.parseObject(s, clazz);
    }
}
