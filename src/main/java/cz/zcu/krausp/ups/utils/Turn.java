package cz.zcu.krausp.ups.utils;

public enum Turn {
    REFUSEFIRST ("RefuseFirst"),
    PLAYFIRST ("PlayFirst");

    private final String label;

    Turn(String label) {
        this.label = label;
    }
}
