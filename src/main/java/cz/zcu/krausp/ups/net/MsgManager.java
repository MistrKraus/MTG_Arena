package cz.zcu.krausp.ups.net;
import cz.zcu.krausp.ups.utils.State;

import java.util.ArrayDeque;

public enum  MsgManager {
    INSTANCE;

    private ArrayDeque<Message> messagesToSend = new ArrayDeque<>();
    private ArrayDeque<Message> receivedMessages = new ArrayDeque<>();
    private ArrayDeque<Message> connectionMessages = new ArrayDeque<>();
    private ArrayDeque<Message> transitionMessages = new ArrayDeque<>();
    private ArrayDeque<Message> gameMessages = new ArrayDeque<>();
    private ArrayDeque<Message> pingMessages = new ArrayDeque<>();

    public synchronized void addMessageToSend(Message msg) {
        System.out.println(msg.toString());         // TODO Delete
        this.messagesToSend.add(msg);
        this.receivedMessages.add(new Message("20-Connection-Name-OK\n"));
    }

    public synchronized void addReceivedMessage(Message msg) {
        switch (msg.getState()) {
            case CONNECTION:
                this.connectionMessages.add(msg);
                break;
            case TRANSITION:
                this.transitionMessages.add(msg);
                break;
            case GAME:
                this.gameMessages.add(msg);
                break;
            case PING:
                this.pingMessages.add(msg);
                break;
            default:
                this.receivedMessages.add(msg);
                break;
        }
    }

    public synchronized Message peekMessageToSend() {
        if (messagesToSend.size() > 0) {
            return messagesToSend.peek();
        }

        return null;
    }

    public synchronized Message peekReceivedMessage(State state) {
        switch (state) {
            case CONNECTION:
                if (this.connectionMessages.size() > 0) {
                    return this.connectionMessages.peek();
                }
                break;
            case TRANSITION:
                if (this.transitionMessages.size() > 0) {
                    return this.transitionMessages.peek();
                }
                break;
            case GAME:
                if (this.gameMessages.size() > 0) {
                    return this.gameMessages.peek();
                }
                break;
            case PING:
                if (this.pingMessages.size() > 0) {
                    return this.pingMessages.peek();
                }
                break;
            default:
                if (this.receivedMessages.size() > 0) {
                    return this.receivedMessages.peek();
                }
                break;
        }

        return null;
    }

    public synchronized Message popMessageToSend() {
        if (messagesToSend.size() > 0) {
            return messagesToSend.pop();
        }

        return null;
    }

    public synchronized Message popReceivedMessage(State state) {
        switch (state) {
            case CONNECTION:
                if (this.connectionMessages.size() > 0) {
                    return this.connectionMessages.pop();
                }
                break;
            case TRANSITION:
                if (this.transitionMessages.size() > 0) {
                    return this.transitionMessages.pop();
                }
                break;
            case GAME:
                if (this.gameMessages.size() > 0) {
                    return this.gameMessages.pop();
                }
                break;
            case PING:
                if (this.pingMessages.size() > 0) {
                    return this.pingMessages.pop();
                }
                break;
            default:
                if (this.receivedMessages.size() > 0) {
                    return this.receivedMessages.pop();
                }
                break;
        }

        return null;
    }
}
