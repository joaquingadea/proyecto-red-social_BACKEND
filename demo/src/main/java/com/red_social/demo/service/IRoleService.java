package com.red_social.demo.service;

import com.red_social.demo.model.Role;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface IRoleService {
    boolean existsByName(String name);
    Role create(Role role);
    List<Role> findAll();
    void deleteById(Long id);
}
