package com.photosharing.app.posts;

import com.photosharing.app.followers.Follower;
import com.photosharing.app.users.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Future;

public interface PostRepo extends CrudRepository<Post, Integer> {
    List<Post> findByUser_Id(Integer userId, Pageable pageable);
    List<Post> findByUserIn(Collection<User> users, Pageable pageable);
    Integer countByUser_Id(Integer userId);
    @Query(value = "SELECT post, COUNT(post.likes), COUNT(post.comments) FROM User user JOIN user.posts post WHERE user.id IN ?1 GROUP BY post")
    List<Object[]> findFeedPostsWithLikesCountAndCommentsCount(Collection<Integer> userIds, Pageable pageable);
}