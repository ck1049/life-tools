package com.loafer.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.UUID;

/**
 * @author loafer
 * @since 2025-03-08 21:10:22
 **/
public class JwtUtil {

    // 过期时间
    private static Long expireTime = 1000 * 60 * 60L;
    // 密钥
    private static final String SECRET_KEY = UUID.randomUUID().toString().replaceAll("-", "");

    /**
     * 修改默认的过期时间
     * @param expireTime 过期时间
     */
    public static void setExpireTime(Long expireTime) {
        JwtUtil.expireTime = expireTime;
    }

    /**
     * 生成JWT
     * @param subject 主题
     * @return JWT
     */
    public static String generateToken(String subject) {
        return generateToken(subject, expireTime);
    }

    /**
     * 生成JWT
     * @param subject 主题
     * @param expireTime 过期时间
     * @return JWT
     */
    public static String generateToken(String subject, Long expireTime) {
        return getJwtBuilder(subject, expireTime).compact();
    }

    /**
     * 获取JwtBuilder
     * @param subject 主题
     * @param expireTime 过期时间
     * @return JwtBuilder
     */
    private static JwtBuilder getJwtBuilder(String subject, Long expireTime) {
        Date now = new Date();
        return Jwts.builder()
                .setId(UUID.randomUUID().toString().replaceAll("-", ""))
                .setSubject(subject)
                .setIssuer("loafer")
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + expireTime))
                .signWith(SignatureAlgorithm.RS256, SECRET_KEY);
    }

    /**
     * 解析JWT
     * @param token JWT
     * @return Claims
     */
    public static Claims parseToken(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }
}
