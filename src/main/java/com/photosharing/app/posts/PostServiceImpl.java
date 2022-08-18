package com.photosharing.app.posts;

import com.photosharing.app.comments.CommentRepo;
import com.photosharing.app.exceptions.NotFoundException;
import com.photosharing.app.exceptions.UnauthorizedException;
import com.photosharing.app.likes.LikeRepo;
import com.photosharing.app.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private PostMapper postMapper;

    @Autowired
    private LikeRepo likeRepo;

    @Autowired
    private CommentRepo commentRepo;

    public PostReadDTO getPostById(Integer postId) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new NotFoundException("Post with id " + postId + "could not be found."));
        return postMapper.postToPostReadDTO(post);
    }

    public List<PostReadDTO> findFeedPostsByUsername(String username) {
        List<PostReadDTO> posts = new ArrayList<PostReadDTO>();
        return posts;
    }

    // make it sorted according to the date created and think about pagination for infinite queries/scroll
    public List<PostReadDTO> getPostsByUserId(Integer userId, Integer offset, Integer limit) {
        Pageable pageable = PageRequest.of(offset, limit);
        List<Post> posts = postRepo.findByUser_Id(userId, pageable);
        return posts.stream()
                .map(post -> postMapper.postToPostReadDTO(post))
                .collect(Collectors.toList());
    }

    public PostReadDetailDTO createNewPost(User user, PostCreateDTO newPostInformation) {
        Post post = new Post(newPostInformation.getCaption(), newPostInformation.getPhotoUrl(), user);
        postRepo.save(post);
        return postMapper.postToPostReadDetailDTO(post, 0, 0);
    }

    public PostReadDetailDTO updatePost(User user, PostUpdateDTO updatePostInformation, Integer postId) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new NotFoundException("Post with id " + postId + " could not be found."));
        if (post.getUser().getId() != user.getId()) {
            throw new UnauthorizedException("You cannot do this request as this post is not owned by you.");
        }
        post.setCaption(updatePostInformation.getCaption());
        // maybe make these 2 queries concurrent
        Integer likesCount = likeRepo.countByPost_Id(post.getId());
        Integer commentsCount = commentRepo.countByPost_Id(post.getId());
        return postMapper.postToPostReadDetailDTO(post, likesCount, commentsCount);
    }

}