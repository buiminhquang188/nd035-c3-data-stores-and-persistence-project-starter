package com.udacity.jdnd.course3.critter.schedule.controller.impl;

import com.udacity.jdnd.course3.critter.schedule.controller.ScheduleController;
import com.udacity.jdnd.course3.critter.schedule.dto.ScheduleDTO;
import com.udacity.jdnd.course3.critter.schedule.service.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ScheduleControllerImpl implements ScheduleController {
    private final ScheduleService scheduleService;

    public ScheduleControllerImpl(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping
    public ResponseEntity<ScheduleDTO> createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        ScheduleDTO schedule = this.scheduleService.createSchedule(scheduleDTO);
        return ResponseEntity.ok(schedule);
    }

    @GetMapping
    public ResponseEntity<List<ScheduleDTO>> getAllSchedules() {
        List<ScheduleDTO> schedules = this.scheduleService.getAllSchedules();
        return ResponseEntity.ok(schedules);
    }

    @GetMapping("/pet/{petId}")
    public ResponseEntity<List<ScheduleDTO>> getScheduleForPet(@PathVariable long petId) {
        List<ScheduleDTO> schedules = this.scheduleService.getScheduleForPet(petId);
        return ResponseEntity.ok(schedules);
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<ScheduleDTO>> getScheduleForEmployee(@PathVariable long employeeId) {
        List<ScheduleDTO> schedules = this.scheduleService.getScheduleForEmployee(employeeId);
        return ResponseEntity.ok(schedules);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<ScheduleDTO>> getScheduleForCustomer(@PathVariable long customerId) {
        List<ScheduleDTO> schedules = this.scheduleService.getScheduleForCustomer(customerId);
        return ResponseEntity.ok(schedules);
    }
}
