package cz.zcu.krausp.ups.utils;

public enum Action {
    ATTACK ("Attack"),
    DEFENCE ("Defence"),
    CANCELATTACK ("CancelAttack"),
    CANCELDEFENCE ("CancelDefence"),
    NEXT ("Next"),
    AGAIN ("Again"),
    LEAVE ("Leave");

    private final String label;

    Action(String label) {
        this.label = label;
    }
}
