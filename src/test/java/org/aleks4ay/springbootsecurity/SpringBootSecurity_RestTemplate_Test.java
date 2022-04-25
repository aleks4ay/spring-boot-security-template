package org.aleks4ay.springbootsecurity;

import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringBootSecurity_RestTemplate_Test {

   	@LocalServerPort
	private int port;

	RestTemplate restTemplate = new RestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @BeforeEach
    public void before() {
        headers.add("Authorization", createHttpAuthenticationHeaderValue(
                "user", "user"));
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    }

    @Test
    public void shouldSucceedWith200_WhenPublicUrl() {
        assertThat(this.restTemplate.getForObject(createURLWithPort("/"), String.class)).contains("Home page.");
    }


    @Test
    public void shouldSucceedWith200_WhenAuthenticatedUrl() {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/auth"),
                HttpMethod.GET, entity, String.class);

        String expected = "Authenticated Page. Name = ";
        assertThat(response.getBody().contains(expected));
    }

    @Test
    public void shouldSucceedWith200_WhenAdminUrl() {
        HttpHeaders adminHeaders = new HttpHeaders();
        adminHeaders.add("Authorization", createHttpAuthenticationHeaderValue(
                "adm", "adm"));
        adminHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(null, adminHeaders);

        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/admin"),
                HttpMethod.GET, entity, String.class);

        String expected = "Admin Page.";
        assertTrue(response.getBody().contains(expected));
    }

    private String createURLWithPort(String uri) {
		return "http://localhost:" + port + "/security" + uri;
	}

    private String createHttpAuthenticationHeaderValue(String userId, String password) {
        String auth = userId + ":" + password;
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(StandardCharsets.US_ASCII) );
        return  "Basic " + new String(encodedAuth);
    }
}
