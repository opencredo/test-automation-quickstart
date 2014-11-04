
#test-automation-quickstart

Java quickstart project for test automation, covering performance, ui acceptance and api acceptance testing.
Created with lessons learned from a large number of development projects to provide all commonly required components and concepts.

##Concepts Included

* Parallel test runs
* Shared state across cucumber step definitions
* Dependency injection
* Page Object pattern
* Common web page interaction methods
* Common api interaction methods
* Mavenised performance tests
* Externalised test configuration
* Commonly used test utility classes

##Tools

* Maven
* Cucumber-JVM
* JUnit
* Spring
* Selenium Webdriver
* Apache HTTPClient
* Jackson
* JMeter

##Requirements

In order to utilise this project you need to have the following installed locally:

* Maven 3
* Firefox (used by default for UI tests, this can be changed in the code)
* Java 1.7

##Usage

The project is broken into separate modules for API, UI and Performance testing. Each of these modules can be utilised independently of the others using maven profiles.

To run all modules, navigate to test-automation-quickstart directory and run:

`mvn clean install`

To run UI acceptance tests only, navigate to test-automation-quickstart directory and run:

`mvn clean install -Pui-acceptance-tests`

To run API acceptance tests only, navigate to test-automation-quickstart directory and run:

`mvn clean install -Papi-acceptance-tests`

To run performance tests only, navigate to test-automation-quickstart directory and run:

`mvn clean install -Pperformance-tests`

##Reporting

Reports for each module are written into their respective /target directories after a successful run.

UI acceptance tests result in a HTML report for each test class being generated in test-automation-quickstart/ui-acceptance-tests/target/cucumber-report/.
In the case of test failures, a screen-shot of the UI at the point of failure is embedded into the report.

API acceptance tests result in a HTML report for each test class being generated in test-automation-quickstart/api-acceptance-tests/target/cucumber-report/.

Performance tests result in a .jtl results file and .png graphs showing response times and transactions per second, generated in test-automation-quickstart/performance-tests/target/jmeter/results

*NOTE*:
As mentioned, cucumber reports are written to a separate file for each test class (*AT.class). This approach supports running tests in parallel.
If using CI, these individual reports can be joined using plugins such as the Jenkins Cucumber-JVM-Reports plugin.
For an alternative approach to combining the cucumber reports, see the [parallel testing blog post on OpenCredo.com]|(http://www.opencredo.com/2013/07/02/running-cucumber-jvm-tests-in-parallel)
