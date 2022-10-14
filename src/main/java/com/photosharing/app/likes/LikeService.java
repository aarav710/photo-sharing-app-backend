package com.photosharing.app.likes;

import com.photosharing.app.users.User;

import java.util.List;

public interface LikeService {
    List<LikeReadDTO> findLikesByPostId(Integer postId);
    LikeReadDTO createLike(Integer postId, User user);

    void deleteLike(Integer likeId, User user);

}
