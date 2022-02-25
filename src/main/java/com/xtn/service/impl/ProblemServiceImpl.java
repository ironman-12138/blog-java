package com.xtn.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xtn.common.Pagination;
import com.xtn.dao.ProblemMapper;
import com.xtn.domain.Blog;
import com.xtn.domain.Problem;
import com.xtn.service.ProblemService;
import com.xtn.util.RedisUtil;
import com.xtn.util.StrUtil;
import com.xtn.vo.ProblemInitVo;
import com.xtn.vo.ProblemSelectVo;
import com.xtn.vo.SelectVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Service
public class ProblemServiceImpl implements ProblemService {

    @Resource
    private ProblemMapper problemMapper;
    @Resource
    private RedisUtil redisUtil;

    @Override
    public Pagination<Problem> getPageList(ProblemSelectVo selectVo) {
        //存放分页数据信息
        Pagination<Problem> pagination = new Pagination<>();

        //pageNum:查询的页数，pageSize:一页显示的数量
        PageHelper.startPage(selectVo.getPageNum(), selectVo.getPageSize());
        List<Problem> list = problemMapper.selectProblemList(selectVo.getTitle(), selectVo.getType());

        //获取总记录数pageInfo.getTotal()
        PageInfo<Problem> pageInfo = new PageInfo<>(list);

        pagination.setTotal((int) pageInfo.getTotal());
        pagination.setDataList(list);

        return pagination;
    }

    @Override
    public Problem getInfoById(Long id) {
        return problemMapper.getInfoById(id);
    }

    @Override
    public void save(Problem problem) {
        problemMapper.save(problem);
    }

    @Override
    public void updateById(Problem problem) {
        problemMapper.updateById(problem);
    }

    @Override
    public void deleteByIds(Long[] ids) {
        problemMapper.deleteByIds(ids);
    }

    @Override
    public void init(ProblemInitVo vo) {
        List<Long> ids = problemMapper.getIdList(vo);
        redisUtil.del("problemIds", "pro" + vo.getUserId());
        redisUtil.sSet("problemIds", ids.toArray());
    }

    @Override
    @Transactional
    public Problem random() {
        System.out.println("随机获取题目--------------start");
        Integer id = (Integer) redisUtil.sPopSet("problemIds");
        if (Objects.nonNull(id)) {
            System.out.println("随机获取题目--------------ending");
            return problemMapper.getInfoById(id.longValue());
        }
        System.out.println("随机获取题目--------------ending");
        return null;
    }

    @Override
    @Transactional
    public String check(Problem problem) {
        System.out.println("检测答案并保存------------start");
        Problem info = problemMapper.getInfoById(problem.getId());
        String res = StrUtil.percentage(StrUtil.SimilarDegree(problem.getAnswer(), info.getAnswer()));
        redisUtil.hset("pro" + problem.getUserId().toString(), problem.getId().toString(), res);
        System.out.println("检测答案并保存------------ending");
        return res;
    }
}
