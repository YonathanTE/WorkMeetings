/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.workmeetings.dao;

import com.sg.workmeetings.dao.EmployeeDao;
import com.sg.workmeetings.dao.RoomDao;
import com.sg.workmeetings.dao.MeetingDao;
import com.sg.workmeetings.TestApplicationConfiguration;
import com.sg.workmeetings.entity.Employee;
import com.sg.workmeetings.entity.Meeting;
import com.sg.workmeetings.entity.Room;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class EmployeeDaoDBTest {
    
    public EmployeeDaoDBTest() {
        
    }
    @Autowired
    RoomDao roomDao;
    
    @Autowired
    EmployeeDao employeeDao;
    
    @Autowired 
    MeetingDao meetingDao;
    
    @Before
    public void setUp() {
        List<Room> rooms = roomDao.getAllRooms();
        for (Room room : rooms) {
            roomDao.deleteRoomById(room.getId());
        }
        
        List<Employee> employees = employeeDao.getAllEmployees();
        for (Employee employee : employees) {
            employeeDao.deleteEmployeeById(employee.getId());
        }
        
        List<Meeting> meetings = meetingDao.getAllMeetings();
        for (Meeting meeting : meetings) {
            meetingDao.deleteMeetingById(meeting.getId());
        }
    }
    
    @Test 
    public void testAddGetEmployee() {
        Employee employee = new Employee();
        employee.setFirstName("Test First");
        employee.setLastName("Test Last");
        employee = employeeDao.addEmployee(employee);
        
        Employee fromDao = employeeDao.getEmployeeById(employee.getId());
        
        assertEquals(employee, fromDao);
    }
    
    @Test
    public void testGetAllEmployees() {
        Employee employee = new Employee();
        employee.setFirstName("Test First");
        employee.setLastName("Test Last");
        employee = employeeDao.addEmployee(employee);
        
        Employee employee2 = new Employee();
        employee2.setFirstName("Test First 2");
        employee2.setLastName("Test Last 2");
        employee2 = employeeDao.addEmployee(employee2);
        
        List<Employee> employees = employeeDao.getAllEmployees();
        
        assertEquals(2, employees.size());
        assertTrue(employees.contains(employee));
        assertTrue(employees.contains(employee2));
    }
    
    @Test
    public void testUpdateEmployee() {
        Employee employee = new Employee();
        employee.setFirstName("Test First");
        employee.setLastName("Test Last");
        employee = employeeDao.addEmployee(employee);
        
        Employee fromDao = employeeDao.getEmployeeById(employee.getId());
        
        assertEquals(employee, fromDao);
        
        employee.setFirstName("Another Test First");
        
        employeeDao.updateEmployee(employee);
        
        assertNotEquals(employee, fromDao);
        
        fromDao = employeeDao.getEmployeeById(employee.getId());
        
        assertEquals(employee, fromDao);
    }
    
    @Test
    public void testDeleteEmployee() {
        Employee employee = new Employee();
        employee.setFirstName("Test First");
        employee.setLastName("Test Last");
        employee = employeeDao.addEmployee(employee);
        
        Room room = new Room();
        room.setName("Test Room");
        room.setDescription("Test Room Description");
        room = roomDao.addRoom(room);
        
        Meeting meeting = new Meeting();
        meeting.setName("Test Meeting");
        meeting.setTime(LocalDateTime.now());
        meeting.setRoom(room);
        List<Employee> employees = new ArrayList<>();
        employees.add(employee);
        meeting.setAttendees(employees);
        meeting = meetingDao.addMeeting(meeting);
        
        employeeDao.deleteEmployeeById(employee.getId());
        
        Employee fromDao = employeeDao.getEmployeeById(employee.getId());
        
        assertNull(fromDao);
    }   
}
