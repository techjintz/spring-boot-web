package com.neo.domain;

import java.text.DateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserRepositoryTests {

	@Autowired
	private UserRepository userRepository;

	@Test
	public void test() throws Exception {
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
		String formattedDate = dateFormat.format(date);

		userRepository.save(new User("aa1", "aa@126.com", "aa", "aa123456", formattedDate));
		userRepository.save(new User("bb2", "bb@126.com", "bb", "bb123456", formattedDate));

		Assert.assertEquals("aa1", userRepository.findByUserName("aa123456").getEmail());
		Assert.assertEquals("bb123456", userRepository.findByUserNameOrEmail("bb123456", "bb").getUserName());
	}

}