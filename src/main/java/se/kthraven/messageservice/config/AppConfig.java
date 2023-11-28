package se.kthraven.messageservice.config;

import org.aspectj.bridge.IMessage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetailsService;
import se.kthraven.messageservice.Model.*;
import se.kthraven.messageservice.Persistence.*;

@Configuration
public class AppConfig {

    @Bean
    public IMessageService IMessageService(){return new MessageService();}
    @Bean
    public IMessagePersistence IMessagePersistence(){return new MessagePersistence();}
}
