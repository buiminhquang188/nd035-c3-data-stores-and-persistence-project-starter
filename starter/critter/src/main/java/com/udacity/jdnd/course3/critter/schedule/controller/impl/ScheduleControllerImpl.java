package com.udacity.jdnd.course3.critter.schedule.controller.impl;

import com.udacity.jdnd.course3.critter.schedule.controller.ScheduleController;
import com.udacity.jdnd.course3.critter.schedule.dto.ScheduleDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ScheduleControllerImpl implements ScheduleController {
    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        throw new UnsupportedOperationException();
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        throw new UnsupportedOperationException();
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        throw new UnsupportedOperationException();
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        throw new UnsupportedOperationException();
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        throw new UnsupportedOperationException();
    }
}
