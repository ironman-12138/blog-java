package com.xtn.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xtn.common.Pagination;
import com.xtn.dao.MessageMapper;
import com.xtn.domain.Blog;
import com.xtn.domain.Message;
import com.xtn.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class MessageServiceImpl implements MessageService {

    @Resource
    private MessageMapper messageMapper;

    @Override
    public Pagination<Message> selectList(Integer pageNum, Integer pageSize) {
        //存放分页数据信息
        Pagination pagination = new Pagination();

        //pageNum:查询的页数，pageSize:一页显示的数量
        PageHelper.startPage(pageNum,pageSize);
        List<Message> messages = messageMapper.selectList();

        //获取总记录数pageInfo.getTotal()
        PageInfo<Message> pageInfo = new PageInfo<>(messages);

        pagination.setTotal((int) pageInfo.getTotal());
        pagination.setDataList(messages);

        return pagination;
    }

    @Override
    public boolean saveMessage(Message message) {
        return messageMapper.saveMessage(message);
    }
}
