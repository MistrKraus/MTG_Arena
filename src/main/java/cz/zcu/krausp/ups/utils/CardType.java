package cz.zcu.krausp.ups.utils;

public enum CardType {
    CREATURE ("Creature"),
    LAND ("Land"),
    UNKNOWN ("Unknown");

    public final String label;

    CardType(String label) {
        this.label = label;
    }
}
