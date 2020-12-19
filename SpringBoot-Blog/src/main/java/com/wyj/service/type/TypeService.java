package com.wyj.service.type;


import com.github.pagehelper.Page;
import com.wyj.pojo.Type;

import java.awt.print.Pageable;
import java.util.List;

public interface TypeService {

    int saveType(Type type);
    Type getTypeById(Long id);
    List<Type> listType();
    Page<Type> pageListType();
    int updateType(Type type);
    int deleteTypeById(Long id);

    Type getTypeByName(String name);

    List<Type> queryTypeBefore(Integer length);
    List<Type> getTypeToBlog();
}

