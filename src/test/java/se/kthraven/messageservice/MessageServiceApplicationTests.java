package se.kthraven.messageservice;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.server.ResponseStatusException;
import se.kthraven.messageservice.Model.MessageService;
import se.kthraven.messageservice.Model.classes.Message;
import se.kthraven.messageservice.Persistence.IMessagePersistence;
import se.kthraven.messageservice.Persistence.entities.MessageDB;
import se.kthraven.messageservice.config.CustomAuthenticationToken;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MessageServiceApplicationTests {

	@Mock
	IMessagePersistence persistence;

	@InjectMocks
	MessageService messageService;

	@Test
	void testGetConversation() {
		CustomAuthenticationToken authToken = mock(CustomAuthenticationToken.class);
		when(authToken.getUserId()).thenReturn("user1");
		SecurityContextHolder.getContext().setAuthentication(authToken);

		when(persistence.getConversation("user1", "user2"))
				.thenReturn(Arrays.asList(new MessageDB("1", "Hello", null, "1", "2")));

		Collection<Message> messages = messageService.getConversation("user1", "user2");

		assertEquals(1, messages.size());
		assertEquals("Hello", messages.iterator().next().getMessage());

		verify(authToken, times(1)).getUserId();
	}

	@Test
	void testCreateMessage() {
		CustomAuthenticationToken authToken = mock(CustomAuthenticationToken.class);
		when(authToken.getUserId()).thenReturn("user1");
		SecurityContextHolder.getContext().setAuthentication(authToken);

		doNothing().when(persistence).createMessage(any(MessageDB.class));

		Message message = new Message("1", "Hello", null, "user1", "user2");

		messageService.createMessage(message);

		verify(authToken).getUserId();

		verify(persistence).createMessage(argThat(messageDb -> {
			assertEquals("user1", messageDb.getSenderId());
			assertEquals("user2", messageDb.getReceiverId());
			assertEquals("Hello", messageDb.getMessage());
			return true;
		}));
	}
}