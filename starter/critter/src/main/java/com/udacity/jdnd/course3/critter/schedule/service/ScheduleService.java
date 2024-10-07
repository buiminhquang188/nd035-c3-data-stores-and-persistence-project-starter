package com.udacity.jdnd.course3.critter.schedule.service;

import com.udacity.jdnd.course3.critter.schedule.dto.ScheduleDTO;

import java.util.List;

public interface ScheduleService {
    ScheduleDTO createSchedule(ScheduleDTO scheduleDTO);

    List<ScheduleDTO> getAllSchedules();

    List<ScheduleDTO> getScheduleForPet(long petId);

    List<ScheduleDTO> getScheduleForEmployee(long employeeId);

    List<ScheduleDTO> getScheduleForCustomer(long customerId);
}
