package com.photosharing.app.posts;

import com.photosharing.app.followers.Follower;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

public interface PostRepo extends CrudRepository<Post, Integer> {
    List<Post> findByUser_Id(Integer userId, Pageable pageable);
    List<Post> findByFollowersIn(Collection<Follower> followers, Pageable pageable);
    Integer countByUser_Id(Integer userId);

    @Query(value = "SELECT post FROM followers follower JOIN follower.user user WHERE user.id = ?1 JOIN user.post post", nativeQuery = true)
    List<Post> findFeedPosts(Integer userId, Pageable pageable);

    @Query(value = "SELECT post, COUNT(like), COUNT(comment) FROM followers follower JOIN follower.user user WHERE user.id=?1 JOIN user.post post JOIN post.likes like JOIN post.comments comment GROUP BY post", nativeQuery = true)
    List<Object[]> findFeedPostsWithLikesCountAndCommentsCount(Integer userId, Pageable pageable);
}