package com.photosharing.app.followers;

import com.photosharing.app.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class FollowerController {

    @Autowired
    FollowerService followerService;

    @GetMapping(path = "users/{userId}/followers")
    public ResponseEntity<List<FollowerReadDTO>> getFollowersByUserId(@PathVariable Integer userId) {
        List<FollowerReadDTO> followers = followerService.getFollowersByUserId(userId);
        return new ResponseEntity<>(followers, HttpStatus.OK);
    }

    @DeleteMapping(path = "followers/{followId}")
    public ResponseEntity<Void> deleteFollow(@PathVariable Integer followId, Principal principal) {
        User user = (User) principal;
        followerService.deleteFollow(followId, user);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @PostMapping(path = "followers/{followingUserId}")
    public ResponseEntity<FollowerReadDTO> createNewFollower(@PathVariable Integer followingUserId, Principal principal) {
        User user = (User) principal;
        FollowerReadDTO follower = followerService.createNewFollower(followingUserId, user);
        return new ResponseEntity<>(follower, HttpStatus.CREATED);
    }
}
