package com.microsoft.azure.cosmosdb.mongodb.demo.app.listitemdetails;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListItemDetailsRepository  extends MongoRepository<ListItemDetails, String>  {
}
