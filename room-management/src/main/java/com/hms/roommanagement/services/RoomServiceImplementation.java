package com.hms.roommanagement.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.roommanagement.models.Reservation;
import com.hms.roommanagement.models.Room;
import com.hms.roommanagement.repositories.ReservationRepository;
import com.hms.roommanagement.repositories.RoomRepository;

@Service
public class RoomServiceImplementation implements RoomService {

	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	private ReservationRepository reservationRepository;

	public Room addRoom(Room room) {
		roomRepository.save(room);
		return room;
	}

	public List<Room> getRooms() {
//		List<Room> rm = roomRepository.findAll();
//		rm.forEach(e->e.getCode());
//		List<Reservation> res = reservationRepository.findAll();
//		res.forEach(e->e.getRoomId());
		return roomRepository.findAll();
	}

	public Optional<Room> getRoomDetailsById(String id) {
		return roomRepository.findById(id);
	}

	public Room updateRoom(String id, Room room) {

		Optional<Room> findById = roomRepository.findById(id);
		System.out.println(findById);
		if (findById.isPresent()) {
			Room userEntity = findById.get();
			if (room.getCode() != null)

				userEntity.setCode(room.getCode());
			userEntity.setRoomInfo(room.getRoomInfo());
			userEntity.setRoomPrice(room.getRoomPrice());
			userEntity.setStatus(room.getStatus());

			return roomRepository.save(userEntity);
		}
		return room;

	}

	public void deleteRoom(String id) {
		roomRepository.deleteById(id);
	}

	// Make Reservation for Guests
	public Reservation makeReservation(Reservation reservation){

		List<Room> roomCode = roomRepository.findByCode(reservation.getRoomId());

		String checkIfAvailable = roomCode.iterator().next().getStatus();
			
		if (checkIfAvailable.equalsIgnoreCase("available")) {	
			reservationRepository.save(reservation);
			if (!reservation.getRoomId().isEmpty()) {
				roomCode.forEach(e -> e.setStatus("unavailable"));
				roomRepository.saveAll(roomCode);
			}
		} else {
			System.out.println("failed to make reservation since the room is unavailable");
			return null;
		}
		return reservation;

	}

	public List<Room> getAvailableRooms() {
		return roomRepository.getAvailableRooms();
	}

	public List<Reservation> getAllReservations() {
		return reservationRepository.findAll();
	}

	public void deleteReservation(String id) {
		reservationRepository.deleteById(id);
		
		Reservation reservation = reservationRepository.findById(id).get();
		List<Room> roomCode = roomRepository.findByCode(reservation.getRoomId());

		String checkIfAvailable = roomCode.iterator().next().getStatus();
		reservationRepository.deleteById(id);
		if (checkIfAvailable.equalsIgnoreCase("unavailable")) { 
			roomCode.forEach(e->e.setStatus("available"));	
	        roomRepository.saveAll(roomCode);
		}
		
		
	}

	public Reservation updateReservation(String id, Reservation reservation) {
		

	    Reservation findById = reservationRepository.findById(id).get();
		List<Room> findByRoomId = roomRepository.findByCode(reservation.getRoomId());
		if (findById.getId() != null) {
			Reservation userEntity = findById;
			List<Room> roomEntity = findByRoomId;
			
			if (reservation.getId() != null)
				userEntity.setAdultsCount(reservation.getAdultsCount());
				userEntity.setCheckIn(reservation.getCheckIn());
				userEntity.setCheckOut(reservation.getCheckOut());
				userEntity.setChildrenCount(reservation.getChildrenCount());
				userEntity.setCost(reservation.getCost());
				userEntity.setGuestId(reservation.getGuestId());
				userEntity.setNightCounts(reservation.getNightCounts());
				userEntity.setRoomCountReq(reservation.getRoomCountReq());
				userEntity.setRoomId(reservation.getRoomId());
				userEntity.setStatus(reservation.getStatus());
				findByRoomId.stream().forEach(e->e.setCode(e.getCode()));
				roomEntity.forEach(e->e.setStatus("unavailable"));
				roomRepository.saveAll(roomEntity);
			return reservationRepository.save(userEntity);
		}
		return reservation;

		

	}

	@Override
	public Optional<Reservation> getReservationDetails(String id) {
		return reservationRepository.findById(id) ;
	}

//	public Optional<Room> getRoomDetailsById(String id) {
//		return roomRepository.findById(id);
//	}
	
}
