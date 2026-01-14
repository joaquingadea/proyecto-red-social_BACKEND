package com.red_social.demo.dto;

import java.time.LocalDateTime;

public record CreateMessageResponseDTO(
        String userCreator,
        Long id,
        String createdTitle,
        String createdMessage,
        LocalDateTime creationDate,
        String response,
        boolean status
) {
}
