package com.photosharing.app.posts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostRepo postRepo;

    public PostReadDTO getPostById(Integer postId) {
        return null;
    }

    public List<PostReadDTO> findFeedPostsByUsername(String username) {
        List<PostReadDTO> posts = new ArrayList<PostReadDTO>();
        return posts;
    }
}