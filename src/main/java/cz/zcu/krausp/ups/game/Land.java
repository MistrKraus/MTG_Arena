package cz.zcu.krausp.ups.game;

import javafx.scene.canvas.GraphicsContext;

public class Land extends Permanent {
    private Mana manaSource;

    private static final String CARD_TYPE = "Land";

    public Land(int id, int colorlessSource)
    {
        this(id, new Mana(colorlessSource));
    }

    public Land(int id, Mana manaSource)
    {
        this(id, CARD_TYPE + "#" + id, manaSource);
    }

    public Land(int id, String name, Mana manaSource)
    {
        super(id, name);

        this.manaSource = manaSource;
    }

    public Mana tapForMana()
    {
        if (!super.tapCard())
        {
            return new Mana(0);
        }

        return this.manaSource;
    }

    @Override
    public void draw(GraphicsContext g, double scaleX, double scaleY) {

    }
}
