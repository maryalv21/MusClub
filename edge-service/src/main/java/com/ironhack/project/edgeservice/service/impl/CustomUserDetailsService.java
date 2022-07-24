package com.ironhack.project.edgeservice.service.impl;

import com.ironhack.project.edgeservice.client.GameProxyClient;
import com.ironhack.project.edgeservice.client.MemberProxyClient;
import com.ironhack.project.edgeservice.client.PlayerProxyClient;
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

    @Autowired
    private GameProxyClient gameProxyClient;

    @Autowired
    private MemberProxyClient memberProxyClient;

    @Autowired
    private PlayerProxyClient playerProxyClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> optionalUser = userRepository.findByUsername(username);

        if(!optionalUser.isPresent()) {
            throw new UsernameNotFoundException("User not found");
        }

        return optionalUser.get();
    }
}
