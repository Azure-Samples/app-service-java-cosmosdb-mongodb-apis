package com.microsoft.azure.cosmosdb.mongodb.demo.app.listitemdetails;

import com.mongodb.BasicDBObject;

import java.util.List;


public interface ListItemDetailsCustomRepository {

    List<ListItemDetails.ListItemCount> findListItemDetailsAggregatedByList();
    List<BasicDBObject> lookupOperation();


  }

