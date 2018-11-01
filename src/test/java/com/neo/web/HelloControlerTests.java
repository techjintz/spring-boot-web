package com.neo.web;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockserver.client.MockServerClient;
import org.mockserver.junit.MockServerRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

import com.neo.util.NeoProperties;

//@WebMvcTest(controllers = HelloController.class)可自动注入mockMvc,但之注入HelloController,其依赖需要自己mock
@RunWith(SpringRunner.class)
@SpringBootTest
// @SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)跟TestRestTemplate配合使用，跟mockMvc一样,完整的web环境
public class HelloControlerTests {

	@Autowired
	private WebApplicationContext context;

	private MockMvc mvc;

	@Autowired
	private NeoProperties neoProperties;

	@Rule
	public MockServerRule server = new MockServerRule(this, 5000);

	private MockServerClient mockClient;

	// @Autowired
	// private TestRestTemplate testRestTemplate;

	@Before
	public void setUp() {
		// mvc = MockMvcBuilders.standaloneSetup(newelloController()).build();仅注入HelloController,其依赖需要自己mock
		mvc = MockMvcBuilders.webAppContextSetup(context).build();//完整的web环境
	}

	@Test
	public void getHello() throws Exception {
		mockClient = new MockServerClient("localhost", 5000);
		String expected = "{ message: 'incorrect username and password combination'}";
		mockClient.when(request().withPath("/").withMethod("GET")
		// .withHeader(new Header(HttpHeaders.ACCEPT, MediaType.TEXT_PLAIN))
		// .withQueryStringParameter(new Parameter("my-token", "12345"))
		).respond(response().withStatusCode(200).withBody(expected));
		neoProperties.setUrl("http://localhost:5000");
		mvc.perform(MockMvcRequestBuilders.get("/hello").content("").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.code").value("hello world"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.result").isNotEmpty()).andDo(MockMvcResultHandlers.print())
				.andReturn();
		mockClient.close();
	}

	@Test
	public void testHello() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/uid").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().string(Matchers.notNullValue()));
	}

}