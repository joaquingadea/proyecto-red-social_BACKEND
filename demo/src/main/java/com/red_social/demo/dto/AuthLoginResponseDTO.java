package com.red_social.demo.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.NotBlank;

@JsonPropertyOrder({"username","message","jwt","status"})
public record AuthLoginResponseDTO(
        String username,
        String message,
        String jwt,
        boolean status
) {
}
