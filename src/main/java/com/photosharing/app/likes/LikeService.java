package com.photosharing.app.likes;

import java.util.List;

public interface LikeService {
    List<LikeReadDTO> findLikesByPostId(Integer postId);
}
