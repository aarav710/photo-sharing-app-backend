package com.photosharing.app.followers;

import com.photosharing.app.users.User;

import java.util.List;

public interface FollowerService {
    List<FollowerReadDTO> getFollowersByUserId(Integer userId);
    void deleteFollow(Integer followId, String username);

    FollowerReadDTO createNewFollower(Integer followingUserId, String username);

    List<FollowerReadDTO> getFollowingsByUserId(Integer userId);

}
