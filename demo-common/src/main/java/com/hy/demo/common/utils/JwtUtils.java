package com.hy.demo.common.utils;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.MappingJsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * JwtUtils
 *
 * @author yuhaiyang
 * @date 2018/5/29
 */
public class JwtUtils {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    private static final String issuer = "hy";

    private static final String key = "hy-secert";

    public static String createJWT(String sub, long expireTime) {
        return Jwts.builder()
                .setIssuer(issuer)
                .setSubject(sub)
                .setExpiration(Date.from(LocalDateTime.now().plusSeconds(expireTime).atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }

    public static boolean verify(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(key).parseClaimsJws(token);
            claimsJws.getSignature();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String getTokenContent(String token) {
        String tokenContent = null;
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(key).parseClaimsJws(token);
            tokenContent = claimsJws.getBody().getSubject();
        } catch (Exception e) {
            logger.error("jwtUtils.getTokenContent.err", e);
        }
        return tokenContent;
    }

    public static String refreshToken(String token, long expireTime) {
        String sub = getTokenContent(token);
        return  createJWT(sub, expireTime);
    }
}
