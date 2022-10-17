package com.example.powerslave.government;

public enum Ideology {
    PLYCISM,
    VYHUCISM,
    AREWOSM,
    HYCROCISM,
    ZOWOTISM,
    BECLYSM,
    NEOLEEISM,
    FOKRITISM,
    NONPARTISAN;

    @Override
    public String toString() {
        switch (this) {
            case PLYCISM:
                return "Plycism";
            case VYHUCISM:
                return "Vyhucism";
            case AREWOSM:
                return "Arewosm";
            case HYCROCISM:
                return "Hycrocism";
            case ZOWOTISM:
                return "Zowotism";
            case BECLYSM:
                return "Beclysm";
            case NEOLEEISM:
                return "Neo-Leeism";
            case FOKRITISM:
                return "Fokritism";
            case NONPARTISAN:
            default:
                return "Nonpartisan";
        }
    }
}
