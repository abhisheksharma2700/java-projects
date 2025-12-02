package com.busbookingsystem.service;

import com.busbookingsystem.exception.NotFoundException;
import com.busbookingsystem.repository.UsersDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UsersDetailsRepository usersDetailsRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usersDetailsRepository.findByUsername(username).orElseThrow(()->new NotFoundException("username not found"));
    }
}
