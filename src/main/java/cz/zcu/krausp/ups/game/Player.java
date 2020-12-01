package cz.zcu.krausp.ups.game;

import java.util.HashMap;

public class Player extends AbstractPlayer {

    private HashMap<Integer, Card> hand = new HashMap<>();

    public Player(String name, int id) {
        super(name, id);
    }

    public Player(String name, int id, int hp) {
        super(name, id, hp);
    }

    public HashMap<Integer, Card> getHand() {
        return this.hand;
    }

    public void addToHand(Card card) {
        this.hand.put(card.getId(), card);
    }

    public void setHand(HashMap<Integer, Card> hand) {
        this.hand = hand;
    }
}
