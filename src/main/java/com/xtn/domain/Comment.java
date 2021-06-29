package com.xtn.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Comment {

    private Long id;

    //博客id
    private Long blogId;

    //评论用户id
    private Long peoId;

    //评论用户姓名
    private String name;

    //评论内容
    private String content;

    //评论用户头像
    private String avatar;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

}
