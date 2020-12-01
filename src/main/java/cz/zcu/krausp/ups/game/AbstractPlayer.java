package cz.zcu.krausp.ups.game;

import java.util.HashMap;

public abstract class AbstractPlayer /*extends Observable*/ {

    protected int hp;
    //protected boolean libEmpty = false;
    protected HashMap<Integer, Card> battlefield = new HashMap<>();
    protected HashMap<Integer, Card> lands = new HashMap<>();

    protected final String name;
    protected final int id;

    private static final int DEF_HP = 20;

    AbstractPlayer(String name, int id) {
        this(name, id, DEF_HP);
    }

    AbstractPlayer(String name, int id, int hp) {
        this.name = name;
        this.id = id;
        this.hp = hp;
    }

    public void update() {

    }


    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
/*
    public boolean isLibEmpty() {
        return libEmpty;
    }

    public void setLibEmpty(boolean libEmpty) {
        this.libEmpty = libEmpty;
    }
*/
    public HashMap<Integer, Card> getBattlefield() {
        return battlefield;
    }

    public void setBattlefield(HashMap<Integer, Card> battlefield) {
        this.battlefield = battlefield;
    }

    public void addToBattlefield(Creature creature) {
        this.battlefield.put(creature.getId(), creature);
    }

    public HashMap<Integer, Card> getLands() {
        return lands;
    }

    public void setLands(HashMap<Integer, Card> lands) {
        this.lands = lands;
    }

    public void addToLand(Land land) {
        this.lands.put(land.getId(), land);
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
