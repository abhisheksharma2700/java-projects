package com.busbookingsystem.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Gender {
    MALE,
    FEMALE;
//    male,
//    female,
//    Male,
//    Female;


    public static boolean isValidGender(String value) {
        if (value == null) return false;
        for (Gender gender : Gender.values()) {
            if (gender.name().equalsIgnoreCase(value)) {
                return true;

            }
        }
        return false;
    }
}
