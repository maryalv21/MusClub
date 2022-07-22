package com.ironhack.project.edgeservice.service.impl;

import com.ironhack.project.edgeservice.models.User;
import com.ironhack.project.edgeservice.repository.UserRepository;
import com.ironhack.project.edgeservice.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (!optionalUser.isPresent()){
            throw new UsernameNotFoundException("User not found");
        }
        CustomUserDetails customUserDetails = new CustomUserDetails(optionalUser.get());
        return customUserDetails;
    }
}
