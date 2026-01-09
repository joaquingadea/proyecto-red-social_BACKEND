package com.red_social.demo.service;

import com.red_social.demo.model.Permission;
import org.jspecify.annotations.Nullable;

import java.util.Optional;

public interface IPermissionService {
    Optional<Permission> findPermissionByName(String name);
    Permission create(Permission permission);
}
