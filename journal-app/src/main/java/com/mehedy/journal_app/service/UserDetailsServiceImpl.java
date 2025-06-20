package com.mehedy.journal_app.service;

import com.mehedy.journal_app.entity.User;
import com.mehedy.journal_app.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository=userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user= userRepository.findUserByUsername(username);
        if(user!=null){

            return org.springframework.security.core.userdetails.User.builder().
             username(user.getUsername())
                     .password(user.getPassword())
                     .roles(user.getRoles().toArray(new String[0]))
                     .build();
        }
        throw new UsernameNotFoundException("User not found with username: " + username);

    }
}
