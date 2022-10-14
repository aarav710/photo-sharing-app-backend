package com.photosharing.app.posts;

import com.photosharing.app.users.User;
import com.photosharing.app.users.UserReadDTO;

import java.util.List;

public interface PostService {
    List<PostReadDTO> getPostsByUserId(Integer userId, Integer page);

    PostReadDetailDTO createNewPost(User user, PostCreateDTO post);

    PostReadDetailDTO updatePost(User user, PostUpdateDTO updatePostInformation, Integer postId);

    void deletePost(User user, Integer postId);

    List<PostReadDetailDTO> findFeed(Integer userId, Integer page);

    PostReadDetailDTO getPostDetailViewById(Integer postId);
}