package com.red_social.demo.controller;

import com.red_social.demo.dto.AuthLoginRequestDTO;
import com.red_social.demo.dto.AuthLoginResponseDTO;
import com.red_social.demo.dto.AuthRegisterRequestDTO;
import com.red_social.demo.service.IUserService;
import com.red_social.demo.service.UserDetailsServiceImp;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private UserDetailsServiceImp userDetails;
    @Autowired
    private IUserService userService;

    @PreAuthorize("permitAll()")
    @PostMapping("/login")
    public ResponseEntity<AuthLoginResponseDTO> login(@RequestBody @Valid AuthLoginRequestDTO request){
        return new ResponseEntity<>(userDetails.loginUser(request), HttpStatus.OK);
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody AuthRegisterRequestDTO request){
        return new ResponseEntity(userService.registerUser(request),HttpStatus.CREATED);
    }
    @PostMapping("/logout")
    public ResponseEntity logout(){
        return ResponseEntity.status(HttpStatus.OK)
                .body("Successfully logout!");
    }
}
