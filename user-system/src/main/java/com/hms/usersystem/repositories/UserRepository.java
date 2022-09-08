package com.hms.usersystem.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.hms.usersystem.models.User;


@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(String username);
    
    @Query("{role:manager}")
	List<User> getAllManagers();
    
    @Query("{role:owner}")
	List<User> getAllOwners();
    
    @Query("{role:receptionist}")
   	List<User> getAllReceptionist();
    
}