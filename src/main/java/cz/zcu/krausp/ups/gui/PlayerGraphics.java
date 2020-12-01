package cz.zcu.krausp.ups.gui;

import cz.zcu.krausp.ups.game.Player;
import cz.zcu.krausp.ups.net.CmdManager;
import cz.zcu.krausp.ups.utils.GamePhase;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

import java.util.ArrayList;
import java.util.HashMap;

public class PlayerGraphics extends AbstractPlayerGraphics {

    private final Player player;

    // TODO make it one object
    private final HashMap<GamePhase, Button> cancelBtns;
    private final HashMap<GamePhase, Button> actionBtns;

    private static final double SELECT_PERC_OFSET = -0.3;

    public PlayerGraphics(Player p, Canvas canvas, HashMap<GamePhase, Button> cancelBtns, HashMap<GamePhase, Button> actionBtns) {
        super(canvas);

        this.player = p;
        this.cancelBtns = cancelBtns;
        this.actionBtns = actionBtns;
    }

    @Override
    public void update(int x, int y, int zoneWidth, int zoneHeight) {
        super.updateCards(this.player);
        super.updateZone(this.hand, this.player.getHand());

        int cardHeight = zoneHeight / 3;
        int verticalGap = cardHeight / 6;
        cardHeight -= 2 * verticalGap;

        // battlefield
        y += 2 * verticalGap;
        int width = (int)(zoneWidth * BATTLEFIELD_WIDTH);
        super.drawZone(x + (int)((zoneWidth - width) / 2.0), y, this.battlefield, width, cardHeight, SELECT_PERC_OFSET);

        // lands
        y += cardHeight + verticalGap;
        width = (int)(zoneWidth * LANDS_WIDTH);
        super.drawZone(x + (int)((zoneWidth - width) / 2.0), y, this.lands, width, cardHeight);

        // player info
        width = (zoneWidth - width) / 2;
        int infoX = (int)(width * (1 - BATTLEFIELD_WIDTH));
        width = (int)(width * BATTLEFIELD_WIDTH);
        super.drawInfo(x + infoX , y, width, 2 * cardHeight + verticalGap,
                this.player.getHp(), this.player.getName());

        // buttons
        drawButtons(x + zoneWidth - 10, y);

        // hand
        y += cardHeight + verticalGap;
        width = (int)(zoneWidth * HAND_WIDTH);
        super.drawZone(x + (int)((zoneWidth - width) / 2.0), y, this.hand, width, cardHeight, SELECT_PERC_OFSET);
        ToggleGroup tg = new ToggleGroup();
        for (ToggleButton tb : this.hand.values()) {
            tb.setToggleGroup(tg);
        }
    }

    public int getHandCardToPlay() {
        for (CardGraphics card : this.hand.values()) {
            if (card.isSelected()) {
                return card.getCardId();
            }
        }

        return -1;
    }

    public Integer[] getCreaturesToAttack() {
        ArrayList<Integer> creaturesId = new ArrayList<>();
        for (CardGraphics card : this.hand.values()) {
            if (card.isSelected()) {
                creaturesId.add(card.getCardId());
            }
        }

        return creaturesId.toArray(new Integer[0]);
    }

    public HashMap<Integer, CardGraphics> getHand() {
        return this.hand;
    }

    public void setHand(HashMap<Integer, CardGraphics> hand) {
        this.hand = hand;
    }

    public void addToHand(CardGraphics card) {
        this.hand.put(card.getCardId(), card);
    }

    @Override
    public void addToBattlefield(CreatureGraphics creature) {
        super.addToBattlefield(creature);

        creature.setOnAction(event -> {
            CmdManager.INSTANCE.declaredAttackCorrupted(this.player.getName());
        });
    }

    private void drawButtons(int x, int y) {
        for (Button b : this.cancelBtns.values()) {
            b.setLayoutX(x - b.getWidth());
            b.setLayoutY(y);
        }

        y += 5 + this.cancelBtns.get(GamePhase.FIRSTMAIN).getHeight();

        for (Button b : this.actionBtns.values()) {
            b.setLayoutX(x - b.getWidth());
            b.setLayoutY(y);
        }
    }
}
