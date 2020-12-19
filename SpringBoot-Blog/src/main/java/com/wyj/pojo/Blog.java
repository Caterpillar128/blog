package com.wyj.pojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Blog {

    private Long id;
    private String title;   // 博客标题
    private String content; // 博客内容
    private String firstPicture;    // 博客首图
    private String flag;    // 博客标记（原创...）
    private Integer views;  // 博客浏览次数
    private boolean appreciation;   // 博客赞赏是否开启
    private boolean shareStatement; // 博客版权是否开启
    private boolean commentabled;   // 博客评论是否开启
    private boolean published;  // 博客发布状态
    private boolean recommend;  // 博客推荐是否开启
    private Date createTime;    // 创建时间
    private Date updateTime;    // 更新时间
    private String description;
    private Long typeId;
    private Long userId;
    private String tagIds;  // 标签集合

    // 多对一：多个博客对应一个xxx
    private Type type;
    private User user;

    // 多对多：多个博客对应多个 Tag
    private List<Tag> tags = new ArrayList<>();
    // 一对多：一个博客对应多个 Comment
    private List<Comment> comments = new ArrayList<>();

//    public void init(){
//        this.tagIds = tagsToIds(this.getTags());
//    }
//
//    public String tagsToIds(List<Tag> tags){
//        if(!tags.isEmpty()){
//            StringBuffer ids = new StringBuffer();
//            boolean flag = false;
//            for (Tag tag : tags) {
//                if (flag){
//                    ids.append(',');
//                }else {
//                    flag = true;
//                }
//                ids.append(tag.getId());
//            }
//            return ids.toString();
//        }else {
//            return tagIds;
//        }
//    }


}
