package com.hms.inventorymanagement.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.inventorymanagement.models.Inventory;
import com.hms.inventorymanagement.repositories.InventoryRepository;

@Service
public class InventoryServiceImplementation implements InventoryService{
	
	@Autowired
	private InventoryRepository inventoryRepository;
	
	public Inventory addInventory(Inventory inventory) {
		inventoryRepository.save(inventory);
		return inventory;
	}

	public List<Inventory> getInventory() {
		return inventoryRepository.findAll();
	}

	public Optional<Inventory> getInventoryItemById(String id) {
		return inventoryRepository.findById(id);
	}
	
	public Inventory updateInventory(String id, Inventory inventory) {
	
		Optional<Inventory> findById = inventoryRepository.findById(id);
		if (findById.isPresent()) {
			Inventory userEntity = findById.get();
			if(inventory.getInventoryItemName() != null && !inventory.getInventoryItemName().isEmpty())
				userEntity.setInventoryItemName(inventory.getInventoryItemName());
				userEntity.setQuantityAvail(inventory.getQuantityAvail());
			    inventoryRepository.save(userEntity);
		}
		return inventory;
		
	}

	public void deleteInventory(String id) {
		inventoryRepository.deleteById(id);
	}
}
