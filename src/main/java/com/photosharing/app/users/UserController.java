package com.photosharing.app.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "/users/{id}")
    public ResponseEntity<UserReadDTO> getUserById(@PathVariable Integer userId) {
        UserReadDTO user = userService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(path = "/users")
    public ResponseEntity<List<UserReadDTO>> getUsersByUsername(@RequestParam String username, @RequestParam Integer page) {
        List<UserReadDTO> users = userService.getUsersByUsername(username, page);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // id as a parameter is not required as the information will be obtained through the principal object
    @PutMapping(path = "/users")
    public ResponseEntity<Integer> updateUser(@RequestBody UserCreateDTO user) {
        return new ResponseEntity<>(5, HttpStatus.OK);
    }

    @PostMapping(path = "/users")
    public ResponseEntity<Integer> createUser(@RequestBody UserCreateDTO user) {
        return new ResponseEntity<>(5, HttpStatus.OK);
    }
}