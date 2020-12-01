package cz.zcu.krausp.ups.game;

public class Land extends Permanent {

    private final int manaSource;

    private static final String CARD_TYPE = "Land";

    public Land(int id, int manaSource) {
        this(id, CARD_TYPE + "#" + id, manaSource);
    }

    public Land(int id, String name, int manaSource) {
        super(id, name);

        this.manaSource = manaSource;
    }

    public int getManaSource() {
        return manaSource;
    }

    public static String getCardType() {
        return CARD_TYPE;
    }
}
