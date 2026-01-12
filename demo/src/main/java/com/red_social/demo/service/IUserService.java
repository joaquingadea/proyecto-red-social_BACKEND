package com.red_social.demo.service;

import com.red_social.demo.dto.AuthRegisterRequestDTO;
import com.red_social.demo.dto.AuthRegisterResponseDTO;
import com.red_social.demo.model.UserSec;
import jakarta.validation.Valid;
import org.jspecify.annotations.Nullable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUserService {
    String encriptPassword(String password);

    List<UserSec> findAll();

    UserSec create(UserSec userSec);

    AuthRegisterResponseDTO registerUser(AuthRegisterRequestDTO request);
    void deleteById(Long id);
    boolean existsById(Long id);
}
