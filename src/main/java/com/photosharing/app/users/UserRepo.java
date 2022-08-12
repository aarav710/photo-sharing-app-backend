package com.photosharing.app.users;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends CrudRepository<User, Integer> {
    List<User> findByUsernameContaining(String username, Pageable pageable);
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
}