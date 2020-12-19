package com.wyj.service.user;

import com.wyj.pojo.User;

import java.util.List;

public interface UserService {
    public User checkUser(String username, String password);
    public List<User> queryUsers();
}
