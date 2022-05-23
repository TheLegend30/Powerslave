package com.example.powerslave;

public enum Sex {
    MALE,
    FEMALE;

    @Override
    public String toString() {
        switch (this) {
            case MALE:
                return "Male";
            case FEMALE:
                return "Female";
            default:
                throw new IllegalStateException("Unexpected value: " + this);
        }
    }
}
