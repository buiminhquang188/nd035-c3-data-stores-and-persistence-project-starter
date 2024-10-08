package com.udacity.jdnd.course3.critter;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.udacity.jdnd.course3.critter.pet.controller.PetController;
import com.udacity.jdnd.course3.critter.pet.dto.PetDTO;
import com.udacity.jdnd.course3.critter.pet.enums.PetType;
import com.udacity.jdnd.course3.critter.schedule.controller.ScheduleController;
import com.udacity.jdnd.course3.critter.schedule.dto.ScheduleDTO;
import com.udacity.jdnd.course3.critter.user.controller.UserController;
import com.udacity.jdnd.course3.critter.user.dto.CustomerDTO;
import com.udacity.jdnd.course3.critter.user.dto.EmployeeDTO;
import com.udacity.jdnd.course3.critter.user.dto.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.user.enums.EmployeeSkill;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * This is a set of functional tests to validate the basic capabilities desired for this application.
 * Students will need to configure the application to run these tests by adding application-test.properties file
 * to the test/resources directory that specifies the datasource. It can run using an in-memory H2 instance
 * and should not try to re-use the same datasource used by the rest of the app.
 * <p>
 * These tests should all pass once the project is complete.
 */
@Transactional
@SpringBootTest(classes = CritterApplication.class)
@TestPropertySource("classpath:application-test.properties")
public class CritterFunctionalTest {

    @Autowired
    private UserController userController;

    @Autowired
    private PetController petController;

    @Autowired
    private ScheduleController scheduleController;

    @Test
    public void testCreateCustomer() {
        CustomerDTO customerDTO = createCustomerDTO();
        ResponseEntity<CustomerDTO> newCustomer = userController.saveCustomer(customerDTO);
        ResponseEntity<List<CustomerDTO>> retrievedCustomer = userController.getAllCustomers();
        Assertions.assertEquals(newCustomer.getBody()
                .getName(), customerDTO.getName());
        Assertions.assertEquals(newCustomer.getBody()
                .getId(), retrievedCustomer.getBody()
                .get(0)
                .getId());
        Assertions.assertTrue(retrievedCustomer.getBody()
                                      .get(0)
                                      .getId() > 0);
    }

    @Test
    public void testCreateEmployee() {
        EmployeeDTO employeeDTO = createEmployeeDTO();
        ResponseEntity<EmployeeDTO> newEmployee = userController.saveEmployee(employeeDTO);
        ResponseEntity<EmployeeDTO> retrievedEmployee = userController.getEmployee(newEmployee.getBody()
                .getId());
        Assertions.assertEquals(employeeDTO.getSkills(), newEmployee.getBody()
                .getSkills());
        Assertions.assertEquals(newEmployee.getBody()
                .getId(), retrievedEmployee.getBody()
                .getId());
        Assertions.assertTrue(retrievedEmployee.getBody()
                                      .getId() > 0);
    }


    @Test
    public void testAddPetsToCustomer() {
        CustomerDTO customerDTO = createCustomerDTO();
        ResponseEntity<CustomerDTO> newCustomer = userController.saveCustomer(customerDTO);

        PetDTO petDTO = createPetDTO();
        petDTO.setOwnerId(newCustomer.getBody()
                .getId());
        ResponseEntity<PetDTO> newPet = petController.savePet(petDTO);

        //make sure pet contains customer id
        ResponseEntity<PetDTO> retrievedPet = petController.getPet(newPet.getBody()
                .getId());
        Assertions.assertEquals(retrievedPet.getBody()
                .getId(), newPet.getBody()
                .getId());
        Assertions.assertEquals(retrievedPet.getBody()
                .getOwnerId(), newCustomer.getBody()
                .getId());

        //make sure you can retrieve pets by owner
        ResponseEntity<List<PetDTO>> pets = petController.getPetsByOwner(newCustomer.getBody()
                .getId());
        Assertions.assertEquals(newPet.getBody()
                .getId(), pets.getBody()
                .get(0)
                .getId());
        Assertions.assertEquals(newPet.getBody()
                .getName(), pets.getBody()
                .get(0)
                .getName());

        //check to make sure customer now also contains pet
        CustomerDTO retrievedCustomer = userController.getAllCustomers()
                .getBody()
                .get(0);
        Assertions.assertTrue(retrievedCustomer.getPetIds() != null && retrievedCustomer.getPetIds()
                                                                               .size() > 0);
        Assertions.assertEquals(retrievedCustomer.getPetIds()
                .get(0), retrievedPet.getBody()
                .getId());
    }

