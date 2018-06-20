package com.lwp.springboot.service;


import com.lwp.springboot.dao.TestDao;
import com.lwp.springboot.dto.User;
import com.lwp.springboot.util.SerializeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class TestService {
    @Autowired
    TestDao testDao;
    @Autowired
    RedisTemplate redisTemplate;

    @Transactional
    public int addUser(User user){
        int i = testDao.insert(user);
        redisTemplate.opsForHash().put("qw","qw","12");
        this.redisTemplate.expire("qw",60, TimeUnit.SECONDS);
        boolean ishas = redisTemplate.hasKey("qw");
        String str=(String)redisTemplate.opsForHash().get("qw","qw");
        System.out.println("取出的字符串为："+str);
        redisTemplate.opsForValue().set("sd",1L);
        RedisAtomicLong redisAtomicLong=new RedisAtomicLong("sd", redisTemplate.getConnectionFactory());
        //Long num =  redisAtomicLong.getAndIncrement();
        long l = redisAtomicLong.get();
        Long strs = (Long)redisTemplate.opsForValue().get("sd");
        System.out.println(l);
        //System.out.println(num);
        System.out.println(ishas);
        int i1 = testDao.insert(user);
        System.out.println(i+i1);
        return i;
    }

    public int test(User user){
        redisTemplate.opsForHash().put("12","12",SerializeUtil.serialize(user));
        byte[] bytes=(byte[])redisTemplate.opsForHash().get("12","12");
        User u = (User)SerializeUtil.unserialize(bytes);
        System.out.println(u);
        return 0;
    }

    public int test1(List<User> list){
        redisTemplate.opsForHash().put("22","22",list);
        List<User> lists=(List<User>)redisTemplate.opsForHash().get("22","22");
        System.out.println(lists);
        System.out.println(lists.get(0).getName());
        return 0;
    }
}
