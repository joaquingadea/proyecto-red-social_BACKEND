package com.red_social.demo.controller;

import com.red_social.demo.model.Permission;
import com.red_social.demo.model.Role;
import com.red_social.demo.service.IPermissionService;
import com.red_social.demo.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;
    @Autowired
    private IPermissionService permissionService;

    @PostMapping
    public ResponseEntity createRole(@RequestBody Role role){

        if(roleService.existsByName(role.getName())){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("El rol "+role.getName()+ " ya existe");
        }

        Set<Permission> permissionsList = new HashSet<>();

        for (Permission per : role.getPermissionList()){
            Optional<Permission> permissionExists = permissionService.findPermissionByName(per.getName());
            if(permissionExists.isPresent()){
                permissionsList.add(per);
            }
        }

        role.setPermissionList(permissionsList);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(roleService.create(role));
    }
}
