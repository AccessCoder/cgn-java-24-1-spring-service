package de.neuefische.cgnjava241springservice.service;

import de.neuefische.cgnjava241springservice.dto.EmployeeWOEmployeeNum;
import de.neuefische.cgnjava241springservice.dto.EmployeeWOSocialSecNum;
import de.neuefische.cgnjava241springservice.model.Employee;
import de.neuefische.cgnjava241springservice.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository repo;
    private final IdService idService;


    public List<EmployeeWOSocialSecNum> getAllEmployees() {
        return repo.findAll().stream()
                .map(employee -> new EmployeeWOSocialSecNum(
                        employee.employeeNumber(),
                        employee.name(),
                        employee.age(),
                        employee.department()))
                .toList();
    }

    public EmployeeWOSocialSecNum getEmployeeById(String id) {
        Employee temp = repo.findById(id).orElseThrow();
        return new EmployeeWOSocialSecNum(
                temp.employeeNumber(),
                temp.name(),
                temp.age(),
                temp.department());
    }

    public EmployeeWOSocialSecNum createNewEmployee(EmployeeWOEmployeeNum newEmployee) {
        Employee employee = new Employee(
                idService.generateId(),
                newEmployee.name(),
                newEmployee.age(),
                newEmployee.department(),
                newEmployee.socialSecNum()
                );
        repo.save(employee);
        return new EmployeeWOSocialSecNum(
                employee.employeeNumber(),
                employee.name(),
                employee.age(),
                employee.department()
        );
    }
}
