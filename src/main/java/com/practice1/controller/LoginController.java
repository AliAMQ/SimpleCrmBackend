package com.practice1.controller;


import com.practice1.dto.LoginDTO;
import com.codahale.metrics.annotation.Timed;
import com.practice1.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * Created by Alireza on 2/8/2018.
 */

@RestController
@RequestMapping(value = "/authenticate")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    @Timed
    public ResponseEntity<?> authorizeUser(@Valid @RequestBody LoginDTO loginDTO, HttpServletResponse response, HttpServletRequest request) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());

        return loginService.authenticateUser(loginDTO, response, request);
    }



}
