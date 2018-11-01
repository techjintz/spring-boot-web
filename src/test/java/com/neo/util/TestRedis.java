package com.neo.util;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import com.neo.domain.User;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRedis {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private RedisTemplate redisTemplate;

	@Test
	public void test() throws Exception {
		stringRedisTemplate.opsForValue().set("aaa", "111");
		Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));
//		stringRedisTemplate.delete("aaa");
	}

	@Test
	public void testObj() throws Exception {
		User user = new User("aa@126.com", "aa", "aa123456", "aa", "123");
		ValueOperations<String, User> operations = redisTemplate.opsForValue();
		operations.set("com.neo.f", user, 1, TimeUnit.SECONDS);
		Assert.assertEquals("aa", operations.get("com.neo.f").getUserName());
		redisTemplate.delete("com.neo.f");
	}

}