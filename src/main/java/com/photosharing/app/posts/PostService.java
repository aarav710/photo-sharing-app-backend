package com.photosharing.app.posts;

import com.photosharing.app.users.User;
import com.photosharing.app.users.UserReadDTO;

import java.util.List;

public interface PostService {
    PostReadDTO getPostById(Integer postId);
    List<PostReadDTO> getPostsByUserId(Integer userId, Integer offset, Integer limit);

    PostReadDetailDTO createNewPost(User user, PostCreateDTO post);

    PostReadDetailDTO updatePost(User user, PostUpdateDTO updatePostInformation, Integer postId);
}