package com.photosharing.app.comments;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepo extends CrudRepository<Comment, Integer> {
    List<Comment> findByPost_Id(Integer id);
    Integer countByPost_Id(Integer id);
}