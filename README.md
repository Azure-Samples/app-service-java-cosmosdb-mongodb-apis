---
page_type: sample
languages:
- java
products:
- cosmossb mongodb
description: "This Sample demonstrates usage of @Query Annotation, One-To-Many Joins on Cross Docs, Aggregations using $lookup and Pagination Sorting"
urlFragment: "https://github.com/Azure-Samples/app-service-azure-cosmosdb-mongodb"
---

# Official Microsoft Sample

<!-- 
Guidelines on README format: https://review.docs.microsoft.com/help/onboard/admin/samples/concepts/readme-template?branch=master

Guidance on onboarding samples to docs.microsoft.com/samples: https://review.docs.microsoft.com/help/onboard/admin/samples/process/onboarding?branch=master

Taxonomies for products and languages: https://review.docs.microsoft.com/new-hope/information-architecture/metadata/taxonomies?branch=master
-->

Give a short description for your sample here. What does it do and why is it important?

## Contents

Outline the file contents of the repository. It helps users navigate the codebase, build configuration and any related assets.

| File/folder       | Description                                |
|-------------------|--------------------------------------------|
| `src`             | Sample source code.                        |
| `.gitignore`      | Define what to ignore at commit time.      |
| `CHANGELOG.md`    | List of changes to the sample.             |
| `CONTRIBUTING.md` | Guidelines for contributing to the sample. |
| `README.md`       | This README file.                          |
| `LICENSE`         | The license for the sample.                |

## Prerequisites

Outline the required components and tools that a user might need to have on their machine in order to run the sample. This can be anything from frameworks, SDKs, OS versions or IDE releases.

## How to run this sample

Working installation of Java and Maven
An Internet connection

Step 1: Download Java (8 and above) for your platform
To successfully use this sample, you need a working installation of Java and Maven.

Step 2: Clone or download this repository  
From your shell or command line:  

git clone https://github.com/Azure-Samples/app-service-azure-cosmosdb-mongodb.git     
cd app-service-azure-cosmosdb-mongodb   

Step 3:   
To create a new Azure Cosmos MongoDB account, sign in to the [Azure Portal](https://ms.portal.azure.com "Azure Portal")     
Create a new Database Account from instructions on https://docs.microsoft.com/en-us/azure/cosmos-db/create-mongodb-java      
Note the connectionString from the "QuickStart". This will be needed later to update in application.properties    

## Running queries from Mongo Shell
mongo mongodb:10255 -u user -p password --ssl --sslAllowInvalidCertificates  
show dbs  
use Catalog  
db.listItem.find()  
db.listItem.find().pretty();  
db.listItem.find().limit(1).pretty();  
db.listItem.aggregate([
   {
       $lookup:
          {
             from: "listItemDetails",
             localField: "listId",
             foreignField: "list_Id",
             as: "list_info"
         }
    }
 ]).pretty()


## Running the sample
goto app-service-azure-cosmosdb-mongodb  
update the connectionString in the application.properties from cosmosdb mondodb account  
mvn clean package  
mvn clean spring-boot:run  


## Key concepts
The Goal of this reference sample is to demonstrate  
1. Adding 2 Collections into Cosmos Mongo ListItem and ListItemDetails  
2. Able to Query elements of Document with @Query annotation  
3. Using aggregates   
4. Lookup Operation for fetching list of lists on Cross documents with a single call 
5. Pagination and Sorting  

https://docs.microsoft.com/en-us/azure/cosmos-db/mongodb-introduction  
https://docs.mongodb.com/manual/reference/operator/aggregation/lookup/ 

Initially @DBRef was used for One-To-Many Doc Join, however it was giving a performance of 1600ms for querying
Switching from @DBRef to $lookup drastically improved perforarmance to 200ms


## Contributing

This project welcomes contributions and suggestions.  Most contributions require you to agree to a
Contributor License Agreement (CLA) declaring that you have the right to, and actually do, grant us
the rights to use your contribution. For details, visit https://cla.opensource.microsoft.com.

When you submit a pull request, a CLA bot will automatically determine whether you need to provide
a CLA and decorate the PR appropriately (e.g., status check, comment). Simply follow the instructions
provided by the bot. You will only need to do this once across all repos using our CLA.

This project has adopted the [Microsoft Open Source Code of Conduct](https://opensource.microsoft.com/codeofconduct/).
For more information see the [Code of Conduct FAQ](https://opensource.microsoft.com/codeofconduct/faq/) or
contact [opencode@microsoft.com](mailto:opencode@microsoft.com) with any additional questions or comments.
