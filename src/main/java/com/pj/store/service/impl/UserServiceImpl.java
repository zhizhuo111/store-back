package com.pj.store.service.impl;

import com.pj.store.dao.UserDao;
import com.pj.store.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl {
    @Autowired
    UserDao userDao;
    public List<User> getAll(){
        return userDao.getAll();
    }

    public boolean insert(String userName, String password){
        if(!userName.equals("") && !password.equals("")){
            userDao.insert(userName, password);
            return true;
        }
        return false;
    }
}
