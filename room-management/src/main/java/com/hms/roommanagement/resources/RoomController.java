package com.hms.roommanagement.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.hms.roommanagement.models.Reservation;
import com.hms.roommanagement.models.Room;
import com.hms.roommanagement.services.RoomService;
//import com.hms.usersystem.resources.IOException;
//import com.hms.usersystem.resources.Path;
//import com.hms.usersystem.resources.ResponseBody;
//import com.hms.usersystem.resources.Student;
import com.hms.roommanagement.services.RoomServiceImplementation;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("api/rooms/")
public class RoomController {
	
	public String filename = null;


	@Autowired
	private RoomService roomService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping(method=RequestMethod.POST, value="/add-rooms")
	public Room addRoom(@RequestBody Room room){
//		room.setFile("aa");
		roomService.addRoom(room);
		return room;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/view-rooms")
	public List<Room> getRooms(){
		return roomService.getRooms();
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/view-room-details/{id}")
	public Optional<Room> viewRooms(@PathVariable String id){
		return roomService.getRoomDetailsById(id);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/update-room/{id}")
	public void updateRoom(@RequestBody Room room, @PathVariable String id ){
		roomService.updateRoom(id, room);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/delete-room/{id}")
	public void deleteRoom( @PathVariable String id ){
		roomService.deleteRoom(id);
	}
	
	// Make Reservation for Guests
	@RequestMapping(method=RequestMethod.POST, value="/make-reservation")
	public Reservation makeReservation(@RequestBody Reservation reservation){
		return roomService.makeReservation(reservation);
	}
	
	// View All Reservations
	@RequestMapping(method=RequestMethod.GET, value="/view-reservations")
	public List<Reservation> viewReservations(){
		return roomService.getAllReservations();
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/view-reservation-details/{id}")
	public Optional<Reservation> viewReservationsDetails(@PathVariable String id){
		return roomService.getReservationDetails(id);
	}
	
	// Update Reservation 
	@RequestMapping(method=RequestMethod.PUT, value="/update-reservation/{id}")
	public Reservation updateReservation(@RequestBody Reservation reservation, @PathVariable String id) {
		return roomService.updateReservation(id, reservation);
	}
	
	// Delete Reservation
	@RequestMapping(method=RequestMethod.DELETE, value="/delete-reservation/{id}")
	public void deleteReservation( @PathVariable String id ){
		roomService.deleteReservation(id);
	}
	
	// Search Room
	@RequestMapping(method=RequestMethod.GET, value="/search-rooms")
	public List<Room> searchRooms(){
		return roomService.getAvailableRooms();
	}
	
	
	
	
//	public static String uploadDirectory = System.getProperty("user.dir") + "/src/main/resources/static/imagedata";
//	
//	@RequestMapping("/add-rooms")
//	@org.springframework.web.bind.annotation.ResponseBody
//	public Room addRoom(@RequestBody Room room, @RequestParam("file") MultipartFile file) {
//		
//		StringBuilder fileNames = new StringBuilder();
//		String filename=room.getId() + file.getOriginalFilename().substring(file.getOriginalFilename().length()-4);
//		Path fileNameAndPath = Paths.get(uploadDirectory,filename);
//		
//		try {
//			Files.write(fileNameAndPath, file.getBytes());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		room.setSphoto(filename);
//		
//		roomService.addRoom(room);
//		return room;
//		
//		
//	}
	
	
	@RequestMapping(value = "/upload",
			method = RequestMethod.POST,
			consumes = MediaType.MULTIPART_FORM_DATA_VALUE)

	public String fileUpload(@RequestParam("file") MultipartFile file) throws IOException
	{
		
		StringBuilder fileNames = new StringBuilder();
		filename="a";
		
		File convertFile = new File("C://Users//Hp//hotel-management-system-ui//hotel-management-system-ui//public//assets//images/" + file.getOriginalFilename());
		convertFile.createNewFile();

		try (FileOutputStream fout = new FileOutputStream(convertFile))
		{
			fout.write(file.getBytes());
		}
		catch (Exception exe)
		{
			exe.printStackTrace();
		}
		return "File has uploaded successfully";
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
