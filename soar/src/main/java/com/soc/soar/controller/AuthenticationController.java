package com.soc.soar.controller;

import com.soc.soar.dto.UserDataDTO;
import com.soc.soar.entity.UserData;
import com.soc.soar.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public List<UserData> getAll(){
        return authenticationService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<UserData> getById(@PathVariable Long id){
        return authenticationService.getById(id);
    }

    @PostMapping("/register")
    public UserData addUser(@RequestBody UserDataDTO userDataDTO){
        userDataDTO.setPassword(passwordEncoder.encode(userDataDTO.getPassword()));
        return authenticationService.addUser(userDataDTO);

    }

}
