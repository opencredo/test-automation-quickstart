package com.opencredo.test.api.acceptance.test.step.definitions;


import com.opencredo.test.EmailAdaptor;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import javax.mail.Message;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class EmailStepDefs extends AbstractStepDefinition {

    @When("^I send a test email$")
    public void iSendATestEmailTo() throws Throwable {
        new EmailAdaptor().connect().sendTestEmail();
    }

    @Then("^there should be '(\\d+)' test (?:email|emails) in my inbox$")
    public void thereShouldBeTestEmailsSentTo(int emailCount) throws Throwable {

        Thread.sleep(30000); // wait for emails to be sent

        final EmailAdaptor emailAdaptor = new EmailAdaptor();
        final List<Message> messages = emailAdaptor.connect().getTestEmails();
        emailAdaptor.disconnect();

        assertThat(messages.size()).isEqualTo(emailCount);
    }

    @Given("^test emails have been deleted from my account$")
    public void testEmailsHaveBeenDeletedFromMyAccount() throws Throwable {
        final EmailAdaptor emailAdaptor = new EmailAdaptor();
        emailAdaptor.connect().deleteTestEmails();
    }
}
