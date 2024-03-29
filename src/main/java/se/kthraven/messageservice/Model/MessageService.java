package se.kthraven.messageservice.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import se.kthraven.messageservice.Model.classes.Message;
import se.kthraven.messageservice.Persistence.IMessagePersistence;
import se.kthraven.messageservice.Persistence.entities.MessageDB;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class MessageService implements IMessageService{

    @Autowired
    private IMessagePersistence persistence;

    @Override
    public Collection<Message> getConversation(String userId1, String userId2) {
        Authentication authToken = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt = (Jwt) authToken.getPrincipal();
        String userId = jwt.getClaim("sub");
        if(!(userId.equals(userId1) || userId.equals(userId2)))
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        Collection<MessageDB> messageDbs = persistence.getConversation(userId1, userId2);
        ArrayList<Message> messages = new ArrayList<>();
        for(MessageDB messageDb : messageDbs){
            messages.add(Message.from(messageDb));
        }
        return messages;
    }

    @Override
    public void createMessage(Message message) {
        Authentication authToken = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt = (Jwt) authToken.getPrincipal();
        String userId = jwt.getClaim("sub");
        System.out.println(userId);
        if(!(userId.equals(message.getSenderId())))
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);

        MessageDB messageDb = message.toMessageDb();

        persistence.createMessage(messageDb);
    }
}
