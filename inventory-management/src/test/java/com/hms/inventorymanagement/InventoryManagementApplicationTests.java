package com.hms.inventorymanagement;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.hms.inventorymanagement.models.Inventory;
import com.hms.inventorymanagement.repositories.InventoryRepository;
import com.hms.inventorymanagement.services.InventoryService;
import com.hms.inventorymanagement.services.InventoryServiceImplementation;

@RunWith(SpringRunner.class)
@SpringBootTest
class InventoryManagementApplicationTests {

	@MockBean
	InventoryRepository inventoryRepository;

	@Autowired
	InventoryServiceImplementation inventoryService;

	@Test
	public void addInventoryTest() {
		Inventory inventory = new Inventory("4", "Med Kit", 8);

		when(inventoryRepository.save(inventory)).thenReturn(inventory);
		Inventory iii = inventoryService.addInventory(inventory);

		Assertions.assertThat(iii).isNotNull();
		Assert.assertEquals(inventory, iii);
	}

	@Test
	public void deleteInventoryTest() {
		Inventory invv = new Inventory("4", "Med Kit", 8);
		inventoryService.deleteInventory("4");
		verify(inventoryRepository, times(1)).deleteById(invv.getId());
	}

	@Test
	public void getInventoryTest() {
		when(inventoryRepository.findAll())
				.thenReturn(Stream.of(new Inventory("41", "Soap", 5)).collect(Collectors.toList()));
		assertEquals(1, inventoryService.getInventory().size());
	}
	
	@Test
	public void getInventoryByIdTest() {
		Inventory invv = new Inventory("4", "Med Kit", 8);
		inventoryService.getInventoryItemById("4");
		verify(inventoryRepository, times(1)).findById(invv.getId());
	}

	@Test
	@DisplayName("new Update method")
	public void newUpdate() {
		Inventory in = new Inventory("4", "med", 5);
		when(inventoryRepository.save(in)).thenReturn(in);
		Assertions.assertThat(in).isNotNull();

		in.setInventoryItemName("kit");
		Inventory updatedIn = inventoryService.updateInventory("4", in);

		when(inventoryRepository.save(updatedIn)).thenReturn(in);

		Assertions.assertThat(in).isNotNull();
		Assertions.assertThat(updatedIn.getInventoryItemName()).isEqualTo("kit");
	}

}
