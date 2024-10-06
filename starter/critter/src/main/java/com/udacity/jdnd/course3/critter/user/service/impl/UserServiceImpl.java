package com.udacity.jdnd.course3.critter.user.service.impl;

import com.udacity.jdnd.course3.critter.common.exception.runtime.NotFoundException;
import com.udacity.jdnd.course3.critter.schedule.repository.ScheduleRepository;
import com.udacity.jdnd.course3.critter.user.dto.CustomerDTO;
import com.udacity.jdnd.course3.critter.user.dto.EmployeeDTO;
import com.udacity.jdnd.course3.critter.user.dto.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.user.entity.*;
import com.udacity.jdnd.course3.critter.user.entity.key.IdUserOperationTime;
import com.udacity.jdnd.course3.critter.user.entity.key.IdUserSkill;
import com.udacity.jdnd.course3.critter.user.enums.EmployeeSkill;
import com.udacity.jdnd.course3.critter.user.enums.RoleType;
import com.udacity.jdnd.course3.critter.user.mapper.UserMapper;
import com.udacity.jdnd.course3.critter.user.repository.*;
import com.udacity.jdnd.course3.critter.user.service.UserService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final SkillRepository skillRepository;
    private final UserSkillRepository userSkillRepository;
    private final RoleRepository roleRepository;
    private final OperationTimeRepository operationTimeRepository;
    private final UserOperationTimeRepository userOperationTimeRepository;
    private final ScheduleRepository scheduleRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, SkillRepository skillRepository, UserSkillRepository userSkillRepository, RoleRepository roleRepository, OperationTimeRepository operationTimeRepository, UserOperationTimeRepository userOperationTimeRepository, ScheduleRepository scheduleRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.skillRepository = skillRepository;
        this.userSkillRepository = userSkillRepository;
        this.roleRepository = roleRepository;
        this.operationTimeRepository = operationTimeRepository;
        this.userOperationTimeRepository = userOperationTimeRepository;
        this.scheduleRepository = scheduleRepository;
        this.userMapper = userMapper;
    }

    @Transactional
    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        UserEntity user = new UserEntity();
        user.setName(customerDTO.getName());
        user.setPhoneNumber(customerDTO.getPhoneNumber());

        Optional<RoleEntity> optionalRole = this.roleRepository.findById(RoleType.CUSTOMER.getValue());
        RoleEntity role = optionalRole.get();

        user.setRole(role);
        UserEntity savedUser = this.userRepository.save(user);

        return this.userMapper.toCustomerDTO(savedUser);
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<UserEntity> users = this.userRepository.findAll();
        return this.userMapper.toCustomerDTOs(users);
    }

    @Override
    public CustomerDTO getOwnerByPet(Long petId) {
        UserEntity user = this.userRepository.findByPetsId(petId);
        return this.userMapper.toCustomerDTO(user);
    }

    @Transactional
    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        UserEntity employee = new UserEntity();

        Optional<RoleEntity> optionalRole = this.roleRepository.findById(RoleType.EMPLOYEE.getValue());
        RoleEntity role = optionalRole.get();

        employee.setName(employeeDTO.getName());
        employee.setRole(role);

        UserEntity savedEmployee = this.userRepository.save(employee);

        Set<UserSkillEntity> userSkills = this.skillRepository.findByIdIn(employeeDTO.getSkills()
                        .stream()
                        .map(EmployeeSkill::getId)
                        .collect(Collectors.toSet()))
                .stream()
                .map(skillEntity -> {
                    IdUserSkill idUserSkill = new IdUserSkill();
                    idUserSkill.setSkillId(skillEntity.getId());
                    idUserSkill.setUserId(savedEmployee.getId());

                    UserSkillEntity userSkill = new UserSkillEntity();
                    userSkill.setId(idUserSkill);
                    return userSkill;
                })
                .collect(Collectors.toSet());

        this.userSkillRepository.saveAll(userSkills);

        employee.setUserSkills(userSkills);
        return this.userMapper.toEmployeeDTO(employee);
    }

    @Override
    public EmployeeDTO getEmployee(Long employeeId) {
        Optional<UserEntity> employee = this.userRepository.findById(employeeId);
        return this.userMapper.toEmployeeDTO(
                employee.orElseThrow(() -> new NotFoundException("Employee Not Found"))
        );
    }

    @Transactional
    @Override
    public void setAvailability(Set<DayOfWeek> daysAvailable, Long employeeId) {
        Optional<UserEntity> optionalEmployee = this.userRepository.findById(employeeId);
        UserEntity employee = optionalEmployee.orElseThrow(
                () -> new NotFoundException("Employee Not Found")
        );

        Set<OperationTimeEntity> operationTimes = this.operationTimeRepository.findByDayOfWeekIn(daysAvailable);
        Set<UserOperationTimeEntity> userOperationTimes = operationTimes.stream()
                .filter(Objects::nonNull)
                .map(operationTime -> {
                    IdUserOperationTime idUserOperationTime = new IdUserOperationTime();
                    idUserOperationTime.setUserId(employee.getId());
                    idUserOperationTime.setOperationTimeId(operationTime.getId());

                    UserOperationTimeEntity userOperationTime = new UserOperationTimeEntity();
                    userOperationTime.setIdUserOperationTime(idUserOperationTime);
                    return userOperationTime;
                })
                .collect(Collectors.toSet());

        this.userOperationTimeRepository.saveAll(userOperationTimes);
        employee.setUserOperationTimes(userOperationTimes);
        this.userRepository.save(employee);
    }

    @Override
    public List<EmployeeDTO> findEmployeesForService(EmployeeRequestDTO employeeDTO) {
        DayOfWeek dayOfWeek = employeeDTO.getDate()
                .getDayOfWeek();
        List<UserEntity> users = this.userRepository.findAllEmployeeAvailability(dayOfWeek, employeeDTO.getSkills(), RoleType.EMPLOYEE);

        return this.userMapper.toEmployeeDTOs(users);
    }
}
