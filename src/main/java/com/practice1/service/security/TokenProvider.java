package com.practice1.service.security;

import com.practice1.model.User;
import com.practice1.repository.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * Created by Alireza on 2/7/2018.
 */

@Service
public class TokenProvider {

    @Autowired
    private UserRepository userRepository;

    @Value("${app.jwt_secret_key}")
    private String jwtSecretKey;

    @Value("${app.jwt_authority_key}")
    private String jwtAuthKey;

    @Value("${app.token_validity_in_seconds}")
    private long tokenValidityInSeconds;

    //Create token from an authentication
    public String createToken(Authentication authentication){

        String authorities = authentication.getAuthorities().stream()
                .map(authority -> authority.getAuthority())
                .collect(Collectors.joining(","));

        Date validity = new Date((new Date()).getTime() + this.tokenValidityInSeconds);

        User user = userRepository.findByUsername(authentication.getName());

        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim("userId", user.getUsername())
                .claim(jwtAuthKey, authorities)
                .signWith(SignatureAlgorithm.HS512, jwtSecretKey)
                .setExpiration(validity)
                .compact();
    }

    //create authentication from a token
    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecretKey)
                .parseClaimsJws(token)
                .getBody();

        Collection<? extends GrantedAuthority> authorities =
                Arrays.asList(claims.get(jwtAuthKey).toString().split(",")).stream()
                        .map(authority -> new SimpleGrantedAuthority(authority))
                        .collect(Collectors.toList());

        org.springframework.security.core.userdetails.User principal = new org.springframework.security.core.userdetails.User(claims.getSubject(), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            //logger.warn("Invalid JWT signature: " + e.toString(), "",  getStrackTraceStr(e));
            return false;
        }
        //Without this exception handling, an internal server error would have been sent to the client
        //May need to somehow gather more information regarding this failed attempt
        catch (MalformedJwtException e) {
            //logger.warn("Malformed JWT: " + e.toString(), "",  getStrackTraceStr(e));
            return false;
        }
    }

}
