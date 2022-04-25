package org.aleks4ay.springbootsecurity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookExample_TestRestTemplate_Test {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void toDoTest() {
		assertThat(restTemplate.getForObject("/", String.class)).contains("Home page.");
	}
}
