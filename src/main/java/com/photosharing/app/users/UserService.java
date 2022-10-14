package com.photosharing.app.users;

import java.util.List;

public interface UserService {
    UserReadDTO getUserById(Integer userId);
    List<UserReadDTO> getUsersByUsername(String username, Integer page);
}