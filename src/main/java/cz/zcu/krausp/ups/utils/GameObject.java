package cz.zcu.krausp.ups.utils;

public enum GameObject {
    CARD ("Card"),
    HP ("HP"),
    HAND ("Hand"),
    BATTLEFIELD ("Battlefield"),
    UPDATE ("Update"),
    TURN ("Turn"),
    MULLIGAN ("Mulligan");

    public final String label;

    GameObject(String label) {
        this.label = label;
    }
}
