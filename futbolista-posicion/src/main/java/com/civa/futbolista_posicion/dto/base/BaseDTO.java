package com.civa.futbolista_posicion.dto.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseDTO {

    private static final LocalDateTime DEFAULT_DATE_TIME = LocalDateTime.now();

    @Builder.Default
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createdAt = DEFAULT_DATE_TIME;

    @Builder.Default
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime updatedAt = null;

    @Builder.Default
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime deletedAt = null;

    @Builder.Default
    private Boolean status = true;

}
