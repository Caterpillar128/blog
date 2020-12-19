package com.wyj.dao;

import com.wyj.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    public User checkUser(String username, String password);
    public List<User> queryUsers();
}
