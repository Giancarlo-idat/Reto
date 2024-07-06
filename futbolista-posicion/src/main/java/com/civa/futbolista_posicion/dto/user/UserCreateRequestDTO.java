package com.civa.futbolista_posicion.dto.user;

import com.civa.futbolista_posicion.dto.base.BaseDTO;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class UserCreateRequestDTO extends BaseDTO {

    @Builder.Default
    private UUID idUser = UUID.randomUUID();

    @Size(min = 3, max = 50, message = "El nombre debe contener entre 3 y 50 caracteres")
    @NotBlank(message = "El nombre es requerido")
    private String name;

    @Size(min = 3, max = 50, message = "El apellido debe contener entre 3 y 50 caracteres")
    @NotBlank(message = "El apellido es requerido")
    private String lastName;

    @Size(min = 3, max = 50, message = "El nickname debe contener entre 3 y 50 caracteres")
    private String nickname;

    @Email(message = "El email no es válido")
    @NotBlank(message = "El email es requerido")
    private String email;

    @Size(max = 9, message = "El teléfono debe contener máximo 9 caracteres")
    private String phone;

    @Size(max = 100, message = "La dirección debe contener máximo 100 caracteres")
    private String address;

    @NotNull(message = "La fecha de nacimiento es requerida")
    @Past(message = "La fecha de nacimiento debe ser en el pasado")
    private LocalDate birthday;

    @NotBlank(message = "El password es requerido")
    private String password;

}
