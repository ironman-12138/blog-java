package com.xtn.controller;

import com.xtn.common.CommonResult;
import com.xtn.common.Pagination;
import com.xtn.domain.Blog;
import com.xtn.domain.Comment;
import com.xtn.domain.Message;
import com.xtn.service.MessageService;
import com.xtn.vo.SelectVo;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Resource
    private MessageService messageService;

    /**
     * 分页查询留言信息
     * @return
     */
    @PostMapping(value = "/list")
    public CommonResult list(@RequestBody SelectVo selectVo){
        Pagination<Message> pagination = messageService.selectList(selectVo.getPageNum(), selectVo.getPageSize());
        return CommonResult.success(pagination);
    }

    /**
     * 添加留言
     * @return
     */
    @RequiresAuthentication  //只有登录认证后才能调用方法
    @PostMapping(value = "/saveMessage")
    public CommonResult saveMessage(@RequestBody Message message){
        boolean flag = false;
        message.setCreated(new Date());
        flag = messageService.saveMessage(message);
        if (flag){
            return CommonResult.success(200,"成功");
        }else {
            return CommonResult.failure(400,"留言失败");
        }
    }

}
