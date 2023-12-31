package com.onurhaktan.springboot.repository;

import com.onurhaktan.springboot.model.Employee;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

// It is used to test JPA-based database operations.
// This annotation enables tests to run quickly and in isolation, and it can use in-memory databases to simulate database operations.
@DataJpaTest
public class EmployeeRepositoryTests {

    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee employee;

    // It is used to define the preparatory operations that need to be done before a specific test method is executed in JUnit tests.
    @BeforeEach
    public void setup(){
        employee = Employee.builder()
                .firstName("Onur")
                .lastName("Haktan")
                .email("onur@email.com")
                .build();
    }

    // JUnit test for save employee operation
    @DisplayName("JUnit test for save employee operation")
    @Test
    public void givenEmployeeObject_whenSave_thenReturnSavedEmployee(){

        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Onur")
//                .lastName("Haktan")
//                .email("onur@email.com")
//                .build();

        // when - action or behaviour that we are going to test
        Employee savedEmployee = employeeRepository.save(employee);

        // then - verify the output
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getId()).isGreaterThan(0);
    }

    // JUnit test for get all employees operation
    @DisplayName("JUnit test for get all employees operation")
    @Test
    public void givenEmployeesList_whenFindAll_thenEmployeeList(){

        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Onur")
//                .lastName("Haktan")
//                .email("onur@email.com")
//                .build();

        Employee employee1 = Employee.builder()
                .firstName("Göksu")
                .lastName("Turaç")
                .email("goksu@email.com")
                .build();

        employeeRepository.save(employee);
        employeeRepository.save(employee1);

        // when - action or behaviour that we are going to test
        List<Employee> employeeList = employeeRepository.findAll();

        // then - verify the output
        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(2);
    }

    // JUnit test for get employee by id operation
    @DisplayName("JUnit test for get employee by id operation")
    @Test
    public void givenEmployeeObject_whenFindById_thenReturnEmployeeObject(){

        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Onur")
//                .lastName("Haktan")
//                .email("onur@email.com")
//                .build();

        employeeRepository.save(employee);

        // when - action or behaviour that we are going to test
        Employee employeeDB = employeeRepository.findById(employee.getId()).get();

        // then - verify the output
        assertThat(employeeDB).isNotNull();
    }

    // JUnit test for get employee by email operation
    @DisplayName("JUnit test for get employee by email operation")
    @Test
    public void givenEmployeeEmail_whenFindByEmail_thenReturnEmployeeObject(){

        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Onur")
//                .lastName("Haktan")
//                .email("onur@email.com")
//                .build();

        employeeRepository.save(employee);

        // when - action or behaviour that we are going to test
        Employee employeeDB = employeeRepository.findByEmail(employee.getEmail()).get();

        // then - verify the output
        assertThat(employeeDB).isNotNull();
    }

    // JUnit test for update employee operation
    @DisplayName("JUnit test for update employee operation")
    @Test
    public void givenEmployeeObject_whenUpdateEmployee_thenReturnUpdatedEmployee(){

        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Onur")
//                .lastName("Haktan")
//                .email("onur@email.com")
//                .build();

        employeeRepository.save(employee);

        // when - action or behaviour that we are going to test
        Employee savedEmployee = employeeRepository.findById(employee.getId()).get();
        savedEmployee.setFirstName("Akın");
        savedEmployee.setEmail("akın@email.com");
        Employee updatedEmployee = employeeRepository.save(savedEmployee);

        // then - verify the output
        assertThat(updatedEmployee.getEmail()).isEqualTo("akın@email.com");
        assertThat(updatedEmployee.getFirstName()).isEqualTo("Akın");
    }

    // JUnit test for delete employee operation
    @DisplayName("JUnit test for delete employee operation")
    @Test
    public void givenEmployeeObject_whenDelete_thenRemoveEmployee(){

        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Onur")
//                .lastName("Haktan")
//                .email("onur@email.com")
//                .build();

        employeeRepository.save(employee);

        // when - action or behaviour that we are going to test
        employeeRepository.deleteById(employee.getId());
        Optional<Employee> optionalEmployee =  employeeRepository.findById(employee.getId());

        // then - verify the output
        assertThat(optionalEmployee).isEmpty();
    }

    // JUnit test for custom query using JPQL with index
    @DisplayName("JUnit test for custom query using JPQL with index")
    @Test
    public void givenFirstNameAndLastName_whenFindByJPQL_thenReturnEmployeeObject(){

        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Onur")
//                .lastName("Haktan")
//                .email("onur@email.com")
//                .build();

        employeeRepository.save(employee);
        String firstName = "Onur";
        String lastName = "Haktan";

        // when - action or behaviour that we are going to test
        Employee savedEmployee = employeeRepository.findByJPQL(firstName, lastName);

        // then - verify the output
        assertThat(savedEmployee).isNotNull();
    }

    // JUnit test for custom query using JPQL with named params
    @DisplayName("JUnit test for custom query using JPQL with named params")
    @Test
    public void givenFirstNameAndLastName_whenFindByJPQLNamedParams_thenReturnEmployeeObject(){

        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Onur")
//                .lastName("Haktan")
//                .email("onur@email.com")
//                .build();

        employeeRepository.save(employee);
        String firstName = "Onur";
        String lastName = "Haktan";

        // when - action or behaviour that we are going to test
        Employee savedEmployee = employeeRepository.findByJPQLNamedParams(firstName, lastName);

        // then - verify the output
        assertThat(savedEmployee).isNotNull();
    }

    // JUnit test for custom query using Native SQL with index params
    @DisplayName("JUnit test for custom query using Native SQL with index params")
    @Test
    public void givenFirstNameAndLastName_whenFindByNativeSQL_thenReturnEmployeeObject(){

        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Onur")
//                .lastName("Haktan")
//                .email("onur@email.com")
//                .build();

        employeeRepository.save(employee);

        // when - action or behaviour that we are going to test
        Employee savedEmployee = employeeRepository.findByNativeSQL(employee.getFirstName(), employee.getLastName());

        // then - verify the output
        assertThat(savedEmployee).isNotNull();
    }

    // JUnit test for custom query using Native SQL with index params
    @DisplayName("JUnit test for custom query using Native SQL with named params")
    @Test
    public void givenFirstNameAndLastName_whenFindByNativeSQLNamedParams_thenReturnEmployeeObject(){
        // given - precondition or setup
        Employee employee = Employee.builder()
                .firstName("Onur")
                .lastName("Haktan")
                .email("onur@email.com")
                .build();

        employeeRepository.save(employee);

        // when - action or behaviour that we are going to test
        Employee savedEmployee = employeeRepository.findByNativeSQLNamedParams(employee.getFirstName(), employee.getLastName());

        // then - verify the output
        assertThat(savedEmployee).isNotNull();
    }
}
