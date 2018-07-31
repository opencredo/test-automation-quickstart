
# test-automation-quickstart

Java quickstart project for test automation, covering performance, UI acceptance, API acceptance testing and security testing.
Created with lessons learned from a large number of development projects to provide all commonly required components and concepts.
The framework was first introduced on the [OpenCredo.com Blog](http://www.opencredo.com/2014/11/04/test-automation-quickstart-framework/).

## Concepts Included

* Parallel test runs
* Shared state across cucumber step definitions
* Dependency injection
* Page Object pattern
* Common web page interaction methods
* Common api interaction methods
* Mavenised performance tests
* Externalised test configuration
* Commonly used test utility classes
* Simple security tests

## Tools

* Maven
* Cucumber-JVM
* JUnit
* Spring
* Selenium Webdriver
* Jackson
* JMeter
* OWASP Zed Attack Proxy

## Requirements

In order to utilise this project you need to have the following installed locally:

* Maven 3
* Chrome and Chromedriver (UI tests use Chrome by default, can be changed in config)
* Java 1.8
* ZAP 2.7.0 (Run it in deamon mode using the following command: `./zap.sh -daemon -port 8888 -config api.disablekey=true`)

In order for the api tests to pass, you will need to configure your test email. You may need to set up a testing email account (free ones can be made on gmail.com). Then you can either edit it in the API properties file, or supplied as maven parameters. E.g.

`mvn install -Papi-acceptance-tests -Dapi.acceptance.test.email.address=MY_EMAIL_ADDRESS -Dapi.acceptance.test.email.password=MY_EMAIL_PASSWORD`


## Usage

The project is broken into separate modules for API, UI, Performance and Security testing. Each of these modules can be utilised independently of the others using maven profiles.

To run all modules, navigate to `test-automation-quickstart` directory and run:

`mvn clean install`

To run UI acceptance tests only, navigate to `test-automation-quickstart` directory and run:

`mvn clean install -Pui-acceptance-tests`

To run API acceptance tests only, navigate to `test-automation-quickstart` directory and run:

`mvn clean install -Papi-acceptance-tests`

To run performance tests only, navigate to `test-automation-quickstart` directory and run:

`mvn clean install -Pperformance-tests`

To run security tests only, navigate to `test-automation-quickstart` directory and run:

`mvn clean install -Psecurity-acceptance-tests`

## Reporting

Reports for each module are written into their respective `/target` directories after a successful run.

UI acceptance tests result in a HTML report for each feature in `test-automation-quickstart/ui-acceptance-tests/target/cucumber-parallel/`.
In the case of test failures, a screen-shot of the UI at the point of failure is embedded into the report.

API acceptance tests result in a HTML report for each feature in `test-automation-quickstart/api-acceptance-tests/target/cucumber-parallel/`.

Performance tests result in a `.jtl` results file and `.png` graphs showing response times and transactions per second, generated in `test-automation-quickstart/performance-tests/target/jmeter/results`.

Security acceptance tests result in a HTML report for each feature in `test-automation-quickstart/security-acceptance-tests/target/cucumber-parallel/`.
They will also generate a security risks HTML report in `test-automation-quickstart/security-acceptance-tests/security-reports/security-report.html`.

*NOTE*:
As mentioned, cucumber reports are written to a separate file for each feature. This occurs as a result of running tests in parallel, meaning that you do not get a single unified test report.
If using CI, these individual reports can be joined using plugins such as the Jenkins Cucumber-JVM-Reports plugin.

For an alternative approach to combining the cucumber reports, see the [parallel testing blog post on OpenCredo.com](http://www.opencredo.com/2013/07/02/running-cucumber-jvm-tests-in-parallel).
