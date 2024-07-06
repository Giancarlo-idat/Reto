package com.civa.futbolista_posicion.dto.auth;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Data
@JsonPropertyOrder({"email", "message", "jwt", "status"})
public class AuthResponseDto {
    String email;
    String message;
    String jwt;
    Boolean status;
}
