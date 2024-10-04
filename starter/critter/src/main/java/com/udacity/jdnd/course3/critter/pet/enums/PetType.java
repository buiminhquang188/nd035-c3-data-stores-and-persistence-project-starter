package com.udacity.jdnd.course3.critter.pet.enums;

/**
 * A example list of pet type metadata that could be included on a request to create a pet.
 */
public enum PetType {
    CAT(1),
    DOG(2),
    LIZARD(3),
    BIRD(4),
    FISH(5),
    SNAKE(6),
    OTHER(7);

    private Integer value;

    PetType(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
