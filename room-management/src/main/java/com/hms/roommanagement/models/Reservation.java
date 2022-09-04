package com.hms.roommanagement.models;

public class Reservation {

	private String id;
	private String guestId;
	private String roomId;
	private String roomCountReq;
	private String childrenCount;
	private String adultsCount;
	private String nightCounts;
	private String checkIn;
	private String checkOut;
	private String cost;
	private String status;
	
	public Reservation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Reservation(String id, String guestId, String roomId, String roomCountReq, String childrenCount,
			String adultsCount, String nightCounts, String checkIn, String checkOut, String cost, String status) {
		super();
		this.id = id;
		this.guestId = guestId;
		this.roomId = roomId;
		this.roomCountReq = roomCountReq;
		this.childrenCount = childrenCount;
		this.adultsCount = adultsCount;
		this.nightCounts = nightCounts;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.cost = cost;
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGuestId() {
		return guestId;
	}

	public void setGuestId(String guestId) {
		this.guestId = guestId;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getRoomCountReq() {
		return roomCountReq;
	}

	public void setRoomCountReq(String roomCountReq) {
		this.roomCountReq = roomCountReq;
	}

	public String getChildrenCount() {
		return childrenCount;
	}

	public void setChildrenCount(String childrenCount) {
		this.childrenCount = childrenCount;
	}

	public String getAdultsCount() {
		return adultsCount;
	}

	public void setAdultsCount(String adultsCount) {
		this.adultsCount = adultsCount;
	}

	public String getNightCounts() {
		return nightCounts;
	}

	public void setNightCounts(String nightCounts) {
		this.nightCounts = nightCounts;
	}

	public String getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(String checkIn) {
		this.checkIn = checkIn;
	}

	public String getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(String checkOut) {
		this.checkOut = checkOut;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
		
}

