package com.wyj.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wyj.pojo.Tag;
import com.wyj.service.tag.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagService;

    // 后台标签列表展示
    @GetMapping("/tags")
    public PageInfo<Tag> tags(@RequestParam(defaultValue = "1",value = "pageNum")int pageNum){
        PageHelper.startPage(pageNum, 5);
        PageInfo<Tag> pageInfo=new PageInfo(tagService.listTag());
        return pageInfo;
    }

    // 后台新增标签界面
    @GetMapping("/tags/addTag")
    public String toAddTag(Model model){
        model.addAttribute("tag", new Tag());
        return "admin/tags-input";
    }

    // 新增标签，并展示标签列表
    @PostMapping("/tags")
    public String addTag(Tag tag, RedirectAttributes attributes){
        Tag tagByName = tagService.getTagByName(tag.getName());
        if (tagByName != null){
           attributes.addFlashAttribute("nameError", tagByName.getName() + " : 分类名称已经添加");
            return "redirect:tags/addTag";
        }
        int count = tagService.saveTag(tag);
        if (count > 0 ){
            //
            attributes.addFlashAttribute("tagMsg", "添加操作成功");
        }else {
            //
            attributes.addFlashAttribute("tagMsg", "添加操作失败");
        }
        return "redirect:/admin/tags";
    }

    // 后台标签修改界面
    @GetMapping("/tags/{id}/toUpdate")
    public String toUpdateTag(@PathVariable("id") Long id, Model model){
        Tag tagById = tagService.getTagById(id);
        model.addAttribute("tag", tagById);
        return "admin/tags-input";
    }

    // 修改标签，并展示标签列表
    @PostMapping("/tags/update")
    public String updateTag(Tag tag, RedirectAttributes attributes){
        Tag tagByName = tagService.getTagByName(tag.getName());
        if (tagByName != null){
            attributes.addFlashAttribute("nameError", tagByName.getName() + " : 该分类名称已添加");
            attributes.addFlashAttribute("tag", tag);
            return "redirect:addTag";
        }
        int count = tagService.updateTag(tag);
        if (count > 0 ){
            //
            attributes.addFlashAttribute("tagMsg", "修改操作成功");
        }else {
            //
            attributes.addFlashAttribute("tagMsg", "修改操作失败");
        }
        return "redirect:/admin/tags";
    }


    // 后台标签删除
    @GetMapping("/tags/{id}/delete")
    public String deleteTag(@PathVariable("id") Long id, RedirectAttributes attributes){
        int deleteTagById = tagService.deleteTagById(id);
        if (deleteTagById > 0){
            //
            attributes.addFlashAttribute("tagMsg", "删除操作成功");
        }else {
            //
            attributes.addFlashAttribute("tagMsg", "删除操作失败");
        }
        return "redirect:/admin/tags";
    }


}
