package cz.zcu.krausp.ups.gui;

import cz.zcu.krausp.ups.game.*;
import cz.zcu.krausp.ups.utils.GamePhase;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.HashMap;

public class GameBoard extends Pane {

    public static final int MIN_HEIGHT = 400;
    public static final int MIN_WIDTH = 500;
    public static final Color BACKGROUND_COLOR = Color.WHITESMOKE;

    private int height;
    private int width;

    private static final double ZONE_HEIGHT = 0.14;          // 14% of height is dedicated for each of 6 zones (16% is for spaces)
    private static final double SPACE_HEIGHT = 0.16;          // 16% of height is dedicated for spaces
    private static final double HAND_WIDTH = 0.7;       // 70% of width is dedicated for hand zone
    private static final double LANDS_WIDTH = 0.7;       // 70% of width is dedicated for lands zone
    private static final double BATTLEFIELD_WIDTH = 0.9;       // 90% of width is dedicated for creatures zone

    private final Game game;

    private final Button nextStepMainBtn;
    private final Button playCardFirstBtn;
    private final Button nextStepCombatBtn;
    private final Button declareAttackBtn;
    private final Button cancelAttackBtn;
    private final Button attackBtn;
    private final Button dontDefendBtn;
    private final Button defendBtn;
    private final Button endTurnBtn;
    private final Button playCardSecondBtn;
    private final Button surrenderBtn;

    // TODO make it one structure
    private final HashMap<GamePhase, Button> cancelButtons = new HashMap<>();
    private final HashMap<GamePhase, Button> actionButtons = new HashMap<>();

    private final Canvas playerC = new Canvas();
    private final Canvas opponentC = new Canvas();
    private final PlayerGraphics player;
    private final OpponentGraphics opponent;
    //private final Stage stage;

    public GameBoard(Game game) {
        this.game = game;
        // TODO constants
        this.nextStepMainBtn = new Button("Next step");
        this.playCardFirstBtn = new Button("Play card");
        this.nextStepCombatBtn = new Button("Next step");
        this.declareAttackBtn = new Button("Declare attack");
        this.cancelAttackBtn = new Button("Cancel attack");
        this.attackBtn = new Button("Attack");
        this.dontDefendBtn = new Button("Don't defend");
        this.defendBtn = new Button("Defend");
        this.endTurnBtn = new Button("End turn");
        this.playCardSecondBtn = new Button("Play card");
        this.surrenderBtn = new Button("Surrender");

        // first main
        this.cancelButtons.put(GamePhase.FIRSTMAIN, this.nextStepMainBtn);
        this.actionButtons.put(GamePhase.FIRSTMAIN, this.playCardFirstBtn);
        // combat
        this.cancelButtons.put(GamePhase.COMBAT, this.nextStepCombatBtn);
        this.actionButtons.put(GamePhase.COMBAT, this.declareAttackBtn);
        // attack
        this.cancelButtons.put(GamePhase.ATTACK, this.cancelAttackBtn);
        this.actionButtons.put(GamePhase.ATTACK, this.attackBtn);
        // defence
        this.cancelButtons.put(GamePhase.DEFEND, this.dontDefendBtn);
        this.actionButtons.put(GamePhase.DEFEND, this.defendBtn);
        // second main
        this.cancelButtons.put(GamePhase.SECONDMAIN, this.endTurnBtn);
        this.actionButtons.put(GamePhase.SECONDMAIN, this.playCardSecondBtn);

        this.player = new PlayerGraphics(this.game.getPlayer(), this.playerC, this.cancelButtons, this.actionButtons);
        this.opponent = new OpponentGraphics(this.game.getOpponent(), this.opponentC);

        createScene();
        setEventHandlers();
    }
    
    public synchronized void update() {
        /*
        // TODO needGraphicUpdate flag
        if (!this.game.isNeedGraphicUpdate()) {
            return;
        }
        */

        if (SceneLib.primaryStage == null || SceneLib.getActiveScene() == null ||
                !SceneLib.getActiveScene().equals(SceneLib.getGameScene()) ||
                SceneLib.primaryStage.getWidth() < MIN_WIDTH - 10 || SceneLib.primaryStage.getHeight() < MIN_HEIGHT - 50) {
            return;
        }

        this.width = (int)SceneLib.primaryStage.getScene().getWidth();
        this.height = (int)SceneLib.primaryStage.getScene().getHeight();

        this.height /= 2;

        this.player.update(0, height, width, height);
        this.opponent.update(0, 0, width, height);

        updateGraphics();
    }

    private synchronized void updateGraphics() {
        HashMap<Integer, CardGraphics> allCards = this.opponent.getAllCards();

        allCards.putAll(this.player.getAllCards());

        // remove old
        this.getChildren().removeIf(n -> n.getClass().getGenericSuperclass().equals(CardGraphics.class) && !allCards.containsValue(n));

        // add new
        for (CardGraphics c : allCards.values()) {
            if (!this.getChildren().contains(c)) {
                this.getChildren().add(c);
            }
        }

        setButtons();
    }

    private synchronized void setButtons() {
        for (Button b : this.cancelButtons.values()) {
            b.setVisible(false);
        }

        for (Button b : this.actionButtons.values()) {
            b.setVisible(false);
        }

        //this.cancelButtons.get(this.game.getPhase()).setVisible(true);
        //this.actionButtons.get(this.game.getPhase()).setVisible(true);
        // TODO
        this.cancelButtons.get(GamePhase.FIRSTMAIN).setVisible(true);
        this.actionButtons.get(GamePhase.FIRSTMAIN).setVisible(true);
    }

    private synchronized void createScene() {
        this.getChildren().addAll(
                this.playerC,
                this.opponentC,
                this.nextStepMainBtn,
                this.playCardFirstBtn,
                this.nextStepCombatBtn,
                this.declareAttackBtn,
                this.cancelAttackBtn,
                this.attackBtn,
                this.dontDefendBtn,
                this.defendBtn,
                this.endTurnBtn,
                this.playCardSecondBtn,
                this.surrenderBtn);

        for (Node node : this.getChildren()) {
            node.setVisible(false);
        }

        this.surrenderBtn.setVisible(true);
        this.playerC.setVisible(true);
        this.opponentC.setVisible(true);
    }

    private void setEventHandlers() {
        SceneLib.primaryStage.widthProperty().addListener((obs, oldVal, newVal) -> {
            update();
        });

        SceneLib.primaryStage.heightProperty().addListener((obs, oldVal, newVal) -> {
            update();
        });

        this.surrenderBtn.setOnAction(event -> {
            SceneLib.switchToLobby();
            // TODO Change to surrender message
            // CmdManager.INSTANCE.surrender();
        });

        this.nextStepMainBtn.setOnAction(event -> {
            this.game.endFirstMain();
        });

        this.playCardFirstBtn.setOnAction(event -> {
            this.game.firstMainPlayCard(this.player.getHandCardToPlay());
            //CmdManager.INSTANCE.firstMainPlayCard(this.player.getCardToPlayId());
        });

        this.nextStepCombatBtn.setOnAction(event -> {
            this.game.endCombat();
        });

        this.declareAttackBtn.setOnAction(event -> {
            this.game.setAttackingCreatures(this.player.getCreaturesToAttack());
        });

        this.cancelAttackBtn.setOnAction(event -> {
            this.game.cancelAttack();
        });

        this.attackBtn.setOnAction(event -> {
            this.game.attack();
        });

        //this.
    }
}
