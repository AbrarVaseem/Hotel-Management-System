package com.hms.inventorymanagement.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hms.inventorymanagement.models.Inventory;
import com.hms.inventorymanagement.services.InventoryService;

@RestController
@RequestMapping("api/inventory/")
public class InventoryController {

	@Autowired
	private InventoryService inventoryService;

	@RequestMapping(method = RequestMethod.POST, value = "/add-inventory")
	public Inventory addInventory(@RequestBody Inventory inventory) {
		inventoryService.addInventory(inventory);
		return inventory;
	}

	@GetMapping("/view-inventory")
	public List<Inventory> getInventory() {
		return inventoryService.getInventory();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/view-inventory-item/{id}")
	public Optional<Inventory> viewInventory(@PathVariable String id) {
		return inventoryService.getInventoryItemById(id);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/update-inventory/{id}")
	public Inventory updateInventory(@RequestBody Inventory inventory, @PathVariable String id) {
		return inventoryService.updateInventory(id, inventory);

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/delete-inventory/{id}")
	public void deleteInventory(@PathVariable String id) {
		inventoryService.deleteInventory(id);
	}

}
