package com.red_social.demo.service;

import com.red_social.demo.model.Role;
import com.red_social.demo.repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService implements IRoleService{

    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public boolean existsByName(String name) {
        return roleRepository.existsByName(name);
    }

    @Override
    public Role create(Role role) {
        return roleRepository.save(role);
    }
}
