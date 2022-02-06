package com.xtn.dao;

import com.xtn.domain.AnswerRecord;
import com.xtn.vo.RecordSelectVo;

import java.util.List;

public interface AnswerRecordMapper {
    void insert(AnswerRecord answerRecord);

    List<AnswerRecord> selectPageList(RecordSelectVo selectVo);
}
