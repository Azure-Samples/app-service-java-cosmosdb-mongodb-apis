package com.microsoft.azure.cosmosdb.mongodb.demo.app.listitem;

public class ListTypeCount {

    private String listType;

    private long total;

    public ListTypeCount(){

    }

    public ListTypeCount(String listType, long total) {
        this.listType = listType;
        this.total = total;
    }

    public String getListType() {
        return listType;
    }

    public void setListType(String listType) {
        this.listType = listType;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
