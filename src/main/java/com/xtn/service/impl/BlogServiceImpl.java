package com.xtn.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xtn.common.Pagination;
import com.xtn.dao.BlogMapper;
import com.xtn.domain.Blog;
import com.xtn.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 博客业务层实现类
 * 时间：2021年2月6日 14:31:09
 */

@Service
@Slf4j
public class BlogServiceImpl implements BlogService {

    @Resource
    private BlogMapper blogMapper;

    //分页查询博客信息
    @Override
    public Pagination<Blog> selectBlogList(Integer pageNum, Integer pageSize) {

        //存放分页数据信息
        Pagination pagination = new Pagination();

        //pageNum:查询的页数，pageSize:一页显示的数量
        PageHelper.startPage(pageNum,pageSize);
        List<Blog> blogList = blogMapper.selectBlogList();

        //获取总记录数pageInfo.getTotal()
        PageInfo<Blog> pageInfo = new PageInfo<>(blogList);

        pagination.setTotal((int) pageInfo.getTotal());
        pagination.setDataList(blogList);

        return pagination;
    }

    //根据id查询博客信息
    @Override
    public Blog selectBlogById(Long id) {
        return blogMapper.selectByPrimaryKey(id);
    }

    //根据id修改博客
    @Override
    public boolean updateBlogById(Blog blog) {
        boolean flag = false;
        int result = blogMapper.updateByPrimaryKeySelective(blog);
        if (result != 0){
            flag = true;
        }
        return flag;
    }

    //添加一条博客
    @Override
    public boolean saveBlog(Blog blog) {
        boolean flag = false;
        int result = blogMapper.insert(blog);
        if (result != 0){
            flag = true;
        }
        return flag;
    }

    //删除一条博客
    @Override
    public boolean deleteById(Long id) {
        boolean flag = false;
        int result = blogMapper.deleteByPrimaryKey(id);
        if (result != 0){
            flag = true;
        }
        return flag;
    }
}
