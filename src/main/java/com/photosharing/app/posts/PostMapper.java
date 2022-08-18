package com.photosharing.app.posts;

import com.photosharing.app.users.UserMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
abstract public class PostMapper {
    @Autowired
    UserMapper userMapper;

    public abstract PostReadDTO postToPostReadDTO(Post post);

    @Mapping(target = "user", expression = "java(userMapper.userToUserReadDTO(post.getUser()))")
    @Mapping(target = "likesCount", source = "likesCount")
    @Mapping(target = "commentsCount", source = "commentsCount")
    @Mapping(target = "id", source = "post.id")
    @Mapping(target = "caption", source = "post.caption")
    @Mapping(target = "photoUrl", source = "post.photoUrl")
    @Mapping(target = "createdAt", source = "post.createdAt")
    public abstract PostReadDetailDTO postToPostReadDetailDTO(Post post, Integer likesCount, Integer commentsCount);
}
