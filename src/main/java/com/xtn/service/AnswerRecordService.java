package com.xtn.service;

import com.xtn.common.CommonResult;
import com.xtn.common.Pagination;
import com.xtn.domain.AnswerRecord;
import com.xtn.vo.RecordSelectVo;
import com.xtn.vo.SelectVo;

/**
 * @description answer_record
 * @author xCoder
 * @date 2022-01-27
 */
public interface AnswerRecordService {

    /**
     * 新增
     */
    public CommonResult insert(Long userId);

    /**
     * 分页查询
     */
    public Pagination<AnswerRecord> pageList(RecordSelectVo selectVo);

}
