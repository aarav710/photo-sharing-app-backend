package com.photosharing.app.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "/users/{id}")
    public ResponseEntity<Integer> getUserById(@RequestParam Integer userId) {
      return new ResponseEntity<>(userId, HttpStatus.OK);
    }

    // id as a parameter is not required as the information will be obtained through the principal object
    @PutMapping(path = "/users/{id}")
    public ResponseEntity<Integer> updateUser(@RequestBody UserCreateDTO user, @RequestParam Integer userId) {
        return new ResponseEntity<>(userId, HttpStatus.OK);
    }

    @PostMapping(path = "/users")
    public ResponseEntity<Integer> createUser(@RequestBody UserCreateDTO user, @RequestParam Integer userId) {
        return new ResponseEntity<>(userId, HttpStatus.OK);
    }
}