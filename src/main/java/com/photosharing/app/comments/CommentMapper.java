package com.photosharing.app.comments;

import com.photosharing.app.posts.Post;
import com.photosharing.app.posts.PostReadDTO;
import com.photosharing.app.users.User;
import com.photosharing.app.users.UserMapper;
import com.photosharing.app.users.UserReadDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
abstract public class CommentMapper {
    //@Autowired
    //public UserMapper userMapper;

    //@Mapping(target = "user", expression = "java(userMapper.userToUserReadDTO(comment.getUser()))")
    public abstract CommentReadDTO commentToCommentReadDTO(Comment comment);

    public abstract UserReadDTO userToUserReadDTO(User user);
}
