package se.kthraven.messageservice.Model;

import se.kthraven.messageservice.Model.classes.Message;

import java.util.Collection;

public interface IMessageService {
    Collection<Message> getConversation(String userId1, String userId2);
    void createMessage(Message message);
}
