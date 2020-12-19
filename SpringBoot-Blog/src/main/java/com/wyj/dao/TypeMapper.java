package com.wyj.dao;

import com.wyj.pojo.Type;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TypeMapper {
    int saveType(Type type);
    int updateType(Type type);
    int deleteTypeById(Long id);

    Type getTypeById(Long id);
    Type getTypeByName(String name);
    // 后台展示分类列表
    List<Type> listType();

    // 查询分类使用次数 前多少个数据
    List<Type> queryTypeBefore(Integer length);

    // 前端展示分类
    List<Type> getTypeToBlog();
}
