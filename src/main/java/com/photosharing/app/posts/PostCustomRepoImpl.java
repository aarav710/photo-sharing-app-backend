package com.photosharing.app.posts;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Repository
public class PostCustomRepoImpl implements PostCustomRepo {
    @PersistenceContext
    EntityManager entityManager;

    public List<Post> findFeedPosts(String username) {
        EntityGraph<Post> entityGraph = entityManager.createEntityGraph(Post.class);
        // doesn't add sub graphs to the comments and likes as that is not needed
        // those will have to be fetched from a separate endpoint as this one only is needed for counts of individual post
        entityGraph.addAttributeNodes("comments");
        entityGraph.addAttributeNodes("likes");
        entityGraph.addAttributeNodes("user");
        // finds posts of the followings of the user. found using the username. sorted by date
        return entityManager.createQuery("SELECT p FROM Post p WHERE p.user.followers.follower.username = :username", Post.class)
                .setParameter("username", username)
                .setHint("javax.persistence.fetchgraph", entityGraph)
                .getResultList();
    }

    public List<Object[]> findUserPostsWithCommentAndLikeCountByUserId(Integer userId) {
        return entityManager.createQuery( "SELECT p, count(p.likes.id), count(p.comments.id) FROM Post p WHERE p.user.id = :userId", Object[].class)
                .setParameter("userId", userId)
                .getResultList();
    }
}
