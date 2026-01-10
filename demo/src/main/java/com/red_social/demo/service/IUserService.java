package com.red_social.demo.service;

import com.red_social.demo.model.UserSec;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface IUserService {
    String encriptPassword(String password);

    List<UserSec> findAll();

    UserSec create(UserSec userSec);
}
