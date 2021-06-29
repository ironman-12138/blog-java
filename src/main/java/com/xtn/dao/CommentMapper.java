package com.xtn.dao;

import com.xtn.domain.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentMapper {
    List<Comment> selectByBlogId(@Param(value="id") Long id);

    boolean saveComment(Comment comment);
}
