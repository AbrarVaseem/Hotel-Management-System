package com.hms.roommanagement.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.hms.roommanagement.models.Reservation;

@Repository
public interface ReservationRepository extends MongoRepository<Reservation, String>{

}
