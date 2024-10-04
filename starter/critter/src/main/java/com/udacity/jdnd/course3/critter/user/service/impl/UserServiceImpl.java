package com.udacity.jdnd.course3.critter.user.service.impl;

import com.udacity.jdnd.course3.critter.common.exception.runtime.NotFoundException;
import com.udacity.jdnd.course3.critter.user.dto.CustomerDTO;
import com.udacity.jdnd.course3.critter.user.dto.EmployeeDTO;
import com.udacity.jdnd.course3.critter.user.dto.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.user.entity.RoleEntity;
import com.udacity.jdnd.course3.critter.user.entity.UserEntity;
import com.udacity.jdnd.course3.critter.user.entity.UserSkillEntity;
import com.udacity.jdnd.course3.critter.user.entity.key.IdUserSkill;
import com.udacity.jdnd.course3.critter.user.enums.EmployeeSkill;
import com.udacity.jdnd.course3.critter.user.enums.RoleType;
import com.udacity.jdnd.course3.critter.user.mapper.UserMapper;
import com.udacity.jdnd.course3.critter.user.repository.RoleRepository;
import com.udacity.jdnd.course3.critter.user.repository.SkillRepository;
import com.udacity.jdnd.course3.critter.user.repository.UserRepository;
import com.udacity.jdnd.course3.critter.user.repository.UserSkillRepository;
import com.udacity.jdnd.course3.critter.user.service.UserService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final SkillRepository skillRepository;
    private final UserSkillRepository userSkillRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, SkillRepository skillRepository, UserSkillRepository userSkillRepository, RoleRepository roleRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.skillRepository = skillRepository;
        this.userSkillRepository = userSkillRepository;
        this.roleRepository = roleRepository;
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

        employee.setName(employeeDTO.getName());
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

    @Override
    public void setAvailability(Set<DayOfWeek> daysAvailable, Long employeeId) {
    }

    @Override
    public List<EmployeeDTO> findEmployeesForService(EmployeeRequestDTO employeeDTO) {
        return null;
    }
}
