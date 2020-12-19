package com.wyj.service.user;

import com.wyj.dao.UserMapper;
import com.wyj.pojo.User;
import com.wyj.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;


    @Override
    public User checkUser(String username, String password) {
        User user = userMapper.checkUser(username, MD5Utils.encrypt(password));
        return user;
    }

    @Override
    public List<User> queryUsers() {
        List<User> userList = userMapper.queryUsers();
        return userList;
    }
}
