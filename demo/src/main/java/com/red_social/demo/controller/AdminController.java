package com.red_social.demo.controller;

import com.red_social.demo.model.Role;
import com.red_social.demo.model.UserSec;
import com.red_social.demo.service.IRoleService;
import com.red_social.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IUserService userService;

    @GetMapping
    public ResponseEntity getUsers(){
        List<UserSec> users = userService.findAll();
        if (users.isEmpty()){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("No existen usuarios todavia");
        }

        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @PatchMapping("/set-admin/{id}")
    public ResponseEntity setAdmin(@PathVariable Long id){
        Optional<Role> roleAdmin = roleService.findByName("ADMIN");
        Optional<UserSec> userMod = userService.findById(id);
        if(userMod.isPresent()){
            return roleAdmin.map(role ->
            ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(userService.addRole(userMod.get(),role)))
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.CONFLICT).build());
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("El usuario "+ userMod.get().getId() +" no existe");
    }

    @PatchMapping("/remove-admin/{id}")
    public ResponseEntity removeAdmin(@PathVariable Long id){
        Optional<Role> roleAdmin = roleService.findByName("ADMIN");
        Optional<UserSec> userMod = userService.findById(id);
        if(userMod.isPresent()){
            return roleAdmin.map(role ->
                            ResponseEntity.status(HttpStatus.ACCEPTED)
                                    .body(userService.removeRole(userMod.get(),role)))
                                        .orElseGet(() ->
                                                ResponseEntity.status(HttpStatus.CONFLICT).build());
        }
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body("El usuario "+ userMod.get().getId() +" no existe");
    }
}
