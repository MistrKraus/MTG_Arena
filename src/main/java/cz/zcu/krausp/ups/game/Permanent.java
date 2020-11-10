package cz.zcu.krausp.ups.game;

public abstract class Permanent extends Card {

    protected boolean tapped;

    protected Permanent(int id, String name) {
        super(id, name);
    }

    /**
     * Tap permanent
     *
     * @return if tapping permanent was successful
     */
    public boolean tapCard()
    {
        if (tapped)
        {
            return false;
        }

        this.tapped = true;
        return true;
    }

    /**
     * Untap permanent
     *
     * @return if untapping permanent was successful
     */
    public boolean untapCard()
    {
        if (!tapped)
        {
            return false;
        }

        this.tapped = false;
        return true;
    }

    public boolean isTapped() {
        return tapped;
    }

    public void setTapped(boolean tapped) {
        this.tapped = tapped;
    }
}
