package com.xtn.service;

import com.xtn.common.Pagination;
import com.xtn.domain.Blog;

/**
 * 博客业务层接口
 * 时间：2021年2月6日 14:29:54
 */

public interface BlogService {

    //分页查询博客信息
    public Pagination<Blog> selectBlogList(Integer pageNum,Integer pageSize,String title);

    //根据id查询博客信息
    public Blog selectBlogById(Long id);

    //根据id修改博客
    boolean updateBlogById(Blog blog);

    //添加一条博客
    boolean saveBlog(Blog blog);

    //删除一条博客
    boolean deleteById(Long id);
}
