# Rest-Assured-Java-API-Automation
Demo of Rest Assured Java API Automation
API Automation Using Rest Assured & TestNG
This project provides a template for automating REST API testing across multiple environments using TestNG and the Rest Assured library.

Rest Assured Automation CI

Tools & Framework Components

Java (17): Core programming language for framework logic
RestAssured: RESTful API automation and validation
TestNG: Test execution and configuration management
ExtentReports: Generation of interactive HTML test reports
Maven: Project build, dependency, and lifecycle management
GitHub Actions: CI/CD pipeline for automated testing and deployment

Clone the repo in the required directory on your PC 
if you want to run locally:
Clone the repository: 
git clone https://github.com/Vidya-jpg/restAssuredAutomation.git
Update config.properties with the correct base URL.
Run the test suite: 
mvn clean test
View the report: 
Open the generated HTML report at: test-output/ExtentReport.html


How to Run the Tests

Prerequisites

Java 17+

Maven 3.6+

Git

The URL for APIs are given below as provided by you, the python code, i ran that code locally....

http://127.0.0.1:8000/

Project structure

.
¦   .gitignore
¦   LICENSE
¦   pom.xml
¦   README.md
¦   
+---.github
¦   +---workflows
¦           rest-assured-java-ci.yml
¦           
+---.idea
¦       .gitignore
¦       workspace.xml
¦       
+---logs
¦       log-2025-07-08-13-59-23.log
¦       
+---reports
¦   +---html-reports
¦           test_execution_results_2025-07-08 13-59-23.html
¦           
+---src
¦   +---main
¦   ¦   +---java
¦   ¦   ¦
¦   ¦   ¦  +---constants
¦   ¦   ¦      ¦  commonConstant.java
¦   ¦   ¦      ¦  reporterConstant.java 
¦   ¦   ¦  +---models
¦   ¦   ¦      ¦ Books.java
¦   ¦   ¦      ¦ userAuth.java
¦   ¦   ¦  +---service
¦   ¦   ¦      ¦  AuthService.java
¦   ¦   ¦      ¦  bookService.java
¦   ¦   ¦      ¦  ExtentReportService.java 
¦   ¦   ¦  +---utils
¦   ¦   ¦      ¦  AccessTokenManager.java                
¦   ¦   ¦      ¦  commonUtils.java                  
¦   ¦   ¦      ¦  fileReader.java
¦   ¦   ¦      ¦  RequestBuilder.java
¦   ¦   ¦      ¦  TestListener.java 
¦   ¦   ¦  +---resources
¦   ¦           log4j2.xml
¦   ¦           test-reporter.properties
¦   ¦           
¦   +---test
¦       +---java
¦       ¦   +---test
¦       ¦       ¦   AuthTest.java
¦       ¦       ¦   BaseTest.java
¦       ¦       ¦   CreateBookingTest.java
¦       ¦       ¦   DeleteBookingTest.java
¦       ¦       ¦   GetAllBookingsTest.java
¦       ¦       ¦   UpdateBookingTest.java
¦       ¦       ¦ 
¦       +---resources
¦           ¦   config.properties
¦                   

How to run tests

Using IntelliJ Community Edition

     Go to Maven Profiles

     Select qa, uat or prod Maven Profile as the environment

     Select the test classes on the src/test/java/test folder

     Right-click and click on Run

How to check reports:-

After running the mvn commands , you can see the reports under

        1) reports/html-reports
        
        2) target/surefire-reports/emailable-report.html
        
        3) target/surefire-reports/index.html

        4) target/surefire-reports/API Automation Demo Smoke Test Suite/Smoke Test.html
            or
           target/surefire-reports/API Automation Demo Regression Test Suite/Regression Test.html
