package cz.zcu.krausp.ups.gui;

import cz.zcu.krausp.ups.game.Creature;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class CreatureGraphics extends CardGraphics {

    private final Creature creature;

    private static final Color CARD_BASE_COLOR = Color.color(0.80, 0.2, 0.2);

    public CreatureGraphics(Creature creature) {
        super();

        this.creature = creature;
    }

    @Override
    protected void setGraphics() {
        Canvas c = new Canvas();
        c.setWidth(cardWidth);
        c.setHeight(cardHeight);
        GraphicsContext g = c.getGraphicsContext2D();

        super.drawBase(g, CARD_BASE_COLOR);

        g.setFont(new Font(AbstractPlayerGraphics.FONT, fontSize));
        g.setStroke(Color.BLACK);
        g.strokeText(Creature.getCardType(), borderSize + borderSize,
                borderSize + borderSize + g.getFont().getSize(), cardWidth - 4 * borderSize);
        // mana cost
        g.strokeText(Integer.toString(this.creature.getManaCost()),
                borderSize + borderSize, cardHeight - borderSize - borderSize);
        // mana power / toughness
        Text txt = new Text(this.creature.getPower() + "/" + this.creature.getToughness());
        txt.setFont(g.getFont());
        g.strokeText(txt.getText(), cardWidth - borderSize - borderSize - txt.getBoundsInLocal().getWidth(),
                cardHeight - 2 * borderSize);

        this.setGraphic(c);
    }

    public boolean isCardSelected() {
        return this.creature.isSelected();
    }

    public int getCardId() {
        return this.creature.getId();
    }
}
