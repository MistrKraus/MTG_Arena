package cz.zcu.krausp.ups.gui;

import cz.zcu.krausp.ups.game.Land;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class LandGraphics extends CardGraphics {

    private final Land land;

    private static final Color CARD_BASE_COLOR = Color.color(0.35, 1.0, 0.6);

    public LandGraphics(Land land) {
        super();

        this.land = land;
    }

    @Override
    protected void setGraphics() {
        Canvas c = new Canvas();
        c.setWidth(cardWidth);
        c.setHeight(cardHeight);
        GraphicsContext g = c.getGraphicsContext2D();

        super.drawBase(g, CARD_BASE_COLOR);

        // name
        g.setFont(new Font(AbstractPlayerGraphics.FONT, fontSize));
        g.setStroke(Color.BLACK);
        g.strokeText(Land.getCardType(), borderSize + borderSize, borderSize + borderSize + g.getFont().getSize());
        // mana source
        g.setFont(new Font(AbstractPlayerGraphics.FONT, fontSize - 1));
        g.strokeText("Add " + this.land.getManaSource() + " mana.", borderSize + borderSize,
                borderSize + g.getFont().getSize() * 5, cardWidth - 4 * borderSize);

        if (isCardSelected()) {
            double alpha = g.getGlobalAlpha();
            g.setGlobalAlpha(0.5);      // TODO make constant
            g.setFill(Color.BLACK);
            g.fillRect(0, 0, cardWidth, cardHeight);
            g.setGlobalAlpha(alpha);
        }

        this.setGraphic(c);
    }

    public int getCardId() {
        return this.land.getId();
    }

    @Override
    public boolean isCardSelected() {
        return this.land.isSelected();
    }
}
