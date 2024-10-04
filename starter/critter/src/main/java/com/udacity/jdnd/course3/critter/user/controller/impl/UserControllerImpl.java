package com.udacity.jdnd.course3.critter.user.controller.impl;

import com.udacity.jdnd.course3.critter.user.controller.UserController;
import com.udacity.jdnd.course3.critter.user.dto.CustomerDTO;
import com.udacity.jdnd.course3.critter.user.dto.EmployeeDTO;
import com.udacity.jdnd.course3.critter.user.dto.EmployeeRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserControllerImpl implements UserController {
    @Override
    public ResponseEntity<CustomerDTO> saveCustomer(CustomerDTO customerDTO) {
        return null;
    }

    @Override
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        return null;
    }

    @Override
    public ResponseEntity<CustomerDTO> getOwnerByPet(long petId) {
        return null;
    }

    @Override
    public ResponseEntity<EmployeeDTO> saveEmployee(EmployeeDTO employeeDTO) {
        return null;
    }

    @Override
    public ResponseEntity<EmployeeDTO> getEmployee(long employeeId) {
        return null;
    }

    @Override
    public void setAvailability(Set<DayOfWeek> daysAvailable, long employeeId) {

    }

    @Override
    public ResponseEntity<List<EmployeeDTO>> findEmployeesForService(EmployeeRequestDTO employeeDTO) {
        return null;
    }
}
