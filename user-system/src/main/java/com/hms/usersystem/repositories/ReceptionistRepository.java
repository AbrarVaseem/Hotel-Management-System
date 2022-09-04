package com.hms.usersystem.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.hms.usersystem.models.Receptionist;

public interface ReceptionistRepository extends MongoRepository<Receptionist, String>{

}
