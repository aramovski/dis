package de.dis;

public class Page {

    private int pageID;
    private int lsn;
    private String data;

    public Page(int pageID, int lsn, String data) {
        this.pageID = pageID;
        this.lsn = lsn;
        this.data = data;
    }

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
