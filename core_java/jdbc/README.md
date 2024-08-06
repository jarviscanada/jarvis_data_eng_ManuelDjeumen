# Introduction
Using Java as the main programming language and some of it's main modules such as HTTP, JSON and SQL modules imported by the use of Maven to take Stock data from a specific server and store it into a database. 
The project is conceived like a first instance app with the controller implementation being done on the terminal for any user to actually use it properly.
The main repositories patterns are inspired from Maven which include a main directory for all the classes that will be used in the app and also a test directory for all the test methods that will be implemented.

# Implementaiton
## ER Diagram
ER diagram

![Digram](https://github.com/jarviscanada/jarvis_data_eng_ManuelDjeumen/blob/develop/core_java/jdbc/src/main/resources/diagramme.png)

## Design Patterns

The project was organised in the maven directory structure, which means that all the classes used for the app were stored inside a package (In this case the "ca.jrvs.apps.stockquote.dao" package) inside the main directory and all the test were present inside the test directory.
We also have the resource directory for files that could be used inside our project but are not classes or entities of the project such as the ```proprieties.txt``` file. The ```pom.xml``` file is also present to handle the dependencies needed to be able to do everything well inside the project. The data will be dealt within a certain database and our app will take the data and convert it into an actual object to process what wil be done with it. In this case, we have 2 major objects that will be used: The Quote object and the Position object. Those are for the Quote and Position entities that were stored in the quote and position sql tables in the database.
Then we have the DAO (Data Access Object) of each type of object. Those classes contain all the necessary methods that will be used to take data from the SQL tables and also put data inside those tables. They contain methods such as the ```findById()``` method which is set to return all the necessary data from the database in an actual object that we can process. It also contains methods to update the tables (put elements inside or delete it).
Those DAO methods are essential to make the link between the database, and the project by extracting all the information needed and proceed with it.


# Test

The test part starts by the database first. We have to implement all the necessary SQL tables where all the data will be stored. We then create a new database or use an existing one where the tables will be stored. Then the test part can really start.
Some classes were created in the project (The test directory) to show case the behavior of the app. Here we tested the DAO and the Service implementation of the app and
API's like Mockito and Junit were used for the tests. We created all the objects needed for the test (sometimes as Mock objects if needed) and then used all the assertions needed for the test in the test classes. Because we want to actually check the result values of the methods, the 'assertEquals' test method was frequently used.
