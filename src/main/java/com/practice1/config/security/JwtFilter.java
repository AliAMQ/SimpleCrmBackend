package com.practice1.config.security;

import com.practice1.config.SimpleCorsFilter;
import com.practice1.service.security.TokenProvider;
import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Alireza on 2/7/2018.
 * Filters incoming requests and installs a Spring Security principal if a header corresponding to a valid user is found.
 */

public class JwtFilter extends GenericFilterBean {

    private final Logger log = LoggerFactory.getLogger(JwtFilter.class);

    private TokenProvider tokenProvider;

    public JwtFilter(TokenProvider tokenProvider){
        this.tokenProvider = tokenProvider;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        try {
            HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
            String jwt = resolveToken(httpServletRequest);
            if (StringUtils.hasText(jwt)){
                if (this.tokenProvider.validateToken(jwt)){
                    Authentication authentication = this.tokenProvider.getAuthentication(jwt);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (ExpiredJwtException eje){
            log.info("Security exception for user {} - {}", eje.getClaims().getSubject(), eje.getMessage());
            SimpleCorsFilter.addHeaders((HttpServletResponse) servletResponse).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    private String resolveToken(HttpServletRequest request){
        String alirezaToken = request.getHeader(JwtConfigurer.AUTHORIZATION_HEADER);
        if(StringUtils.hasText(alirezaToken) && alirezaToken.startsWith("Alireza ")){
            String jwt = alirezaToken.substring(7, alirezaToken.length());
            return jwt;
        }
        return null;
    }

}
