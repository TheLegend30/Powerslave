package com.example.powerslave.government;

public enum Continent {
    EY,
    NY,
    SY,
    WY,

    IB,
    OB,

    CA,
    FA,

    ME,

    GE;

    @Override
    public String toString() {
        switch (this) {
            case EY:
                return "Eastern Yutrera";
            case NY:
                return "Northern Yutrera";
            case SY:
                return "Southern Yutrera";
            case WY:
                return "Western Yutrera";

            case IB:
                return "Inner Brumane";
            case OB:
                return "Outer Brumane";

            case CA:
                return "Central Adus";
            case FA:
                return "Far Adus";

            case ME:
                return "Mehela";

            case GE:
                return "Great Ewira";

            default:
                return "No Continent";
        }
    }
}
