package se.kthraven.messageservice.Persistence;

import jakarta.persistence.EntityManager;
import se.kthraven.messageservice.Persistence.entities.MessageDB;

import java.util.Collection;
import java.util.UUID;

public class MessagePersistence implements IMessagePersistence{
    @Override
    public Collection<MessageDB> getConversation(String userId1, String userId2) {
        EntityManager em = DBManager.getEntityManager();
        Collection<MessageDB> messages = em.createQuery("SELECT m FROM MessageDB m WHERE (m.senderId = :sid1 AND m.receiverId = :rid1) OR (m.senderId = :sid2 AND m.receiverId = :rid2)" +
                        "order by sendDate", MessageDB.class)
                .setParameter("sid1", userId1)
                .setParameter("rid1", userId2)
                .setParameter("sid2", userId2)
                .setParameter("rid2", userId1)
                .getResultList();
        em.close();
        return messages;
    }

    @Override
    public void createMessage(MessageDB message) {
        EntityManager em = DBManager.getEntityManager();
        em.getTransaction().begin();

        message.setId(UUID.randomUUID().toString());
        em.persist(message);

        em.getTransaction().commit();
        em.close();
    }
}
