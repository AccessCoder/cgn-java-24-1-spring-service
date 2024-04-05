package de.neuefische.cgnjava241springservice.repository;

import de.neuefische.cgnjava241springservice.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {
}
