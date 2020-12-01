package cz.zcu.krausp.ups.game;

import cz.zcu.krausp.ups.net.CmdManager;
import cz.zcu.krausp.ups.net.Message;
import cz.zcu.krausp.ups.net.MsgManager;
import cz.zcu.krausp.ups.utils.GamePhase;
import cz.zcu.krausp.ups.utils.State;

public class Game {

    //private static final int MAIN_ZONE_COUNT = 200;
    private final Player player;
    private final Opponent opponent;

    private AbstractPlayer playerOnTurn;
    private GamePhase phase;
    private boolean needGraphicUpdate;

    public Game(int playerId, String player, int opponnetId, String opponnet) {
        this.player = new Player(player, playerId, 20);
        this.opponent = new Opponent(opponnet, opponnetId, 20);
        this.phase = GamePhase.UNKNOWN;

        int cardNum = 7;
        for (int i = 0; i < cardNum; i++) {
            this.player.addToBattlefield(new Creature(i, 1, 1, 1));
            this.player.addToLand(new Land(i + cardNum, 1));
            if (i % 2 == 0) {
                this.player.addToHand(new Creature(i + 2 * cardNum, 1, 1, 1));
            }
            else {
                this.player.addToHand(new Land(i + 2 * cardNum, 1));
            }

            this.opponent.addToBattlefield(new Creature(i + 3 * cardNum, 1, 1, 1));
            this.opponent.addToLand(new Land(i + 4 * cardNum, 1));
        }

        this.opponent.setCardsInHand(cardNum);
    }

    // TODO Re-do
    public void update() {
        Message msg = MsgManager.INSTANCE.popReceivedMessage(State.GAME);

        if (msg == null) {
            return;
        }

        needGraphicUpdate = true;

        switch (GamePhase.valueOf(msg.getCmd()[0])) {
            case FIRSTMAIN:
                processFirstMain(msg.getCmd());
                this.setPhase(GamePhase.FIRSTMAIN);
                break;
            case ATTACK:
                processAttack(msg.getCmd());
                break;
            default:
                needGraphicUpdate = false;

                break;
        }
    }

    private void processFirstMain(String[] cmd) {
        switch (cmd[1]) {

        }
    }

    private void processAttack(String[] cmd) {
        if (cmd[1].equals(this.player.name)) {

            return;
        }

        if (cmd[1].equals(this.opponent.name)) {

            return;
        }


    }

    public void endFirstMain() {
        CmdManager.INSTANCE.endFirstMain();
    }

    public void firstMainPlayCard(int cardId) {
        CmdManager.INSTANCE.firstMainPlayCard(cardId);
    }

    public void endCombat() {
        CmdManager.INSTANCE.endCombat();
    }

    public void cancelAttack() {
        CmdManager.INSTANCE.cancelAttack();
    }

    public void setAttackingCreatures(Integer[] creatureIds) {
        CmdManager.INSTANCE.setAttackingCreature(creatureIds);
    }

    public void attack() {
        CmdManager.INSTANCE.attack();
    }

    public void setPlayerName(String name) {

    }

    public void setOpponentName(String name) {

    }

    public void setPlayerOnTurn(AbstractPlayer player) {
        this.playerOnTurn = player;
    }

    public void setPhase(GamePhase phase) {

    }

    public Player getPlayer() {
        return this.player;
    }

    public Opponent getOpponent() {
        return this.opponent;
    }

    public AbstractPlayer getPlayerOnTurn() {
        return playerOnTurn;
    }

    public GamePhase getPhase() {
        return this.phase;
    }

    public boolean isNeedGraphicUpdate() {
        return needGraphicUpdate;
    }
}