    @Test
    public void testFindPetsByOwner() {
        CustomerDTO customerDTO = createCustomerDTO();
        ResponseEntity<CustomerDTO> newCustomer = userController.saveCustomer(customerDTO);

        PetDTO petDTO = createPetDTO();
        petDTO.setOwnerId(newCustomer.getBody()
                .getId());
        ResponseEntity<PetDTO> newPet = petController.savePet(petDTO);
        petDTO.setType(PetType.DOG);
        petDTO.setName("DogName");
        ResponseEntity<PetDTO> newPet2 = petController.savePet(petDTO);

        ResponseEntity<List<PetDTO>> pets = petController.getPetsByOwner(newCustomer.getBody()
                .getId());
        Assertions.assertEquals(pets.getBody()
                .size(), 2);
        Assertions.assertEquals(pets.getBody()
                .get(0)
                .getOwnerId(), newCustomer.getBody()
                .getId());
        Assertions.assertEquals(pets.getBody()
                .get(0)
                .getId(), newPet.getBody()
                .getId());
    }

    @Test
    public void testFindOwnerByPet() {
        CustomerDTO customerDTO = createCustomerDTO();
        ResponseEntity<CustomerDTO> newCustomer = userController.saveCustomer(customerDTO);

        PetDTO petDTO = createPetDTO();
        petDTO.setOwnerId(newCustomer.getBody()
                .getId());
        ResponseEntity<PetDTO> newPet = petController.savePet(petDTO);

        ResponseEntity<CustomerDTO> owner = userController.getOwnerByPet(newPet.getBody()
                .getId());
        Assertions.assertEquals(owner.getBody()
                .getId(), newCustomer.getBody()
                .getId());
        Assertions.assertEquals(owner.getBody()
                .getPetIds()
                .get(0), newPet.getBody()
                .getId());
    }

    @Test
    public void testChangeEmployeeAvailability() {
        EmployeeDTO employeeDTO = createEmployeeDTO();
        ResponseEntity<EmployeeDTO> emp1 = userController.saveEmployee(employeeDTO);
        Assertions.assertNull(emp1.getBody()
                .getDaysAvailable());

        Set<DayOfWeek> availability = Sets.newHashSet(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY);
        userController.setAvailability(availability, emp1.getBody()
                .getId());

        ResponseEntity<EmployeeDTO> emp2 = userController.getEmployee(emp1.getBody()
                .getId());
        Assertions.assertEquals(availability, emp2.getBody()
                .getDaysAvailable());
    }

    @Test
    public void testFindEmployeesByServiceAndTime() {
        EmployeeDTO emp1 = createEmployeeDTO();
        EmployeeDTO emp2 = createEmployeeDTO();
        EmployeeDTO emp3 = createEmployeeDTO();

        emp1.setDaysAvailable(Sets.newHashSet(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY));
        emp2.setDaysAvailable(Sets.newHashSet(DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY));
        emp3.setDaysAvailable(Sets.newHashSet(DayOfWeek.FRIDAY, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY));

        emp1.setSkills(Sets.newHashSet(EmployeeSkill.FEEDING, EmployeeSkill.PETTING));
        emp2.setSkills(Sets.newHashSet(EmployeeSkill.PETTING, EmployeeSkill.WALKING));
        emp3.setSkills(Sets.newHashSet(EmployeeSkill.WALKING, EmployeeSkill.SHAVING));

        ResponseEntity<EmployeeDTO> emp1n = userController.saveEmployee(emp1);
        ResponseEntity<EmployeeDTO> emp2n = userController.saveEmployee(emp2);
        ResponseEntity<EmployeeDTO> emp3n = userController.saveEmployee(emp3);

        //make a request that matches employee 1 or 2
        EmployeeRequestDTO er1 = new EmployeeRequestDTO();
        er1.setDate(LocalDate.of(2019, 12, 25)); //wednesday
        er1.setSkills(Sets.newHashSet(EmployeeSkill.PETTING));

        Set<Long> eIds1 = userController.findEmployeesForService(er1)
                .getBody()
                .stream()
                .map(EmployeeDTO::getId)
                .collect(Collectors.toSet());
        Set<Long> eIds1expected = Sets.newHashSet(emp1n.getBody()
                .getId(), emp2n.getBody()
                .getId());
        Assertions.assertEquals(eIds1expected, eIds1);

        //make a request that matches only employee 3
        EmployeeRequestDTO er2 = new EmployeeRequestDTO();
        er2.setDate(LocalDate.of(2019, 12, 27)); //friday
        er2.setSkills(Sets.newHashSet(EmployeeSkill.WALKING, EmployeeSkill.SHAVING));

        Set<Long> eIds2 = userController.findEmployeesForService(er2)
                .getBody()
                .stream()
                .map(EmployeeDTO::getId)
                .collect(Collectors.toSet());
        Set<Long> eIds2expected = Sets.newHashSet(emp3n.getBody()
                .getId());
        Assertions.assertEquals(eIds2expected, eIds2);
    }

