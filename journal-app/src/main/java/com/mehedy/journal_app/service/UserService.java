package com.mehedy.journal_app.service;

import com.mehedy.journal_app.entity.User;
import com.mehedy.journal_app.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void save(User user){
        userRepository.save(user);
    }

    public boolean saveNewUser(User user){
       try {
           user.setPassword(passwordEncoder.encode(user.getPassword()));
           user.setRoles(List.of("USER"));
           userRepository.save(user);
           return true;
       }catch (Exception e){
           return false;
       }

    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public Optional<User> findById(ObjectId id){
        return userRepository.findById(id);
    }

    public void deleteById(ObjectId id){
        userRepository.deleteById(id);
    }
    public void deleteByUsername(String username){
        userRepository.deleteByUsername(username);
    }

    public User findByUserName(String userName)
    {
        return userRepository.findUserByUsername(userName);
    }


    public void saveAdmin(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(List.of("USER","ADMIN"));
        userRepository.save(user);
    }
}
