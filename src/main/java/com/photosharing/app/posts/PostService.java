package com.photosharing.app.posts;

import com.photosharing.app.users.UserReadDTO;

public interface PostService {
    PostReadDTO getPostById(Integer postId);
}