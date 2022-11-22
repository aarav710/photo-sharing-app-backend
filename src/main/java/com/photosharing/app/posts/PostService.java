package com.photosharing.app.posts;

import com.photosharing.app.users.User;
import com.photosharing.app.users.UserReadDTO;

import java.util.List;

public interface PostService {
    List<PostReadDTO> getPostsByUserId(Integer userId, Integer page);

    PostReadDetailDTO createNewPost(String username, PostCreateDTO post);

    PostReadDetailDTO updatePost(String username, PostUpdateDTO updatePostInformation, Integer postId);

    void deletePost(String username, Integer postId);

    List<PostReadDetailDTO> findFeed(String username, Integer page);

    PostReadDetailDTO getPostDetailViewById(Integer postId);
}