package com.pj.store.controller;

import com.pj.store.model.User;
import com.pj.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @RequestMapping("/xxx")
    public List<User> index(){
        ModelAndView mav = new ModelAndView("index");
        List<User> list = userService.getAll();
        //mav.addObject("list",list);
        return list;
    }
}
