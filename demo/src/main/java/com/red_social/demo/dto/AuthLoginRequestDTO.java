package com.red_social.demo.dto;

import jakarta.validation.constraints.NotBlank;

// record ->
// permite a traves de los parametros hacer una clase
// crea sus atrbutos, getters y setters
public record AuthLoginRequestDTO(
        @NotBlank String username,
        @NotBlank String password
) {}
