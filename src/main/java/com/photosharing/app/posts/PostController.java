package com.photosharing.app.posts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping(path = "/posts/{id}")
    public ResponseEntity<Integer> getPostById(@PathVariable Integer postId) {
        return new ResponseEntity<>(postId, HttpStatus.OK);
    }

    @GetMapping(path = "/feed")
    public ResponseEntity<Integer> getFeed() {
        return new ResponseEntity<>(5, HttpStatus.OK);
    }
}