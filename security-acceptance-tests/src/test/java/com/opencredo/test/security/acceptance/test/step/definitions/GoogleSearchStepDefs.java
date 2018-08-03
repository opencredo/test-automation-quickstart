package com.opencredo.test.security.acceptance.test.step.definitions;

import com.opencredo.test.security.acceptance.test.interaction.objects.GoogleSearchPageObject;
import com.opencredo.test.security.acceptance.test.stepdefstables.StepDefRisks;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.zaproxy.clientapi.core.Alert;
import org.zaproxy.clientapi.core.ClientApi;
import org.zaproxy.clientapi.core.ClientApiException;

import java.io.*;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class GoogleSearchStepDefs extends AbstractStepDefinition {
    private static Logger log = Logger.getLogger(GoogleSearchStepDefs.class.getName());

    @Autowired
    private GoogleSearchPageObject googleSearchPage;
    @Autowired
    private ClientApi clientApi;

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
    public void iAmOnTheGoogleSearchPage() throws Throwable {
        googleSearchPage.goToAndWait();
    }

    @When("^I search for \"(.+)\"$")
    public void iSearchFor(String searchText) throws Throwable {
        googleSearchPage.search(searchText);
    }

    @Then("^the site \"(.+)\" should be present in the results$")
    public void theResultShouldContainUrl(String resultUrl) throws Throwable {
        assertThat(googleSearchPage.isSearchResultPresent(resultUrl)).isTrue();
    }


    @Then("^the number of risks per category should not be greater than$")
    public void theNumberOfSecurityAlertsShouldNotBeGreaterThan(List<StepDefRisks> risks) throws Throwable {
        List<Alert> alertsList = null;

        try {
            alertsList = clientApi.getAlerts(googleSearchPage.getPath(), -1, -1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<Alert> lowAlerts = getAlertsWithRisk(alertsList, Alert.Risk.Low);
        List<Alert> mediumAlerts = getAlertsWithRisk(alertsList, Alert.Risk.Medium);
        List<Alert> highAlerts = getAlertsWithRisk(alertsList, Alert.Risk.High);

        assertThat(lowAlerts.size()).as("Low risk Alerts").isLessThanOrEqualTo(risks.get(0).getLow());
        assertThat(mediumAlerts.size()).as("Medium risk Alerts").isLessThanOrEqualTo(risks.get(0).getMedium());
        assertThat(highAlerts.size()).as("High risk Alerts").isLessThanOrEqualTo(risks.get(0).getHigh());

        String setPlainText = "\033[0;0m";
        String setBoldText = "\033[0;1m";
        log.info(setBoldText + " Found " + lowAlerts.size() + " low risk alerts: " + setPlainText);
        lowAlerts.forEach(a -> log.info("Alert: " + a.getAlert() + " at URL: " + a.getUrl() + " Parameter: " + a.getParam() + " CWE ID: " + a.getCweId() + " Risk: " + a.getRisk()));
        log.info(setBoldText + " Found " + mediumAlerts.size() + " medium risk alerts: " + setPlainText);
        mediumAlerts.forEach(a -> log.info("Alert: " + a.getAlert() + " at URL: " + a.getUrl() + " Parameter: " + a.getParam() + " CWE ID: " + a.getCweId() + " Risk: " + a.getRisk()));
        log.info(setBoldText + " Found " + highAlerts.size() + " high risk alerts: " + setPlainText);
        highAlerts.forEach(a -> log.info("Alert: " + a.getAlert() + " at URL: " + a.getUrl() + " Parameter: " + a.getParam() + " CWE ID: " + a.getCweId() + " Risk: " + a.getRisk()));
    }

    private List<Alert> getAlertsWithRisk(List<Alert> alertsList, Alert.Risk risk) {
        return alertsList.stream().filter(a -> a.getRisk().equals(risk)).collect(Collectors.toList());
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
            String reportPath = "security-reports/security-report.html";
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
