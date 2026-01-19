package com.red_social.demo.service;

import com.red_social.demo.dto.*;
import com.red_social.demo.model.Message;
import jakarta.validation.Valid;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface IMessageService {
    CreateMessageResponseDTO create(@Valid CreateMessageRequestDTO message, String name);

    List<MessageResponseDTO> getOwnMessages(String name);

    void deleteById(Long id);

    void edit(Long id, EditMessageRequestDTO requestEdit, Authentication authentication);

    List<MessageResponseDTO> getAllMessages();

    GetEditMessageResponseDTO findById(Long id, Authentication authentication);
}
