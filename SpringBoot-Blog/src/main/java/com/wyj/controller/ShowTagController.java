package com.wyj.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wyj.pojo.Blog;
import com.wyj.pojo.Tag;
import com.wyj.service.blog.BlogService;
import com.wyj.service.tag.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ShowTagController {

    @Autowired
    private TagService tagService;
    @Autowired
    private BlogService blogService;


    @GetMapping("/tags/{id}")
//    @ResponseBody
    public String blogPage(@RequestParam(required = false,defaultValue = "1",value = "pageNum")int pageNum, @PathVariable Long id, Model model) {
        List<Tag> tags = tagService.getTagToBlog();
        //-1从导航点过来的
        if (id == -1){
            id = tags.get(0).getId();
        }
        PageHelper.startPage(pageNum, 5);
        PageInfo<Blog> pageInfo = new PageInfo(blogService.getBlogsByTagId(id));
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("tags", tags);
        model.addAttribute("activeTagId", id);
        return "tags";
    }

}
