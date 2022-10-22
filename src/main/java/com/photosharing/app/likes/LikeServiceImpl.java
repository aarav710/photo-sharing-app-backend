package com.photosharing.app.likes;

import com.photosharing.app.exceptions.NotFoundException;
import com.photosharing.app.exceptions.ResourceAlreadyExists;
import com.photosharing.app.exceptions.UnauthorizedException;
import com.photosharing.app.posts.Post;
import com.photosharing.app.posts.PostRepo;
import com.photosharing.app.users.User;
import com.photosharing.app.users.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    private LikeRepo likeRepo;

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private LikeMapper likeMapper;

    @Autowired
    private UserRepo userRepo;

    public List<LikeReadDTO> findLikesByPostId(Integer postId) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        List<Like> likes = likeRepo.findByPost_Id(postId, sort);
        return likes.stream()
                .map(likeMapper::likeToLikeReadDTO)
                .collect(Collectors.toList());
    }

    public LikeReadDTO createLike(Integer postId, String username) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new NotFoundException("Post with id " + postId + " could not be found."));
        User user = userRepo.findByUsername(username).orElseThrow(() -> new NotFoundException("User with username " + username + " could not be found."));
        boolean likeExists = likeRepo.existsByPostIdAndUserId(postId, user.getId());
        if (likeExists) {
            throw new ResourceAlreadyExists("You have already liked this post before.");
        }
        Like like = new Like(user, post);
        likeRepo.save(like);
        return likeMapper.likeToLikeReadDTO(like);
    }

    public void deleteLike(Integer likeId, String username) {
        Like like = likeRepo.findById(likeId).orElseThrow(() -> new NotFoundException("Like with id " + likeId + " could not be found."));
        User user = userRepo.findByUsername(username).orElseThrow(() -> new NotFoundException("User with username " + username + " could not be found."));
        if (like.getUser().getId() != user.getId()) {
            throw new UnauthorizedException("You are unauthorized to do this action because this like belongs to another user.");
        }
        likeRepo.delete(like);
    }
}
