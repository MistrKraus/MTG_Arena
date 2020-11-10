package cz.zcu.krausp.ups.game;

public abstract class Card implements IDrawable {

    protected final int id;
    protected final String name;

    protected Card(int id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getId() + " - " + getName();
    }
}
