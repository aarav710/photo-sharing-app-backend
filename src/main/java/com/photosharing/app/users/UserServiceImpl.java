package com.photosharing.app.users;

import com.photosharing.app.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    final public static Integer USERS_RESPONSE_LIMIT = 30;
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserMapper userMapper;

    public UserReadDTO getUserById(Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new NotFoundException("User with id " + userId + " is not found."));
        return userMapper.userToUserReadDTO(user);
    }

    public List<UserReadDTO> getUsersByUsername(String username, Integer page) {
        Pageable pageable = PageRequest.of(page, UserServiceImpl.USERS_RESPONSE_LIMIT);
        List<User> users = userRepo.findByUsernameContaining(username, pageable);
        return users.stream()
                .map(userMapper::userToUserReadDTO)
                .collect(Collectors.toList());
    }
}