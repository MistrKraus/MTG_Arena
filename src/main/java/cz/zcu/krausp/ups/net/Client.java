package cz.zcu.krausp.ups.net;

import cz.zcu.krausp.ups.utils.State;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

public class Client {

    public static final int DEF_TIMEOUT = 2000;
    public static final int INVALID_INPUT_TOLERANCE = 5;
    public static final int PING_PONG_PERIOD = 100;

    public static final String[] ERRORS = new String[]
            {
                    "Land can be played only once per round",
                    "Not enough of mana",
                    "Creature can not attack due to summoning sickness",
                    "Creature can not block because it is tapped",
                    "You do not control this card",
                    "Card must be on battlefield for this action",
                    "Select creature to attack with before attacking"
            };

    private String ip;
    private int port;
    private int timeout;

    private Socket socket;
    private Send sender;
    private Receive receiver;
    private Timer pingPong;

    /**
     * Constructor
     *
     * @param ip server ipv4 address
     * @param port server port
     */
    public Client(String ip, int port)
    {
        this(ip, port, DEF_TIMEOUT);
    }

    /**
     * Constructor
     *
     * @param ip server ipv4 address
     * @param port server port
     * @param msTimeout timeout in milliseconds
     */
    public Client(String ip, int port, int msTimeout)
    {
        this.ip = ip;
        this.port = port;
        this.timeout = msTimeout;
    }

    /**
     * Disconnect sender, receiver and close socket
     */
    public void disconnect() {
        try {
            this.receiver.disconnect();
            this.sender.disconnect();
            this.pingPong.cancel();

            this.receiver.join();
            this.sender.join();

            this.socket.close();
        }
        catch (Exception e) {
            // TODO ex
            System.out.println("Proper disconnection failed");
        }
    }

    /**
     * Start new thread responding to ping messages.
     */
    public void startPingPong() {
        this.pingPong = new Timer();
        this.pingPong.schedule(new TimerTask() {
            @Override
            public void run() {
                if (MsgManager.INSTANCE.peekReceivedMessage(State.PING) == null) {
                    return;
                }

                String[] cmd = MsgManager.INSTANCE.popReceivedMessage(State.PING).getCmd();

                if (cmd == null || cmd.length < 1) {
                    // TODO invalid cmd counter
                    return;
                }

                CmdManager.INSTANCE.pong(cmd[0]);
            }
        }, 0, PING_PONG_PERIOD);
    }

    /**
     * Test if client is connected.
     * Try to connect if not connected and start responding to ping messages.
     *
     * @return If client is connected.
     */
    public boolean connect() {
        if (this.socket != null && this.socket.isConnected()) {
            System.out.println("Connected");
            return true;
        }

        try {
            this.socket = new Socket();
            InetSocketAddress isa = new InetSocketAddress(this.ip, this.port);
            this.socket.connect(isa, this.timeout);
        }
        catch (IllegalArgumentException argExc)
        {
            System.out.println("Given ip or port is incorrect or not accessible\n" + argExc.getMessage());

            return false;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());

            return false;
        }

        startPingPong();

        System.out.println("Connected");

        return true;
    }

    /**
     * Create new thread and send massages to server
     *
     * @return if messages are send
     */
    public boolean sendMsgs() {
        if (!this.connect())
        {
            System.out.println("Unable to send messages - not connected");
            return false;
        }

        if (this.sender == null) {
            OutputStream os = null;
            OutputStreamWriter outputStreamWriter;
            try {
                os = socket.getOutputStream();
            }
            catch (IOException e) {   // TODO ex
                System.out.println("Unable to get output stream");
                return false;
            }

            if (os != null) {
                outputStreamWriter = new OutputStreamWriter(os);
            }
            else {
                return false;
            }

            this.sender = new Send(outputStreamWriter);
            return false;
        }

        if (!this.sender.isReady()) {
            System.out.println("Unable to send messages - ");
        }

        this.sender.start();
        return true;
    }

    /**
     * Create new thread and receive server messages
     *
     * @return if messages are received
     */
    public boolean recieveMsgs() {
        if (!this.connect()) {
            System.out.println("Unable to send messages - not connected");
            // TODO
            return false;
        }

        if (this.receiver == null) {
            InputStream is;
            BufferedReader bufferedReader;

            try {
                is = socket.getInputStream();
            }
            catch (IOException e) {     // TODO ex
                System.out.println("Unable to get input stream");
                return false;
            }

            if (is != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(is));
            }
            else {
                // TODO log
                System.out.println("Buffer was unable to connect");
                return false;
            }

            this.receiver = new Receive(bufferedReader);
        }

        if (!this.receiver.isReady()) {
            System.out.println("Unable to receive messages - ");
            // TODO log
            return false;
        }

        this.receiver.start();
        return true;
    }
}
