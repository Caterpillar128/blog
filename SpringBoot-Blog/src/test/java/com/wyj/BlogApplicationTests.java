package com.wyj;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.wyj.dao.UserMapper;
import com.wyj.pojo.Blog;
import com.wyj.pojo.Type;
import com.wyj.pojo.User;
import com.wyj.service.blog.BlogService;
import com.wyj.service.type.TypeService;
import com.wyj.service.type.TypeServiceImpl;
import com.wyj.service.user.UserService;
import com.wyj.service.user.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
class BlogApplicationTests {

//    @Autowired
//    private UserService userService;
//    @Test
//    void contextLoads() {
//
////        User user = userService.checkUser("root", "123");
////        List<User> userList = userService.queryUsers();
//        List<User> userList = userService.queryUsers();
//        for (User user : userList) {
//            System.out.println("User --> " + user);
//        }
////        System.out.println("User --> " + user);
//
//    }

//    @Autowired
//    private TypeService typeService;
//    @Test
//    public void testType(){
//        Type type = new Type();
//        type.setName("SpringMVC");
//        System.out.println("save --> "+typeService.saveType(type));
//        System.out.println("get --> "+typeService.getTypeById(2L));
//        List<Type> types = typeService.listType();
//        System.out.println("=============List==============");
//        for (Type type1 : types) {
//            System.out.println(type1);
//        }
//        System.out.println("===========================");
//        Type type2 = new Type();
//        type2.setId(59);
//        type2.setName("Dome");
//        System.out.println("update --> "+typeService.updateTypeById(type2));
//        System.out.println(typeService.deleteTypeById(61L));
//    }


//    @Autowired
//    private TypeService typeService;
//    @Test
//    public void pageTypeTest(){
//        //pageNum:表示第几页  pageSize:表示一页展示的数据
//        PageHelper.startPage(2, 5);
//        List<Type> types = typeService.listType();
//        //将查询到的数据封装到PageInfo对象
//        PageInfo<Type> pageInfo=new PageInfo(types);
////        System.out.println("nums --> " + pageInfo.getNavigatepageNums().length);
//        //分割数据成功
////        System.out.println("======PageInfo========");
////        System.out.println(pageInfo.toString());
////        System.out.println("==============");
//        List<Type> list = pageInfo.getList();
//        System.out.println("======List========");
//        for (Type type : list) {
//            System.out.println(type);
//        }
//        System.out.println("==============");
//    }
//    /*
//    PageInfo{pageNum=1, pageSize=3, size=3, startRow=1, endRow=3, total=8, pages=3,
//        list=Page{
//            count=true, pageNum=1, pageSize=3, startRow=0, endRow=3, total=8,
//            pages=3, reasonable=true, pageSizeZero=false}[
//                Type{id=1, name='java基础'},
//                Type{id=2, name='mysql数据库'},
//                Type{id=3, name='javaWeb'}],
//            prePage=0, nextPage=2, isFirstPage=true, isLastPage=false,
//            hasPreviousPage=false, hasNextPage=true, navigatePages=8,
//            navigateFirstPage=1, navigateLastPage=3, navigatepageNums=[1, 2, 3]}
//     */

//    @Autowired
//    private BlogService blogService;
//    @Test
//    public void blogPage(){
//        PageHelper.startPage(1,5);
//        PageInfo<Blog> pageInfo = new PageInfo<>(blogService.listBlogs());
//        System.out.println("===============List==============");
//        List<Blog> list = pageInfo.getList();
//        for (Blog blog : list) {
//            System.out.println(blog);
//        }
//        System.out.println("=============================");
//    }
//    @Test
//    public void blogTest(){
//        Blog blog = new Blog();
//        blog.setTitle("Three");
//        blog.setContent("30");
//        blog.setFirstPicture("no");
//        blog.setFlag("good");
//        blog.setViews(30);
//        System.out.println("Blog Save ===> " + blogService.saveBlog(blog));
//
//        System.out.println("===============List==============");
//        List<Blog> blogs = blogService.listBlogs();
//        for (Blog blog1 : blogs) {
//            System.out.println("Blog list ===> " + blog1);
//        }
//        System.out.println("=============================");
//
//        Blog blogU = new Blog();
//        blogU.setId(62L);
//        blogU.setTitle("OneUpdate");
//        blogService.updateBlog(blogU);
//        System.out.println("Blog Id ==> " + blogService.getBlogById(62L));
//
//        System.out.println("Blog Delete ==> " + blogService.deleteBlogById(62L));
//
//        System.out.println("Blog Id ==> " + blogService.getBlogById(62L));
//
//    }

//    public static void main(String[] args) {
////        Date date = new Date();
////        System.out.println(date);
////        System.out.println(date.getTime());
//    }

    @Autowired
    BlogService blogService;
    @Test
    public void testBlog(){
//        System.out.println("***");

        int i = blogService.saveBlogToTagId(86L, 12L);

    }


}
