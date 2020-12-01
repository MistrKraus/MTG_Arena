package cz.zcu.krausp.ups.gui;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ToggleButton;
import javafx.scene.paint.Color;

public abstract class CardGraphics extends ToggleButton {

    protected boolean mouseHover;

    protected static int cardHeight;
    protected static int cardWidth;
    protected static int borderSize;
    protected static int fontSize;

    protected static final double XY_RATIO = 0.7;
    protected static final double BORDER_WIDTH_RATIO = 0.05;

    protected CardGraphics() {
        this.mouseHover = false;

        setEventHandlers();       // TODO uncomment
    }

    public static void updateSize(int height) {
        cardHeight = height;
        cardWidth = (int)(cardHeight * XY_RATIO);
        borderSize = (int)(cardWidth * BORDER_WIDTH_RATIO);
        fontSize = 4 * borderSize;
    }

    protected void drawBase(GraphicsContext g, Color base) {
        g.setFill(base.darker());
        g.fillRect(0, 0, cardWidth, cardHeight);
        // card body
        g.setFill(base);
        g.fillRect(borderSize, borderSize, cardWidth - 2 * borderSize, cardHeight - 2 * borderSize);
    }

    protected void setEventHandlers() {
        this.setOnMouseEntered(event -> {
            this.mouseHover = true;
        });

        this.setOnMouseExited(event -> {
            this.mouseHover = false;
        });

        /*
         * TODO set location change if selected
        this.selectedProperty().addListener(event -> {
            if (this.isSelected()) {

            }
        });
         */
    }

    public abstract boolean isCardSelected();

    public abstract int getCardId();

    protected void setGraphics() {
        System.out.println("---");
    }
}
