package com.hms.usersystem.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.hms.usersystem.models.Guest;

public interface GuestRepository extends MongoRepository<Guest, String>{

}
