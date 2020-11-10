package cz.zcu.krausp.ups.game;

public class PowerToughness {

    private int power;
    private int toughness;

    /**
     * Power and toughness of a creature
     *
     * @param power
     * @param toughness
     */
    public PowerToughness(int power, int toughness)
    {
        this.power = power;
        this.toughness = toughness;
    }

    /**
     * Add/Subtract power by x.
     * To add x > 0. To subtract x < 0
     *
     * @param x number to add/subtract from power
     */
    public void addPower(int x)
    {
        this.power += x;
    }

    /**
     * Add/Subtract toughness by x.
     * To add x > 0. To subtract x < 0
     *
     * @param x number to add/subtract from toughness
     */
    public void addToughness(int x)
    {
        this.toughness += x;
    }

    public int getPower() {
        return this.power;
    }

    public int getToughness() {
        return this.toughness;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void setToughness(int toughness) {
        this.toughness = toughness;
    }

    @Override
    public boolean equals(Object obj) {
        return this.power == ((PowerToughness)obj).power &&
                this.toughness == ((PowerToughness)obj).toughness;
    }
}
