package com.photosharing.app.posts;

import com.photosharing.app.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping(path = "/posts/{postId}")
    public ResponseEntity<PostReadDetailDTO> getPostById(@PathVariable Integer postId) {
        PostReadDetailDTO post = postService.getPostDetailViewById(postId);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }


    @GetMapping(path = "/feed")
    public ResponseEntity<List<PostReadDetailDTO>> getFeed(@RequestParam Integer page, Principal principal) {
        UserDetails user = (UserDetails) principal;
        List<PostReadDetailDTO> posts = postService.findFeed(user.getUsername(), page);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }


    // returns the image normal view of the posts
    @GetMapping(path = "/users/{userId}/posts")
    public ResponseEntity<List<PostReadDTO>> getPostsByUserId(@PathVariable Integer userId, @RequestParam Integer page) {
        List<PostReadDTO> posts = postService.getPostsByUserId(userId, page);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @PostMapping(path = "/posts")
    public ResponseEntity<PostReadDetailDTO> createNewPost(Principal principal, @Valid @RequestBody PostCreateDTO newPostInformation) {
        UserDetails user = (UserDetails) principal;
        PostReadDetailDTO post = postService.createNewPost(user.getUsername(), newPostInformation);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    @PutMapping(path = "/posts/{postId}")
    public ResponseEntity<PostReadDetailDTO> updatePost(Principal principal,@Valid @RequestBody PostUpdateDTO updatePostInformation, @PathVariable Integer postId) {
        UserDetails user = (UserDetails) principal;
        PostReadDetailDTO post = postService.updatePost(user.getUsername(), updatePostInformation, postId);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @DeleteMapping(path = "/posts/{postId}")
    public ResponseEntity<Void> deletePost(Principal principal, @PathVariable Integer postId) {
        UserDetails user = (UserDetails) principal;
        postService.deletePost(user.getUsername(), postId);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}