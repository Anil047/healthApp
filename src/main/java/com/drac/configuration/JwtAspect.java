package com.drac.configuration;

import com.drac.model.JwtToken;
import com.drac.service.impl.JwtService;
import io.jsonwebtoken.JwtException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by anil on 3/17/17.
 */
@Aspect
@Component
public class JwtAspect {

    @Value("${jwt.auth.header}")
    String authHeader;
    private JwtService jwtService;
    private HttpServletRequest httpServletRequest;

    public JwtService getJwtService() {
        return jwtService;
    }

    @Autowired
    public void setJwtService(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }

    @Autowired
    public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    @Before("execution(* com.drac.controller.PatientController.*(..))")
    public void validateToken(JoinPoint joinPoint) {
        final String authHeaderVal = httpServletRequest.getHeader(authHeader);
        System.out.println("validateToken is running!");
        System.out.println("JWT ASPECT  : " + joinPoint.getSignature().getName());
        System.out.println("******");

        try {
            JwtToken jwtToken = jwtService.getJwtToken(authHeaderVal);
            httpServletRequest.setAttribute("jwtToken", jwtToken);
        } catch (JwtException e) {
            throw new RuntimeException("Access Denied...");
        }
    }
}