    @Test
    public void testSchedulePetsForServiceWithEmployee() {
        EmployeeDTO employeeTemp = createEmployeeDTO();
        employeeTemp.setDaysAvailable(Sets.newHashSet(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY));
        ResponseEntity<EmployeeDTO> employeeDTO = userController.saveEmployee(employeeTemp);
        ResponseEntity<CustomerDTO> customerDTO = userController.saveCustomer(createCustomerDTO());
        PetDTO petTemp = createPetDTO();
        petTemp.setOwnerId(customerDTO.getBody()
                .getId());
        ResponseEntity<PetDTO> petDTO = petController.savePet(petTemp);

        LocalDate date = LocalDate.of(2019, 12, 25);
        List<Long> petList = Lists.newArrayList(petDTO.getBody()
                .getId());
        List<Long> employeeList = Lists.newArrayList(employeeDTO.getBody()
                .getId());
        Set<EmployeeSkill> skillSet = Sets.newHashSet(EmployeeSkill.PETTING);

        scheduleController.createSchedule(createScheduleDTO(petList, employeeList, date, skillSet));
        ScheduleDTO scheduleDTO = scheduleController.getAllSchedules()
                .getBody()
                .get(0);

        Assertions.assertEquals(scheduleDTO.getActivities(), skillSet);
        Assertions.assertEquals(scheduleDTO.getDate(), date);
        Assertions.assertEquals(scheduleDTO.getEmployeeIds(), employeeList);
        Assertions.assertEquals(scheduleDTO.getPetIds(), petList);
    }

    @Test
    public void testFindScheduleByEntities() {
        ScheduleDTO sched1 = populateSchedule(1, 2, LocalDate.of(2019, 12, 25), Sets.newHashSet(EmployeeSkill.FEEDING, EmployeeSkill.WALKING));
        ScheduleDTO sched2 = populateSchedule(3, 1, LocalDate.of(2019, 12, 26), Sets.newHashSet(EmployeeSkill.PETTING));

        //add a third schedule that shares some employees and pets with the other schedules
        ScheduleDTO sched3 = new ScheduleDTO();
        sched3.setEmployeeIds(sched1.getEmployeeIds());
        sched3.setPetIds(sched2.getPetIds());
        sched3.setActivities(Sets.newHashSet(EmployeeSkill.SHAVING, EmployeeSkill.PETTING));
        sched3.setDate(LocalDate.of(2020, 3, 23));
        scheduleController.createSchedule(sched3);

        /*
            We now have 3 schedule entries. The third schedule entry has the same employees as the 1st schedule
            and the same pets/owners as the second schedule. So if we look up schedule entries for the employee from
            schedule 1, we should get both the first and third schedule as our result.
         */

        //Employee 1 in is both schedule 1 and 3
        List<ScheduleDTO> scheds1e = scheduleController.getScheduleForEmployee(sched1.getEmployeeIds()
                        .get(0))
                .getBody();
        compareSchedules(sched1, scheds1e.get(0));
        compareSchedules(sched3, scheds1e.get(1));

        //Employee 2 is only in schedule 2
        List<ScheduleDTO> scheds2e = scheduleController.getScheduleForEmployee(sched2.getEmployeeIds()
                        .get(0))
                .getBody();
        compareSchedules(sched2, scheds2e.get(0));

        //Pet 1 is only in schedule 1
        List<ScheduleDTO> scheds1p = scheduleController.getScheduleForPet(sched1.getPetIds()
                        .get(0))
                .getBody();
        compareSchedules(sched1, scheds1p.get(0));

        //Pet from schedule 2 is in both schedules 2 and 3
        List<ScheduleDTO> scheds2p = scheduleController.getScheduleForPet(sched2.getPetIds()
                        .get(0))
                .getBody();
        compareSchedules(sched2, scheds2p.get(0));
        compareSchedules(sched3, scheds2p.get(1));

        //Owner of the first pet will only be in schedule 1
        List<ScheduleDTO> scheds1c = scheduleController.getScheduleForCustomer(userController.getOwnerByPet(sched1.getPetIds()
                                .get(0))
                        .getBody()
                        .getId())
                .getBody();
        compareSchedules(sched1, scheds1c.get(0));

        //Owner of pet from schedule 2 will be in both schedules 2 and 3
        List<ScheduleDTO> scheds2c = scheduleController.getScheduleForCustomer(userController.getOwnerByPet(sched2.getPetIds()
                                .get(0))
                        .getBody()
                        .getId())
                .getBody();
        compareSchedules(sched2, scheds2c.get(0));
        compareSchedules(sched3, scheds2c.get(1));
    }


