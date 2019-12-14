package com.example.demo.serviceImpl;

import com.example.demo.dao.person.PersonMapper;
import com.example.demo.dao.user.UserMapper;
import com.example.demo.pojo.Person;
import com.example.demo.pojo.User;
import com.example.demo.service.CustomService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class CustomServiceImpl implements CustomService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private PersonMapper personMapper;

    @Override
    @Transactional
    public void update(User user,Person person,Integer i) {
        user.setAge(6);
        userMapper.updateById(user);

        person.setAge(6);
        personMapper.updateById(person);

        if (i.equals(0)){
            throw new RuntimeException("出错了");
        };
    }
}
