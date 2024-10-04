package com.udacity.jdnd.course3.critter.user.controller;

import com.udacity.jdnd.course3.critter.user.dto.CustomerDTO;
import com.udacity.jdnd.course3.critter.user.dto.EmployeeDTO;
import com.udacity.jdnd.course3.critter.user.dto.EmployeeRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

/**
 * Handles web requests related to Users.
 * <p>
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */

public interface UserController {
    @PostMapping("/customer")
    ResponseEntity<CustomerDTO> saveCustomer(@RequestBody CustomerDTO customerDTO);

    @GetMapping("/customer")
    ResponseEntity<List<CustomerDTO>> getAllCustomers();

    @GetMapping("/customer/pet/{petId}")
    ResponseEntity<CustomerDTO> getOwnerByPet(@PathVariable long petId);

    @PostMapping("/employee")
    ResponseEntity<EmployeeDTO> saveEmployee(@RequestBody EmployeeDTO employeeDTO);

    @PostMapping("/employee/{employeeId}")
    ResponseEntity<EmployeeDTO> getEmployee(@PathVariable long employeeId);

    @PutMapping("/employee/{employeeId}")
    void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId);

    @GetMapping("/employee/availability")
    ResponseEntity<List<EmployeeDTO>> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO);
}
