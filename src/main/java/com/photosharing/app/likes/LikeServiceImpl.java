package com.photosharing.app.likes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.stream.Collectors;

public class LikeServiceImpl implements LikeService {

    @Autowired
    private LikeRepo likeRepo;

    @Autowired
    private LikeMapper likeMapper;

    public List<LikeReadDTO> findLikesByPostId(Integer postId) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        List<Like> likes = likeRepo.findByPost_Id(postId, sort);
        return likes.stream()
                .map(like -> likeMapper.likeToLikeReadDTO(like))
                .collect(Collectors.toList());
    }


}
