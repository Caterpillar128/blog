package com.wyj.service.blog;

import com.wyj.dao.BlogMapper;
import com.wyj.pojo.Blog;
import com.wyj.util.MarkdownUtils;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogMapper blogMapper;

    @Override
    public Blog getBlogById(Long id) {
        return blogMapper.getBlogById(id);
    }

    @Override
    public List<Blog> listBlogs() {
        return blogMapper.listBlogs();
    }

    @Override
    public List<Blog> searchAllBlog(Blog blog) {
        return blogMapper.searchAllBlog(blog);
    }

    @Transactional  // 事务
    @Override
    public int saveBlog(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);
        blog.setUserId(1L);
        return blogMapper.saveBlog(blog);
    }

    @Transactional  // 事务
    @Override
    public int updateBlog(Blog blog) {
        blog.setUpdateTime(new Date());
        // 修改博客时，将博客和标签的关系一起修改
            // 先删除关系，再新建
        blogMapper.deleteBlogToTagId(blog.getId());
            // 新建
        Long blogId = blog.getId();
        String tagIds = blog.getTagIds();
        String[] split = tagIds.split(",");
        int length = split.length;
        while (length > 0){
            // 保存 blogID 和 tagId 的关系
            saveBlogToTagId(blogId, Long.valueOf(split[length-1]));
            length--;
        }

        return blogMapper.updateBlog(blog);
    }

    @Override
    public int deleteBlogById(Long id) {
        return blogMapper.deleteBlogById(id);
    }

    @Override
    public Long getBlogId() {
        return blogMapper.getBlogId();
    }

    @Override
    public int saveBlogToTagId(Long blogId, Long tagId) {
        return blogMapper.saveBlogToTagId(blogId, tagId);
    }


    @Override
    public int deleteBlogToTagId(Long blogId) {
        return blogMapper.deleteBlogToTagId(blogId);
    }

    @Override
    public List<Blog> topViews() {
        return blogMapper.topViews();
    }

    @Override
    public List<Blog> receptionListBlogs() {
        return blogMapper.receptionListBlogs();
    }

    @Override
    public List<Blog> queryListBlogs(String query) {
        return blogMapper.queryListBlogs(query);
    }

    @Override
    public Blog getDetailedBlogById(Long id) throws NotFoundException {
        Blog blog = blogMapper.getDetailedBlogById(id);
        if (blog == null) {
            throw new NotFoundException("该博客不存在");
        }
        String content = blog.getContent();
        blog.setContent(MarkdownUtils.markdownToHtmlExtensions(content));  //将Markdown格式转换成html
        return blog;
    }

    @Override
    public List<Blog> getBlogsByTypeId(Long typeId) {
        return blogMapper.getBlogsByTypeId(typeId);
    }

    @Override
    public List<Blog> getBlogsByTagId(Long tagId) {
        return blogMapper.getBlogsByTagId(tagId);
    }

    @Override
    public Map<String, List<Blog>> archiveBlog() {
        List<String> years = blogMapper.findAllYear();
        Set<String> set = new HashSet<>(years);  //set去掉重复的年份
        Map<String, List<Blog>> map = new HashMap<>();
        for (String year : set) {
            map.put(year, blogMapper.findBlogsByYear(year));
        }
        return map;
    }

    @Override
    public int countBlog() {
        return blogMapper.listBlogs().size();
    }


}
