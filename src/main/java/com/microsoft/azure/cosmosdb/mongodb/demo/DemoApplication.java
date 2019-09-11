package com.microsoft.azure.cosmosdb.mongodb.demo;

import com.microsoft.azure.cosmosdb.mongodb.demo.app.listitem.*;
import com.microsoft.azure.cosmosdb.mongodb.demo.app.listitemdetails.*;
import com.mongodb.BasicDBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
@EnableMongoAuditing

public class DemoApplication implements CommandLineRunner {

	@Autowired
	private ListItemRepository listItemRepository;

	@Autowired
	private ListItemDetailsRepository listItemDetailsRepository;

	@Autowired
	ListItemService service;

	@Autowired
	ListItemDetailsService listItemDetailsService;


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		runListItemTests();
	}


	public void runListItemTests(){
		listItemDetailsRepository.deleteAll();
		listItemRepository.deleteAll();

		System.out.println("saving a list item to repo");

		//Create List of ListType 'CA'
		String lisType1 = "CA";
		ListItem newItem = createListItem(lisType1);
		listItemRepository.save(newItem);

		//Add ListItem for the above List
		//Stores as reference in the DB
		int cnt = 5;
		for(int i = 0; i <= cnt; i++){
			String reqChannel = "ONLINE";
			String currencyCode = "USD";
			ListItemDetails newItemDetail = createListItemDetails(reqChannel, currencyCode, newItem.listId);
			listItemDetailsRepository.save(newItemDetail);
		}

		//Create List of ListType 'OR'
		String lisType2 = "OR";
		ListItem newItem1 = createListItem(lisType2);
		listItemRepository.save(newItem1);

		//Add ListItem for the above List
		//Stores as reference in the DB
		for(int i = 0; i <= cnt; i++){
			String reqChannel = "STORE";
			String currencyCode = "USD";
			ListItemDetails newItemDetail = createListItemDetails(reqChannel, currencyCode, newItem1.listId);
			listItemDetailsRepository.save(newItemDetail);
		}

		System.out.println("done saving a list item to repo");

		System.out.println("start getting all list items");
		System.out.println("-------------------------------");
		for (ListItem li : listItemRepository.findAll()) {
			System.out.println(li);
		}
		System.out.println();
		System.out.println("done getting all list items");

		System.out.println("paging list items - page 1");
		System.out.println("-------------------------------");
		listItemRepository.findAll(new PageRequest(0, 5)).forEach(System.out::println);

		System.out.println("paging list found for page 2");
		System.out.println("-------------------------------");
		listItemRepository.findAll(new PageRequest(1, 5)).forEach(System.out::println);

		System.out.println("paging list found for page 3");
		System.out.println("-------------------------------");
		listItemRepository.findAll(new PageRequest(2, 5)).forEach(System.out::println);

		System.out.println("done paging all list items");


		System.out.println("start getting all list item details");
		System.out.println("-------------------------------");
		for (ListItemDetails lid : listItemDetailsRepository.findAll()) {
			System.out.println(lid);
		}
		System.out.println();
		System.out.println("done getting all list items");

		System.out.println("paging list item details - page 1");
		System.out.println("-------------------------------");
		listItemDetailsRepository.findAll(new PageRequest(0, 5)).forEach(System.out::println);

		System.out.println("paging list item details for page 2");
		System.out.println("-------------------------------");
		listItemDetailsRepository.findAll(new PageRequest(1, 5)).forEach(System.out::println);

		System.out.println("paging list item details for page 3");
		System.out.println("-------------------------------");
		listItemDetailsRepository.findAll(new PageRequest(2, 5)).forEach(System.out::println);

		System.out.println("done paging all list items details");

		System.out.println("--------------------------");
		System.out.println("listcount by listtype");

		//If we hit this error: Caused by: com.mongodb.MongoCommandException: Command failed with error 2: 'The aggregation pipeline is not enabled for this account. Please see https://aka.ms/mongodb-aggregation for details.' on server test-jyotsna-mongodb.documents.azure.com:10255. The full response is { "_t" : "OKMongoResponse", "ok" : 0, "code" : 2, "errmsg" : "The aggregation pipeline is not enabled for this account. Please see https://aka.ms/mongodb-aggregation for details.", "$err" : "The aggregation pipeline is not enabled for this account. Please see https://aka.ms/mongodb-aggregation for details." }
		//Do this :Enable Aggregation pipeline, MongoWireProtocol on the Comsos Mongo "Overview"

		for (ListTypeCount li : service.findListAggregatedByListType()) {
			System.out.println("ListType: " + li.getListType() + " total: " + li.getTotal());
		}
		System.out.println("--------------------------");
		System.out.println("listitemdetailscount by listitem");

		//If we hit this error: Caused by: com.mongodb.MongoCommandException: Command failed with error 2: 'The aggregation pipeline is not enabled for this account. Please see https://aka.ms/mongodb-aggregation for details.' on server test-jyotsna-mongodb.documents.azure.com:10255. The full response is { "_t" : "OKMongoResponse", "ok" : 0, "code" : 2, "errmsg" : "The aggregation pipeline is not enabled for this account. Please see https://aka.ms/mongodb-aggregation for details.", "$err" : "The aggregation pipeline is not enabled for this account. Please see https://aka.ms/mongodb-aggregation for details." }
		//Do this :Enable Aggregation pipeline, MongoWireProtocol on the Comsos Mongo "Overview"

		listItemDetailsService.lookupOperation();

	}

	public ListItem createListItem(String listType) {

		String uuid = UUID.randomUUID().toString();
		String listId = UUID.randomUUID().toString();
		String memberShipNo = UUID.randomUUID().toString();
		String clubNumber = UUID.randomUUID().toString();
		String userAccountId = UUID.randomUUID().toString();

		ListItem newItem = new ListItem(uuid,
				listId,
				"list_1",
				"",
				null,
				listType,
				memberShipNo,
				"",
				clubNumber,
				userAccountId);

		return newItem;
	}



	//public static ListItemDetails createListItemDetails(String reqChannel, String currencyCode, ListItem newItem) {
	public static ListItemDetails createListItemDetails(String reqChannel, String currencyCode, String newItemId) {

		String uuid = UUID.randomUUID().toString();
		int reqQuantity = 10;
		String listItemId = UUID.randomUUID().toString();
		String productId = UUID.randomUUID().toString();
		String productName = "samsproduct";
		String sequenceNo = UUID.randomUUID().toString();
		String price = "10.0";
		String offerId = UUID.randomUUID().toString();
		String lastOrderAt = Instant.now().toString();

		ListItemDetails newItemDetail = new ListItemDetails(uuid, listItemId, reqQuantity, productId,  productName,
													  lastOrderAt, sequenceNo, price, reqChannel, offerId, currencyCode, newItemId);
		return newItemDetail;
	}


}