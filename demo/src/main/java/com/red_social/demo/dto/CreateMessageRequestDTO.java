package com.red_social.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.lang.NonNull;

public record CreateMessageRequestDTO(
        @Size(max = 75)
        String title,
        @Size(max = 250)
        String text
) {
}
