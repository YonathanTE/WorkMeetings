package com.sg.workmeetings.dao;

import com.sg.workmeetings.entity.Room;
import java.util.List;

public interface RoomDao {
    List<Room> getAllRooms();
    Room getRoomById(int id);
    Room addRoom(Room room);
    void updateRoom(Room room);
    void deleteRoomById(int id);
}
