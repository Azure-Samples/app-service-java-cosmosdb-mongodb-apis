package com.microsoft.azure.cosmosdb.mongodb.demo.app.listitem;

import com.microsoft.azure.cosmosdb.mongodb.demo.app.listitemdetails.ListItemDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Service
public class ListItemService implements  ListItemCustomRepository{
    Logger logger = LoggerFactory.getLogger(ListItemService.class);

    private final MongoTemplate mongoTemplate;

    @Autowired
    public ListItemService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Autowired
    private ListItemRepository repository;

    public Page<ListItem> getAllListItems(Pageable pageable) {
            return (Page<ListItem>) repository.findAll(pageable);
    }

    public Optional<ListItem> getListById(String id) {
        return  repository.findById(id);
    }

    public Page<ListItem> getListByClubNo(String clubNo, Pageable pageable) {
        return (Page<ListItem>) repository.findByClubNo(clubNo, pageable);
    }

    public ListItem getListByClubNoAndMembershipNo(String clubNo, String membershipNo) {
        logger.info("service clubNo " +clubNo +"membershipNo " +membershipNo  );
        return repository.findByClubNoAndMembershipNo(clubNo, membershipNo);
    }

    public long findCount(){
        return repository.findCount();
    }

    @Override
    public List<ListTypeCount> findListAggregatedByListType() {

        Aggregation agg = newAggregation(
                group("listType").count().as("total"),
                project("total").and("listType").previousOperation(),
                sort(Sort.Direction.DESC, "total")
        );

        //Convert the aggregation result into a List
        AggregationResults<ListTypeCount> groupResults = mongoTemplate.aggregate(agg, ListItem.class, ListTypeCount.class);
        List<ListTypeCount> result = groupResults.getMappedResults();

        return result;
    }


}


