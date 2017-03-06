package com.opencredo.test.security.acceptance.test.step.definitions;

import com.opencredo.test.security.acceptance.test.interaction.objects.GoogleSearchPageObject;
import com.opencredo.test.security.acceptance.test.stepdefstables.StepDefRisks;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.zaproxy.clientapi.core.Alert;
import org.zaproxy.clientapi.core.ClientApi;
import org.zaproxy.clientapi.core.ClientApiException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class GoogleSearchStepDefs extends AbstractStepDefinition {
    @Autowired
    private GoogleSearchPageObject googleSearchPage;
    @Autowired
    private ClientApi clientApi;

    private final String setBoldText = "\033[0;1m";
    private final String setPlainText = "\033[0;0m";
    private final String reportPath = "security-reports/security-report.html";

    static Logger log = Logger.getLogger(GoogleSearchStepDefs.class.getName());

    @Before
    public void before(Scenario scenario) {
        super.before(scenario);
        try {
            clientApi.core.deleteAllAlerts(null);
        } catch (ClientApiException e) {
            e.printStackTrace();
        }
    }

    @Given("^I am on the Google search page")
    public void I_am_on_the_google_search_page() throws Throwable {
        googleSearchPage.goToAndWait();
    }

    @When("^I search for \"(.+)\"$")
    public void I_search_for(String searchText) throws Throwable {
        googleSearchPage.search(searchText);
    }

    @Then("^the site \"(.+)\" should be present in the results$")
    public void the_result_should_contain_url(String resultUrl) throws Throwable {
        assertThat(googleSearchPage.isSearchResultPresent(resultUrl)).isTrue();
    }


    @And("^the number of risks per category should not be greater than$")
    public void theNumberOfSecurityAlertsShouldNotBeGreaterThan(List<StepDefRisks> risks) throws Throwable {
        List<Alert> alertsList = null;

        try {
            alertsList = clientApi.getAlerts(googleSearchPage.getPath(), -1, -1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<Alert> lowAlerts = alertsList.stream().filter(a -> a.getRisk().equals(Alert.Risk.Low)).collect(Collectors.toList());
        List<Alert> mediumAlerts = alertsList.stream().filter(a -> a.getRisk().equals(Alert.Risk.Medium)).collect(Collectors.toList());
        List<Alert> highAlerts = alertsList.stream().filter(a -> a.getRisk().equals(Alert.Risk.High)).collect(Collectors.toList());

        assertThat(lowAlerts.size() <= risks.get(0).getLow()).as("Low risk Alerts").isTrue();
        assertThat(mediumAlerts.size() <= risks.get(0).getMedium()).as("Medium risk Alerts").isTrue();
        assertThat(highAlerts.size() <= risks.get(0).getHigh()).as("High risk Alerts").isTrue();

        log.info(setBoldText + " Found " + lowAlerts.size() + " low risk alerts: " + setPlainText);
        lowAlerts.forEach(a -> log.info("Alert: " + a.getAlert() + " at URL: " + a.getUrl() + " Parameter: " + a.getParam() + " CWE ID: " + a.getCweId() + " Risk: " + a.getRisk()));
        log.info(setBoldText + " Found " + mediumAlerts.size() + " medium risk alerts: " + setPlainText);
        mediumAlerts.forEach(a -> log.info("Alert: " + a.getAlert() + " at URL: " + a.getUrl() + " Parameter: " + a.getParam() + " CWE ID: " + a.getCweId() + " Risk: " + a.getRisk()));
        log.info(setBoldText + " Found " + highAlerts.size() + " high risk alerts: " + setPlainText);
        highAlerts.forEach(a -> log.info("Alert: " + a.getAlert() + " at URL: " + a.getUrl() + " Parameter: " + a.getParam() + " CWE ID: " + a.getCweId() + " Risk: " + a.getRisk()));
    }

    @After()
    public void generateHTMLReport() {
        byte[] report = null;
        try {
            report = clientApi.core.htmlreport(null);
        } catch (ClientApiException e) {
            e.printStackTrace();
        }
        OutputStream htmlFile = null;
        try {
            File htmlReport = new File(reportPath);
            htmlReport.getParentFile().mkdirs();
            htmlReport.createNewFile();
            htmlFile = new FileOutputStream(htmlReport.getAbsoluteFile());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            htmlFile.write(report);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            htmlFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
