package com.xtn.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xtn.common.CommonResult;
import com.xtn.common.Pagination;
import com.xtn.dao.AnswerRecordMapper;
import com.xtn.dao.UserMapper;
import com.xtn.domain.AnswerRecord;
import com.xtn.domain.Problem;
import com.xtn.service.AnswerRecordService;
import com.xtn.util.RedisUtil;
import com.xtn.vo.RecordSelectVo;
import com.xtn.vo.SelectVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AnswerRecordServiceImpl implements AnswerRecordService {
    @Resource
    private AnswerRecordMapper answerRecordMapper;
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private UserMapper userMapper;

    @Override
    public CommonResult insert(Long userId) {
        System.out.println("保存答题记录------------start");
        AnswerRecord answerRecord = new AnswerRecord();
        int success = 0, fail = 0;
        Map<Object, Object> map = redisUtil.hmget("pro" + userId);
        for (Map.Entry<Object, Object> entry : map.entrySet()){
            if (compareTo((String) entry.getValue(), "60%") >= 0) {
                success++;
            }else {
                fail++;
            }
        }
        answerRecord.setSuccessNum(success);
        answerRecord.setFailNum(fail);
        answerRecord.setTotalNum(success + fail);
        answerRecord.setUserId(userId);
        answerRecord.setUsername(userMapper.selectByPrimaryKey(userId).getUsername());
        answerRecord.setCreateTime(new Date());
        answerRecordMapper.insert(answerRecord);
        return CommonResult.success();
    }

    @Override
    public Pagination<AnswerRecord> pageList(RecordSelectVo selectVo) {
        //存放分页数据信息
        Pagination<AnswerRecord> pagination = new Pagination<>();

        //pageNum:查询的页数，pageSize:一页显示的数量
        PageHelper.startPage(selectVo.getPageNum(), selectVo.getPageSize());
        List<AnswerRecord> list = answerRecordMapper.selectPageList(selectVo);

        //获取总记录数pageInfo.getTotal()
        PageInfo<AnswerRecord> pageInfo = new PageInfo<>(list);

        pagination.setTotal((int) pageInfo.getTotal());
        pagination.setDataList(list);

        return pagination;
    }

    public static Integer compareTo(String v1, String v2) {
        //去掉%
        String tempA = v1.substring(0, v1.length() - 1);
        String tempB = v2.substring(0, v2.length() - 1);
        //精确表示
        BigDecimal dataA = new BigDecimal(tempA);
        BigDecimal dataB = new BigDecimal(tempB);
//        System.out.println(dataA.compareTo(dataB));//大于为1，相同为0，小于为-1
        return dataA.compareTo(dataB);//大于为1，相同为0，小于为-1
    }
}
