package cz.zcu.krausp.ups.utils;

public enum GamePhase {
    BEGGINING ("Beginning"),
    FIRSTMAIN("FirstMain"),
    COMBAT ("Combat"),
    ATTACK("Attack"),
    DEFEND("Defend"),
    SECONDMAIN("SecondMain"),
    ENDING ("End"),
    SKIP ("Skip"),
    UNKNOWN ("Unknown");

    public final String label;

    GamePhase(String label) {
        this.label = label;
    }
}
