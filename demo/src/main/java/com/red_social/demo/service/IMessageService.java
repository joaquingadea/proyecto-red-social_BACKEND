package com.red_social.demo.service;

import com.red_social.demo.dto.CreateMessageRequestDTO;
import com.red_social.demo.dto.CreateMessageResponseDTO;
import com.red_social.demo.dto.EditMessageRequestDTO;
import com.red_social.demo.dto.MessageResponseDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface IMessageService {
    CreateMessageResponseDTO create(@Valid CreateMessageRequestDTO message, String name);

    List<MessageResponseDTO> getOwnMessages(String name);

    void deleteById(Long id);

    void edit(Long id, EditMessageRequestDTO requestEdit);
}
