package com.photosharing.app.comments;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepo extends CrudRepository<Comment, Integer> {
    List<Comment> findByPost_Id(Integer id);
    Integer countByPost_Id(Integer id);
    @Query(value = "SELECT post_id, COUNT(*) as commentsCount FROM comments WHERE post_id IN ?1 GROUP BY post_id", nativeQuery = true)
    List<Object[]> countCommentsByPost_Ids(List<Integer> postIds);
}