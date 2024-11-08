package com.example.instructor_log.services;

import com.example.instructor_log.config.MyUserDetails;
import com.example.instructor_log.entity.MyUsers;
import com.example.instructor_log.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class MyUserDetailsServece implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<MyUsers> users = userRepository.findByLogin(username);//проверки логина введенного с бд, есть ли он там

        return users.map(MyUserDetails::new)
                .orElseThrow(()-> new UsernameNotFoundException(username + "нету такого пользователя"));
    }
}
