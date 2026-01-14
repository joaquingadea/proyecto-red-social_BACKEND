package com.red_social.demo.dto;

import java.time.LocalDateTime;

public record MessageResponseDTO(
        Long id,
        String title,
        String text,
        LocalDateTime creationDate,
        String userCreator
) {
}
