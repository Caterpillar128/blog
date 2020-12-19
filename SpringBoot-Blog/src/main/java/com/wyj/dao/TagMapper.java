package com.wyj.dao;

import com.wyj.pojo.Tag;
import com.wyj.pojo.Type;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TagMapper {
    int saveTag(Tag tag);
    Tag getTagById(Long id);
    List<Tag> listTag();
    int updateTag(Tag tag);
    int deleteTagById(Long id);
    Tag getTagByName(String name);

    List<Tag> queryTagBefore(Integer length);
    // 前端展示标签
    List<Tag> getTagToBlog();
}
