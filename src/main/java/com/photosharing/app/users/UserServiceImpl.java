package com.photosharing.app.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    public UserReadDTO getUserById(Integer userId) {
        return new UserReadDTO();
    }

    public UserReadDTO getUsersByUsername(String username) {
        return new UserReadDTO();
    }
}