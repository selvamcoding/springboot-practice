package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl() { return "http://localhost:" + port; }

	@Test
	void contextLoads() {
	}

	@Test
	public void getAllTopics() {
		HttpHeaders httpHeaders = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, httpHeaders);

		ResponseEntity<String> response = testRestTemplate.exchange(getRootUrl() + "/v2/topics",
				HttpMethod.GET, entity, String.class);

		assertNotNull(response.getBody());
	}

}
