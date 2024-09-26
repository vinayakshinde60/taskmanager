package com.pj.service;

import com.pj.model.Appuser;
import com.pj.model.Login;
import com.pj.repo.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private AppUserRepository appUserRepository;

    public boolean authenticateUser(Login loginRequest) {
        Appuser appuser = appUserRepository.findByUsername(loginRequest.getUsername());
        if (appuser != null && appuser.getPassword().equals(loginRequest.getPassword())) {
            return true;
        }
        return false;
    }
}