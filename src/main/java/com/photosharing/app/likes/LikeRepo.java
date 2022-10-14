package com.photosharing.app.likes;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LikeRepo extends CrudRepository<Like, Integer> {
    List<Like> findByPost_Id(Integer id, Sort sort);
    Integer countByPost_Id(Integer id);

    @Query(value = "SELECT post_id, COUNT(*) as likesCount FROM likes WHERE post_id IN ?1 GROUP BY post_id", nativeQuery = true)
    List<Object[]> countLikesByPost_Ids(List<Integer> postIds);
}
