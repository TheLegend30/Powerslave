package com.example.powerslave.government;

public enum GovernmentType {
    PRE_REP,
    PAR_REP,
    MIX_REP,
    MIL_DIC,
    PER_DIC,
    REL_STA,
    TRI_STA,
    CON_MON,
    ABS_MON,
    PLY_EMP,
    ARE_STA,
    VYH_STA,
    HYC_REP,
    ZOW_REP;

    @Override
    public String toString() {
        switch (this) {
            case PRE_REP:
                return "Presidential Republic";
            case PAR_REP:
                return "Parliamentary Republic";
            case MIX_REP:
                return "Mixed-government Republic";
            case MIL_DIC:
                return "Military Dictatorship";
            case PER_DIC:
                return "Personalist Dictatorship";
            case REL_STA:
                return "Theocracy";
            case TRI_STA:
                return "Tribal State";
            case CON_MON:
                return "Constitutional Monarchy";
            case ABS_MON:
                return "Absolute Monarchy";
            case PLY_EMP:
                return "Plycitic Empire";
            case ARE_STA:
                return "Arewoist State";
            case VYH_STA:
                return "Vyhucitic State";
            case HYC_REP:
                return "Hycrocitic Republic";
            case ZOW_REP:
                return "Zowotic Republic";
            default:
                return "No Government";

        }
    }
}
