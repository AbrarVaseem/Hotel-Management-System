package com.hms.roommanagement.services;

import java.util.List;
import java.util.Optional;

import com.hms.roommanagement.models.Reservation;
import com.hms.roommanagement.models.Room;

public interface RoomService {

	public Room addRoom(Room room);

	public List<Room> getRooms();

	public Optional<Room> getRoomDetailsById(String id);
	
	public Room updateRoom(String id, Room room);
	
	public void deleteRoom(String id);

	//Make Reservation for Guests
	public Reservation makeReservation(Reservation reservation);

	public List<Room> getAvailableRooms();

	public List<Reservation> getAllReservations();

	public void deleteReservation(String id);

	public Reservation updateReservation(String id, Reservation reservation);
	
//	Optional<Room> getRoomCode(String roomCode);
	
}
