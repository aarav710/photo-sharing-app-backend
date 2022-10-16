package com.photosharing.app.auth;

import com.photosharing.app.exceptions.ResourceAlreadyExists;
import com.photosharing.app.users.User;
import com.photosharing.app.users.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepo userRepo;
    public void register(Register register) {
        boolean doesUsernameExist = userRepo.existsByUsername(register.getUsername());
        if (doesUsernameExist) {
            throw new ResourceAlreadyExists("This username is already taken. Please try registering with a new username.");
        }
        boolean doesEmailExist = userRepo.existsByEmail(register.getEmail());
        if (doesEmailExist) {
            throw new ResourceAlreadyExists("An account with this email already exists. Please try registering with a new email.");
        }
        User user = new User(register.getEmail(), register.getUsername(), register.getPassword(), new ArrayList<>());
        userRepo.save(user);
    }
}
