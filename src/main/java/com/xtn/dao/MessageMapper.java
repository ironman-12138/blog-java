package com.xtn.dao;

import com.xtn.domain.Comment;
import com.xtn.domain.Message;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MessageMapper {

    List<Message> selectList();

    boolean saveMessage(Message message);

}
