package com.red_social.demo.service;

import com.red_social.demo.dto.AuthLoginRequestDTO;
import com.red_social.demo.dto.AuthLoginResponseDTO;
import com.red_social.demo.model.UserSec;
import com.red_social.demo.repository.IUserSecRepository;
import com.red_social.demo.utils.JwtUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private IUserSecRepository userRepo;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserSec userSec = userRepo.findUserSecEntityByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Invalid username or password"));

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        // Conseguir los permisos -> almacenarlos en authorities (SimpleGrantedAuthority)

        userSec.getRoleList().stream()
                .flatMap(role -> role.getPermissionList().stream())
                .forEach(permission -> authorities.add(new SimpleGrantedAuthority(permission.getName())));

        // Conseguir los roles -> almacenarlos en authorities (SimpleGrantedAuthority)

        userSec.getRoleList().stream()
                .forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_".concat(role.getName()))));

        // Devolver el usuario como un UserDetails

        return new User(
                userSec.getUsername(),
                userSec.getPassword(),
                userSec.isEnabled(),
                userSec.isAccountNotExpired(),
                userSec.isCredentialNotExpired(),
                userSec.isAccountNotLocked(),
                authorities
        );

    }

    public AuthLoginResponseDTO loginUser(@Valid AuthLoginRequestDTO request) {
        String username = request.username();
        String password = request.password();
        Authentication authentication = this.authenticate(username,password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String accessToken = jwtUtils.createToken(authentication);
        AuthLoginResponseDTO response = new AuthLoginResponseDTO(username,"Login successfull", accessToken, true);
        return response;
    }

    public Authentication authenticate(String username, String password) {
        UserDetails user = this.loadUserByUsername(username);
        if (user == null){
            throw new IllegalArgumentException("Invalid username or password");
        }
        if (!passwordEncoder.matches(password,user.getPassword())){
            throw new IllegalArgumentException("Invalid username or password");
        }
        return new UsernamePasswordAuthenticationToken(username,user.getPassword(),user.getAuthorities());
    }
}
