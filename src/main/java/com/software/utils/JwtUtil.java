package com.software.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

/**
 * @author Wang Hao
 * @date 2022/10/5 13:17
 * @description JWT工具类
 */
public class JwtUtil {

    /**
     * 缓存有效期（1小时）
     */
    private static final Long JWT_TTL = 60 * 60 * 1000L;

    private static final String JWT_KEY = "123456";

    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 生成jwt
     *
     * @param subject token中要存的数据（json格式）
     * @return
     */
    public static String createJWT(String subject) {
        JwtBuilder jwtBuilder = getJwtBuilder(subject, null, getUUID());
        return jwtBuilder.compact();
    }

    /**
     * 生成jwt
     *
     * @param subject token中要存的数据（json格式）
     * @param ttl     过期时间
     * @return
     */
    public static String createJWT(String subject, long ttl) {
        JwtBuilder jwtBuilder = getJwtBuilder(subject, ttl, getUUID());
        return jwtBuilder.compact();
    }

    /**
     * 生成jwt
     *
     * @param subject token中要存的数据（json格式）
     * @param ttl     过期时间
     * @param id      uuid
     * @return
     */
    public static String createJWT(String subject, long ttl, String id) {
        JwtBuilder jwtBuilder = getJwtBuilder(subject, ttl, id);
        return jwtBuilder.compact();
    }

    /**
     * 获取jwtBuilder
     *
     * @param subject token中要存的数据（json格式）
     * @param ttl     过期时间
     * @param uuid    uuid
     * @return
     */
    private static JwtBuilder getJwtBuilder(String subject, Long ttl, String uuid) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        SecretKey secretKey = generalKry();
        Date time = new Date();

        if (ttl == null) {
            ttl = JWT_TTL;
        }

        long expiredTime = ttl + time.getTime();
        return Jwts.builder()
                .setId(uuid)
                .setSubject(subject)
                .setIssuer("sunxiaochuan")
                .setIssuedAt(time)
                .signWith(signatureAlgorithm, secretKey)
                .setExpiration(new Date(expiredTime));
    }

    /**
     * 生成加密后的密钥key
     *
     * @return
     */
    public static SecretKey generalKry() {
        byte[] encodeKey = Base64.getEncoder().encode(JWT_KEY.getBytes());
        return new SecretKeySpec(encodeKey, 0, encodeKey.length, "AES");
    }

    /**
     * 解析
     *
     * @param jwt jwt
     * @return
     */
    public static Claims parseJWT(String jwt) {
        SecretKey secretKey = generalKry();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }
}
