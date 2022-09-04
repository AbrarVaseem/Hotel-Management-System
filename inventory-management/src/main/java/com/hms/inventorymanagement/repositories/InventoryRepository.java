package com.hms.inventorymanagement.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.hms.inventorymanagement.models.Inventory;

public interface InventoryRepository extends MongoRepository<Inventory, String>{

}
