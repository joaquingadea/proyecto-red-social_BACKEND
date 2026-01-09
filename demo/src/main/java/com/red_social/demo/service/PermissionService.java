package com.red_social.demo.service;

import com.red_social.demo.model.Permission;
import com.red_social.demo.repository.IPermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PermissionService implements IPermissionService {

    @Autowired
    private IPermissionRepository permissionRepository;

    @Override
    public Optional<Permission> findPermissionByName(String name) {
        return permissionRepository.findPermissionByName(name);
    }

    @Override
    public Permission create(Permission permission) {
        return permissionRepository.save(permission);
    }
}
