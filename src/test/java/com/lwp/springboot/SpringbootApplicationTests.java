package com.lwp.springboot;

import com.lwp.springboot.dao.TestDao;
import com.lwp.springboot.dto.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootApplicationTests {
	@Autowired
	TestDao testDao;

	@Test
	public void contextLoads() {
		User user=new User();
		user.setName("lwp");
		user.setPassword("12385");
		int insert = testDao.insert(user);
		System.out.println(insert);
	}

}
