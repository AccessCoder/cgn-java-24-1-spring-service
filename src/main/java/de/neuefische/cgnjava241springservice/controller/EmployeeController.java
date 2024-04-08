package de.neuefische.cgnjava241springservice.controller;

import de.neuefische.cgnjava241springservice.dto.EmployeeWOEmployeeNum;
import de.neuefische.cgnjava241springservice.dto.EmployeeWOSocialSecNum;
import de.neuefische.cgnjava241springservice.model.Employee;
import de.neuefische.cgnjava241springservice.repository.EmployeeRepository;
import de.neuefische.cgnjava241springservice.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService service;

    @GetMapping
    public List<EmployeeWOSocialSecNum> getAllEmployees(){
        return service.getAllEmployees();
    }

    @GetMapping("/{id}")
    public EmployeeWOSocialSecNum getEmployeeById(@PathVariable String id){
        return service.getEmployeeById(id);
    }

    @PostMapping
    public EmployeeWOSocialSecNum createNewEmployee(@RequestBody EmployeeWOEmployeeNum newEmployee){
        return service.createNewEmployee(newEmployee);
    }


}
