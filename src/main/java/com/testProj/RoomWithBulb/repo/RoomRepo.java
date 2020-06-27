package com.testProj.RoomWithBulb.repo;

import com.testProj.RoomWithBulb.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepo extends JpaRepository<Room, Long> {
    Iterable<Room> findByRoomName(String roomname);
}
