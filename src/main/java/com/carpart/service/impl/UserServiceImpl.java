package com.carpart.service.impl;

import com.carpart.dao.UserRepository;
import com.carpart.entity.User;
import com.carpart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/3/21.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findOne(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public User add(User user) {
        return userRepository.save(user);
    }

    @Override
    public void pwdInit(String userName, String password) {
        List<User> users = userRepository.findByUserName(userName);
        if(users != null && users.size() > 0){
            User user = users.get(0);
            user.setPassword(password);
            userRepository.save(user);
        }
    }

    @Override
    public User checkUserExits(User user) {
        List<User> users = userRepository.findByUserNameAndPassword(user.getUserName(),user.getPassword());
        if(users != null && users.size() > 0){
            user = users.get(0);
            return user;
        }
        return null;
    }
}
