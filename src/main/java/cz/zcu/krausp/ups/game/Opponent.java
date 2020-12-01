package cz.zcu.krausp.ups.game;

public class Opponent extends AbstractPlayer {

    private int cardsInHand;

    public Opponent(String name, int id) {
        super(name, id);
    }

    public Opponent(String name, int id, int hp) {
        super(name, id, hp);
    }

    @Override
    public void update() {

    }


    public int getCardsInHand() {
        return cardsInHand;
    }

    public void setCardsInHand(int numberOfCards) {
        this.cardsInHand = numberOfCards;
    }
}
