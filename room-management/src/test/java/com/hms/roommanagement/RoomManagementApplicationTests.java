package com.hms.roommanagement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.hms.roommanagement.models.Room;
import com.hms.roommanagement.repositories.RoomRepository;
import com.hms.roommanagement.services.RoomServiceImplementation;

@RunWith(SpringRunner.class)
@SpringBootTest
class RoomManagementApplicationTests {

	@MockBean
	RoomRepository roomRepository;

	@Autowired
	RoomServiceImplementation roomService;

	@Test
	public void addRoomTest() {
		Room room = new Room("2", "S3", "2 Bed Room", 20000, "available");
		Room fake_room = new Room("5", "B2", "3 Bed Room", 80000, "unavailable");

		when(roomRepository.save(room)).thenReturn(room);
		Room rm = roomService.addRoom(room);

		Assertions.assertThat(rm).isNotNull();
		Assert.assertEquals(room, rm);
	}

	@Test
	public void deleteRoomTest() {
		Room room = new Room("2", "S3", "2 Bed Room", 20000, "available");
		roomService.deleteRoom("2");
		verify(roomRepository, times(1)).deleteById(room.getId());
	}

	@Test
	public void getRoomTest() {
		when(roomRepository.findAll()).thenReturn(
				Stream.of(new Room("2", "S3", "2 Bed Room", 20000, "available")).collect(Collectors.toList()));
		assertEquals(1, roomService.getRooms().size());
	}

	@Test
	@DisplayName("new Update method")
	public void newUpdate() {
		Room in = new Room("2", "S3", "2 Bed Room", 20000, "available");
		when(roomRepository.save(in)).thenReturn(in);
		Assertions.assertThat(in).isNotNull();

		in.setRoomInfo("3 Bed room with AC");
		Room updatedIn = roomService.updateRoom("2", in);

		when(roomRepository.save(updatedIn)).thenReturn(in);

		Assertions.assertThat(in).isNotNull();
		Assertions.assertThat(updatedIn.getRoomInfo()).isEqualTo("3 Bed room with AC");
	}

}
