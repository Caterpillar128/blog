package com.wyj.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wyj.constants.AllConstants;
import com.wyj.pojo.Blog;
import com.wyj.pojo.Type;
import com.wyj.pojo.User;
import com.wyj.service.blog.BlogService;
import com.wyj.service.tag.TagService;
import com.wyj.service.type.TypeService;
import com.wyj.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class BlogController {
    private static final String LIST = "admin/blogs";
    private static final String INPUT = "admin/blogs-input";
    private static final String REDIRECT_LIST = "redirect:/admin/blogs";


    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    // 后台博客列表展示
    @GetMapping("/blogs")
    public String blogs(@RequestParam(defaultValue = "1",value = "pageNum")int pageNum, Model model){
        PageHelper.startPage(pageNum, 5);
        PageInfo<Blog> pageInfo=new PageInfo(blogService.listBlogs());
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("types", typeService.listType());
        return LIST;
    }

    // 后台博客列表搜索
    @PostMapping("/blogs/search")
    public String search(@RequestParam(defaultValue = "1",value = "pageNum")int pageNum, Model model, Blog blog){
        PageHelper.startPage(pageNum, 5);
        PageInfo<Blog> pageInfo=new PageInfo(blogService.searchAllBlog(blog));
        model.addAttribute("pageInfo",pageInfo);
        return LIST;
    }

    // 后台博客编写界面
    @GetMapping("/blogs/addBlog")
    public String toMakeBlog(Model model){
        // 初始化分类
        model.addAttribute("types", typeService.listType());
        // 初始化标签
        model.addAttribute("tags", tagService.listTag());
        model.addAttribute("blog", new Blog());
        return INPUT;
    }

    // 新增博客，并重定向到博客展示列表
    @Transactional  // 事务
    @PostMapping("/blogs")
    public String addBlog(Blog blog, RedirectAttributes attributes, HttpSession session){
//        blog.setUser((User) session.getAttribute(AllConstants.USER_SESSION));

        int count = blogService.saveBlog(blog);
        if (count > 0 ){
            attributes.addFlashAttribute("blogMsg", "新增文章操作成功");
        }else {
            attributes.addFlashAttribute("blogMsg", "新增文章操作失败");
        }
        // 获取最新插入blog 的 id
        long blogId = blogService.getBlogId();
        // 通过 id 获取 blog
        Blog blogById = blogService.getBlogById(blogId);
        String tagIds = blog.getTagIds();
        String[] split = tagIds.split(",");
        int length = split.length;

        /*test*/
//        System.out.println("Blog addBlog blogId ===> " + blogId);
//        System.out.println("Blog addBlog tagIds ===> " + tagIds);
//        System.out.println("Blog addBlog length ===> " + length);
        /*----*/

        while (length > 0){
            // 保存 blogID 和 tagId 的关系
            blogService.saveBlogToTagId(blogId, Long.valueOf(split[length-1]));
            length--;
        }
        return REDIRECT_LIST;
    }

    // 后台博客修改界面
    @GetMapping("/blogs/{id}/toUpdate")
    public String toUpdateBlog(@PathVariable("id") Long id, Model model){
        // 初始化分类
        model.addAttribute("types", typeService.listType());
        // 初始化标签
        model.addAttribute("tags", tagService.listTag());

        Blog blog = blogService.getBlogById(id);
        blog.setType(typeService.getTypeById(blog.getType().getId()));
        model.addAttribute("blog", blog);
        return INPUT;
    }

    // 修改博客，并重定向到博客展示列表
    @Transactional  // 事务
    @PostMapping("/blogs/update")
    public String updateBlog(Blog blog, RedirectAttributes attributes){
        int count = blogService.updateBlog(blog);
        if (count > 0 ){
            attributes.addFlashAttribute("blogMsg", "修改文章操作成功");
        }else {
            attributes.addFlashAttribute("blogMsg", "修改文章操作失败");
        }
        return REDIRECT_LIST;
    }

    // 后台博客删除
    @GetMapping("/blogs/{id}/delete")
    public String deleteBlog(@PathVariable("id") Long id , RedirectAttributes attributes){
        int count = blogService.deleteBlogById(id);
        int delete = blogService.deleteBlogToTagId(id);
        if (count > 0 && delete > 0){
            attributes.addFlashAttribute("blogMsg", "删除文章操作成功");
        }else {
            attributes.addFlashAttribute("blogMsg", "删除文章操作失败");
        }
        return REDIRECT_LIST;
    }


}
