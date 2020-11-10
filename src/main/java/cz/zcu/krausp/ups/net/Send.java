package cz.zcu.krausp.ups.net;

import java.io.IOException;
import java.io.OutputStreamWriter;

public class Send extends Thread implements IComunicator {

    private boolean send;

    private final OutputStreamWriter output;

    public Send(OutputStreamWriter outputStreamWriter) {
        this.output = outputStreamWriter;
    }

    public void run() {
        if (!this.isReady()) {
            // TODO unable to send
            return;
        }

        Message msg;
        this.send = true;
        while (this.send) {
            msg =  MsgManager.INSTANCE.popMessageToSend();

            try {
                if (msg == null) {
                    sleep(100);
                    continue;
                }

                this.output.write(msg.toString());
                this.output.flush();
            }
            catch (InterruptedException interruptedE) {
                // TODO ex
                System.out.println();
            }
            catch (IOException e) {
                // TODO ex
                System.out.println("Failed to send the message:");
                System.out.println(msg.toString());
            }
        }
    }

    @Override
    public void disconnect() {
        this.send = false;
    }

    @Override
    public boolean isReady() {
        return this.output != null;
    }
}
