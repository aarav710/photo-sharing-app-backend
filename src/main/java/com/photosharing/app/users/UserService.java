package com.photosharing.app.users;

public interface UserService {
    UserReadDTO getUserById(Integer userId);
    UserReadDTO getUsersByUsername(String username);
}