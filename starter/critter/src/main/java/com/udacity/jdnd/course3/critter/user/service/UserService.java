package com.udacity.jdnd.course3.critter.user.service;

import com.udacity.jdnd.course3.critter.user.dto.CustomerDTO;
import com.udacity.jdnd.course3.critter.user.dto.EmployeeDTO;
import com.udacity.jdnd.course3.critter.user.dto.EmployeeRequestDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

public interface UserService {
    CustomerDTO saveCustomer(CustomerDTO customerDTO);

    List<CustomerDTO> getAllCustomers();

    CustomerDTO getOwnerByPet(Long petId);

    EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO);

    EmployeeDTO getEmployee(@PathVariable Long employeeId);

    void setAvailability(Set<DayOfWeek> daysAvailable, Long employeeId);

    List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO);
}
