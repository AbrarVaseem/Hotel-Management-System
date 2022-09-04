package com.hms.usersystem.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.hms.usersystem.models.Owner;

public interface OwnerRepository extends MongoRepository<Owner, String>{



}
