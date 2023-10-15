package com.project.bookingHotel.controllers;

import com.project.bookingHotel.dtos.UserCreateDto;
import com.project.bookingHotel.model.User;
import com.project.bookingHotel.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity createUser(@RequestBody @Valid UserCreateDto user){
        System.out.println("Passei no controller" + user);
        return userService.createUser(user);
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<User> findUserById(@PathVariable(value = "id") Long id){
        return userService.findUserById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<User> updateUserById(@PathVariable(value = "id") Long id, @RequestBody User user){
        return userService.updateUserById(id, user);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> deleteUserById(@PathVariable(value = "id") Long id){
        return userService.deleteUserById(id);
    }
}
