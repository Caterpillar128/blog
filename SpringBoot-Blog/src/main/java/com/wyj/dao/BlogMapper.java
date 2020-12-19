package com.wyj.dao;

import com.wyj.pojo.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BlogMapper {
    /* =============后台数据============== */
    // 通过博客 id 获取博客
    Blog getBlogById(Long id);
    // 列出博客列表
    List<Blog> listBlogs();

    //根据标题、分类、推荐搜索博客
    List<Blog> searchAllBlog(Blog blog);
    // 保存博客
    int saveBlog(Blog blog);
    // 修改博客
    int updateBlog(Blog blog);
    // 通过博客 id 删除博客
    int deleteBlogById(Long id);

    // 获取刚插入的博客的id值
    Long getBlogId();

    // 关联 blog id和 tag id
    int saveBlogToTagId(Long blogId, Long tagId);
    // 修改 关联通过删除，再保存修改
    int deleteBlogToTagId(Long blogId);

    /* =============前台数据============== */
    // 根据观看次数来推荐的博客
    List<Blog> topViews();
    // 前端展示
    List<Blog> receptionListBlogs();

    List<Blog> queryListBlogs(String query);
    // 博客详情
    Blog getDetailedBlogById(Long id);

    // 通过 分类id 获取博客列表
    List<Blog> getBlogsByTypeId(Long typeId);
    //  通过 标签id 获取博客列表
    List<Blog> getBlogsByTagId(Long tagId);

    // 查询所有年份，返回一个集合
    List<String> findAllYear();
    // 按年份查询博客
    List<Blog> findBlogsByYear(String year);
}
