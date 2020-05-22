package de.dis;

import java.util.Hashtable;

public class Buffer {

    private static final int BUFFER_SIZE = 5;
    private static int keyCounter = 0;

    private Hashtable<Integer, Page> storage;


    public Buffer() {
        storage = new Hashtable<>();
    }

    public boolean isFull() {
        return storage.size() >= BUFFER_SIZE;
    }

    public void write(Page page) {
        storage.put(keyCounter++, page);
        isFull();
    }
}
