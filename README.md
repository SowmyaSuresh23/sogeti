# Requirements: Java, Maven, Eclipse (or any IDE), Lombok

# Technical summary:
1.	The application is developed using Java 17 and Spring boot framework following microservices architecture.
2.	H2 database is currently being used to persist data. Any other relational database can also be used in future by changing the datasource properties in application.properties file.
3.	Swagger is implemented for API documentation.
4.	Actuator is enabled to monitor the application. 
5.	Junit 5 and Mockito framework are used for unit and integration tests.
6.	The request bodies and request parameters are validated using Jakarta validation.
7.	Feign client is used for communicating between microservices.

# How to run the application?
1.	Clone the code from GIT or download as zip file from the URL: https://github.com/SowmyaSuresh23/sogeti.git
2.	Import the project to a workspace in the IDE.
3.	Build the project using Maven (perform maven clean install).
4.	Run the application as Spring Boot application.
5.	To validate the test cases, run the application as JUnit Test.
