package se.kthraven.messageservice.Persistence;

import se.kthraven.messageservice.Persistence.entities.MessageDB;

import java.util.Collection;

public interface IMessagePersistence {
    Collection<MessageDB> getConversation(String userId1, String userId2);
    void createMessage(MessageDB message);
}
