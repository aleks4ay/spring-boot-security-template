package org.aleks4ay.springbootsecurity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringBootSecurity_Mock_Test {

	@Autowired
	private MockMvc mockMvc;


	@WithMockUser(username = "user")
	@Test
	public void givenAuthRequest_shouldSucceedWith200() throws Exception {
		mockMvc.perform(get("/auth")).andExpect(status().isOk());
	}

	@WithMockUser(value = "admin", roles = {"USER","ADMIN"})
	@Test
	public void givenAuthRequestOnPrivateService_shouldSucceedWith200() throws Exception {
		mockMvc.perform(get("/admin")).andExpect(status().isOk());
	}


	@Test
	public void shouldSucceedWith200_WhenPublicUrl() throws Exception {
		mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Home page.")));
	}

	@Test
	public void shouldSucceedWith200_WhenAuthenticatedUrl() throws Exception {
		mockMvc.perform(get("/auth")
				.with(user("adm").password("adm").roles("USER","ADMIN")))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("Authenticated Page.")));
	}

	@Test
	@WithMockUser(username = "user3", password = "$2a$12$sJYXgNNJqbDViGgICheAr.041czjxFACtUPJoP5JSj7DKmAqlfgIK", roles = {"USER","ADMIN"})
	public void shouldSucceedWith200_WhenAdminUrl() throws Exception {
		mockMvc.perform(get("/admin")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Admin Page.")));
	}
}

