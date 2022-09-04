package com.hms.inventorymanagement.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="inventory")
public class Inventory {

	@Id
	private String id;
	private String inventoryItemName;
	private int quantityAvail;
	
	public Inventory() {
		super();
	}

	public Inventory(String id, String inventoryItemName, int quantityAvail) {
		super();
		this.id = id;
		this.inventoryItemName = inventoryItemName;
		this.quantityAvail = quantityAvail;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInventoryItemName() {
		return inventoryItemName;
	}

	public void setInventoryItemName(String inventoryItemName) {
		this.inventoryItemName = inventoryItemName;
	}

	public int getQuantityAvail() {
		return quantityAvail;
	}

	public void setQuantityAvail(int quantityAvail) {
		this.quantityAvail = quantityAvail;
	}	
	
	
	
}
