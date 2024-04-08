package de.neuefische.cgnjava241springservice.service;

import de.neuefische.cgnjava241springservice.dto.EmployeeWOEmployeeNum;
import de.neuefische.cgnjava241springservice.dto.EmployeeWOSocialSecNum;
import de.neuefische.cgnjava241springservice.model.Employee;
import de.neuefische.cgnjava241springservice.repository.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeServiceTest {

    private final EmployeeRepository mockRepo = mock(EmployeeRepository.class);
    private final IdService mockIdService = mock(IdService.class);

    @Test
    void getEmployeeById() {
        //GIVEN
        EmployeeService service = new EmployeeService(mockRepo, mockIdService);
        Employee testEmployee = new Employee(
                "1",
                "Test",
                0,
                "Testing",
                "2"
        );
        when(mockRepo.findById("1")).thenReturn(Optional.of(testEmployee));

        EmployeeWOSocialSecNum expected = new EmployeeWOSocialSecNum(
                "1",
                "Test",
                0,
                "Testing");
        //WHEN
        EmployeeWOSocialSecNum actual = service.getEmployeeById("1");
        //THEN
        Assertions.assertEquals(expected, actual);
        verify(mockRepo).findById("1");
    }

    @Test
    void createNewEmployee(){
        //GIVEN
        EmployeeService service = new EmployeeService(mockRepo, mockIdService);
        when(mockIdService.generateId()).thenReturn("1");
        EmployeeWOEmployeeNum testEmployee = new EmployeeWOEmployeeNum(
                "Test",
                0,
                "Testing",
                "2"
        );

        EmployeeWOSocialSecNum expected = new EmployeeWOSocialSecNum(
                "1",
                "Test",
                0,
                "Testing"

        );
        //WHEN
        EmployeeWOSocialSecNum actual = service.createNewEmployee(testEmployee);
        //THEN
        Assertions.assertEquals(expected, actual);
        verify(mockRepo).save(new Employee(
                "1",
                "Test",
                0,
                "Testing",
                "2"
        ));
    }
}