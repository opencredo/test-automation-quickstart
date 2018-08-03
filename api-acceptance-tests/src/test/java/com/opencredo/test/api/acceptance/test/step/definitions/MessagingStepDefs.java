package com.opencredo.test.api.acceptance.test.step.definitions;

import com.opencredo.test.api.acceptance.test.interaction.api.objects.MessagingApi;
import com.opencredo.test.api.acceptance.test.interaction.dto.User;
import com.opencredo.test.utils.RandomUtils;
import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class MessagingStepDefs extends AbstractStepDefinition {
    @Autowired
    MessagingApi messagingApi;

    @Before
    public void before(Scenario scenario) {
        super.before(scenario);
    }

    @Given("^the following users:$")
    public void theFollowingUsers(List<String> userAliases) throws Throwable {
        for (String userAlias : userAliases) {
            createUser(userAlias);
        }
    }

    @When("^I log in as \"([^\"]*)\"$")
    public void iLogInAs(String userAlias) throws Throwable {
        logIn(userAlias);
    }

    @When("^I send a message to \"([^\"]*)\"$")
    public void iSendAMessageTo(String userAlias) throws Throwable {
        User recipient = testWorld.getUserByAlias(userAlias);
        User sender = testWorld.getCurrentUser();

        String message = RandomUtils.randomAlphaString(20);

        messagingApi.sendMessage(sender.userName, sender.authToken, recipient.userName, message);
    }

    @Then("^\"([^\"]*)\" should have received a message from \"([^\"]*)\"$")
    public void shouldHaveReceivedAMessageFrom(String recipientUserAlias, String senderUserAlias) throws Throwable {
        User recipient = testWorld.getUserByAlias(recipientUserAlias);
        User sender = testWorld.getUserByAlias(senderUserAlias);

        List<String> peopleWhoSentMessagesToUser = messagingApi.getMessages().stream()
                .filter(m -> m.recipientUserName.equals(recipient.userName))
                .map(m -> m.senderUserName)
                .collect(Collectors.toList());

        assertThat(peopleWhoSentMessagesToUser).contains(sender.userName);
    }

    /**
     * Helper methods
     */

    private void createUser(String userAlias) {
        User newUser = new User();

        messagingApi.registerUser(newUser);

        testWorld.addUser(userAlias, newUser);
    }

    private void logIn(String userAlias) {
        User user = testWorld.getUserByAlias(userAlias);

        user.authToken = messagingApi.authenticate(user.userName, testWorld.USER_PASSWORD);

        testWorld.currentUserAlias = userAlias;
        testWorld.addUser(userAlias, user);
    }


}
