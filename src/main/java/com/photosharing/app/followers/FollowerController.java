package com.photosharing.app.followers;

import com.photosharing.app.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class FollowerController {

    @Autowired
    private FollowerService followerService;

    @GetMapping(path = "/users/{userId}/followers")
    public ResponseEntity<List<FollowerReadDTO>> getFollowersByUserId(@PathVariable Integer userId) {
        List<FollowerReadDTO> followers = followerService.getFollowersByUserId(userId);
        return new ResponseEntity<>(followers, HttpStatus.OK);
    }

    @GetMapping(path = "users/{userId}/followings")
    public ResponseEntity<List<FollowerReadDTO>> getFollowingsByUserId(@PathVariable Integer userId) {
        List<FollowerReadDTO> followings = followerService.getFollowingsByUserId(userId);
        return new ResponseEntity<>(followings, HttpStatus.OK);
    }

    @DeleteMapping(path = "/followers/{followId}")
    public ResponseEntity<Void> deleteFollow(@PathVariable Integer followId, Principal principal) {
        UserDetails user = (UserDetails) principal;
        followerService.deleteFollow(followId, user.getUsername());
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @PostMapping(path = "/followers/{followingUserId}")
    public ResponseEntity<FollowerReadDTO> createNewFollower(@PathVariable Integer followingUserId, Principal principal) {
        UserDetails user = (UserDetails) principal;
        FollowerReadDTO follower = followerService.createNewFollower(followingUserId, user.getUsername());
        return new ResponseEntity<>(follower, HttpStatus.CREATED);
    }
}
