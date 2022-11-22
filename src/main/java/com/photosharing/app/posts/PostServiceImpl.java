package com.photosharing.app.posts;

import com.photosharing.app.comments.CommentRepo;
import com.photosharing.app.exceptions.NotFoundException;
import com.photosharing.app.exceptions.UnauthorizedException;
import com.photosharing.app.followers.Follower;
import com.photosharing.app.followers.FollowerRepo;
import com.photosharing.app.likes.LikeRepo;
import com.photosharing.app.users.User;
import com.photosharing.app.users.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    final public static Integer POSTS_RESPONSE_LIMIT = 20;

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private FollowerRepo followerRepo;

    @Autowired
    private LikeRepo likeRepo;

    @Autowired
    private CommentRepo commentRepo;

    // make it cacheable
    private Post getPostById(Integer postId) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new NotFoundException("Post with id " + postId + " could not be found."));
        return post;
    }

    public PostReadDetailDTO getPostDetailViewById(Integer postId) {
        Post post = getPostById(postId);
        Integer likesCount = likeRepo.countByPost_Id(post.getId());
        Integer commentsCount = commentRepo.countByPost_Id(post.getId());
        return postMapper.postToPostReadDetailDTO(post, likesCount, commentsCount);
    }

public List<PostReadDetailDTO> findFeed(String username, Integer page) {
    Pageable pageable = PageRequest.of(page, POSTS_RESPONSE_LIMIT, Sort.by(Sort.Direction.DESC, "createdAt"));
    List<Follower> followings = followerRepo.findByFollower_Username(username);
    List<Integer> followingIds = followings.stream()
            .map(following -> following.getFollowing().getId())
            .collect(Collectors.toList());
    List<Object[]> posts = postRepo.findFeedPostsWithLikesCountAndCommentsCount(followingIds, pageable);
    return posts.stream()
            .map(post -> postMapper.postToPostReadDetailDTO((Post) post[0], (Integer) post[1], (Integer) post[2]))
            .collect(Collectors.toList());
}

    public List<PostReadDTO> getPostsByUserId(Integer userId, Integer page) {
        Pageable pageable = PageRequest.of(page, POSTS_RESPONSE_LIMIT, Sort.by(Sort.Direction.DESC, "createdAt"));
        List<Post> posts = postRepo.findByUser_Id(userId, pageable);
        return posts.stream()
                .map(postMapper::postToPostReadDTO)
                .collect(Collectors.toList());
    }

    public PostReadDetailDTO createNewPost(String username, PostCreateDTO newPostInformation) {
        User user = userRepo.findByUsername(username).orElseThrow(() -> new NotFoundException("User with username " + username + " could not be found."));
        Post post = new Post(newPostInformation.getCaption(), newPostInformation.getPhotoUrl(), user);
        postRepo.save(post);
        return postMapper.postToPostReadDetailDTO(post, 0, 0);
    }

    public PostReadDetailDTO updatePost(String username, PostUpdateDTO updatePostInformation, Integer postId) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new NotFoundException("Post with id " + postId + " could not be found."));
        User user = userRepo.findByUsername(username).orElseThrow(() -> new NotFoundException("User with username " + username + " could not be found."));
        if (post.getUser().getId() != user.getId()) {
            throw new UnauthorizedException("You cannot do this request as this post is not owned by you.");
        }
        post.setCaption(updatePostInformation.getCaption());
        postRepo.save(post);

        Integer likesCount = likeRepo.countByPost_Id(post.getId());
        Integer commentsCount = commentRepo.countByPost_Id(post.getId());
        return postMapper.postToPostReadDetailDTO(post, likesCount, commentsCount);
    }

    public void deletePost(String username, Integer postId) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new NotFoundException("Post with id " + postId + " could not be found."));
        if (post.getUser().getUsername() != username) {
            throw new UnauthorizedException("You cannot do this request as this post is not owned by you.");
        }
        postRepo.delete(post);
    }
}