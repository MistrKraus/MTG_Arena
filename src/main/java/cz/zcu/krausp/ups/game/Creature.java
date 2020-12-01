package cz.zcu.krausp.ups.game;

public class Creature extends Permanent {
    private final int power;
    private final int toughness;
    private final int manaCost;

    private final static String CARD_TYPE = "Creature";

    public Creature(int id, int power, int toughness, int manaCost)
    {
        this(id, CARD_TYPE + "#" + id, power, toughness, manaCost);
    }

    public Creature(int id, String name, int power, int toughness, int manaCost)
    {
        super(id, name);

        this.power = power;
        this.toughness = toughness;
        this.manaCost = manaCost;
    }

    public int getPower() {
        return this.power;
    }

    public int getToughness() {
        return this.toughness;
    }

    public int getManaCost() {
        return this.manaCost;
    }

    public static String getCardType() {
        return CARD_TYPE;
    }
}
