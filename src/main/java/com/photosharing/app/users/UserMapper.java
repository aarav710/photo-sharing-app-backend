package com.photosharing.app.users;

import org.mapstruct.Mapper;

@Mapper
abstract public class UserMapper {

    public abstract UserReadDTO userToUserReadDTO(User user);
}