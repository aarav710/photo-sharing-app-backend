package com.photosharing.app.followers;

import com.photosharing.app.users.User;

import java.util.List;

public interface FollowerService {
    List<FollowerReadDTO> getFollowersByUserId(Integer userId);
    void deleteFollow(Integer followId, User user);

    FollowerReadDTO createNewFollower(Integer followingUserId, User user);

}
