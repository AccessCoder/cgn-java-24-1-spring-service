package de.neuefische.cgnjava241springservice;

import de.neuefische.cgnjava241springservice.model.Employee;
import de.neuefische.cgnjava241springservice.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class EmployeeIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private EmployeeRepository repo;

    @Test
    void getAllEmployees_ShouldReturnEmptyList_WhenCalledInitially() throws Exception {
        //GIVEN
        //WHEN & THEN
        mvc.perform(MockMvcRequestBuilders.get("/api/employee"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("[]"));
    }

    @Test
    void createNewEmployee_shouldReturnEmployee_WhenCalledWithValidData() throws Exception {
        //GIVEN
        //WHEN & THEN
        mvc.perform(MockMvcRequestBuilders.post("/api/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "name" : "Test",
                                    "age" : 23,
                                    "department" : "Testing",
                                    "socialSecNum" : "1"
                                }
                                """))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("""
                        {
                            "name" : "Test",
                            "age" : 23,
                            "department" : "Testing"
                        }
                        """))
                .andExpect(MockMvcResultMatchers.jsonPath("$.employeeNumber").exists());


    }

    @Test
    void getEmployeeById_shouldReturnEmployee1_WhenCalledWithId1() throws Exception {
        //GIVEN
        Employee testEmployee = new Employee("1", "Test", 12, "School", "1");
        repo.save(testEmployee);
        //WHEN & THEN
        mvc.perform(MockMvcRequestBuilders.get("/api/employee/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("""
                      
                        {
                            "employeeNumber" : "1",
                            "age" : 12,
                            "name" : "Test", 
                            "department" : "School"
                        }
                        """));
    }
}
