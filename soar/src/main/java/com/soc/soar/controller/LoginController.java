package com.soc.soar.controller;

import com.soc.soar.dto.LoginDTO;
import com.soc.soar.dto.SignUpDTO;
import com.soc.soar.entity.User;
import com.soc.soar.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/signup")
    public User signUp(@RequestBody SignUpDTO signUpDTO){
        return loginService.signUp(signUpDTO);
    }

    @PostMapping("/login")
    public Map<String,Object> loginRequest(@RequestBody LoginDTO loginDTO){
        return loginService.loginRequest(loginDTO);
    }

    @GetMapping("/{id}")
    public Optional<User> getUser(@PathVariable int id){
        return loginService.getUser(id);
    }

    @PutMapping
    public User updateUser(@RequestBody User user){
        return loginService.updateUser(user);
    }

}
