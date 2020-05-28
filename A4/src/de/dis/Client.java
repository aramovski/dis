package de.dis;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

public class Client extends Thread {

    private List<Integer> pageIds;
    private List<String> datas;
    private String clientID;

    private PersistenceManager manager;

    public Client (String clientID, PersistenceManager manager) {
        this.clientID = clientID;
        pageIds = new ArrayList<>();
        datas = new ArrayList<>();
        this.manager = manager;
    }

    public void queryWriteOperation(int pageId, String data)
    {
        pageIds.add(pageId);
        datas.add(data);
    }

    public void run() {
        System.out.println("Client [" + clientID + "] is starting...");
        randomSleep();

        int tid = manager.beginTransaction();
        System.out.println("Client [" + clientID + "] just started transaction " + tid);
        randomSleep();
        for(int i = 0; i < pageIds.size(); i++)
        {
            manager.write(tid,pageIds.get(i), datas.get(i));
            System.out.println("Client [" + clientID + "] just wrote " + datas.get(i) + " to page " + pageIds.get(i));
            randomSleep();
        }
        manager.commit(tid);
        System.out.println("Client [" + clientID + "] just committed transaction " + tid);
    }

    private void randomSleep()
    {
        int randomSleep = ThreadLocalRandom.current().nextInt(1000,2000);
        try {
            Thread.sleep(randomSleep);
        } catch(InterruptedException e){
            return;
        }
    }
}
