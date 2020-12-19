package com.wyj.service.tag;

import com.wyj.pojo.Tag;
import com.wyj.pojo.Type;

import java.util.List;

public interface TagService {

    int saveTag(Tag tag);
    Tag getTagById(Long id);
    List<Tag> listTag();
    int updateTag(Tag tag);
    int deleteTagById(Long id);
    Tag getTagByName(String name);

    List<Tag> queryTagBefore(Integer length);
    List<Tag> getTagToBlog();

}

