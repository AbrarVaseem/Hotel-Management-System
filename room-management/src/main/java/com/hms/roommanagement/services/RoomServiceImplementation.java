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
	public void makeReservation(Reservation reservation) {

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
		}

	}

	public List<Room> getAvailableRooms() {
		return roomRepository.getAvailableRooms();
	}

	public List<Reservation> getAllReservations() {
		return reservationRepository.findAll();
	}

}