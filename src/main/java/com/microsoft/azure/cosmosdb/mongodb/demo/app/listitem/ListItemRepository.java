package com.microsoft.azure.cosmosdb.mongodb.demo.app.listitem;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListItemRepository  extends MongoRepository<ListItem, String> {

    @Query("{ 'clubNo' : ?0 }")
    Page<ListItem> findByClubNo(String clubNo, Pageable pageable);

    @Query("{'clubNo' : ?0 , 'membershipNo' : ?1}")
    ListItem findByClubNoAndMembershipNo(String clubNo, String membershipNo);

    @Query(value = "{}", count = true)
    Long findCount();


}
