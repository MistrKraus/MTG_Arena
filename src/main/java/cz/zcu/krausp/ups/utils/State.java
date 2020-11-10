package cz.zcu.krausp.ups.utils;

public enum State {
    CONNECTION("Connection"),
    TRANSITION("Transition"),
    GAME("Game"),
    PING("Ping"),
    PONG("Pong"),
    UNKNOWN("Unknown");

    public final String label;

    State(String label) {
        this.label = label;
    }

    public static State stateOf(String test) {
        for (State c : State.values()) {
            if (c.label.equals(test)) {
                return c;
            }
        }

        return UNKNOWN;
    }
}
