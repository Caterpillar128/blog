package com.wyj.service.type;

import com.github.pagehelper.Page;
import com.wyj.dao.TypeMapper;
import com.wyj.pojo.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeMapper typeMapper;

    @Override
    public int saveType(Type type) {
        return typeMapper.saveType(type);
    }

    @Override
    public Type getTypeById(Long id) {
        return typeMapper.getTypeById(id);
    }

    @Override
    public List<Type> listType() {
        return typeMapper.listType();
    }

    @Override
    public Page<Type> pageListType() {
        return (Page<Type>) typeMapper.listType();
    }

    @Override
    public int updateType(Type type) {
        return typeMapper.updateType(type);
    }

    @Override
    public int deleteTypeById(Long id) {
        return typeMapper.deleteTypeById(id);
    }

    @Override
    public Type getTypeByName(String name) {
        return typeMapper.getTypeByName(name);
    }

    @Override
    public List<Type> queryTypeBefore(Integer length) {
        return typeMapper.queryTypeBefore(length);
    }

    @Override
    public List<Type> getTypeToBlog() {
        return typeMapper.getTypeToBlog();
    }


}
