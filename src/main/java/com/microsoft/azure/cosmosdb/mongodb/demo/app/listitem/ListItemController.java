package com.microsoft.azure.cosmosdb.mongodb.demo.app.listitem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResponseErrorHandler;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/listitem", produces = MediaType.APPLICATION_JSON_VALUE)
public class ListItemController {
    Logger logger = LoggerFactory.getLogger(ListItemController.class);


    @Autowired
    ListItemService service;

    //http://localhost:8080/api/listitem?page=1&size=2&sort=desc

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public ResponseEntity<Long> findCount() {
        long count = service.findCount();
        return new ResponseEntity<Long>(count, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<Page<ListItem>> getAllListItems(Pageable pageable ) {
        @SuppressWarnings("deprecation")
        Page<ListItem> listItems = service.getAllListItems(pageable);
        return new ResponseEntity<>(listItems, HttpStatus.OK);
    }

    @RequestMapping(value = "/findByClubNo/{clubNo}", method = RequestMethod.GET)
    public ResponseEntity<Page<ListItem>> getByClubNo(@PathVariable("clubNo") String clubNo, Pageable pageable ) {
        logger.info("ListItemController clubNo" +clubNo);
        if (clubNo != null) {
            logger.info("ListItemController clubNo" +clubNo);
            @SuppressWarnings("deprecation")
            Page<ListItem> listItems = service.getListByClubNo(clubNo, pageable);
            return new ResponseEntity<>(listItems, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    //http://localhost:8080/api/listitem/findByClubNoAndMembershipNo?clubNo=c81ca1e1-3aae-4cf0-b69b-dd8144605b86&membershipNo=c4bc2374-3187-4725-802c-a7d96423fc2e
    @RequestMapping(value = "/findByClubNoAndMembershipNo", params = {"clubNo", "membershipNo"}, method = RequestMethod.GET)
    public ResponseEntity<ListItem> getByClubNoAndMembershipNo(@RequestParam("clubNo") String clubNo,
                                                               @RequestParam("membershipNo") String membershipNo) {
        logger.info("clubNo " + clubNo + "membershipNo " +membershipNo );
        if (clubNo != null) {
            ListItem li = service.getListByClubNoAndMembershipNo(clubNo, membershipNo);
            return new ResponseEntity<ListItem>(li, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ListItem> getListItemById(@PathVariable("id") final String id ) {
        Optional<ListItem> listItem = service.getListById(id);
        if(listItem.isPresent()) {
            return new ResponseEntity<>(listItem.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/findAggregatedByListType", method = RequestMethod.GET)
    public ResponseEntity<ListTypeCount> findAggregatedByListType() {
        List<ListTypeCount> liCount = service.findListAggregatedByListType();
        return new ResponseEntity<>((ListTypeCount)liCount, HttpStatus.OK);
    }



}

