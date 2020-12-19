package com.wyj.controller;


import com.wyj.pojo.Blog;
import com.wyj.service.blog.BlogService;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ShowBlogController {

    @Autowired
    private BlogService blogService;


    @GetMapping("/blog/{id}")
//    @ResponseBody
    public String blog(@PathVariable("id") Long id, Model model) throws NotFoundException {
        model.addAttribute("blog", blogService.getDetailedBlogById(id));
        return "blog";
    }



}
