package com.hms.usersystem.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.usersystem.models.Guest;
import com.hms.usersystem.models.Manager;
import com.hms.usersystem.models.Receptionist;
import com.hms.usersystem.repositories.GuestRepository;
import com.hms.usersystem.repositories.ManagerRepository;
import com.hms.usersystem.repositories.ReceptionistRepository;

@Service
public class UserService {

	@Autowired
	private ManagerRepository managerRepository;

	@Autowired
	private ReceptionistRepository receptionistRepository;

	@Autowired
	private GuestRepository guestRepository;

	// Manager CRUD for Owner
	public void addManager(Manager manager) {
		managerRepository.save(manager);
	}
	public List<Manager> getManagers() {
		List<Manager> allManagers = managerRepository.findAll();
		return allManagers;
	}
	public Manager updateManager(String id, Manager manager) {
		Optional<Manager> findById = managerRepository.findById(id);
		if (findById.isPresent()) {
			Manager userEntity = findById.get();
			if (manager.getId() != null)
				userEntity.setAddress(manager.getAddress());
			userEntity.setAge(manager.getAge());
			userEntity.setEmail(manager.getEmail());
			userEntity.setName(manager.getName());
			userEntity.setRole(manager.getRole());
			userEntity.setSalary(manager.getSalary());
			return managerRepository.save(userEntity);
		}
		return null;
	}
	public void deleteManager(String id) {
		managerRepository.deleteById(id);
	}
	public Optional<Manager> getManagerById(String id) {
		return managerRepository.findById(id);
	}
	
	
	// Receptionist CRUD for Owner and Manager
	public void addReceptionist(Receptionist receptionist) {
		receptionistRepository.save(receptionist);
	}
	public List<Receptionist> getReceptionists() {
		List<Receptionist> allReceptionist = receptionistRepository.findAll();
		return allReceptionist;
	}
	public Optional<Receptionist> getReceptionistById(String id) {
		return receptionistRepository.findById(id);
	}
	public Receptionist updateReceptionist(String id, Receptionist receptionist) {
		Optional<Receptionist> findById = receptionistRepository.findById(id);
		if (findById.isPresent()) {
			Receptionist userEntity = findById.get();
			if(receptionist.getId() != null)
//				userEntity.setAddress(receptionist.getAddress());
//				userEntity.setAge(receptionist.getAge());
//				userEntity.setEmail(receptionist.getEmail());
//				userEntity.setName(receptionist.getName());
//				userEntity.setRole(receptionist.getRole());
//				userEntity.setSalary(receptionist.getSalary());
				return receptionistRepository.save(userEntity);
		}
		return null;
	}
	public void deleteReceptionist(String id) {
		receptionistRepository.deleteById(id);
	}
	
	
	
	
	
	// Guests CRUD for Owner and Manager
	public void addGuest(Guest guest) {
		guestRepository.save(guest);
	}
	public List<Guest> getGuests() {
		List<Guest> allGuests = guestRepository.findAll();
		return allGuests;
	}
	public Optional<Guest> getGuestById(String id) {
		return guestRepository.findById(id);
	}
	public void updateGuest(String id, Guest guest) {
		
	}
	public void deleteGuest(String id) {
		receptionistRepository.deleteById(id);
	}
	
	
	

	

	
	

}
