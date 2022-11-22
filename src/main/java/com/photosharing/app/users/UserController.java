package com.photosharing.app.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "/users/{userId}")
    public ResponseEntity<UserReadDTO> getUserById(@PathVariable Integer userId) {
        UserReadDTO user = userService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // page as a natural number, starting from 0.
    @GetMapping(path = "/users")
    public ResponseEntity<List<UserReadDTO>> getUsersByUsername(@RequestParam String username, @RequestParam Integer page) {
        List<UserReadDTO> users = userService.getUsersByUsername(username, page);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // id as a parameter is not required as the information will be obtained through the principal object
    @PutMapping(path = "/users")
    public ResponseEntity<UserReadDTO> updateUser(@Valid @RequestBody UserCreateDTO updateUserInformation, Principal principal, BindingResult bindingResult) {
        UserDetails user = (UserDetails) principal;
        UserReadDTO userResponse = userService.updateUser(user.getUsername(), updateUserInformation);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }
}