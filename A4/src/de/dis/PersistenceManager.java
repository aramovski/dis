package de.dis;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Hashtable;
import java.util.concurrent.atomic.AtomicInteger;

public class PersistenceManager {

    private static final int BUFFER_THRESHHOLD = 5;

    private static PersistenceManager instance;
    private AtomicInteger transactionID = new AtomicInteger(0);
    private AtomicInteger logSequence = new AtomicInteger(0);
    private Hashtable<Integer, BufferPage> buffer;
    private Hashtable<Integer, Boolean> transactionCommits; //true means commited. false means not yet commited.

    private PersistenceManager () {
        buffer = new Hashtable<>();
        transactionCommits = new Hashtable<>();
    }

    public static synchronized PersistenceManager getInstance () {
        if (PersistenceManager.instance == null) {
            try {
                RecoveryTool.recoverDbFromLog();
            } catch (IOException e) {
                e.printStackTrace();
            }
            PersistenceManager.instance = new PersistenceManager ();
            System.out.println("New Persistence Manager created: " + PersistenceManager.instance);
        }
        return PersistenceManager.instance;
    }

    public int beginTransaction() {
        int tid = transactionID.incrementAndGet();
        transactionCommits.put(tid, false);
        return tid;
    }

    public void commit(int transactionID) {
        int lsn = logSequence.incrementAndGet();

        saveEotToLog(transactionID, lsn);
        transactionCommits.put(transactionID, true);
    }

    public void write(int transactionID, int pageID, String data) {
        if(data.equals("EOT"))
        {
            System.out.println("invalid data. do not write EOT.");
            return;
        }
        int lsn = logSequence.incrementAndGet();
        BufferPage page = new BufferPage(transactionID, pageID, lsn, data);
        savePageToLog(page);

        if(buffer.size() > BUFFER_THRESHHOLD)
        {
            persistCommittedTransactions();
            // The excercise says nothing about cleaning the buffer afterwards. so this will be called a lot of times.
            // Excercise says "the persistence manager checks whether there are operations related to already committed transactions"
            // We interpret this as that he goes over all entries in the buffer and persists the ones that are linked to a committed transaction
            // Please give feedback if this was not intended.
        }

        buffer.put(pageID, page);
    }

    private synchronized void persistCommittedTransactions()
    {
        for (Integer pageID: buffer.keySet()) {
            BufferPage page = buffer.get(pageID);
            int tid = page.getTransactionId();
            if(transactionCommits.get(tid))
            {
                try {
                    FileWriter fw = new FileWriter("Page" + pageID + ".data", false);
                    fw.write(page.getLsn() + "," + page.getData());
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void saveEotToLog(int transactionId, int lsn)
    {
        String toWrite = lsn + "," + transactionId + ",EOT";
        writeLog(toWrite);
    }

    private void savePageToLog(BufferPage page)
    {
        String toWrite = page.getLsn()+ "," + page.getTransactionId() + "," + page.getPageID() + "," + page.getData();
        writeLog(toWrite);
    }

    private synchronized void writeLog(String entry)
    {
        try {
            FileWriter fw = new FileWriter("logfile.log", true);
            fw.write(entry + "\n");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
