package com.photosharing.app.followers;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface FollowerRepo extends CrudRepository<Follower, Integer> {
    List<Follower> findByFollower_Id(Integer userId);
    boolean existsByFollower_IdAndFollowing_Id(Integer followerId, Integer followingId);

    List<Follower> findByFollowing_Id(Integer userId);
}
