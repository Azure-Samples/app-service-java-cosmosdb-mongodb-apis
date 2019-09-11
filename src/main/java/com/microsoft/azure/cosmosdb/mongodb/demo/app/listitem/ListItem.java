package com.microsoft.azure.cosmosdb.mongodb.demo.app.listitem;

import com.microsoft.azure.cosmosdb.mongodb.demo.app.listitemdetails.ListItemDetails;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Document
public class ListItem {

    @Id
    public String id;

    public String listId;

    @Indexed
    public String listName;

    public Object relatedListId;
    public String listType;
    public String membershipNo;
    public Object expiresAt;
    public Object clubNo;
    public Object userAccountId;

    public Object listItemDetails;

    @CreatedBy
    private String createdBy;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedBy
    private String modifiedBy;

    @LastModifiedDate
    private Instant modifiedAt;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        id = id;
    }

    public String getListId() {
        return listId;
    }

    public void setListId(String listId) {
        this.listId = listId;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public Object getRelatedListId() {
        return relatedListId;
    }

    public void setRelatedListId(Object relatedListId) {
        this.relatedListId = relatedListId;
    }

    public Object getListItemDetails() {
        return listItemDetails;
    }

    public void setListItemDetails(Object listItemDetails) {
        this.listItemDetails = listItemDetails;
    }

    public String getListType() {
        return listType;
    }

    public void setListType(String listType) {
        this.listType = listType;
    }

    public String getMembershipNo() {
        return membershipNo;
    }

    public void setMembershipNo(String membershipNo) {
        this.membershipNo = membershipNo;
    }

    public Object getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Object expiresAt) {
        this.expiresAt = expiresAt;
    }

    public Object getClubNo() {
        return clubNo;
    }

    public void setClubNo(Object clubNo) {
        this.clubNo = clubNo;
    }

    public Object getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(Object userAccountId) {
        this.userAccountId = userAccountId;
    }

    public ListItem(String id, String listId, String listName, Object relatedListId, ListItemDetails listItemDetails, String listType, String membershipNo, Object expiresAt, Object clubNo, Object userAccountId) {
        this.id = id;
        this.listId = listId;
        this.listName = listName;
        this.relatedListId = relatedListId;
        this.listItemDetails = listItemDetails;
        this.listType = listType;
        this.membershipNo = membershipNo;
        this.expiresAt = expiresAt;
        this.clubNo = clubNo;
        this.userAccountId = userAccountId;
    }

    @Override
    public String toString() {
        return "ListItem{" +
                "Id='" + id + '\'' +
                ", listId='" + listId + '\'' +
                ", listName='" + listName + '\'' +
                ", relatedListId=" + relatedListId +
                ", listItemDetails=" + listItemDetails +
                ", listType='" + listType + '\'' +
                ", membershipNo='" + membershipNo + '\'' +
                ", expiresAt=" + expiresAt +
                ", clubNo=" + clubNo +
                ", userAccountId=" + userAccountId +
                ", createdBy='" + createdBy + '\'' +
                ", createdAt=" + createdAt +
                ", modifiedBy='" + modifiedBy + '\'' +
                ", modifiedAt=" + modifiedAt +
                '}';
    }
}