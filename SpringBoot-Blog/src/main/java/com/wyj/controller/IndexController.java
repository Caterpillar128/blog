package com.wyj.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wyj.MyNotFoundException;
import com.wyj.pojo.Blog;
import com.wyj.pojo.Type;
import com.wyj.service.blog.BlogService;
import com.wyj.service.tag.TagService;
import com.wyj.service.type.TypeService;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @RequestMapping({"/", "/index"})
//    @ResponseBody
    public String index(@RequestParam(required = false,defaultValue = "1",value = "pageNum")int pageNum, Model model){
        PageHelper.startPage(pageNum, 8);
        PageInfo<Blog> pageInfo=new PageInfo(blogService.receptionListBlogs());
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("types", typeService.queryTypeBefore(10));
        model.addAttribute("tags", tagService.queryTagBefore(10));
//        /*根据观看次数来推荐的博客*/
        model.addAttribute("viewsBlogs", blogService.topViews());

//        List<Type> types = typeService.queryTypeBefore(10);
//        return types.toString();
        return "index";
    }



    @PostMapping("/search")
    public String search(@RequestParam(required = false,defaultValue = "1",value = "pageNum")int pageNum, @RequestParam String query, Model model){
        PageHelper.startPage(pageNum, 8);
        PageInfo<Blog> pageInfo=new PageInfo(blogService.queryListBlogs(query));
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("query", query);
        return "search";
    }

}
