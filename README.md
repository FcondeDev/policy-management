-The problem was solved by using Java, Spring Boot, Maven and H2 in memory database (as main database and also for testing)

-The flow starts in the controller in which a DTO is received 
then passed to the Service in which we have some validations before converting the DTO to Domain Object,
here date validation takes place and then the Policy Domain Object can go to the infrastructure layer(Which is an H2 implementation of the repository abstraction)

controller(receives DTO) -> service(fields validation, DTO conversion to Domain Objects, Business Logic) -> infrastructure (h2 implementation 
for the repository interface)


-In terms of database we have two tables, one called policy and another one called InsuredPerson, Policy has a oneToMany relationship with InsuredPerson

-There is an exception layer in charge of dealing with the unchecked and checked exceptions by using a RestControllerAdvice

- All the code is using dependency injection (interfaces and constructor injection)

- there are integration and unit test for the flows and components (7 in total).


Postman endpoints:

Creation:
POST localhost:8080/policies

{
"startDate": "11.11.2023",
"insuredPersons": [
{
"firstName": "Jane",
"secondName": "Johnson",
"premium": 12.90
},
{
"firstName": "Jack",
"secondName": "Doe",
"premium": 35.90
}
]
}

Modification:
PUT localhost:8080/policies
{
"policyId": 1,
"effectiveDate": "15.12.2024",
"insuredPersons": [
{
"id": 1,
"firstName": "Jane",
"secondName": "Johnson",
"premium": 12.90
},
{
"firstName": "Will",
"secondName": "Smith",
"premium": 12.90
}
]
}

Information:
GET localhost:8080/policies/id/1?requestDate=13.11.2023

You can set up the application by doing a mvn clean install.

