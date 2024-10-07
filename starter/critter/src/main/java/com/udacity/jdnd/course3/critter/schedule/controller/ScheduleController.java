package com.udacity.jdnd.course3.critter.schedule.controller;

import com.udacity.jdnd.course3.critter.schedule.dto.ScheduleDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Handles web requests related to Schedules.
 */
@RequestMapping("/schedule")
public interface ScheduleController {
    @PostMapping
    ResponseEntity<ScheduleDTO> createSchedule(@RequestBody ScheduleDTO scheduleDTO);

    @GetMapping
    ResponseEntity<List<ScheduleDTO>> getAllSchedules();

    @GetMapping("/pet/{petId}")
    ResponseEntity<List<ScheduleDTO>> getScheduleForPet(@PathVariable long petId);

    @GetMapping("/employee/{employeeId}")
    ResponseEntity<List<ScheduleDTO>> getScheduleForEmployee(@PathVariable long employeeId);

    @GetMapping("/customer/{customerId}")
    ResponseEntity<List<ScheduleDTO>> getScheduleForCustomer(@PathVariable long customerId);
}
