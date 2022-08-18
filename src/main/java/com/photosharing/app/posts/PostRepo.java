package com.photosharing.app.posts;

import com.photosharing.app.followers.Follower;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

public interface PostRepo extends CrudRepository<Post, Integer>, PostCustomRepo {
    List<Post> findByUser_Id(Integer userId, Pageable pageable);
    List<Post> findByFollowersIn(Collection<Follower> followers);

}