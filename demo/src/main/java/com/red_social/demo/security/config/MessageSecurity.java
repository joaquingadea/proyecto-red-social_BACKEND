package com.red_social.demo.security.config;

import com.red_social.demo.repository.IMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class MessageSecurity {

    private IMessageRepository messageRepository;

    public MessageSecurity(IMessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public boolean isOwner(Long messageId, Authentication authentication){
        String username = authentication.getName();
        return messageRepository.findById(messageId)
                .map(message -> message.getProfile().getUser().getUsername().equals(username))
                .orElse(false);
    }
}
