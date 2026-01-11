package com.red_social.demo.service;

import com.red_social.demo.model.Role;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public interface IRoleService {
    boolean existsByName(String name);
    Role create(Role role);
    List<Role> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);
    Optional<Role> findById(Long id);
}
