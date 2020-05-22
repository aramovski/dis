package de.dis;

public class Client extends Thread {

    private String clientID;

    public Client (String clientID) {
        this.clientID = clientID;
    }

    public void run() {
        while (true) {
            System.out.println("Client [" + clientID + "] is performing...");
            try {
                Thread.sleep(2000);
            } catch(InterruptedException e){
                return;
            }
        }
    }
}
