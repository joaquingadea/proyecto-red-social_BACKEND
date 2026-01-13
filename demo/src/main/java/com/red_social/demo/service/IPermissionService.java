package com.red_social.demo.service;

import com.red_social.demo.model.Permission;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public interface IPermissionService {
    Optional<Permission> findPermissionByName(String name);
    Permission create(Permission permission);
    List<Permission> getPermissions();
    Optional<Permission> findById(Long id);
    void deleteById(Long id);
    void createPermissions(List<Permission> permissions);
}
