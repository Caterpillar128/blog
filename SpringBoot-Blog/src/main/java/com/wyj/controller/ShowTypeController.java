package com.wyj.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wyj.pojo.Blog;
import com.wyj.pojo.Type;
import com.wyj.service.blog.BlogService;
import com.wyj.service.type.TypeService;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ShowTypeController {

    @Autowired
    private TypeService typeService;
    @Autowired
    private BlogService blogService;


    @GetMapping("/types/{id}")
//    @ResponseBody
    public String blog(@RequestParam(required = false,defaultValue = "1",value = "pageNum")int pageNum, @PathVariable Long id, Model model) {
        List<Type> types = typeService.getTypeToBlog();
        //-1从导航点过来的
        if (id == -1){
            id = types.get(0).getId();
        }
        PageHelper.startPage(pageNum, 5);
        PageInfo<Blog> pageInfo = new PageInfo(blogService.getBlogsByTypeId(id));
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("types", types);
        model.addAttribute("activeTypeId", id);
        return "types";

//        return pageInfo.toString()+" <<==>> " + types;
    }



}
