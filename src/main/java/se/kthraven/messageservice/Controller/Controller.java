package se.kthraven.messageservice.Controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import se.kthraven.messageservice.Model.IMessageService;
import se.kthraven.messageservice.Model.classes.*;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class Controller {

    @Autowired
    private IMessageService messageService;

    @GetMapping("/messages")
    public Collection<Message> getConversation(@RequestParam String userId1, @RequestParam String userId2){
        return messageService.getConversation(userId1, userId2);
    }

    @PostMapping("/message")
    public Message createMessage(@RequestBody Message message){
        messageService.createMessage(message);
        return message;
    }
}
