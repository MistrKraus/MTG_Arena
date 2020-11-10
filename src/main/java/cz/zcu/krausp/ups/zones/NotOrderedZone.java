package cz.zcu.krausp.ups.zones;

import cz.zcu.krausp.ups.game.Card;

import java.util.ArrayList;

public abstract class NotOrderedZone {

    protected ArrayList<Card> cards;

    protected final boolean publicZ;
    protected final boolean sharedZ;

    public NotOrderedZone(boolean publicZ, boolean sharedZ) {
        this.publicZ = publicZ;
        this.sharedZ = sharedZ;

        this.cards = new ArrayList<>();
    }

    public boolean addCard(Card card)
    {
        if (card == null)
        {
            return false;
        }

        this.cards.add(card);
        return true;
    }

    public Card getCard(int id)
    {
        if (id < 0 || id >= cards.size())
        {
            return null;
        }

        return this.cards.get(id);
    }

    public Card drawCard(int id)
    {
        Card c = this.getCard(id);

        if (c == null)
        {
            return null;
        }

        this.cards.remove(c);
        return c;
    }
}
