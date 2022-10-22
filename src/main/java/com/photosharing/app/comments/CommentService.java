package com.photosharing.app.comments;

import com.photosharing.app.users.User;

import java.util.List;

public interface CommentService {
    List<CommentReadDTO> getCommentsByPostId(Integer postId);
    CommentReadDTO createNewComment(CommentCreateDTO newComment, Integer postId, String username);
    void deleteComment(String username, Integer commentId);
}