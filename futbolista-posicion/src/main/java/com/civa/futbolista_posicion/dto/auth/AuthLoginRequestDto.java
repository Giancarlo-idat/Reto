package com.civa.futbolista_posicion.dto.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthLoginRequestDto{

    @NotBlank(message = "Email es requerido")
    String email;

    @NotBlank(message = "Password es requerido")
    String password;
}
