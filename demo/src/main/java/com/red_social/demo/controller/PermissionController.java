package com.red_social.demo.controller;

import com.red_social.demo.model.Permission;
import com.red_social.demo.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    @PostMapping //("/create")
    public ResponseEntity createPermission(@RequestBody Permission permission){
        Optional<Permission> permissionExist = permissionService.findPermissionByName(permission.getName());

        if (permissionExist.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Ya existe el permiso "+ permission.getName());
        }

         // Si el permiso no existe lo crea
        return ResponseEntity.status(HttpStatus.CREATED)
                    .body(permissionService.create(permission));

    }
}
