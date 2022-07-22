Coverage: 70%
# Project Title

This project invlves creating an inventory management system with CRUD functionality for customers, items and orders. The user is expected to interact with the application from the command line interface (CLI).

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

What things you need to install the software and how to install them

Java SE Development kit 
https://www.oracle.com/java/technologies/downloads/

MySQL Community including MySQL Workbench
https://dev.mysql.com/downloads/windows/installer/8.0.html

gitbash
https://git-scm.com/downloads

Apache Maven
https://maven.apache.org/download.cgi


### Installing

A step by step series of examples that tell you how to get a development env running:
Step 1- Clone the repsitory to make a local copy, using gitbash or importing into eclipse
Step 2- Locate and edit the db.properties file so that the user and password match that of your MySQL
Step 3- Open and run the database schema and data found from the repo
Right click in the main "IMS-Project" folder and launch "Git Bash Here".
Type mvn clean.
Type mvn package.
Close GitBash terminal.
Move to the "target" folder.
Right click in the folder and launch "Git Bash Here".
Type "java -jar ims-0.0.1-jar-with-dependencies.jar". ``


## Running the tests

As the Prerequisites have been complete access the testing feature through the src/test/java.

Select folder with right click.
Select "Coverage As"
Select "JUnit test" This will run all the tests and generate a coverage table indicating how much of the code has been covered in the testing and what percentage passed/failed/errored.

### Unit Tests 

The unit tests are designed to test the core files of the programme, as well as the most basic methods for each class. This includes the CRUD and Controller tests. The tests are run with set results expected and are only able to pass when the test return matches the expected results for each test.
Example:

//Junit example
class TargetClass_TEST {
    @Test
    public void constructor_TEST(){
        TargetClass object = new TargetClass();
        // expectation, result
        AssertTrue(object instanceof TargetClass.class);
    }

    @Test
    public void getID_TEST(){
        TargetClass object = new TargetClass();
        // expectation, result
        AssertEquals(0, object.id);
    }

    @Test
    public void rtnMessage_TEST(){
        TargetClass object = new TargetClass();
        String result = object.rtnMessage();
        // expectation, result
        AssertEquals("Hello World", result);
    }
}


### Integration Tests 

Integration testing is a testing approach that targets the very fundamental building blocks of an application, the idea is to prove that each 'integration' of the application is functioning as expected.


## Built With

Git - Version Control System
GitHub - Source Code Management
Jira - Kanban Board
MySQLServer - Database Management System
Java - Back-End Programming Language
Maven - Build Tool
JUnit - Unit Testing
Mockito - Mock Testing

## Versioning

Github for versioning.

## Authors

* **Chris Perrins** - *Initial work* - [christophperrins](https://github.com/christophperrins)
* **Jariful Hoque** - *Final work* - [jarifulhoque](https://github.com/jarifulhoque)
* 
## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgments

* Thanks and appreciation to my trainers and peers for their help and guidance
