package cz.zcu.krausp.ups.game;

import javafx.scene.canvas.GraphicsContext;

public class Creature extends Permanent {
    private PowerToughness powerToughness;
    private Mana manaCost;

    private final static String CARD_TYPE = "Creature";

    public Creature(int id, int power, int toughness, int colorless)
    {
        this(id, new PowerToughness(power, toughness), new Mana(colorless));
    }

    public Creature(int id, String name, int power, int toughness, int colorless)
    {
        this(id, name, new PowerToughness(power, toughness), new Mana(colorless));
    }

    public Creature(int id, PowerToughness powerToughness, Mana manaCost)
    {
        this(id, CARD_TYPE + "#" + id, powerToughness, manaCost);
    }

    public Creature(int id, String name, PowerToughness powerToughness, Mana manaCost)
    {
        super(id, name);

        this.powerToughness = powerToughness;
        this.manaCost = manaCost;
    }

    public PowerToughness getPowerToughness() {
        return powerToughness;
    }

    public Mana getManaCost() {
        return manaCost;
    }


    @Override
    public void draw(GraphicsContext g, double scaleX, double scaleY) {

    }
}
