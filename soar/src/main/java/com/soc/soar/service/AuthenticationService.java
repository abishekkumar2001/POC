package com.soc.soar.service;

import com.soc.soar.dto.UserDataDTO;
import com.soc.soar.entity.UserData;
import com.soc.soar.repository.AuthenticationRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private AuthenticationRepo authenticationRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserData> user = authenticationRepo.findByUserName(username);
        if(user.isPresent()){
            var userObj = user.get();
            return User.builder()
                    .username(userObj.getUserName())
                    .password(userObj.getPassword())
                    .roles(userObj.getRoles())
                    .build();
        }
        else {
            throw new UsernameNotFoundException("User with Entered username not found");
        }
    }

    public List<UserData> getAll() {
        return authenticationRepo.findAll();
    }

    public Optional<UserData> getById(Long id) {
        return authenticationRepo.findById(id);
    }


    public UserData addUser(UserDataDTO userDataDTO) {
        UserData userData = modelMapper.map(userDataDTO,UserData.class);
        return authenticationRepo.save(userData);
    }
}
