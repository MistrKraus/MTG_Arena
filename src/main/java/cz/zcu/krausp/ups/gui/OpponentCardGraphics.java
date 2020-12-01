package cz.zcu.krausp.ups.gui;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class OpponentCardGraphics extends CardGraphics {

    private static final Color CARD_BASE_COLOR = Color.color(0.41, 0.17, 0);

    @Override
    protected void setGraphics() {
        Canvas c = new Canvas();
        c.setWidth(cardWidth);
        c.setHeight(cardHeight);

        super.drawBase(c.getGraphicsContext2D(), CARD_BASE_COLOR);

        this.setGraphic(c);
    }

    @Override
    public boolean isCardSelected() {
        return false;
    }

    @Override
    public int getCardId() {
        return 0;
    }
}
