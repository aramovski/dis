package de.dis;

public class Main {

    public static void main(String[] args) {
        PersistenceManager persistenceManager = PersistenceManager.getInstance();

        Client client1 = new Client("Client 1");
        Client client2 = new Client("Client 2");
        Client client3 = new Client("Client 3");

        client1.start();
        client2.start();
        client3.start();
    }
}
