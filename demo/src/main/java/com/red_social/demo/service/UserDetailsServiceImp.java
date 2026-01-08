package com.red_social.demo.service;

import com.red_social.demo.model.UserSec;
import com.red_social.demo.repository.IUserSecRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private IUserSecRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserSec userSec = userRepo.findUserSecEntityByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario " + username + " no existe"));
        // Conseguir los permisos -> almacenarlos en authorities (SimpleGrantedAuthority)
        // Conseguir los roles -> almacenarlos en authorities (SimpleGrantedAuthority)

        // Devolver el usuario como un UserDetails

        return null;

    }
}
