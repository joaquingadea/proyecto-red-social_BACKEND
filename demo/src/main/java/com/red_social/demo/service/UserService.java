package com.red_social.demo.service;

import com.red_social.demo.dto.AuthRegisterRequestDTO;
import com.red_social.demo.dto.AuthRegisterResponseDTO;
import com.red_social.demo.model.Profile;
import com.red_social.demo.model.Role;
import com.red_social.demo.model.UserSec;
import com.red_social.demo.repository.IRoleRepository;
import com.red_social.demo.repository.IUserSecRepository;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService implements IUserService{

    @Autowired
    private IUserSecRepository userRepo;
    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public String encriptPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    @Override
    public List<UserSec> findAll() {
        return userRepo.findAll();
    }

    @Override
    public UserSec create(UserSec userSec) {
        return userRepo.save(userSec);
    }

    @Override
    public AuthRegisterResponseDTO registerUser(AuthRegisterRequestDTO request){

        Optional<UserSec> user = userRepo.findUserSecEntityByUsername(request.username());
        if(user.isPresent()){ //el usuario ya existe
            throw new IllegalArgumentException("Username alredy exists");
        }
        if((request.password().length() < 8) || (request.passwordConfirm().length() < 8)){
            throw new IllegalArgumentException("Password must be at least 8 characters");
        }
        if(!(request.password().equals
                (request.passwordConfirm()))){ //si las contraseñas no son iguales
            throw new IllegalArgumentException("Passwords do not match");
        }


        // Rol por defecto para los usuarios
        Role userRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new IllegalStateException("USER role not found"));

        UserSec newUser = new UserSec();

        newUser.setUsername(request.username());
        newUser.setPassword(encriptPassword(request.password()));
        newUser.setEnabled(true);
        newUser.setAccountNotExpired(true);
        newUser.setAccountNotLocked(true);
        newUser.setCredentialNotExpired(true);
        newUser.getRoleList().add(userRole);

        Profile profile =  new Profile(LocalDate.now());
        newUser.addProfile(profile);


        userRepo.save(newUser);
        return new AuthRegisterResponseDTO("User created successfully!");
    }

    @Override
    public void deleteById(Long id) {
        userRepo.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return userRepo.existsById(id);
    }

    @Override
    public Optional<UserSec> findById(Long id) {
        return userRepo.findById(id);
    }

    @Override
    public String addRole(UserSec userMod,Role roleAdmin) {
        userMod.getRoleList().add(roleAdmin);
        userRepo.save(userMod);
        return "Se le seteo el rol de admin al usuario con el id: " + userMod.getId();
    }

    @Override
    public String removeRole(UserSec userSec, Role role) {
        userSec.getRoleList().remove(role);
        userRepo.save(userSec);
        return "Se elimino el rol ADMIN del usuario con id: "+ userSec.getId();
    }
}
