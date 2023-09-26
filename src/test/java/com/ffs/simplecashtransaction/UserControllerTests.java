package com.ffs.simplecashtransaction;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ffs.simplecashtransaction.controllers.UserController;
import com.ffs.simplecashtransaction.domain.user.UserType;
import com.ffs.simplecashtransaction.dtos.UserRequestDTO;
import com.ffs.simplecashtransaction.services.UserService;

@WebMvcTest(UserController.class)
public class UserControllerTests {
	
	private final static String ENDPOINT = "/api/v1/users";
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private UserService userService;
	
	@Test
	public void testAddUserShoudReturn201() throws Exception {
		UserRequestDTO newUser = new UserRequestDTO("First Name", "Last Name", "1234561", new BigDecimal(200), "email", "321654", UserType.PERSONAL);
		
		String requestBody = objectMapper.writeValueAsString(newUser);
		
		mockMvc.perform(post(ENDPOINT)
							.contentType(MediaType.APPLICATION_JSON)
							.content(requestBody))
							.andExpect(status().is2xxSuccessful());
	}

}
