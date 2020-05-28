package de.dis;

public class BufferPage {

    private int transactionId;
    private int pageID;
    private int lsn;
    private String data;

    public BufferPage(int transactionId, int pageID, int lsn, String data) {
        this.transactionId = transactionId;
        this.pageID = pageID;
        this.lsn = lsn;
        this.data = data;
    }

    public int getTransactionId() { return transactionId; }

    public void setTransactionId(int transactionId) { this.transactionId = transactionId; }

    public int getPageID() {
        return pageID;
    }

    public void setPageID(int pageID) {
        this.pageID = pageID;
    }

    public int getLsn() {
        return lsn;
    }

    public void setLsn(int lsn) {
        this.lsn = lsn;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
