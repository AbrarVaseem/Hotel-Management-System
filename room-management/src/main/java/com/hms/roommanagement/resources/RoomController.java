package com.hms.roommanagement.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hms.roommanagement.models.Reservation;
import com.hms.roommanagement.models.Room;
import com.hms.roommanagement.services.RoomService;

@RestController
@RequestMapping("api/rooms/")
public class RoomController {

	@Autowired
	private RoomService roomService;
	
	@RequestMapping(method=RequestMethod.POST, value="/add-rooms")
	public Room addRoom(@RequestBody Room room) {
		roomService.addRoom(room);
		return room;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/view-rooms")
	public List<Room> getRooms(){
		return roomService.getRooms();
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/view-room-details/{id}")
	public Optional<Room> viewRooms(@PathVariable String id){
		return roomService.getRoomDetailsById(id);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/update-room/{id}")
	public void updateRoom(@RequestBody Room room, @PathVariable String id ){
		roomService.updateRoom(id, room);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/delete-room/{id}")
	public void deleteRoom( @PathVariable String id ){
		roomService.deleteRoom(id);
	}
	
	// Make Reservation for Guests
	@RequestMapping(method=RequestMethod.POST, value="/make-reservation")
	public Reservation makeReservation(@RequestBody Reservation reservation) {
		return roomService.makeReservation(reservation);
	}
	
	// View All Reservations
	@RequestMapping(method=RequestMethod.GET, value="/view-reservations")
	public List<Reservation> viewReservations(){
		return roomService.getAllReservations();
	}
	
	// Update Reservation 
	@RequestMapping(method=RequestMethod.PUT, value="/update-reservation/{id}")
	public Reservation updateReservation(@RequestBody Reservation reservation, @PathVariable String id) {
		return roomService.updateReservation(id, reservation);
	}
	
	// Delete Reservation
	@RequestMapping(method=RequestMethod.DELETE, value="/delete-reservation/{id}")
	public void deleteReservation( @PathVariable String id ){
		roomService.deleteReservation(id);
	}
	
	// Search Room
	@RequestMapping(method=RequestMethod.GET, value="/search-rooms")
	public List<Room> searchRooms(){
		return roomService.getAvailableRooms();
	}
	
	
}
