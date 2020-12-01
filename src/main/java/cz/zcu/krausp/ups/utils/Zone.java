package cz.zcu.krausp.ups.utils;

public enum Zone {
    LIBRARY ("Library"),
    HAND ("Hand"),
    LANDS ("Lands"),
    BATTLEFIELD ("Battlefield"),
    GRAVEYARD ("Graveyard"),
    STACK ("Stack");

    public final String label;

    Zone(String label) {
        this.label = label;
    }
}
