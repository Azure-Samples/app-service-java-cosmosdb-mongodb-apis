package com.microsoft.azure.cosmosdb.mongodb.demo.app.listitemdetails;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document
public class ListItemDetails {

    @Id
    public String id;

    public String listItemId;
    public Integer reqQuantity;
    public String productId;
    public String productName;
    public String lastOrderAt;
    public String sequenceNo;
    public String price;
    public String reqChannel;
    public String offerId;
    public String currencyCode;

    public String list_Id;

    @CreatedBy
    public String createdBy;
    @CreatedDate
    public Instant createdAt;
    @LastModifiedBy
    public String modifiedBy;
    @LastModifiedDate
    public Instant modifiedAt;

    public ListItemDetails(){

    }

   /* public ListItemDetails(String id, String listItemId, Integer reqQuantity, String productId, String productName, String lastOrderAt, String sequenceNo, String price, String reqChannel, String offerId, String currencyCode,ListItem listItem ) {
        this.id = id;
        this.listItemId = listItemId;
        this.reqQuantity = reqQuantity;
        this.productId = productId;
        this.productName = productName;
        this.lastOrderAt = lastOrderAt;
        this.sequenceNo = sequenceNo;
        this.price = price;
        this.reqChannel = reqChannel;
        this.offerId = offerId;
        this.currencyCode = currencyCode;
        this.listItem = listItem;
    }*/

    public ListItemDetails(String id, String listItemId, Integer reqQuantity, String productId, String productName, String lastOrderAt, String sequenceNo, String price, String reqChannel, String offerId, String currencyCode,String list_Id) {
        this.id = id;
        this.listItemId = listItemId;
        this.reqQuantity = reqQuantity;
        this.productId = productId;
        this.productName = productName;
        this.lastOrderAt = lastOrderAt;
        this.sequenceNo = sequenceNo;
        this.price = price;
        this.reqChannel = reqChannel;
        this.offerId = offerId;
        this.currencyCode = currencyCode;
        this.list_Id = list_Id;
    }


    @Override
    public String toString() {
        return "ListItemDetails{" +
                "Id='" + id + '\'' +
                ", listItemId='" + listItemId + '\'' +
                ", reqQuantity=" + reqQuantity +
                ", productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", lastOrderAt='" + lastOrderAt + '\'' +
                ", sequenceNo='" + sequenceNo + '\'' +
                ", price='" + price + '\'' +
                ", reqChannel='" + reqChannel + '\'' +
                ", offerId='" + offerId + '\'' +
                ", currencyCode='" + currencyCode + '\'' +
               // ", listItem=" + listItem + '\'' +
                ", listId=" + list_Id + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        id = id;
    }

    public String getListItemId() {
        return listItemId;
    }

    public void setListItemId(String listItemId) {
        this.listItemId = listItemId;
    }

    public Integer getReqQuantity() {
        return reqQuantity;
    }

    public void setReqQuantity(Integer reqQuantity) {
        this.reqQuantity = reqQuantity;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getLastOrderAt() {
        return lastOrderAt;
    }

    public void setLastOrderAt(String lastOrderAt) {
        this.lastOrderAt = lastOrderAt;
    }

    public String getSequenceNo() {
        return sequenceNo;
    }

    public void setSequenceNo(String sequenceNo) {
        this.sequenceNo = sequenceNo;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getReqChannel() {
        return reqChannel;
    }

    public void setReqChannel(String reqChannel) {
        this.reqChannel = reqChannel;
    }

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

   /*public ListItem getListItem() {
        return listItem;
    }

    public void setListItem(ListItem listItem) {
        this.listItem = listItem;
    }*/

    public String getList_Id() {
        return list_Id;
    }

    public void setListItem(String listId) {
        this.list_Id = listId;
    }


    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Instant getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Instant modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public static class ListItemCount {

        private String listId;

        private long total;

        public String getListId() {
            return listId;
        }

        public void setListId(String listId) {
            this.listId = listId;
        }

        public long getTotal() {
            return total;
        }

        public void setTotal(long total) {
            this.total = total;
        }

        public ListItemCount(){

        }

        public ListItemCount(String listId, long total) {
            this.listId = listId;
            this.total = total;
        }


    }
}
