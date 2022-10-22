package com.photosharing.app.followers;

import com.photosharing.app.users.User;
import com.photosharing.app.users.UserMapper;
import com.photosharing.app.users.UserReadDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
abstract public class FollowerMapper {
    //@Autowired
    //public UserMapper userMapper;

    //@Mappings({
    //        @Mapping(target = "following", expression = "java(userMapper.userToUserReadDTO(follower.getFollowing()))"),
    //        @Mapping(target = "follower", expression = "java(userMapper.userToUserReadDTO(follower.getFollower()))")
    //})
    public abstract FollowerReadDTO followerToFollowerReadDTO(Follower follower);

    public abstract UserReadDTO userToUserReadDTO(User user);
}
