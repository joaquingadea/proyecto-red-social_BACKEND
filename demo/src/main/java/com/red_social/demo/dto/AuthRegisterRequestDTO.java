package com.red_social.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthRegisterRequestDTO(
        @NotBlank String username,
        @Size(min = 8)
        @NotBlank String password,
        @Size(min = 8)
        @NotBlank String passwordConfirm
) {
}
