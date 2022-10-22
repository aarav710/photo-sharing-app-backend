package com.photosharing.app.followers;

import com.photosharing.app.exceptions.NotFoundException;
import com.photosharing.app.exceptions.ResourceAlreadyExists;
import com.photosharing.app.exceptions.UnauthorizedException;
import com.photosharing.app.users.User;
import com.photosharing.app.users.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FollowerServiceImpl implements FollowerService {

    @Autowired
    private FollowerRepo followerRepo;

    @Autowired
    private FollowerMapper followerMapper;

    @Autowired
    private UserRepo userRepo;

    public List<FollowerReadDTO> getFollowersByUserId(Integer userId) {
        List<Follower> followers = followerRepo.findByFollower_Id(userId);
        return followers.stream()
                .map(followerMapper::followerToFollowerReadDTO)
                .collect(Collectors.toList());
    }

    public List<FollowerReadDTO> getFollowingsByUserId(Integer userId) {
        List<Follower> followings = followerRepo.findByFollowing_Id(userId);
        return followings.stream()
                .map(followerMapper::followerToFollowerReadDTO)
                .collect(Collectors.toList());
    }

    public void deleteFollow(Integer followId, String username) {
        Follower follow = followerRepo.findById(followId).orElseThrow(() -> new NotFoundException("Follow with the id " + followId + " could not be found"));
        User user = userRepo.findByUsername(username).orElseThrow(() -> new NotFoundException("User with username " + username + " could not be found."));
        if (follow.getFollower().getId() != user.getId()) {
            throw new UnauthorizedException("User who has made this request does not have the appropriate follow for this request.");
        }
        followerRepo.delete(follow);
    }

    public FollowerReadDTO createNewFollower(Integer followingUserId, String username) {
        User user = userRepo.findByUsername(username).orElseThrow(() -> new NotFoundException("User with username " + username + " could not be found."));
        // logic to check if this particular follower/following relationship exists
        if (followerRepo.existsByFollower_IdAndFollowing_Id(user.getId(), followingUserId)) {
            throw new ResourceAlreadyExists(user.getUsername() + " already follows given user.");
        }
        User followingUser = userRepo.findById(followingUserId).orElseThrow(() -> new NotFoundException("User with id " + followingUserId + " could not be found."));
        Follower follower = new Follower(followingUser, user);
        followerRepo.save(follower);
        return followerMapper.followerToFollowerReadDTO(follower);
    }
}
