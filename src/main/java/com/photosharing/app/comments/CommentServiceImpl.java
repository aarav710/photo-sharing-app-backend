package com.photosharing.app.comments;

import org.springframework.beans.factory.annotation.Autowired;

public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentRepo commentRepo;


}