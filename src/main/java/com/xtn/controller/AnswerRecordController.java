package com.xtn.controller;

import com.xtn.common.CommonResult;
import com.xtn.common.Pagination;
import com.xtn.domain.AnswerRecord;
import com.xtn.service.AnswerRecordService;
import com.xtn.vo.RecordSelectVo;
import com.xtn.vo.SelectVo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description 答题记录
 * @author xcoder
 * @date 2022-01-27
 */
@RestController
@RequestMapping(value = "/answerRecord")
public class AnswerRecordController {

    @Resource
    private AnswerRecordService answerRecordService;

    /**
     * 新增
     **/
    @RequestMapping("/insert")
    public CommonResult insert(@RequestBody RecordSelectVo vo){
        return answerRecordService.insert(vo.getUserId());
    }

    /**
     * 查询 分页查询
     **/
    @RequestMapping("/pageList")
    public CommonResult pageList(@RequestBody RecordSelectVo selectVo) {
        Pagination<AnswerRecord> pageList = answerRecordService.pageList(selectVo);
        return CommonResult.success(pageList);
    }

}
