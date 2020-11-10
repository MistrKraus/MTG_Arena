package cz.zcu.krausp.ups.zones;

public class Battlefield extends NotOrderedZone {
    public Battlefield() {
        super(true, true);
    }

    /*
     * Logic is on server!!!
    @Override
    public boolean addCard(Card card)
    {
        if (super.addCard(card))
        {
            if (General.getClassName(card).equals(CardType.CREATURE.label))
            {
                Creature
            }

            return true;
        }

        return false;
    }
    */
}
