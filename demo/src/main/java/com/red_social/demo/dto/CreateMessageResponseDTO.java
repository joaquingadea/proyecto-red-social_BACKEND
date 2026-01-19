package com.red_social.demo.dto;

import java.time.LocalDateTime;

public record CreateMessageResponseDTO(
        String userCreator,
        Long id,
        String title,
        String text,
        LocalDateTime creationDate,
        String response,
        boolean status
) {
}
