package com.photosharing.app.posts;

import com.photosharing.app.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping(path = "/posts/{id}")
    public ResponseEntity<PostReadDTO> getPostById(@PathVariable Integer postId) {
        PostReadDTO post = postService.getPostById(postId);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @GetMapping(path = "/feed")
    public ResponseEntity<Integer> getFeed() {
        return new ResponseEntity<>(5, HttpStatus.OK);
    }

    // returns the image normal view of the posts
    @GetMapping(path = "users/{userId}/posts")
    public ResponseEntity<List<PostReadDTO>> getPostsByUserId(@PathVariable Integer userId, @RequestParam Integer offset, @RequestParam Integer limit) {
        List<PostReadDTO> posts = postService.getPostsByUserId(userId, offset, limit);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @PostMapping(path = "/posts")
    public ResponseEntity<PostReadDetailDTO> createNewPost(Principal principal, @RequestBody PostCreateDTO newPostInformation) {
        User user = (User) principal;
        PostReadDetailDTO post = postService.createNewPost(user, newPostInformation);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    @PutMapping(path = "/posts/{postId}")
    public ResponseEntity<PostReadDetailDTO> updatePost(Principal principal, @RequestBody PostUpdateDTO updatePostInformation, @PathVariable Integer postId) {
        User user = (User) principal;
        PostReadDetailDTO post = postService.updatePost(user, updatePostInformation, postId);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

}