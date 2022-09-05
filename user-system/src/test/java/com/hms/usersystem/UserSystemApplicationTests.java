package com.hms.usersystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.hms.usersystem.models.Manager;
import com.hms.usersystem.repositories.ManagerRepository;
import com.hms.usersystem.repositories.OwnerRepository;
import com.hms.usersystem.repositories.ReceptionistRepository;
import com.hms.usersystem.services.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserSystemApplicationTests {

	@MockBean
	OwnerRepository ownerRepository;
	
	@MockBean
	ManagerRepository managerRepository;
	
	@MockBean
	ReceptionistRepository receptionistRepository;

	@Autowired
	UserService userService;

//	@Test
//	public void addManagerTest() {
//		Manager manager = new Manager();
//		Manager fake_manager = new Manager();
//
//		when(managerRepository.save(manager)).thenReturn(manager);
//		Manager rm = userService.addManager(manager);
//
//		Assertions.assertThat(rm).isNotNull();
//		Assert.assertEquals(manager, rm);
//	}

	@Test
	public void deleteManagerTest() {
		Manager manager = new Manager();
		userService.deleteManager("2");
		verify(managerRepository, times(1)).deleteById(manager.getId());
	}

	@Test
	public void getManagerTest() {
		when(managerRepository.findAll()).thenReturn(
				Stream.of(new Manager()).collect(Collectors.toList()));
		assertEquals(1, userService.getManagers().size());
	}

	@Test
	@DisplayName("new Update method")
	public void newUpdate() {
		Manager in = new Manager();
		when(managerRepository.save(in)).thenReturn(in);
		Assertions.assertThat(in).isNotNull();

		in.setName("a");
		Manager updatedIn = userService.updateManager("2", in);

		when(managerRepository.save(updatedIn)).thenReturn(in);

		Assertions.assertThat(in).isNotNull();
		Assertions.assertThat(updatedIn.getName()).isEqualTo("a");
	}

}
