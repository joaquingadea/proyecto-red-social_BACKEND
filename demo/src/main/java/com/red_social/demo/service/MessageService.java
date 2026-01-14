package com.red_social.demo.service;

import com.red_social.demo.dto.CreateMessageRequestDTO;
import com.red_social.demo.dto.CreateMessageResponseDTO;
import com.red_social.demo.dto.EditMessageRequestDTO;
import com.red_social.demo.dto.MessageResponseDTO;
import com.red_social.demo.model.Message;
import com.red_social.demo.model.Profile;
import com.red_social.demo.repository.IMessageRepository;
import com.red_social.demo.repository.IProfileRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService implements IMessageService{

    @Autowired
    private IProfileRepository profileRepository;
    @Autowired
    private IMessageRepository messageRepository;

    @Override
    public CreateMessageResponseDTO create(@Valid CreateMessageRequestDTO message, String userCreator) {

        Profile profile = profileRepository.findByUserUsername(userCreator)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        Message newMessage = new Message(
                message.title(),
                message.text(),
                LocalDateTime.now(),
                profile
        );

        messageRepository.save(newMessage);

        return new CreateMessageResponseDTO(
                userCreator,
                newMessage.getId(),
                message.title(),
                message.text(),
                newMessage.getCreationDate(),
                "Successfully created message!",
                true
        );
    }

    @Override
    public List<MessageResponseDTO> getOwnMessages(String name) {
        return messageRepository.findAllByProfileUserUsernameOrderByCreationDateDesc(name)
                .stream()
                .map(m ->
                      new MessageResponseDTO(
                              m.getId(),
                              m.getTitle(),
                              m.getText(),
                              m.getCreationDate(),
                              m.getProfile().getUser().getUsername()
                      )).toList();
    }

    @Override
    public void deleteById(Long id) {
        messageRepository.deleteById(id);
    }

    @Override
    public void edit(Long id, EditMessageRequestDTO requestEdit) {
        Message messageEdit = messageRepository.findById(id).orElseThrow();
        messageEdit.setTitle(requestEdit.title());
        messageEdit.setText(requestEdit.text());
        messageEdit.setEdited(true);
        messageRepository.save(messageEdit);
    }
}
