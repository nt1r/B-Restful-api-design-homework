package com.thoughtworks.capability.gtb.restfulapidesign.entity;

public enum Gender {
    Male("male"), Female("female");

    private final String description;

    Gender(String description) {
        this.description = description;
    }
}
