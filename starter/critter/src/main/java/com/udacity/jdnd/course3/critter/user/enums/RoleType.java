package com.udacity.jdnd.course3.critter.user.enums;

public enum RoleType {
    CUSTOMER(1),
    EMPLOYEE(2);

    private Integer value;

    RoleType(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
