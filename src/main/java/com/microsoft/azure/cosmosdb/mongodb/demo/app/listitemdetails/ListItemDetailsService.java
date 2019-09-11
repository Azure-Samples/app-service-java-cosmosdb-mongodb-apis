package com.microsoft.azure.cosmosdb.mongodb.demo.app.listitemdetails;

import com.microsoft.azure.cosmosdb.mongodb.demo.app.listitem.*;
import com.mongodb.BasicDBObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Service
public class ListItemDetailsService implements ListItemDetailsCustomRepository {

    Logger logger = LoggerFactory.getLogger(ListItemDetailsService.class);

    private final MongoTemplate mongoTemplate;

    @Autowired
    public ListItemDetailsService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Autowired
    private ListItemDetailsRepository repository;

    @Autowired
    private ListItemRepository itemRepository;

    public List<ListItemDetails> getAllListItemDetails() {
        return (List<ListItemDetails>) repository.findAll();
    }


    public Optional<ListItemDetails> getListItemDetailById(String id) {
        return  repository.findById(id);
    }


    @Override
    public List<ListItemDetails.ListItemCount> findListItemDetailsAggregatedByList() {

        GroupOperation groupCountByListId = group("listId").count().as("total");
        SortOperation sortByCountDesc = sort(new Sort(Sort.Direction.DESC, "total"));
        ProjectionOperation projectToMatchModel = project()
                .andExpression("listId").as("listId")
                .andExpression("total").as("count");
        Aggregation aggregation = Aggregation
                .newAggregation(groupCountByListId, sortByCountDesc, projectToMatchModel);

        AggregationResults<ListItemDetails.ListItemCount> groupResults =
                mongoTemplate.aggregate(aggregation, ListItemDetails.class, ListItemDetails.ListItemCount.class);
        List<ListItemDetails.ListItemCount> result = groupResults.getMappedResults();

        return result;

    }

    @Override
    public List<BasicDBObject> lookupOperation() {
      LookupOperation lookupOperation = LookupOperation.newLookup()
                .from("listItemDetails")
                .localField("listId")
                .foreignField("list_Id")
                .as("listItemDetails");

        Aggregation aggregation = Aggregation.newAggregation(lookupOperation);

        List<BasicDBObject> results = mongoTemplate.aggregate(aggregation, "listItem", BasicDBObject.class).getMappedResults();

        logger.info("Obj Size " +results.size());
        System.out.println(" Results : "+results.toString());
        return  results;
    }

}



