package com.neo.web;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.neo.util.NeoProperties;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProPertiesTest {

	@Autowired
	private NeoProperties neoProperties;

	@Test
	public void getHello() throws Exception {
		neoProperties.setTitle("k");
		Assert.assertEquals(neoProperties.getTitle(), "k");
		neoProperties.setDescription("v");
		Assert.assertEquals(neoProperties.getDescription(), "v");
	}

}