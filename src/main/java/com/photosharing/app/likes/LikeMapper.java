package com.photosharing.app.likes;

import com.photosharing.app.users.UserMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
abstract public class LikeMapper {
    @Autowired
    private UserMapper userMapper;

    @Mapping(target = "user", expression = "java(userMapper.userToUserReadDTO(like.getUser()))")
    public abstract LikeReadDTO likeToLikeReadDTO(Like like);
}
