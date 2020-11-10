package cz.zcu.krausp.ups.utils;

public enum GamePhase {
    BEGGINING ("Beginning"),
    FIRSTMAIN("FirstMain"),
    COMBAT ("Combat"),
    SECONDMAIN("SecondMain"),
    ENDING ("End"),
    SKIP ("Skip");

    public final String label;

    GamePhase(String label) {
        this.label = label;
    }
}
