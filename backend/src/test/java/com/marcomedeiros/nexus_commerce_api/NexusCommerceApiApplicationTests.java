package com.marcomedeiros.nexus_commerce_api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.marcomedeiros.nexus_commerce_api.dto.access.users.ProfessionalUserResponseDTO;
import com.marcomedeiros.nexus_commerce_api.dto.access.users.UserResponseDTO;
import com.marcomedeiros.nexus_commerce_api.service.access.users.ProfessionalUserService;
import com.marcomedeiros.nexus_commerce_api.service.access.users.UserService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class NexusCommerceApiApplicationTests {

	@Autowired
	private ProfessionalUserService professionalUserService;

	@Autowired
	private UserService userService;

	@Test
	void contextLoads() {
	}

	@Test
	void findProfessionalUserByCpf() {
		ProfessionalUserResponseDTO response = professionalUserService.findByDocument("528.143.697-09");
		assertNotNull(response);
		assertEquals("Carlos Vendedor", response.name());
	}

	@Test
	void findProfessionalUserByCnpjFormatted() {
		ProfessionalUserResponseDTO response = professionalUserService.findByDocument("45.678.901/0001-75");
		assertNotNull(response);
		assertEquals("Tech Solutions LTDA", response.name());
	}

	@Test
	void findProfessionalUserByCnpjUnmasked() {
		ProfessionalUserResponseDTO response = professionalUserService.findByDocument("45678901000175");
		assertNotNull(response);
		assertEquals("Tech Solutions LTDA", response.name());
	}

	@Test
	void findUserByCpf() {
		UserResponseDTO response = userService.findByDocument("142.153.316-25");
		assertNotNull(response);
		assertEquals("Majin Kodak", response.name());
	}

}
