package com.red_social.demo.service;

import com.red_social.demo.dto.AuthRegisterRequestDTO;
import com.red_social.demo.dto.AuthRegisterResponseDTO;
import com.red_social.demo.model.Role;
import com.red_social.demo.model.UserSec;
import jakarta.validation.Valid;
import org.jspecify.annotations.Nullable;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    String encriptPassword(String password);

    List<UserSec> findAll();

    UserSec create(UserSec userSec);

    AuthRegisterResponseDTO registerUser(AuthRegisterRequestDTO request);

    void deleteById(Long id);

    boolean existsById(Long id);

    Optional<UserSec> findById(Long id);

    String addRole(UserSec userSec,Role roleAdmin);

    String removeRole(UserSec userSec, Role role);
}
