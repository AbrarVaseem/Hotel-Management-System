package com.hms.roommanagement.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.hms.roommanagement.models.Room;

@Repository
public interface RoomRepository extends MongoRepository<Room, String>{
		
	@Query("{status:available}")
	List<Room> getAvailableRooms();
	
	List<Room> findByCode(String code);
	
}
