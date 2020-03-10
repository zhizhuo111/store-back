package com.pj.store.controller;

import com.pj.store.model.User;
import com.pj.store.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserServiceImpl userServiceImpl;

    @RequestMapping("login")
    @ResponseBody
    public String login(String userName, String password) {
        String result = "";
        List<User> users = userServiceImpl.getAll();
        System.out.println(users);
        System.out.println(userName);
        System.out.println(password);
        for (int i=0; i<users.size(); i++){
            System.out.println(userName.equals(users.get(i).getUSER_USERNAME()));
            System.out.println(password.equals(users.get(i).getUSER_PASSWORD()));
            if (userName.equals(users.get(i).getUSER_USERNAME()) && password.equals(users.get(i).getUSER_PASSWORD())){
                result =  users.get(i).getUSER_USERNAME();
                break;
            }
        }
        System.out.println(result);
        return result;
    }

}



