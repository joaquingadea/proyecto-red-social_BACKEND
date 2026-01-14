package com.red_social.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateMessageRequestDTO(
        @NotBlank
        @Size(max = 75)
        String title,
        @NotBlank
        @Size(max = 250)
        String text
) {
}
