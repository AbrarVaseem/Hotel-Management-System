package com.hms.roommanagement.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.hms.roommanagement.models.Reservation;

public interface ReservationRepository extends MongoRepository<Reservation, String>{

}
