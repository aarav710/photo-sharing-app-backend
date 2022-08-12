package com.photosharing.app.posts;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface PostRepo extends CrudRepository<Post, Integer>, PostCustomRepo {
    List<Post> findByUser_Id(Integer userId, Pageable pageable);
}