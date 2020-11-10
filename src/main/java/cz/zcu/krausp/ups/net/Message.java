package cz.zcu.krausp.ups.net;

import cz.zcu.krausp.ups.utils.State;

public class Message {

    private final String rawMsg;
    private final String[] cmd;
    private final State state;
    private final boolean structureOk;

    public Message(String rawMsg) {
        this.rawMsg = rawMsg;
        this.structureOk = isStructureOk();

        if (this.structureOk) {
            String[] split = this.rawMsg.split(Constants.MSG_PART_SEPARATOR);
            this.cmd = new String[split.length - 2];

            for (int i = 0; i < this.cmd.length; i++) {
                this.cmd[i] = split[i + 2];
            }

            this.state = State.stateOf(split[1]);
        }
        else {
            this.cmd = null;
            this.state = State.UNKNOWN;
        }
    }

    /**
     * Check if message is made of at least 3 parts (contains 2 message part separators),
     * first part is a number representing length of the rest of a message including separator,
     * last char is separator.
     *
     * @return if message is in correct format
     */
    private boolean isStructureOk() {
        String[] splitMsg = this.rawMsg.split(Constants.MSG_PART_SEPARATOR);

        if (splitMsg.length < Constants.MIN_MSG_PARTS ||
            !splitMsg[0].matches(Constants.NUMBER_REGEX) ||
            this.rawMsg.length() - 1 != this.rawMsg.lastIndexOf(Constants.MSG_SEPARATOR)) {
            return false;
        }

        int len = Integer.parseInt(splitMsg[0]);

        return rawMsg.substring(rawMsg.indexOf(Constants.MSG_PART_SEPARATOR)).length() == len;
    }

    public boolean getMsgStructureOk() {
        return this.structureOk;
    }

    public String[] getCmd() {
        return this.cmd;
    }

    public State getState() {
            return this.state;
    }

    @Override
    public String toString() {
        return this.rawMsg;
    }
}
