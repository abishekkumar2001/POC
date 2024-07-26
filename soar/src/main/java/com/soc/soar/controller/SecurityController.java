package com.soc.soar.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

    @GetMapping("/")
    public String allUsers(){
        return "WELCOME ... WE ENCOURAGE TO GIVE A TRY USING OUR APP";
    }

    @GetMapping("/user")
    public String AppUsers(){
        return "WELCOME APP USER";
    }

    @GetMapping("/admin")
    public String AppAdmins(){
        return "WELCOME ADMIN";
    }
}
