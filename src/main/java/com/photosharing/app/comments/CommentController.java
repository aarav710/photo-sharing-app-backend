package com.photosharing.app.comments;

import com.photosharing.app.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping(path = "/posts/{postId}/comments")
    public ResponseEntity<List<CommentReadDTO>> getCommentsByPostId(@PathVariable Integer postId) {
        List<CommentReadDTO> comments = commentService.getCommentsByPostId(postId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @PostMapping(path = "/posts/{postId}/comments")
    public ResponseEntity<CommentReadDTO> createNewCommentByPostId(@PathVariable Integer postId, Principal principal, @Valid @RequestBody CommentCreateDTO newComment, BindingResult bindingResult) {
        UserDetails user = (UserDetails) principal;
        CommentReadDTO comment = commentService.createNewComment(newComment, postId, user.getUsername());
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Integer commentId, Principal principal) {
        UserDetails user = (UserDetails) principal;
        commentService.deleteComment(user.getUsername(), commentId);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
