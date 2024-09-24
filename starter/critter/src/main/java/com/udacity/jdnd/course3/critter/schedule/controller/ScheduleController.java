package com.udacity.jdnd.course3.critter.schedule.controller;

import com.udacity.jdnd.course3.critter.schedule.dto.ScheduleDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Handles web requests related to Schedules.
 */
@RequestMapping("/schedule")
public interface ScheduleController {
    @PostMapping
    ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO);

    @GetMapping
    List<ScheduleDTO> getAllSchedules();

    @GetMapping("/pet/{petId}")
    List<ScheduleDTO> getScheduleForPet(@PathVariable long petId);

    @GetMapping("/employee/{employeeId}")
    List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId);

    @GetMapping("/customer/{customerId}")
    List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId);
}
