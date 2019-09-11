package com.microsoft.azure.cosmosdb.mongodb.demo.app.listitemdetails;

import com.microsoft.azure.cosmosdb.mongodb.demo.app.listitem.ListItem;
import com.microsoft.azure.cosmosdb.mongodb.demo.app.listitem.ListItemService;
import com.mongodb.BasicDBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/listitemdetails", produces = MediaType.APPLICATION_JSON_VALUE)
public class ListItemDetailsController {

    @Autowired
    ListItemDetailsService service;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<ListItemDetails>> getAllListItemDetails() {
        List<ListItemDetails> listItemDetails = service.getAllListItemDetails();
        return new ResponseEntity<>(listItemDetails, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ListItemDetails> getListItemById(@PathVariable("id") final String id ) {
        Optional<ListItemDetails> listItemDetails = service.getListItemDetailById(id);
        if(listItemDetails.isPresent()) {
            return new ResponseEntity<>(listItemDetails.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/listitemdetailsbylistitem", method = RequestMethod.GET)
    public ResponseEntity<List<BasicDBObject>> getListItemById() {
        List<BasicDBObject> basicDBObjects =   service.lookupOperation();
        return new ResponseEntity<>(basicDBObjects, HttpStatus.OK);
    }


}

