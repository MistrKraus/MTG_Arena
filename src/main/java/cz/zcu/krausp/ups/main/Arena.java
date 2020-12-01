package cz.zcu.krausp.ups.main;

import cz.zcu.krausp.ups.game.Creature;
import cz.zcu.krausp.ups.game.Land;

public class Arena {



    public Arena()
    {
        Creature creature = new Creature(0, "Test creature", 1, 1, 1);
        Land land = new Land(1, 1);
        /*
        Library lib = new Library();

        lib.addCard(creature);
        lib.addCard(land);

        Card c = lib.drawTopCard();
        System.out.println("Drawn: " + General.getClassName(c));
        Card l = lib.drawTopCard();
        System.out.println("Drawn: " + l.getClass());
        */
    }
}
