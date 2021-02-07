package com.xtn.controller;

import cn.hutool.core.date.DateUtil;
import com.xtn.common.CommonResult;
import com.xtn.common.Pagination;
import com.xtn.domain.Blog;
import com.xtn.service.BlogService;
import com.xtn.util.ShiroUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 博客控制层
 * 时间：2021年2月7日 16:05:09
 */

@RestController
@RequestMapping("/blog")
public class BlogController {

    @Resource
    private BlogService blogService;

    /**
     * 分页查询博客信息
     * @param pageNum 查询的页号
     * @param pageSize 一页显示的条数
     * @return
     */
    @GetMapping(value = "/List")
    public CommonResult list(@RequestParam(defaultValue = "1") Integer pageNum,Integer pageSize){
        Pagination<Blog> pagination = blogService.selectBlogList(pageNum, pageSize);
        return CommonResult.success(pagination);
    }

    /**
     * 查询博客详情
     * @param id 博客id
     * @return
     */
    @GetMapping(value = "/select")
    public CommonResult detail(Long id){
        Blog blog = blogService.selectBlogById(id);

        if (blog == null){
            return CommonResult.failure(400,"该博客已被删除");
        }

        return CommonResult.success(blog);
    }

    /**
     * 编辑博客信息
     * @param blog 博客的内容
     * @return
     */
    @RequiresAuthentication  //只有登录认证后才能调用方法
    @PostMapping(value = "/edit")
    public CommonResult edit(@RequestBody Blog blog){
        Blog temp = null;
        boolean flag = false;

        if (blog.getId() != null){
            //如果id不为空表示要修改该博客
            temp = blogService.selectBlogById(blog.getId());
            if (temp.getUserId() == ShiroUtil.getProfile().getId()){
                //如果登录人就是这条博客的创建者就可以进行修改
                flag = blogService.updateBlogById(blog);
                if (flag){
                    return CommonResult.success(200,"修改成功");
                }else {
                    return CommonResult.failure(400,"修改失败");
                }
            }else {
                return CommonResult.failure(400,"没有权限编辑");
            }
        }else {
            //添加一条博客
            temp = new Blog();
            temp.setUserId(ShiroUtil.getProfile().getId());
            temp.setCreated(DateUtil.date());
            temp.setStatus((byte) 0);
            temp.setContent(blog.getContent());
            temp.setDescription(blog.getDescription());
            temp.setTitle(blog.getTitle());

            flag = blogService.saveBlog(temp);
            if (flag){
                return CommonResult.success(200,"添加成功");
            }else {
                return CommonResult.failure(400,"添加失败");
            }
        }
    }

    /**
     * 删除一条博客
     * @param id 博客id
     * @return
     */
    @RequiresAuthentication  //只有登录认证后才能调用方法
    @GetMapping(value = "/delete")
    public CommonResult delete(Long id){
        boolean flag = false;
        Blog blog = blogService.selectBlogById(id);
        if (blog.getUserId() == ShiroUtil.getProfile().getId()) {
            //如果登录人就是这条博客的创建者就可以进行删除
            flag = blogService.deleteById(id);
        }else {
            return CommonResult.failure(400,"无删除权限");
        }
        if (flag){
            return CommonResult.success(200,"删除成功");
        }else {
            return CommonResult.failure(400,"删除失败");
        }
    }
}
