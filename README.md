# CPTEST : Test automation framework using Selenium ,TestNG, ExtentReport

A quickstarter guide using page object model to automate any application.



## Features
* Test application against mutiple enviornment via config
* Central test data management for all suites
* Custom enhanced reporting

###open source libraries used
Selenium
TestNG
log4j
Extent Reports

### Steps to clone execute the tests
```
git clone https://github.com/naveenanimation20/PageObjectModel
cd cptest
mvn clean test
```
* Execute test with env specific
```
create/update env specific config file under /resources/config and make entry into common.properties
* Created qa_config.properties
* added entry into common.properties under # Environment Specific configuration file
qa=config/qa_config.properties

Execute test:

mvn clean test -Denv=qa
```




### References:
* https://github.com/SeleniumHQ/selenium.git
* https://github.com/cbeust/testng.git
* https://github.com/anshooarora/extentreports-java.git
* https://github.com/naveenanimation20/PageObjectModel