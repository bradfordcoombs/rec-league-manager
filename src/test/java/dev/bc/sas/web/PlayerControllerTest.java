package dev.bc.sas.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlConfig.ErrorMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import dev.bc.sas.ApplicationConfig;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { ApplicationConfig.class })
@WebAppConfiguration
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql(scripts = "classpath:/data/data.sql", executionPhase = ExecutionPhase.BEFORE_TEST_CLASS, config = @SqlConfig(errorMode = ErrorMode.CONTINUE_ON_ERROR))
public class PlayerControllerTest {

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
	void retrievePlayersView() throws Exception {
		this.mockMvc.perform(get("/players")).andDo(print()).andExpect(view().name("players"));
	}

	@Test
	@WithMockUser(username = "director@test.com", authorities = { "DIRECTOR" })
	void retrievePlayerViewForDirector() throws Exception {
		this.mockMvc.perform(get("/players/{playerId}", "1")).andDo(print()).andExpect(view().name("player"));
	}

	@Test
	@WithMockUser(username = "lamelo@hornets.com", authorities = { "PLAYER" })
	void retrievePlayerViewForSelf() throws Exception {
		this.mockMvc.perform(get("/players/{playerId}", "1")).andDo(print()).andExpect(view().name("player"));
	}

	@Test
	@WithMockUser(username = "player@test.com", authorities = { "PLAYER" })
	void retrievePlayerViewForOther() throws Exception {
		this.mockMvc.perform(get("/players/{playerId}", "1")).andDo(print()).andExpect(status().is4xxClientError());
	}
}
