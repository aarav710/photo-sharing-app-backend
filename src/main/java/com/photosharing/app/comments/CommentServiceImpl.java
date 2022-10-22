package com.photosharing.app.comments;

import com.photosharing.app.exceptions.NotFoundException;
import com.photosharing.app.exceptions.UnauthorizedException;
import com.photosharing.app.posts.Post;
import com.photosharing.app.posts.PostRepo;
import com.photosharing.app.users.User;
import com.photosharing.app.users.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private CommentMapper commentMapper;

    public List<CommentReadDTO> getCommentsByPostId(Integer postId) {
        List<Comment> comments = commentRepo.findByPost_Id(postId);
        return comments.stream()
                .map(commentMapper::commentToCommentReadDTO)
                .collect(Collectors.toList());
    }

    public CommentReadDTO createNewComment(CommentCreateDTO newComment, Integer postId, String username) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new NotFoundException("Post with id " + postId + " could not be found."));
        User user = userRepo.findByUsername(username).orElseThrow(() -> new NotFoundException("User with username " + username + " could not be found."));
        Comment comment = new Comment(newComment.getText(), user, post);
        commentRepo.save(comment);
        return commentMapper.commentToCommentReadDTO(comment);
    }

    public void deleteComment(String username, Integer commentId) {
        Comment comment = commentRepo.findById(commentId).orElseThrow(() -> new NotFoundException("Comment with id " + commentId + " could not be found."));
        User user = userRepo.findByUsername(username).orElseThrow(() -> new NotFoundException("User with username " + username + " could not be found."));
        if (comment.getUser().getId() != user.getId()) {
            throw new UnauthorizedException("You are not authorized to delete comment with id " + commentId);
        }
        commentRepo.delete(comment);
    }
}