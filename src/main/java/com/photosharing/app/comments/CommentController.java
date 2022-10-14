package com.photosharing.app.comments;

import com.photosharing.app.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<CommentReadDTO> createNewCommentByPostId(@PathVariable Integer postId, Principal principal, @Valid @RequestBody CommentCreateDTO newComment) {
        User user = (User) principal;
        CommentReadDTO comment = commentService.createNewComment(newComment, postId, user);
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Integer commentId, Principal principal) {
        User user = (User) principal;
        commentService.deleteComment(user, commentId);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
