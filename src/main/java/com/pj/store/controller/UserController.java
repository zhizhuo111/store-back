package com.pj.store.controller;

import com.pj.store.model.User;
import com.pj.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("login")
    @ResponseBody
    public String login(String userName, String password) {
        String result = "";
        List<User> users = userService.getAll();
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



