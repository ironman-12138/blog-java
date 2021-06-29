package com.xtn.service;

import com.xtn.common.Pagination;
import com.xtn.domain.Message;

import java.util.List;

public interface MessageService {

    Pagination<Message> selectList(Integer pageNum, Integer pageSize);

    boolean saveMessage(Message message);

}
