package com.photosharing.app.auth;

import com.photosharing.app.exceptions.ResourceAlreadyExists;
import com.photosharing.app.users.User;
import com.photosharing.app.users.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Transactional
    public void register(Register register) {
        boolean doesUsernameExist = userRepo.existsByUsername(register.getUsername());
        if (doesUsernameExist) {
            throw new ResourceAlreadyExists("This username is already taken. Please try registering with a new username.");
        }
        boolean doesEmailExist = userRepo.existsByEmail(register.getEmail());
        if (doesEmailExist) {
            throw new ResourceAlreadyExists("An account with this email already exists. Please try registering with a new email.");
        }
        Role role = new Role();
        role.setAuthority("ROLE_USER");
        List<Role> authorities = new ArrayList<>();
        authorities.add(role);
        User user = new User(register.getEmail(), register.getUsername(), register.getPassword(), authorities);
        user = userRepo.save(user);
        /*
        userRepo.flush();
        Role role = new Role();
        role.setUser(user);
        role.setAuthority("ROLE_USER");
        List<Role> authorities = user.getAuthorities();
        authorities.add(role);
        user.setAuthorities(authorities);
        userRepo.save(user);
        roleRepo.save(role);

         */
    }
}
