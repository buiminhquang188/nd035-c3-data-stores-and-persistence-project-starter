package com.udacity.jdnd.course3.critter.user.enums;

/**
 * A example list of employee skills that could be included on an employee or a schedule request.
 */
public enum EmployeeSkill {
    PETTING(1),
    WALKING(2),
    FEEDING(3),
    MEDICATING(4),
    SHAVING(5);

    private final Integer id;

    EmployeeSkill(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
