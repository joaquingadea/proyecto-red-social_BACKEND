package com.red_social.demo.service;

import com.red_social.demo.dto.*;
import com.red_social.demo.exception.ResourceNotFoundException;
import com.red_social.demo.model.Message;
import com.red_social.demo.model.Profile;
import com.red_social.demo.model.UserSec;
import com.red_social.demo.repository.IMessageRepository;
import com.red_social.demo.repository.IProfileRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
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
                              m.getProfile().getUser().getUsername(),
                              m.isEdited()
                      )).toList();
    }

    @Override
    public void deleteById(Long id) {
        messageRepository.deleteById(id);
    }

    @Override
    public void edit(Long id, EditMessageRequestDTO requestEdit, Authentication authentication) {

        String username = authentication.getName();

        Message messageEdit = messageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mensaje con id " + id + " no encontrado"));

        if(!(messageEdit.getProfile().getUser().getUsername()
                .equals
                        (username))){
            throw new AccessDeniedException("No podés editar mensajes ajenos");
        }

        messageEdit.setTitle(requestEdit.title());
        messageEdit.setText(requestEdit.text());
        messageEdit.setEdited(true);

        messageRepository.save(messageEdit);
    }

    @Override
    public List<MessageResponseDTO> getAllMessages() {
        return messageRepository.findAll(Sort.by (Sort.Direction.DESC,"creationDate"))
                .stream()
                .map(message -> new MessageResponseDTO(
                        message.getId(),
                        message.getTitle(),
                        message.getText(),
                        message.getCreationDate(),
                        message.getProfile().getUser().getUsername(),
                        message.isEdited()
                ))
                .toList();
    }

    @Override
    public GetEditMessageResponseDTO findById(Long id, Authentication authentication) {
        String username = authentication.getName();

        Message messageFind = messageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mensaje con id " + id + " no encontrado"));

        if(!(messageFind.getProfile().getUser().getUsername()
                .equals
                        (username))){
            throw new AccessDeniedException("No podés editar mensajes ajenos");
        }
        return new GetEditMessageResponseDTO(messageFind.getTitle(), messageFind.getText());
    }
}
