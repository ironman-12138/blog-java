package com.xtn.service.impl;

import com.xtn.dao.CommentMapper;
import com.xtn.domain.Comment;
import com.xtn.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentMapper commentMapper;

    @Override
    public boolean saveComment(Comment comment) {
        return commentMapper.saveComment(comment);
    }
}
