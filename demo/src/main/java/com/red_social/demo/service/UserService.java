package com.red_social.demo.service;

import com.red_social.demo.model.UserSec;
import com.red_social.demo.repository.IUserSecRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService{

    @Autowired
    private IUserSecRepository userRepo;

    @Override
    public String encriptPassword(String password) {
        return new BCryptPasswordEncoder().encode (password);
    }

    @Override
    public List<UserSec> findAll() {
        return userRepo.findAll();
    }

    @Override
    public UserSec create(UserSec userSec) {
        return userRepo.save(userSec);
    }
}
