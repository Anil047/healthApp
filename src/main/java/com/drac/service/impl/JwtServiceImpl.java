package com.drac.service.impl;

import com.drac.model.JwtToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by anil on 3/17/17.
 */
@Service
public class JwtServiceImpl {
    @Value("${jwt.expire.hours}")
    private Long expireHours;

    @Value("${jwt.token.secret}")
    private String secretKey;
    private String encodedKey;

    @PostConstruct
    protected void init() {
        this.encodedKey = getEncodedKey(this.secretKey);
    }

    protected String getEncodedKey(String secretKey) {
        if (StringUtils.isEmpty(secretKey)) {
            throw new IllegalArgumentException("JWT secret key cannot be null or empty.");
        }
        return Base64
                .getEncoder()
                .encodeToString(this.secretKey.getBytes());
    }

    protected Date getExpirationTime() {
        Date now = new Date();
        Long expireTimeInMilis = TimeUnit.HOURS.toMillis(expireHours);
        return new Date(expireTimeInMilis + now.getTime());
    }

    protected JwtToken getJwtToken(String ecodedKey, String jwtToken) {
        Claims claims = Jwts.parser()
                .setSigningKey(ecodedKey)
                .parseClaimsJws(jwtToken)
                .getBody();
        String userName = claims.getSubject();
        JwtToken token = new JwtToken();
        token.setUserName(userName);
        return token;
    }

    public JwtToken getJwtToken(String token) {
        return getJwtToken(this.encodedKey, token);
    }

    protected String getEncodedToken(String encodedSecret, JwtToken jwtToken) {
        Date now = new Date();
        return Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setSubject(jwtToken.getUserName())
                .setIssuedAt(now)
                .setExpiration(getExpirationTime())
                .signWith(SignatureAlgorithm.HS512, encodedSecret)
                .compact();
    }

    public String getEncodedToken(JwtToken jwtToken) {
        return getEncodedToken(this.encodedKey, jwtToken);
    }

}
