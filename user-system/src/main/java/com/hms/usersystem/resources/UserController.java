package com.hms.usersystem.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.loadbalancer.CompletionContext.Status;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.hms.usersystem.models.Guest;
import com.hms.usersystem.models.Inventory;
import com.hms.usersystem.models.Manager;
import com.hms.usersystem.models.Receptionist;
import com.hms.usersystem.models.Reservation;
import com.hms.usersystem.models.Room;
import com.hms.usersystem.models.User;
import com.hms.usersystem.repositories.UserRepository;
import com.hms.usersystem.services.UserService;


@CrossOrigin(origins = "http://localhost:3000", allowedHeaders="POST")
@RestController
@RequestMapping("api/users/")
public class UserController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private RestTemplate restTemplate;
	
	// Manager CRUD for Owner
	@RequestMapping(method = RequestMethod.POST, value = "/add-manager")
	public void addManager(@RequestBody Manager manager) {
		userService.addManager(manager);
	}
	
//	@PreAuthorize("hasAuthority('manager')")
//	@CrossOrigin(origins = "*", allowedHeaders="*")
	@RequestMapping(method = RequestMethod.GET, value = "/view-managers")
	public List<User> getManagers() {
		return userService.getManagers();
	}
	@RequestMapping(method = RequestMethod.GET, value = "/view-manager/{id}")
	public Optional<Manager> getManagerById(@PathVariable String id){
		return userService.getManagerById(id);
	}
	@RequestMapping(method = RequestMethod.PUT, value = "/update-manager/{id}")
	public void updateManager(@RequestBody Manager manager, @PathVariable String id) {
		userService.updateManager(id, manager);
	}
	@RequestMapping(method = RequestMethod.DELETE, value = "/delete-manager/{id}")
	public void deleteManager(@PathVariable String id) {
		userService.deleteManager(id);
	}
	
	
	
	
	

	// Receptionist CRUD for Owner and Manager
	@RequestMapping(method = RequestMethod.POST, value = "/add-receptionist")
	public void addReceptionist(@RequestBody Receptionist receptionist) {
		userService.addReceptionist(receptionist);
	}
	@GetMapping("/view-receptionist")
	public List<User> getReceptionists() {
		return userService.getReceptionists();
	}
	@RequestMapping(method = RequestMethod.GET, value = "/view-receptionist/{id}")
	public Optional<Receptionist> getReceptionistById(@PathVariable String id){
		return userService.getReceptionistById(id);
	}
	@RequestMapping(method = RequestMethod.PUT, value = "/update-receptionist/{id}")
	public void updateReceptionist(@RequestBody Receptionist receptionist, @PathVariable String id) {
		userService.updateReceptionist(id, receptionist);
	}
	@RequestMapping(method = RequestMethod.DELETE, value = "/delete-receptionist/{id}")
	public void deleteReceptionist(@PathVariable String id) {
		userService.deleteReceptionist(id);
	}
	
	
	
	

	// Guests CRUD for Owner and Manager
	@RequestMapping(method = RequestMethod.POST, value = "/add-guest")
	public void addGuest(@RequestBody Guest guest) {
		userService.addGuest(guest);
	}
	@GetMapping("/view-guests")
	public List<Guest> getGuests() {
		return userService.getGuests();
	}
	@RequestMapping(method = RequestMethod.GET, value = "/view-guest/{id}")
	public Optional<Guest> getGuestById(@PathVariable String id){
		return userService.getGuestById(id);
	}
	@RequestMapping(method = RequestMethod.PUT, value = "/update-guest/{id}")
	public void updateGuest(@RequestBody Guest guest, @PathVariable String id) {
		userService.updateGuest(id, guest);
	}
	@RequestMapping(method = RequestMethod.DELETE, value = "/delete-guest/{id}")
	public void deleteGuest(@PathVariable String id) {
		userService.deleteGuest(id);
	}
	
	
	

	
	
	
	// Rooms CRUD for Owner and Manager
	@RequestMapping(method = RequestMethod.POST, value = "/add-rooms")
	public Room addRoom(@RequestBody Room room) {
		return restTemplate.postForObject("http://room-management/api/rooms/add-rooms", room, Room.class);
	}
	@GetMapping("view-rooms")
	public List<Room> getRooms() {
		@SuppressWarnings("unchecked")
		List<Room> room = restTemplate.getForObject("http://room-management/api/rooms/view-rooms/", List.class);
		return room;
	}
	@RequestMapping(method = RequestMethod.PUT, value = "/update-room/{id}")
	public void updateRoom(@RequestBody Room room, @PathVariable String id) {
		restTemplate.put("http://room-management/api/rooms/update-room/"+id, room , Room.class);
	}
	@RequestMapping(method = RequestMethod.DELETE, value = "/delete-room/{id}")
	public void deleteRoom(@PathVariable String id) {
		restTemplate.delete("http://room-management/api/rooms/delete-room/"+id, Room.class);
	}
	
	//Make Reservation
	@RequestMapping(method = RequestMethod.POST, value = "/make-reservation")
	public Reservation makeReservation(@RequestBody Reservation reservation) {
		return restTemplate.postForObject("http://room-management/api/rooms/make-reservation", reservation, Reservation.class);
	}
	
	//View Reservations
	@GetMapping("view-reservations")
	public List<Reservation> getReservations() {
		@SuppressWarnings("unchecked")
		List<Reservation> reservations = restTemplate.getForObject("http://room-management/api/rooms/view-reservations/", List.class);
		return reservations;
	}
	
	
	
	
	
	

	// Inventory CRUD for Owner and Manager
	@RequestMapping(method = RequestMethod.POST, value = "/add-inventory")
	public Inventory addInventory(@RequestBody Inventory inventory) {
		return restTemplate.postForObject("http://inventory-management/api/add-inventory", inventory, Inventory.class);
	}
	@GetMapping("view-inventory")
	public List<Inventory> getInventory() {
		@SuppressWarnings("unchecked")
		List<Inventory> inventory = restTemplate.getForObject("http://inventory-management/api/view-inventory", List.class);
		return inventory;
	}
	@RequestMapping(method = RequestMethod.PUT, value = "/update-inventory/{id}")
	public void updateInvenotry(@RequestBody Inventory inventory, @PathVariable String id) {
		restTemplate.put("http://inventory-management/api/inventory/update-inventory/"+id, inventory , Inventory.class);
	}
	@RequestMapping(method = RequestMethod.DELETE, value = "/delete-inventory/{id}")
	public void deleteInventory(@PathVariable String id) {
		restTemplate.put("http://inventory-management/api/inventory/delete-inventory/"+id, Inventory.class);
	}
	
	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
//	@CrossOrigin(origins = "http://localhost:3000", allowedHeaders="POST")
	public Authentication login(@RequestBody User userRequest) {
	    Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userRequest.getUsername(), userRequest.getPassword()));
	    boolean isAuthenticated = isAuthenticated(authentication);
	    if (isAuthenticated) {
	        SecurityContextHolder.getContext().setAuthentication(authentication);
	    }
	    return authentication;
	}

	private boolean isAuthenticated(Authentication authentication) {
	    return authentication != null && !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();
	}
	

	
}
