package com.hms.usersystem.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.hms.usersystem.models.Manager;

public interface ManagerRepository extends MongoRepository<Manager, String>{

}
