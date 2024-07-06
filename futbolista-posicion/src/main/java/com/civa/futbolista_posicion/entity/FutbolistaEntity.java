package com.civa.futbolista_posicion.entity;


import com.civa.futbolista_posicion.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "futbolista")
@SuperBuilder
public class FutbolistaEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_futbolista", nullable = false, unique = true)
    private UUID idFutbolista;

    @Column(name = "nombres", nullable = false)
    private String nombres;

    @Column(name = "apellidos", nullable = false)
    private String apellidos;

    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    @ElementCollection
    @CollectionTable(name = "caracteristicas", joinColumns = @JoinColumn(name = "id_futbolista"))
    @MapKeyColumn(name = "caracteristicas_key")
    @Column(name = "caracteristicas_value")
    private Map<String, String> caracteristicas;

    @ManyToMany
    @JoinTable(name = "futbolista_posicion", joinColumns = @JoinColumn(name = "id_futbolista"),
            inverseJoinColumns = @JoinColumn(name = " id_posicion"))
    private Set<PosicionEntity> posiciones = new HashSet<>();
}
