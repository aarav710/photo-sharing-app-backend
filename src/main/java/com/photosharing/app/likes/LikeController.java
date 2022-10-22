package com.photosharing.app.likes;

import com.photosharing.app.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class LikeController {
    @Autowired
    private LikeService likeService;

    @PostMapping(path = "/posts/{postId}/likes")
    public ResponseEntity<LikeReadDTO> createLike(@PathVariable Integer postId, Principal principal) {
        UserDetails user = (UserDetails) principal;
        LikeReadDTO like = likeService.createLike(postId, user.getUsername());
        return new ResponseEntity<>(like, HttpStatus.CREATED);
    }

    @GetMapping(path = "/posts/{postId}/likes")
    public ResponseEntity<List<LikeReadDTO>> getLikesByPostId(@PathVariable Integer postId) {
        List<LikeReadDTO> likes = likeService.findLikesByPostId(postId);
        return new ResponseEntity<>(likes, HttpStatus.OK);
    }

    @DeleteMapping(path = "/likes/{likeId}")
    public ResponseEntity<Void> deleteLike(@PathVariable Integer likeId, Principal principal) {
        UserDetails user = (UserDetails) principal;
        likeService.deleteLike(likeId, user.getUsername());
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
