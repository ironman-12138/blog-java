package com.xtn.service;

import com.xtn.domain.Blog;
import com.xtn.domain.Comment;

public interface CommentService {

    //添加一条评论
    boolean saveComment(Comment comment);

}
