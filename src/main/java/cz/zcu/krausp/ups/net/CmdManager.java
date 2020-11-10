package cz.zcu.krausp.ups.net;

import cz.zcu.krausp.ups.utils.*;

public enum  CmdManager {
    INSTANCE;

    public void pong(String uuid) {
        String cmd = Constants.MSG_PART_SEPARATOR +
                State.PONG +
                Constants.MSG_PART_SEPARATOR +
                uuid +
                Constants.MSG_SEPARATOR;

        addCommandToSend(cmd);
    }

    public void login(String name) {
        String cmd = Constants.MSG_PART_SEPARATOR +
                State.CONNECTION.label + // World.INSTANCE.currentState +      // TODO ??? replace with current state - for every cmd ???
                Constants.MSG_PART_SEPARATOR +
                name +
                Constants.MSG_SEPARATOR;

        addCommandToSend(cmd);
    }

    public void unknownCard(int id) {
        String cmd = Constants.MSG_PART_SEPARATOR +
                State.GAME.label +
                Constants.MSG_PART_SEPARATOR +
                GameObject.CARD +
                Constants.MSG_PART_SEPARATOR +
                CardType.UNKNOWN +
                Constants.MSG_PART_SEPARATOR +
                id +
                Constants.MSG_SEPARATOR;

        addCommandToSend(cmd);
    }

    public void mulligan() {
        String cmd = Constants.MSG_PART_SEPARATOR +
                State.GAME.label +
                Constants.MSG_SEPARATOR +
                GameObject.MULLIGAN.label +
                Constants.MSG_SEPARATOR;

        addCommandToSend(cmd);
    }

    public void refuseFirst() {
        String cmd = Constants.MSG_PART_SEPARATOR +
                State.GAME +
                Constants.MSG_PART_SEPARATOR +
                GameObject.TURN +
                Constants.MSG_PART_SEPARATOR +
                Turn.REFUSEFIRST +
                Constants.MSG_SEPARATOR;

        addCommandToSend(cmd);
    }

    public void playFirst() {
        String cmd = Constants.MSG_PART_SEPARATOR +
                State.GAME +
                Constants.MSG_PART_SEPARATOR +
                GameObject.TURN +
                Constants.MSG_PART_SEPARATOR +
                Turn.PLAYFIRST +
                Constants.MSG_SEPARATOR;

        addCommandToSend(cmd);
    }

    public void firstMainPlayCard(int id) {
        String cmd = Constants.MSG_PART_SEPARATOR +
                State.GAME +
                Constants.MSG_PART_SEPARATOR +
                GamePhase.FIRSTMAIN +
                Constants.MSG_PART_SEPARATOR +
                id +
                Constants.MSG_SEPARATOR;

        addCommandToSend(cmd);
    }

    public void endFirstMain() {
        String cmd = Constants.MSG_PART_SEPARATOR +
                State.GAME +
                Constants.MSG_PART_SEPARATOR +
                GamePhase.FIRSTMAIN +
                Constants.MSG_PART_SEPARATOR +
                Action.NEXT +
                Constants.MSG_SEPARATOR;

        addCommandToSend(cmd);
    }

    public void endCombat() {
        String cmd = Constants.MSG_PART_SEPARATOR +
                State.GAME +
                Constants.MSG_PART_SEPARATOR +
                GamePhase.COMBAT +
                Constants.MSG_PART_SEPARATOR +
                Action.NEXT +
                Constants.MSG_SEPARATOR;

        addCommandToSend(cmd);
    }

    public void endSecondMain() {
        String cmd = Constants.MSG_PART_SEPARATOR +
                State.GAME +
                Constants.MSG_PART_SEPARATOR +
                GamePhase.SECONDMAIN +
                Constants.MSG_PART_SEPARATOR +
                Action.NEXT +
                Constants.MSG_SEPARATOR;

        addCommandToSend(cmd);
    }

    public void setAttackingCreature(int id) {
        String cmd = Constants.MSG_PART_SEPARATOR +
                State.GAME +
                Constants.MSG_PART_SEPARATOR +
                GamePhase.COMBAT +
                Constants.MSG_PART_SEPARATOR +
                Action.ATTACK +
                Constants.MSG_SEPARATOR +
                id +
                Constants.MSG_SEPARATOR;

        addCommandToSend(cmd);
    }

    public void unsetAttackingCreature(int id) {
        String cmd = Constants.MSG_PART_SEPARATOR +
                State.GAME +
                Constants.MSG_PART_SEPARATOR +
                GamePhase.COMBAT +
                Constants.MSG_PART_SEPARATOR +
                Action.CANCELATTACK +
                Constants.MSG_PART_SEPARATOR +
                id +
                Constants.MSG_SEPARATOR;

        addCommandToSend(cmd);
    }

    public void setAttack() {
        String cmd = Constants.MSG_PART_SEPARATOR +
                State.GAME +
                Constants.MSG_PART_SEPARATOR +
                GamePhase.COMBAT +
                Constants.MSG_PART_SEPARATOR +
                Action.ATTACK +
                Constants.MSG_SEPARATOR;

        addCommandToSend(cmd);
    }

    public void setDefendingCreature(int defender, int attacker) {
        String cmd = Constants.MSG_PART_SEPARATOR +
                State.GAME +
                Constants.MSG_PART_SEPARATOR +
                GamePhase.COMBAT +
                Constants.MSG_PART_SEPARATOR +
                Action.DEFENCE +
                Constants.MSG_PART_SEPARATOR +
                defender +
                Constants.MSG_PART_SEPARATOR +
                attacker +
                Constants.MSG_SEPARATOR;

        addCommandToSend(cmd);
    }

    public void unsetDefendingCreature(int id) {
        String cmd = Constants.MSG_PART_SEPARATOR +
                State.GAME +
                Constants.MSG_PART_SEPARATOR +
                GamePhase.COMBAT +
                Constants.MSG_PART_SEPARATOR +
                Action.CANCELDEFENCE +
                Constants.MSG_PART_SEPARATOR +
                id +
                Constants.MSG_SEPARATOR;

        addCommandToSend(cmd);
    }

    public void setDefence() {
        String cmd = Constants.MSG_PART_SEPARATOR +
                State.GAME +
                Constants.MSG_PART_SEPARATOR +
                GamePhase.COMBAT +
                Constants.MSG_PART_SEPARATOR +
                Action.DEFENCE +
                Constants.MSG_SEPARATOR;

        addCommandToSend(cmd);
    }

    public void playAgain() {
        String cmd = Constants.MSG_PART_SEPARATOR +
                State.GAME +
                Constants.MSG_PART_SEPARATOR +
                Action.AGAIN +
                Constants.MSG_SEPARATOR;

        addCommandToSend(cmd);
    }

    public void backToLobby() {
        String cmd = Constants.MSG_PART_SEPARATOR +
                State.GAME +
                Constants.MSG_PART_SEPARATOR +
                Action.LEAVE +
                Constants.MSG_SEPARATOR;

        addCommandToSend(cmd);
    }

    /**
     * Add length of command to a message and add new message to list of messages to be send
     *
     * @param cmd command
     */
    private void addCommandToSend(String cmd) {
        MsgManager.INSTANCE.addMessageToSend(new Message(cmd.length() + cmd));
    }

}
