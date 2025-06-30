package com.mehedy.journal_app.controller;

import com.mehedy.journal_app.api.response.WeatherResponse;
import com.mehedy.journal_app.entity.User;
import com.mehedy.journal_app.service.UserService;
import com.mehedy.journal_app.service.WeatherService;
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
    private final WeatherService weatherService;

    public UserController(UserService userService, WeatherService weatherService) {
        this.userService = userService;
        this.weatherService = weatherService;
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


    @GetMapping()
    ResponseEntity<?> greeting(){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String greeting="";
        WeatherResponse weatherResponse=weatherService.getWeather("Dhaka");
        if(weatherResponse!=null) {
            greeting= "Weather feels like "+weatherService.getWeather("Dhaka").getCurrent().getTemperature()+"!";
        }
        return new ResponseEntity<>("Hi"+authentication.getName()+greeting,HttpStatus.OK);
    }



}
