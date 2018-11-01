package com.neo.web;

import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.neo.util.NeoProperties;

@RestController
public class HelloController {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private NeoProperties neoProperties;

	@RequestMapping("/hello")
	public String hello() {
		String result = restTemplate.getForObject(neoProperties.getUrl(), String.class);
		return "{\"code\":\"hello world\",\"result\":" + result + "}";
	}

	@RequestMapping("/uid")
	String uid(HttpSession session) {
		UUID uid = (UUID) session.getAttribute("uid");
		if (uid == null) {
			uid = UUID.randomUUID();
		}
		session.setAttribute("uid", uid);
		return session.getId();
	}

}