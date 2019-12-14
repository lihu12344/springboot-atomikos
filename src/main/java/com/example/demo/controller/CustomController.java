package com.example.demo.controller;

import com.example.demo.dao.person.PersonMapper;
import com.example.demo.dao.user.UserMapper;
import com.example.demo.pojo.Person;
import com.example.demo.pojo.User;
import com.example.demo.service.CustomService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class CustomController {

    @Resource
    private CustomService customService;

    @Resource
    private UserMapper userMapper;

    @Resource
    private PersonMapper personMapper;

    @RequestMapping("/update")
    public String hello(@RequestParam("id") Integer id,int num){
        System.out.println("更新前的数据为：");
        User user=userMapper.selectById(id);
        Person person=personMapper.selectById(id);
        System.out.println(user);
        System.out.println(person);

        try{
            customService.update(user,person,num);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        System.out.println("更新后的数据为：");
        System.out.println(userMapper.selectById(id));
        System.out.println(personMapper.selectById(id));

        return "success";
    }

}