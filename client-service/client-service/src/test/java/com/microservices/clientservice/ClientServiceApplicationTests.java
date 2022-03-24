package com.microservices.clientservice;

import com.microservices.clientservice.dto.ClientDTO;
import com.microservices.clientservice.entity.Client;
import com.microservices.clientservice.repository.ClientRepository;
import com.microservices.clientservice.service.ClientService;
import com.microservices.clientservice.utils.exceptions.ClientUnprocessableEntity;
import com.microservices.clientservice.validator.ClientValidator;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class ClientServiceApplicationTests {

	@Autowired
	private ClientService service;

	@MockBean
	private ClientRepository repository;

	@Autowired
	private ClientValidator validator;

	@Test
	void getAllClientsTest() {
		when(repository.findAll()).thenReturn(Stream
				.of(new Client("ABC", "Alan", "Castro", "alan@gmail.net", "pass123", 24, 'M'),
					new Client("DEF", "Luis", "Martinez", "luis@gmail.net", "pass123", 23, 'M'))
				.collect(Collectors.toList()));
		assertEquals(2, service.getAllClients().size());
	}
	@Test
	void getClientByIdTest() {
		String userId = "ABC";
		Optional<Client> client = Optional.of(new Client("ABC",
				"Alan",
				"Castro",
				"alan@gmail.net",
				"pass123",
				24,
				'M'));
		when(repository.findClientById(userId)).thenReturn(client);
		assertEquals(userId, service.getClientById(userId).getId());
	}

	@Test
	void saveNewClientTest() {
		Client fakeClient = new Client("ABC",
				"Alan",
				"Castro",
				"alan@gmail.com",
				"pass123",
				24,
				'M');
		ClientDTO fakeClientDTO = new ClientDTO("ABC",
				"Alan",
				"Castro",
				"alan@gmail.com",
				24,
				'M');
		when(repository.save(fakeClient)).thenReturn(fakeClient);
		assertEquals(fakeClientDTO, service.createClient(fakeClient));
	}

	@Test
	void saveNewClientExceptionTest() {
		Client fakeClient = new Client("ABC", "A", "A", "A", "A", -10, 'Q');
		assertThrows(ClientUnprocessableEntity.class, ()-> validator.validateClient(fakeClient));
	}


}
