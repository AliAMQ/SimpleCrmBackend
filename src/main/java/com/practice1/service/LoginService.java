package com.practice1.service;

import com.practice1.config.security.JwtConfigurer;
import com.practice1.config.security.JwtToken;
import com.practice1.dto.LoginDTO;
import com.practice1.model.Authority;
import com.practice1.model.User;
import com.practice1.repository.UserRepository;
import com.practice1.service.security.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;

/**
 * Created by Alireza on 2/7/2018.
 */
@Component("LoginService")
public class LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Environment env;

    public User findUser(LoginDTO loginDTO){
        User user = userRepository.findByUsername(loginDTO.getUsername().toLowerCase());
        return user;
    }

    public ResponseEntity<?> authenticateUser(LoginDTO loginDTO, HttpServletResponse response, HttpServletRequest request){
        try {
            User user = findUser(loginDTO);
            if (user == null){
                throw new RuntimeException("User not found!");
            }
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());

            Authentication authentication = this.authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = tokenProvider.createToken(authentication);
            //userRepository.save(user);
            if (response != null) {
                response.addHeader(JwtConfigurer.AUTHORIZATION_HEADER, "Alireza " + jwt);
            }

            return ResponseEntity.ok(new JwtToken(jwt, user.getFirstName(),(user.getAuthority().getTitle())));
        } catch (AuthenticationException exception) {
            return new ResponseEntity<>(Collections.singletonMap("AuthenticationException", exception.getLocalizedMessage()),
                    HttpStatus.UNAUTHORIZED);
        }
    }

}