    private static EmployeeDTO createEmployeeDTO() {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setName("TestEmployee");
        employeeDTO.setSkills(Sets.newHashSet(EmployeeSkill.FEEDING, EmployeeSkill.PETTING));
        return employeeDTO;
    }

    private static CustomerDTO createCustomerDTO() {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName("TestEmployee");
        customerDTO.setPhoneNumber("123-456-789");
        return customerDTO;
    }

    private static PetDTO createPetDTO() {
        PetDTO petDTO = new PetDTO();
        petDTO.setName("TestPet");
        petDTO.setType(PetType.CAT);
        return petDTO;
    }

    private static EmployeeRequestDTO createEmployeeRequestDTO() {
        EmployeeRequestDTO employeeRequestDTO = new EmployeeRequestDTO();
        employeeRequestDTO.setDate(LocalDate.of(2019, 12, 25));
        employeeRequestDTO.setSkills(Sets.newHashSet(EmployeeSkill.FEEDING, EmployeeSkill.WALKING));
        return employeeRequestDTO;
    }

    private static ScheduleDTO createScheduleDTO(List<Long> petIds, List<Long> employeeIds, LocalDate date, Set<EmployeeSkill> activities) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setPetIds(petIds);
        scheduleDTO.setEmployeeIds(employeeIds);
        scheduleDTO.setDate(date);
        scheduleDTO.setActivities(activities);
        return scheduleDTO;
    }

    private ScheduleDTO populateSchedule(int numEmployees, int numPets, LocalDate date, Set<EmployeeSkill> activities) {
        List<Long> employeeIds = IntStream.range(0, numEmployees)
                .mapToObj(i -> createEmployeeDTO())
                .map(e -> {
                    e.setSkills(activities);
                    e.setDaysAvailable(Sets.newHashSet(date.getDayOfWeek()));
                    return userController.saveEmployee(e)
                            .getBody()
                            .getId();
                })
                .collect(Collectors.toList());
        ResponseEntity<CustomerDTO> cust = userController.saveCustomer(createCustomerDTO());
        List<Long> petIds = IntStream.range(0, numPets)
                .mapToObj(i -> createPetDTO())
                .map(p -> {
                    p.setOwnerId(cust.getBody()
                            .getId());
                    return petController.savePet(p)
                            .getBody()
                            .getId();
                })
                .collect(Collectors.toList());
        return scheduleController.createSchedule(createScheduleDTO(petIds, employeeIds, date, activities))
                .getBody();
    }

    private static void compareSchedules(ScheduleDTO sched1, ScheduleDTO sched2) {
        Assertions.assertEquals(sched1.getPetIds(), sched2.getPetIds());
        Assertions.assertEquals(sched1.getActivities(), sched2.getActivities());
        Assertions.assertEquals(sched1.getEmployeeIds(), sched2.getEmployeeIds());
        Assertions.assertEquals(sched1.getDate(), sched2.getDate());
    }

}
