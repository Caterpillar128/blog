package com.wyj.controller.admin;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wyj.pojo.Type;
import com.wyj.service.type.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/types")
    public PageInfo<Type> types(@RequestParam(defaultValue = "1",value = "pageNum")int pageNum){
//        System.out.println("TypeController pageNum ===> " + pageNum);
        PageHelper.startPage(pageNum, 5);
        PageInfo<Type> pageInfo=new PageInfo(typeService.listType());
        return pageInfo;
    }

    @GetMapping("/types/addType")
    public String toAddType(){
        return "admin/types-input";
    }

    @PostMapping("/types")
    public String addType(Type type, RedirectAttributes attributes){
        Type typeByName = typeService.getTypeByName(type.getName());
        if (typeByName != null){
           attributes.addFlashAttribute("nameError", typeByName.getName() + " : 分类名称已经添加");
            return "redirect:types/addType";
        }
        int count = typeService.saveType(type);
        if (count > 0 ){
            //
            attributes.addFlashAttribute("typeMsg", "添加操作成功");
        }else {
            //
            attributes.addFlashAttribute("typeMsg", "添加操作失败");
        }
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/toUpdate")
    public String toUpdateType(@PathVariable("id") Long id, Model model){
        Type typeById = typeService.getTypeById(id);
        model.addAttribute("type", typeById);
        return "admin/types-input";
    }

    @PostMapping("/types/update")
    public String updateType(Type type, RedirectAttributes attributes){
//        System.out.println("Type Update ==> " + type.toString());
        Type typeByName = typeService.getTypeByName(type.getName());
        if (typeByName != null){
            attributes.addFlashAttribute("nameError", typeByName.getName() + " : 该分类名称已添加");
            attributes.addFlashAttribute("type", type);
            return "redirect:addType";
        }
        int count = typeService.updateType(type);
        if (count > 0 ){
            //
            attributes.addFlashAttribute("typeMsg", "修改操作成功");
        }else {
            //
            attributes.addFlashAttribute("typeMsg", "修改操作失败");
        }
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/delete")
    public String deleteType(@PathVariable("id") Long id, RedirectAttributes attributes){
        int deleteTypeById = typeService.deleteTypeById(id);
        if (deleteTypeById > 0){
            //
            attributes.addFlashAttribute("typeMsg", "删除操作成功");
        }else {
            //
            attributes.addFlashAttribute("typeMsg", "删除操作失败");
        }
        return "redirect:/admin/types";
    }


}
