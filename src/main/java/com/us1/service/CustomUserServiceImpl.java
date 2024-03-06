package com.us1.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.us1.mapper.UserMapper;

@Service
public class CustomUserServiceImpl implements UserDetailsService {
	
    @Autowired
    private UserMapper userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

       com.us1.model.User user=userRepository.findByUsername(username);

       if(user==null){
           throw new UsernameNotFoundException("User not found with the email");
       }

       return new CustomUserDetails(user);
    }
}
