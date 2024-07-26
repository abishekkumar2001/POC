package com.soc.soar.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soc.soar.dto.LoginDTO;
import com.soc.soar.dto.SignUpDTO;
import com.soc.soar.entity.User;
import com.soc.soar.repository.LoginRepo;
import com.soc.soar.utils.JwtUtils;
import org.modelmapper.ModelMapper;
import com.soc.soar.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private LoginRepo loginRepo;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private ModelMapper modelMapper;

    public User signUp(SignUpDTO signUpDTO) {
        User user = modelMapper.map(signUpDTO, User.class);
        return loginRepo.save(user);
    }

    public Map<String,Object> loginRequest(LoginDTO loginDTO){

        User user = loginRepo.findOneByEmailAndPassword(loginDTO.getEmail(),loginDTO.getPassword());

        if (user == null){
            Map<String, Object> response = new HashMap<>();
            response.put("Status", "Log in failed");
            return response;
        }

        String token = jwtUtils.generateJwt(user);
        Map<String, Object> response = new HashMap<>();
        response.put("Status", "Successfully logged in");
        response.put("Access Token", token);
        return response;

    }

    public Optional<User> getUser(int id) {
        return loginRepo.findById(id);
    }

    public User updateUser(User user) {
        if(!loginRepo.existsById(user.getId())){
            throw new NotFoundException("Record not found");
        }
        return loginRepo.save(user);
    }
}
