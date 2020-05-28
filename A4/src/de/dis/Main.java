package de.dis;

public class Main {

    public static void main(String[] args) {
        PersistenceManager persistenceManager = PersistenceManager.getInstance();

        Client client1 = new Client("Alice", persistenceManager);
        client1.queryWriteOperation(11, "Hello World");
        client1.queryWriteOperation(13, "Kartoffel");
        client1.queryWriteOperation(15, "Dunkel war’s, der Mond schien helle, schneebedeckt die grüne Flur, als ein Wagen blitzesschnelle, langsam um die Ecke fuhr.");
        client1.queryWriteOperation(11, "Bye World");
        Client client2 = new Client("Bob", persistenceManager);
        client2.queryWriteOperation(20, "SPAM");
        client2.queryWriteOperation(21, "SPAM");
        client2.queryWriteOperation(22, "SPAM");
        client2.queryWriteOperation(23, "SPAM");
        client2.queryWriteOperation(24, "SPAM");
        client2.queryWriteOperation(25, "SPAM");
        client2.queryWriteOperation(26, "SPAM");
        client2.queryWriteOperation(27, "SPAM");
        client2.queryWriteOperation(28, "SPAM");
        Client client3 = new Client("Charlie", persistenceManager);
        client3.queryWriteOperation(33, "Dreiunddreißig");
        client3.queryWriteOperation(32, "Zweiunddreißig");
        client3.queryWriteOperation(32, "DreiZwei");

        client1.start();
        client2.start();
        client3.start();
    }
}
