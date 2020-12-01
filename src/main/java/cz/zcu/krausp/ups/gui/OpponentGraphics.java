package cz.zcu.krausp.ups.gui;

import cz.zcu.krausp.ups.game.Opponent;
import javafx.scene.canvas.Canvas;

public class OpponentGraphics extends AbstractPlayerGraphics {

    private final Opponent opponent;

    private static final double SELECT_PERC_OFSET = 0.3;

    public OpponentGraphics(Opponent o, Canvas canvas) {
        super(canvas);

        this.opponent = o;
    }

    @Override
    public void update(int x, int y, int zoneWidth, int zoneHeight) {
        super.updateCards(this.opponent);
        setHand(this.opponent.getCardsInHand());

        int cardHeight = zoneHeight / 3;
        int verticalGap = cardHeight / 6;
        cardHeight -= 2 * verticalGap;

        // hand
        y += verticalGap;
        int width = (int)(zoneWidth * HAND_WIDTH);
        super.drawZone(x + (int)((zoneWidth - width) / 2.0), y, this.hand, width, cardHeight, SELECT_PERC_OFSET);

        // lands
        y += cardHeight + verticalGap;
        width = (int)(zoneWidth * LANDS_WIDTH);
        super.drawZone(x + (int)((zoneWidth - width) / 2.0), y, this.lands, width, cardHeight);

        // player info
        y += cardHeight;
        width = (zoneWidth - width) / 2;
        super.drawInfo(zoneWidth - width, y - (2 * cardHeight + verticalGap),
                (int)(width * BATTLEFIELD_WIDTH), 2 * cardHeight + verticalGap,
                this.opponent.getHp(), this.opponent.getName());

        // battlefield
        y += verticalGap;
        width = (int)(zoneWidth * BATTLEFIELD_WIDTH);
        super.drawZone((int)((zoneWidth - width) / 2.0), y, this.battlefield, width, cardHeight, SELECT_PERC_OFSET);
    }

    private void setHand(int cardCount) {
        for (int i = 0; i < cardCount; i++) {
            this.hand.put(Integer.MAX_VALUE - i, new OpponentCardGraphics());      // negative key for unknown card id
        }
    }
}
