package cz.zcu.krausp.ups.main;

import cz.zcu.krausp.ups.net.Message;

public class Main {

    public static void main(String[] args) {
        //Client client = new Client("192.168.56.102", 1000, 2000);

    }

    public static void msgTest() {
        Message msg = new Message("10-msg-aaaa\n");
        System.out.println(msg.getMsgStructureOk());
    }
}
