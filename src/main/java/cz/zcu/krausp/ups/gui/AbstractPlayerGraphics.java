package cz.zcu.krausp.ups.gui;

import cz.zcu.krausp.ups.game.AbstractPlayer;
import cz.zcu.krausp.ups.game.Card;
import cz.zcu.krausp.ups.game.Creature;
import cz.zcu.krausp.ups.game.Land;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.HashMap;
import java.util.Random;

public abstract class AbstractPlayerGraphics /*implements Observer*/ {

    public static final String FONT = "Source Serif Pro ExtraLight";

    protected static final double ZONE_HEIGHT = 0.25;          // 25% of height is dedicated for each of 6 zones (16% is for spaces)
    protected static final double SPACE_HEIGHT = 0.05;          // 5% of height is dedicated for spaces
    protected static final double LANDS_WIDTH = 0.7;       // 70% of width is dedicated for lands zone
    protected static final double BATTLEFIELD_WIDTH = 0.9;       // 90% of width is dedicated for creatures zone
    protected static final double HAND_WIDTH = 0.7;       // 70% of width is dedicated for hand zone

    protected HashMap<Integer, CardGraphics> battlefield = new HashMap<>();
    protected HashMap<Integer, CardGraphics> lands = new HashMap<>();
    protected HashMap<Integer, CardGraphics> hand = new HashMap<>();
    protected Canvas infoGraphics;

    private static final double HEART_WIDTH = 0.8;       // 80% of width is dedicated for heart
    private static final Color HEART_COLOR = Color.RED;

    protected AbstractPlayerGraphics(Canvas infoCanvas) {
        this.infoGraphics = infoCanvas;
    }

    public abstract void update(int x, int y, int zoneWidth, int zoneHeight);

    protected void drawZone(int x, int y, HashMap<Integer, CardGraphics> cards, int width, int height) {
        drawZone(x, y, cards, width, height, 0.0);
    }

    protected void drawZone(int x, int y, HashMap<Integer, CardGraphics> cards, int width, int height, double selectPercOfset) {
        if (cards.isEmpty()) {
            return;
        }

        CardGraphics.updateSize(height);

        int cardHorizontalSpace = width / (cards.size() + 1);
        int i = 1;
        int rand = new Random().nextInt(cards.size());
        for (CardGraphics c : cards.values()) {
            // TODO sideNote: c.isSelected() refers to toggleButton - can be used for GUI -> faster and seamless response
            c.setLayoutY(c.isCardSelected() ? y + (CardGraphics.cardHeight * selectPercOfset) : y);
            c.setLayoutX(x + (i * cardHorizontalSpace) - CardGraphics.cardWidth * 0.75);        // 0.75 is magic... that works // TODO constant

            c.setGraphics();

            i++;
        }
    }

    protected void drawInfo(int x, int y, int width, int height, int hp, String name) {
        //System.out.println("X: " + x + " Y: " + y + " width: " + width + " height: " + height);
        this.infoGraphics.setLayoutX(x);
        this.infoGraphics.setLayoutY(y);
        this.infoGraphics.setWidth(width);
        this.infoGraphics.setHeight(height);
        GraphicsContext g = this.infoGraphics.getGraphicsContext2D();

        g.setFill(SceneLib.primaryStage.getScene().getFill());
        g.fillRect(0, 0, width, height);

        double midX = (double)width / 2.0;
        double heartMinY = 0;
        double heartHeight = 2.0 * height / 3.0;
        double startY = heartMinY + (heartHeight - heartMinY) / 3.0;

        //System.out.println("midX: " + midX + " startY: " + startY);

        g.setFill(HEART_COLOR);
        g.fillPolygon(new double[] { midX, midX * 1.5, width, width, midX, 0, 0, midX * 0.5 },
                    new double[] { startY,
                            heartMinY,
                            startY * 0.5,
                            heartHeight * 0.5,
                            heartHeight,
                            heartHeight * 0.5,
                            startY * 0.5,
                            heartMinY }, 8);

        g.setFill(Color.BLACK);
        g.setFont(new Font(FONT, heartHeight * 0.3));
        Text hpText = new Text(Integer.toString(hp));
        hpText.setFont(g.getFont());
        g.fillText(hpText.getText(), midX - hpText.getLayoutBounds().getWidth() / 2, startY + hpText.getLayoutBounds().getHeight() * 0.8);

        Text nameText = new Text(name);
        nameText.setFont(g.getFont());
        g.fillText(name, midX - nameText.getLayoutBounds().getWidth() / 2, heartMinY + heartHeight + hpText.getLayoutBounds().getHeight() * 0.8);

    }

    public HashMap<Integer, CardGraphics> getAllCards() {
        HashMap<Integer, CardGraphics> cards = new HashMap<>();
        cards.putAll(this.battlefield);
        cards.putAll(this.lands);
        cards.putAll(this.hand);

        return cards;
    }

    public HashMap<Integer, CardGraphics> getBattlefield() {
        return this.battlefield;
    }

    public void updateCards(AbstractPlayer player) {
        updateZone(this.battlefield, player.getBattlefield());
        updateZone(this.lands, player.getLands());
    }

    public void setBattlefield(HashMap<Integer, CardGraphics> battlefield) {
        this.battlefield = battlefield;
    }

    public void addToBattlefield(CreatureGraphics creature) {
        this.battlefield.put(creature.getCardId(), creature);
    }

    public HashMap<Integer, CardGraphics> getLands() {
        return this.lands;
    }

    public void setLands(HashMap<Integer, CardGraphics> lands) {
        this.lands = lands;
    }

    public void addToLand(LandGraphics land) {
        this.lands.put(land.getCardId(), land);
    }

    protected void updateZone(HashMap<Integer, CardGraphics> graphicCards, HashMap<Integer, Card> cards) {
        // remove old
        graphicCards.keySet().removeIf(n -> !cards.containsKey(n));

        // add new
        for (Card c : cards.values()) {
            if (graphicCards.containsKey(c.getId())) {
                continue;
            }

            CardGraphics newCard;
            if (c.getClass().equals(Land.class)) {
                newCard = new LandGraphics((Land)c);
            } // the only other option is creature
            else {
                newCard = new CreatureGraphics((Creature)c);
            }

            graphicCards.put(c.getId(), newCard);
        }
    }
}
