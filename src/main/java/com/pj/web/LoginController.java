package com.pj.web;

import com.pj.model.Login;
import com.pj.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class LoginController {

    @Autowired
    private LoginService loginService;


   @PostMapping("/login")
public ResponseEntity<String> login(@RequestBody Login loginRequest) {
    try {
        boolean isAuthenticated = loginService.authenticateUser(loginRequest);
        if (isAuthenticated) {
            return ResponseEntity.ok("{\"result\":\"success\"}");
        } else {
            return new ResponseEntity<>("{\"result\":\"failed\"}", HttpStatus.UNAUTHORIZED);
        }
    } catch (Exception e) {
        return new ResponseEntity<>("{\"result\":\"failed\", \"error\":\"An error occurred during login\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
}