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

import java.util.*;

@RestController
@PreAuthorize("permitAll()")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;

    @GetMapping
    public ResponseEntity getUsers(){
        List<UserSec> users = userService.findAll();
        if (users.isEmpty()){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("No existen usuarios todavia");
        }

        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    //Creacion de user PRUEBA -> luego hay que transformarlo en register

    @PostMapping
    public ResponseEntity createUser(@RequestBody UserSec userSec){

        if (userSec.getPassword().length() < 8){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("La contraseña tiene que tener minimo 8 caracteres");
        }

        if (userSec.getRoleList().isEmpty()){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("La lista de roles esta vacia");
        }
        userSec.setPassword(userService.encriptPassword(userSec.getPassword()));

        Set<Role> roles = new HashSet<>();

        //Se guardan los roles que existen nada mas
        for (Role role : userSec.getRoleList()){
            if(roleService.existsById(role.getId())){
                roles.add(role);
            }
        }
        if (roles.isEmpty()){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Los roles pasados no existen");
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.create(userSec));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Long id){
        boolean userExists = userService.existsById(id);
        if(userExists){
            userService.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("User successfully deleted!");
        }
        else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User does not exist");
        }
    }
}
