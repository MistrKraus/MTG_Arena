package cz.zcu.krausp.ups.net;

import java.io.BufferedReader;
import java.io.IOException;

public class Receive extends Thread implements IComunicator {

    private int invalidMessageCounter = 0;
    //private long receivedMsgTime = 0;
    private boolean receive;
    private StringBuilder rawMsg;

    private final BufferedReader input;

    public Receive(BufferedReader bufferedReader) {
        this.input = bufferedReader;
    }

    public void run() {
        if (!this.isReady()) {
            System.out.println("Buffer was unable to connect");
            // TODO log
            return;
        }

        this.rawMsg = new StringBuilder();
        this.receive = true;
        while (this.receive) {
            try {
                if (!this.input.ready()) {
                    // TODO change from sleep
                    sleep(100);
                    continue;
                }

                if (!preprocessMsg(this.input.readLine())) {
                    System.out.println("Received message is invalid!");
                    // TODO log
                    if (++this.invalidMessageCounter > Client.INVALID_INPUT_TOLERANCE) {
                        System.out.println("Limit for invalid messages receive overdrawn");
                        // TODO log
                        break;
                    }
                }

                this.invalidMessageCounter = 0;
            }
            catch (InterruptedException interruptedE) {
                // TODO ex
                System.out.println();
            }
            catch (IOException e) {
                // TODO ex
                System.out.println("Unable to read");
            }
        }
    }

    @Override
    public void disconnect() {
        this.receive = false;
    }

    @Override
    public boolean isReady() {
        return this.input != null;
    }

    /**
     * Add received message to further processing if it follows protocol structure
     *
     * @param rawMsg received message
     * @return if received message follows protocol structure
     */
    private boolean preprocessMsg(String rawMsg) {
        Message msg = new Message(rawMsg);

        if (msg.getMsgStructureOk()) {
            MsgManager.INSTANCE.addReceivedMessage(msg);
            return true;
        }
        else {
            return false;
        }

        /*  V TCP zbytecne (pravdepodobne)
        // Message is
        if (rawMsg == null) {
            if (this.receivedMsgTime != 0) {
                if (System.currentTimeMillis() - this.receivedMsgTime > Client.DEF_TIMEOUT) {
                    return false;
                }
            }
            else {
                this.receivedMsgTime = System.currentTimeMillis();
            }
        }
        else {
            this.receivedMsgTime = 0;


        }
        */
    }
}
