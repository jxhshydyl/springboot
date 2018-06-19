package com.lwp.springboot.controller;

import com.lwp.springboot.dto.User;
import com.lwp.springboot.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TestController {
    @Autowired
    TestService testService;

    @Value("${com.lwp.test}")
    private String test;

    @RequestMapping("/getString")
    @ResponseBody
    public String getString(){
        System.out.println(test);
        return "husj";
    }

    @RequestMapping("/addUser")
    public String addUser(User user){
        System.out.println(user);
        testService.addUser(user);
        return "test";
    }
}
