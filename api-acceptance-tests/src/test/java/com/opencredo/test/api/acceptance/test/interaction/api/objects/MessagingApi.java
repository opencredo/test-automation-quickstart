package com.opencredo.test.api.acceptance.test.interaction.api.objects;

import com.opencredo.test.api.acceptance.test.interaction.dto.Message;
import com.opencredo.test.api.acceptance.test.interaction.dto.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy API class used to represent a non-existant messaging system
 */
public class MessagingApi extends AbstractApiObject {
    private List<Message> messages = new ArrayList<>();

    public MessagingApi(String baseUrl) {
        super(baseUrl);
    }

    public void registerUser(User user) {
        //In a real test, this would interact with a system to create a user
    }

    public String authenticate(String userName, String password) {
        //in a real test, this would authenticate with the system and retrieve an auth token for future interactions
        return "dummy-authtoken";
    }

    public void sendMessage(String senderUserName, String senderAuthToken, String recipientUserName, String message) {
        //in a real test, this would interact with the system to create a new message
        messages.add(new Message(senderUserName, recipientUserName, message));
    }

    public List<Message> getMessages() {
        //in a real test, this would interact with the system to retrieve messages (probably for a specific user)
        return messages;
    }
}
