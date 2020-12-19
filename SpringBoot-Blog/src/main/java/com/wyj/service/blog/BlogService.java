package com.wyj.service.blog;

import com.wyj.pojo.Blog;
import org.apache.ibatis.javassist.NotFoundException;

import java.util.List;
import java.util.Map;

public interface BlogService {

    Blog getBlogById(Long id);
    List<Blog> listBlogs();
    List<Blog> searchAllBlog(Blog blog);
    int saveBlog(Blog blog);
    int updateBlog(Blog blog);
    int deleteBlogById(Long id);

    /*获取刚插入的数据的id值*/
    Long getBlogId();

    int saveBlogToTagId(Long blogId, Long tagId);
    int deleteBlogToTagId(Long blogId);

    List<Blog> topViews();

    List<Blog> receptionListBlogs();
    List<Blog> queryListBlogs(String query);
    Blog getDetailedBlogById(Long id) throws NotFoundException;
    List<Blog> getBlogsByTypeId(Long typeId);
    List<Blog> getBlogsByTagId(Long tagId);

    Map<String,List<Blog>> archiveBlog();  //归档博客
    int countBlog();

}
