package cz.zcu.krausp.ups.zones;

import cz.zcu.krausp.ups.game.Card;
import java.util.ArrayList;
import java.util.LinkedList;

public abstract class OrderedZone {
    protected LinkedList<Card> cards;

    protected final boolean publicZ;
    protected final boolean sharedZ;

    public OrderedZone(boolean publicZ, boolean sharedZ) {
        this.publicZ = publicZ;
        this.sharedZ = sharedZ;

        this.cards = new LinkedList<>();
    }

    /**
     * Test card if it is correct type and add it
     *
     * @param card card
     */
    public void addCard(Card card) {
        if (card == null)
        {

        }

        cards.add(card);
    }

    /**
     * Add arrayList of cards into the list
     *
     * @param cards arrayList of cards
     */
    public void addCards(ArrayList<Card> cards) {
        for (Card card : cards) {
            this.addCard(card);
        }
    }

    public Card getTopCard()
    {
        return this.cards.getFirst();
    }

    public Card drawTopCard()
    {
        return this.cards.removeFirst();
    }

    public Card getBottomCard()
    {
        return this.cards.getLast();
    }
}
