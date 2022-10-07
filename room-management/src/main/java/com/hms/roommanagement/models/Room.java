package com.hms.roommanagement.models;

import java.io.File;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="rooms")
public class Room {
	@Id
	private String id;
	private String code;
	private String roomInfo;
	private long roomPrice;
	private String status;
	private String file;
	
	public Room() {
		super();
	}

	public Room(String id, String code, String roomInfo, long roomPrice, String status, String file) {
		super();
		this.id = id;
		this.code = code;
		this.roomInfo = roomInfo;
		this.roomPrice = roomPrice;
		this.status = status;
		this.file = file;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getRoomInfo() {
		return roomInfo;
	}

	public void setRoomInfo(String roomInfo) {
		this.roomInfo = roomInfo;
	}

	public long getRoomPrice() {
		return roomPrice;
	}

	public void setRoomPrice(long roomPrice) {
		this.roomPrice = roomPrice;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	
	
	

	
}

