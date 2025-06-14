package com.mehedy.journal_app.controller;

import com.mehedy.journal_app.entity.User;
import com.mehedy.journal_app.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all-users")
    public List<User> getAllUsers() {
        return userService.findAll();
    }


    @PutMapping()
    public ResponseEntity<?> updateUser(@RequestBody User user) {
       Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
       String username=authentication.getName();

        User existingUser=userService.findByUserName(username);
        if(existingUser!=null) {
            existingUser.setUsername(user.getUsername());
            existingUser.setPassword(user.getPassword());
            userService.saveNewUser(existingUser);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();


    }

    @DeleteMapping
    ResponseEntity<?> deleteUser() throws Exception {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        User existingUser=userService.findByUserName(username);
        if(existingUser!=null) {
            userService.deleteByUsername(username);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        throw new UsernameNotFoundException("Username not found");
    }



}
