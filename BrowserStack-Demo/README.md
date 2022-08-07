Selenium Cucumber tests


To Set User Credentials for Browser Stack:
Go to config file : configs/Configuration.properties and modify username and access_key

T0 execute the Test in Local:

Go to config file : configs/Configuration.properties and update the value for environment as 'local' and enter a browser
of choice

To execute the Test with TestNG
  --> Navigate to src\test\java\runners\TestNgTestRunner.java class and run the class as TestNG

To execute the Test with JUnit
 --> Navigate to src\test\java\runners\JUnitTestRunner.java class and run the class as JUnit

To view the Feature file
Go to src\test\resources\Features\FlipKart.feature
 
To view Reports after Test Execution:
Go to target\cucumber-reports\report.html and open it in a browser of choice


