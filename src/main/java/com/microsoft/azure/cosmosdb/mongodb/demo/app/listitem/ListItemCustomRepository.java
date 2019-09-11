package com.microsoft.azure.cosmosdb.mongodb.demo.app.listitem;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface ListItemCustomRepository  {

    List<ListTypeCount> findListAggregatedByListType();

}
