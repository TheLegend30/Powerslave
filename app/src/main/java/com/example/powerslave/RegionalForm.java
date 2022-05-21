package com.example.powerslave;

public enum RegionalForm {
    UNI,
    FED,
    CNF;

    @Override
    public String toString() {
        switch (this) {
            case UNI:
                return "Unitary";
            case FED:
                return "Federal";
            case CNF:
                return "Confederate";
            default:
                return "No";
        }
    }
}
