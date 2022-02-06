package com.xtn.dao;

import com.xtn.domain.Problem;
import com.xtn.vo.ProblemInitVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * 
 * @author xCoder
 * @email 1481806085@qq.com
 * @date 2022-01-27 10:32:29
 */
@Mapper
public interface ProblemMapper {

    List<Problem> selectProblemList(@Param("title") String title, @Param("type") Integer type);

    Problem getInfoById(Long id);

    void save(Problem problem);

    void updateById(Problem problem);

    void deleteByIds(Long[] ids);

    List<Long> getIdList(ProblemInitVo vo);
}
