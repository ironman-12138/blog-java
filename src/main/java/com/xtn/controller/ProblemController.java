package com.xtn.controller;

import java.util.Arrays;
import java.util.Map;

import com.xtn.common.CommonResult;
import com.xtn.common.Pagination;
import com.xtn.domain.Problem;
import com.xtn.service.ProblemService;
import com.xtn.vo.ProblemInitVo;
import com.xtn.vo.ProblemSelectVo;
import com.xtn.vo.SelectVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 
 *
 * @author xCoder
 * @email 1481806085@qq.com
 * @date 2022-01-27 10:32:29
 */
@RestController
@RequestMapping("/problem")
public class ProblemController {
    @Resource
    private ProblemService problemService;

    /**
     * 列表
     */
    @PostMapping(value = "/list")
    public CommonResult list(@RequestBody ProblemSelectVo selectVo){
        Pagination<Problem> page = problemService.getPageList(selectVo);
        return CommonResult.success(page);
    }


    /**
     * 详情
     */
    @PostMapping("/info/{id}")
    public CommonResult info(@PathVariable("id") Long id){
        Problem problem = problemService.getInfoById(id);
        return CommonResult.success(problem);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public CommonResult save(@RequestBody Problem problem){
        problemService.save(problem);
        return CommonResult.success();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    public CommonResult update(@RequestBody Problem problem){
        problemService.updateById(problem);
        return CommonResult.success();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    public CommonResult delete(@RequestBody Long[] ids){
        problemService.deleteByIds(ids);
        return CommonResult.success();
    }

    /**
     * 初始化题库
     */
    @PostMapping("/init")
    public CommonResult init(@RequestBody ProblemInitVo vo){
        problemService.init(vo);
        return CommonResult.success();
    }

    /**
     * 随机获取题目
     */
    @PostMapping("/random")
    public CommonResult random(){
        Problem problem = problemService.random();
        return CommonResult.success(problem);
    }

    /**
     * 检测答案
     * return 返回相似度百分比
     */
    @PostMapping("/check")
    public CommonResult check(@RequestBody Problem problem){
        String check = problemService.check(problem);
        return CommonResult.success(check);
    }

}
