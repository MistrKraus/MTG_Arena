package cz.zcu.krausp.ups.game;

public class Mana implements Comparable<Mana> {

    private final int convertedManaCost;

    public Mana(int colorless) {
        this.convertedManaCost = colorless;
    }

    @Override
    public int compareTo(Mana o) {
        return Integer.compare(this.convertedManaCost, o.convertedManaCost);
    }
}
