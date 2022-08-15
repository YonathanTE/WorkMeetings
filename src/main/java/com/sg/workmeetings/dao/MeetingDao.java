package com.sg.workmeetings.dao;

import com.sg.workmeetings.entity.Employee;
import com.sg.workmeetings.entity.Meeting;
import com.sg.workmeetings.entity.Room;
import java.util.List;

public interface MeetingDao {
    List<Meeting> getAllMeetings();
    Meeting getMeetingByid(int id);
    Meeting addMeeting(Meeting meeting);
    void updateMeeting(Meeting meeting);
    void deleteMeetingById(int id);
    
    List<Meeting> getMeetingsForRoom(Room room);
    List<Meeting> getMeetingsForEmployee(Employee employee);
}
