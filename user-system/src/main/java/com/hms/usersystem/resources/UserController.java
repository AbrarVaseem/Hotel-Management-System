package com.hms.usersystem.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.loadbalancer.CompletionContext.Status;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
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

import com.hms.usersystem.models.AuthenticationRequest;
import com.hms.usersystem.models.AuthenticationResponse;
import com.hms.usersystem.models.Guest;
import com.hms.usersystem.models.Inventory;

import com.hms.usersystem.models.Manager;
import com.hms.usersystem.models.Receptionist;
import com.hms.usersystem.models.Reservation;
import com.hms.usersystem.models.Room;
import com.hms.usersystem.models.User;
import com.hms.usersystem.repositories.UserRepository;
import com.hms.usersystem.services.MyUserDetailsService;
import com.hms.usersystem.services.UserService;
import com.hms.usersystem.util.JwtUtil;

import org.springframework.security.core.userdetails.UserDetails;










import java.io.InputStream;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;








import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


@RestController
@RequestMapping("api/users/")
public class UserController {

	
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MyUserDetailsService userDetailsService;
	
	
	

	@Autowired
	private UserService userService;

	@Autowired
	private RestTemplate restTemplate;
	
	// Manager CRUD for Owner
	@RequestMapping(method = RequestMethod.POST, value = "/add-manager")
	public Manager addManager(@RequestBody Manager manager) {
		userService.addManager(manager);
		return manager;
	}
	
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
	public Receptionist addReceptionist(@RequestBody Receptionist receptionist) {
		userService.addReceptionist(receptionist);
		return receptionist;
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
	@RequestMapping(method=RequestMethod.GET, value="/view-room-details/{id}")
	public Optional<Room> viewRooms(@PathVariable String id){
		@SuppressWarnings("unchecked")
		Optional<Room> roomById = restTemplate.getForObject("http://room-management/api/rooms/view-room-details/"+id, Optional.class);
		return roomById;
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
	
	@RequestMapping(method=RequestMethod.GET, value="/view-reservation-details/{id}")
	public Optional<Reservation> viewReservationsDetails(@PathVariable String id){
//		return roomService.getReservationDetails(id);.
		@SuppressWarnings("unchecked")
		Optional<Reservation> reservationById = restTemplate.getForObject("http://room-management/api/rooms/view-reservation-details/"+id, Optional.class);
		return reservationById;
	}
	
	
	
	
	
	

	// Inventory CRUD for Owner and Manager
	@RequestMapping(method = RequestMethod.POST, value = "/add-inventory")
	public Inventory addInventory(@RequestBody Inventory inventory) {
		return restTemplate.postForObject("http://inventory-management/api/inventory/add-inventory", inventory, Inventory.class);
	}
	@RequestMapping(method = RequestMethod.GET, value = "/view-inventory-item/{id}")
	public Optional<Inventory> viewInventory(@PathVariable String id) {
		@SuppressWarnings("unchecked")
		Optional<Inventory> inventoryById = restTemplate.getForObject("http://inventory-management/api/inventory/view-inventory-item/"+id, Optional.class);
		return inventoryById;
	}
	@GetMapping("view-inventory")
	public List<Inventory> getInventory() {
		@SuppressWarnings("unchecked")
		List<Inventory> inventory = restTemplate.getForObject("http://inventory-management/api/inventory/view-inventory", List.class);
		return inventory;
	}
	@RequestMapping(method = RequestMethod.PUT, value = "/update-inventory/{id}")
	public void updateInvenotry(@RequestBody Inventory inventory, @PathVariable String id) {
		restTemplate.put("http://inventory-management/api/inventory/update-inventory/"+id, inventory , Inventory.class);
	}
	@RequestMapping(method = RequestMethod.DELETE, value = "/delete-inventory/{id}")
	public void deleteInventory(@PathVariable String id) {
		restTemplate.delete("http://inventory-management/api/inventory/delete-inventory/"+id, Inventory.class);
	}
	
	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
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
	
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
			);
		}
		catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}
		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String jwt = jwtTokenUtil.generateToken(userDetails);
		
		User us = userRepository.findByUsername(authenticationRequest.getUsername());

		return ResponseEntity.ok(new AuthenticationResponse(jwt, us));		
		
	}

	
//	private static final Logger logger = Logger.getLogger(UserController.class.getName());
//
//	@PostMapping("/upload")
//	public ResponseEntity<String> uploadData(@RequestParam("file") MultipartFile file) throws Exception {
//
//		if (file == null) {
//			throw new RuntimeException("You must select the a file for uploading");
//		}
//
//		InputStream inputStream = file.getInputStream();
//		String originalName = file.getOriginalFilename();
//		String name = file.getName();
//		String contentType = file.getContentType();
//		long size = file.getSize();
//
//		logger.info("inputStream: " + inputStream);
//		logger.info("originalName: " + originalName);
//		logger.info("name: " + name);
//		logger.info("contentType: " + contentType);
//		logger.info("size: " + size);
//
//
//		return new ResponseEntity<String>(originalName, HttpStatus.OK);
//	}
	
	
//	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
//	private static final Logger logger = Logger.getLogger(UserController.class.getName());
//
//    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity uploadFile(@RequestParam MultipartFile file) {
//        logger.info(String.format("File name '%s' uploaded successfully.", file.getOriginalFilename()));
//        return ResponseEntity.ok().build();
//    }
	
	
	
	
//	@RequestMapping(value = "/upload",
//			method = RequestMethod.POST,
//			consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//
//	public String fileUpload(@RequestParam("file") MultipartFile file) throws IOException
//	{
//		File convertFile = new File("C://Users//Hp//hotel-management-system-ui//hotel-management-system-ui//src//static//images//room-images/" + file.getOriginalFilename());
//		convertFile.createNewFile();
//
//		try (FileOutputStream fout = new FileOutputStream(convertFile))
//		{
//			fout.write(file.getBytes());
//		}
//		catch (Exception exe)
//		{
//			exe.printStackTrace();
//		}
//		return "File has uploaded successfully";
//	}
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
}
