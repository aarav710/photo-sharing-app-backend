package com.photosharing.app.posts;

import com.photosharing.app.users.UserMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper
abstract public class PostMapper {
    @Autowired
    UserMapper userMapper;


}
