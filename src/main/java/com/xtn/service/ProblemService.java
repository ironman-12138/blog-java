package com.xtn.service;


import com.xtn.common.Pagination;
import com.xtn.domain.Problem;
import com.xtn.vo.ProblemInitVo;
import com.xtn.vo.ProblemSelectVo;
import com.xtn.vo.SelectVo;

/**
 * 
 *
 * @author xCoder
 * @email 1481806085@qq.com
 * @date 2022-01-27 10:32:29
 */
public interface ProblemService {

    Pagination<Problem> getPageList(ProblemSelectVo selectVo);

    Problem getInfoById(Long id);

    void save(Problem problem);

    void updateById(Problem problem);

    void deleteByIds(Long[] ids);

    void init(ProblemInitVo vo);

    Problem random();

    String check(Problem problem);
}

