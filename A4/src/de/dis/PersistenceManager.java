package de.dis;

import java.util.concurrent.atomic.AtomicInteger;

public class PersistenceManager {

    private static PersistenceManager instance;
    private static Buffer buffer = new Buffer();
    private AtomicInteger transactionID = new AtomicInteger(0);

    private PersistenceManager () {}

    public static synchronized PersistenceManager getInstance () {
        if (PersistenceManager.instance == null) {
            PersistenceManager.instance = new PersistenceManager ();
            System.out.println("New Persistence Manager created: " + PersistenceManager.instance);
        }
        return PersistenceManager.instance;
    }

    public int beginTransaction() {
        return transactionID.incrementAndGet();
    }

    public void commit(int transactionID) {

    }

    public void write(int transactionID, int pageID, String data) {
        buffer.write(new Page(pageID, 0, data));
    }
}
