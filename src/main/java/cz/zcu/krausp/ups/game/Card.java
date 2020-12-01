package cz.zcu.krausp.ups.game;

public abstract class Card {

    protected final int id;
    protected final String name;

    protected boolean selected;

    protected Card(int id, String name) {
        this.id = id;
        this.name = name;

        this.selected = false;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public String toString() {
        return getId() + " - " + getName();
    }
}
