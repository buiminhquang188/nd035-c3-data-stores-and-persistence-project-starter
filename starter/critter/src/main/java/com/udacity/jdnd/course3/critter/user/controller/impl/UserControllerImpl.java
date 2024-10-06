package com.udacity.jdnd.course3.critter.user.controller.impl;

import com.udacity.jdnd.course3.critter.user.controller.UserController;
import com.udacity.jdnd.course3.critter.user.dto.CustomerDTO;
import com.udacity.jdnd.course3.critter.user.dto.EmployeeDTO;
import com.udacity.jdnd.course3.critter.user.dto.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserControllerImpl implements UserController {
    private final UserService userService;

    public UserControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<CustomerDTO> saveCustomer(CustomerDTO customerDTO) {
        CustomerDTO customer = this.userService.saveCustomer(customerDTO);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        List<CustomerDTO> customers = this.userService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    @Override
    public ResponseEntity<CustomerDTO> getOwnerByPet(Long petId) {
        CustomerDTO owner = this.userService.getOwnerByPet(petId);
        return ResponseEntity.ok(owner);
    }

    @Override
    public ResponseEntity<EmployeeDTO> saveEmployee(EmployeeDTO employeeDTO) {
        EmployeeDTO employee = this.userService.saveEmployee(employeeDTO);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<EmployeeDTO> getEmployee(Long employeeId) {
        EmployeeDTO employee = this.userService.getEmployee(employeeId);
        return ResponseEntity.ok(employee);
    }

    @Override
    public void setAvailability(Set<DayOfWeek> daysAvailable, Long employeeId) {
        this.userService.setAvailability(daysAvailable, employeeId);
    }

    @Override
    public ResponseEntity<List<EmployeeDTO>> findEmployeesForService(EmployeeRequestDTO employeeDTO) {
        List<EmployeeDTO> employee = this.userService.findEmployeesForService(employeeDTO);
        return ResponseEntity.ok(employee);
    }
}
