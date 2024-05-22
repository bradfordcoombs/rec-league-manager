package dev.bc.sas.web;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlTemplate;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import dev.bc.sas.ApplicationConfig;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { ApplicationConfig.class })
@WebAppConfiguration
public class TeamControllerTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;


	@BeforeEach
	public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext)
				.apply(SecurityMockMvcConfigurers.springSecurity()).build();
	}

	@Test
	@WithMockUser(username = "director@test.com", authorities = { "DIRECTOR" })
	void loggedInUserReturnsView() throws Exception {
		this.mockMvc.perform(get("/teams").with(csrf())).andDo(print()).andExpect(view().name("teams"));
	}

	@Test
	@WithMockUser(username = "player@test.com", authorities = { "PLAYER" })
	void playerDeniedAccess() throws Exception {
		this.mockMvc.perform(post("/teams").with(csrf())).andExpect(status().is(403));
	}

	@Test
	@WithMockUser(username = "director@test.com", authorities = { "DIRECTOR" })
	void directorSuccessfulCreate() throws Exception {
		this.mockMvc.perform(
				post("/teams").contentType(MediaType.APPLICATION_FORM_URLENCODED).formField("name", "Test Team")
						.with(csrf()))
				.andDo(print()).andExpect(redirectedUrlTemplate("/teams"));
	}

	@Test
	@WithMockUser(username = "director@test.com", authorities = { "DIRECTOR" })
	void directorFailedValidationCreate() throws Exception {
		this.mockMvc
				.perform(post("/teams").contentType(MediaType.APPLICATION_FORM_URLENCODED)
						.formField("name", "").with(csrf()))
				.andDo(print()).andExpect(redirectedUrlTemplate("/teams"));
	}

	@Test
	void unauthenticatedUserRedirected() throws Exception {
		this.mockMvc.perform(get("/teams").with(csrf())).andExpect(status().is3xxRedirection());
	}

}
