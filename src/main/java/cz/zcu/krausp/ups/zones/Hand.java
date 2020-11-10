package cz.zcu.krausp.ups.zones;

public class Hand extends NotOrderedZone {

    private int handSize;

    private final static int DEFAULT_HAND_SIZE = 7;

    public Hand() {
        super(false, false);

        this.handSize = DEFAULT_HAND_SIZE;
    }


}
