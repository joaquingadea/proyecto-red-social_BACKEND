package com.red_social.demo.controller;

import com.red_social.demo.model.Permission;
import com.red_social.demo.model.UserSec;
import com.red_social.demo.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id){

        Optional<Permission> permission = permissionService.findById(id);

        return permission.map(p -> ResponseEntity.status(HttpStatus.OK).body(p))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.CONFLICT).build());
    }

    @GetMapping
    public ResponseEntity getPermissions(){
        List<Permission> permissionsList = permissionService.getPermissions();

        if(permissionsList.isEmpty()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("No existen permisos todavia");
        }

        return ResponseEntity.status(HttpStatus.OK).body(permissionsList);
    }

    @PostMapping("/create")
    public ResponseEntity createPermission(@RequestBody Permission permission){
        Optional<Permission> permissionExist = permissionService.findPermissionByName(permission.getName());

        if (permissionExist.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Ya existe el permiso "+ permission.getName());
        }

         // Si el permiso no existe lo crea
        return ResponseEntity.status(HttpStatus.OK)
                    .body(permissionService.create(permission));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Long id){
        permissionService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Successfully deleted!");
    }
    @PostMapping
    public void createPermissions(@RequestBody List<Permission> permissions){
        permissionService.createPermissions(permissions);
    }
}
