package com.carpart.service;

import com.carpart.entity.User;

import java.util.List;

/**
 * Created by Administrator on 2017/3/20.
 */
public interface UserService {

    List<User> findAll();

    User findOne(Long id);

    void delete(User user);

    User add(User user);

    void pwdInit(String userName,String password);

    User checkUserExits(User user);
}
