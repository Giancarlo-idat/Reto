package com.civa.futbolista_posicion.dto.user;

import com.civa.futbolista_posicion.dto.base.BaseDTO;
import com.civa.futbolista_posicion.dto.role.RoleResponseDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponseDTO extends BaseDTO {

    private UUID idUser;

    private String name;

    private String lastName;

    private String nickname;

    private String email;

    private String phone;

    private String address;

    private LocalDate birthday;


    private String password;


}
