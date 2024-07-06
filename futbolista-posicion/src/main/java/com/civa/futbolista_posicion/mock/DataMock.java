package com.civa.futbolista_posicion.mock;

import com.civa.futbolista_posicion.entity.FutbolistaEntity;
import com.civa.futbolista_posicion.entity.PosicionEntity;
import com.civa.futbolista_posicion.entity.RoleEntity;
import com.civa.futbolista_posicion.entity.UserEntity;
import com.civa.futbolista_posicion.repository.FutbolistaRepository;
import com.civa.futbolista_posicion.repository.PosicionRepository;
import com.civa.futbolista_posicion.repository.RoleRepository;
import com.civa.futbolista_posicion.repository.UserRepository;
import com.civa.futbolista_posicion.service.FutbolistaPosicionService;
import com.civa.futbolista_posicion.service.UserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
public class DataMock implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DataMock.class);

    private final FutbolistaRepository futbolistaRepository;
    private final PosicionRepository posicionRepository;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final FutbolistaPosicionService futbolistaPosicionService;
    private final UserRoleService userRoleService;

    public DataMock(FutbolistaRepository futbolistaRepository, PosicionRepository posicionRepository, RoleRepository roleRepository, UserRepository userRepository,
                    FutbolistaPosicionService futbolistaPosicionService, UserRoleService userRoleService) {
        this.futbolistaRepository = futbolistaRepository;
        this.posicionRepository = posicionRepository;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.futbolistaPosicionService = futbolistaPosicionService;
        this.userRoleService = userRoleService;
    }

    @Override
    public void run(String... args) {
        try {
            if (roleRepository.count() == 0) {
                List<RoleEntity> roles = initRoles();
                for (RoleEntity role : roles) {
                    roleRepository.save(role);
                }
            }

            if (userRepository.count() == 0) {
                List<UserEntity> users = initUsers();
                for (UserEntity user : users) {
                    userRepository.save(user);
                }
            }

            if (futbolistaRepository.count() == 0) {
                List<FutbolistaEntity> futbolistas = initFutbolistas();
                for (FutbolistaEntity futbolista : futbolistas) {
                    futbolistaRepository.save(futbolista);
                }
            }

            if (posicionRepository.count() == 0) {
                List<PosicionEntity> posiciones = initPosiciones();
                for (PosicionEntity posicion : posiciones) {
                    posicionRepository.save(posicion);
                }
            }

            // Añadir roles a usuarios
            if (roleRepository.count() > 0 && userRepository.count() > 0) {
                Set<UUID> userIds = userRepository.findAll().stream().map(UserEntity::getIdUser).collect(Collectors.toSet());
                Set<UUID> roleIds = roleRepository.findAll().stream().map(RoleEntity::getIdRole).collect(Collectors.toSet());
                userRoleService.addRoleToUser(userIds, roleIds);
            }

            // Añadir posiciones a futbolistas
            if (futbolistaRepository.count() > 0 && posicionRepository.count() > 0) {
                Set<UUID> futbolistaIds = futbolistaRepository.findAll().stream().map(FutbolistaEntity::getIdFutbolista).collect(Collectors.toSet());
                Set<UUID> posicionIds = posicionRepository.findAll().stream().map(PosicionEntity::getIdPosicion).collect(Collectors.toSet());
                futbolistaPosicionService.addFutbolistaPosicion(futbolistaIds, posicionIds);
            }

            logger.info("Initial data loaded successfully.");

        } catch (Exception e) {
            logger.error("Error occurred while loading initial data", e);
        }

    }

    private List<RoleEntity> initRoles() {
        List<RoleEntity> rolesList = new ArrayList<>();

        rolesList.add(createRole("Admin"));

        return rolesList;
    }

    private List<UserEntity> initUsers() {
        List<UserEntity> usersList = new ArrayList<>();

        usersList.add(createUser("Admin", "admin", "admin", "admin@hotmail.com", "982189211", "Lima-Perú",
                LocalDate.of(1990, 1, 1), "$2a$10$cMY29RPYoIHMJSuwRfoD3eQxU1J5Rww4VnNOUOAEPqCBshkNfrEf6", true, true, true, true));

        usersList.add(createUser(
                "Juan", "Perez", "Juancito", "juancito@gmail.com", "987654321", "Lima - Perú",
                LocalDate.of(1990, 1, 1), "$2a$10$cMY29RPYoIHMJSuwRfoD3eQxU1J5Rww4VnNOUOAEPqCBshkNfrEf6", true,
                true, true, true));

        return usersList;
    }

    private List<FutbolistaEntity> initFutbolistas() {
        List<FutbolistaEntity> futbolistasList = new ArrayList<>();

        futbolistasList.add(createFutbolista("Lionel", "Messi", LocalDate.of(1987, 6, 24), Map.of("goles", "700", "asistencias", "300")));

        futbolistasList.add(createFutbolista("Cristiano", "Ronaldo", LocalDate.of(1985, 2, 5), Map.of("goles", "750", "asistencias", "200")));

        futbolistasList.add(createFutbolista("Neymar", "Jr", LocalDate.of(1992, 2, 5), Map.of("goles", "300", "asistencias", "150")));

        futbolistasList.add(createFutbolista("Kylian", "Mbappé", LocalDate.of(1998, 12, 20), Map.of("goles", "150", "asistencias", "100")));

        return futbolistasList;

    }

    private List<PosicionEntity> initPosiciones() {
        List<PosicionEntity> posicionesList = new ArrayList<>();

        posicionesList.add(createPosicion("Delantero"));
        posicionesList.add(createPosicion("Volante"));
        posicionesList.add(createPosicion("Mediocampista"));
        posicionesList.add(createPosicion("Defensa"));
        posicionesList.add(createPosicion("Arquero"));

        return posicionesList;
    }

    public static RoleEntity createRole(String name) {
        return RoleEntity.builder()
                .name(name)
                .build();
    }

    public static UserEntity createUser(String name, String lastName, String nickname, String email, String phone, String address, LocalDate birthday, String password,
                                        boolean isEnabled, boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired) {
        return UserEntity.builder()
                .name(name)
                .lastName(lastName)
                .nickname(nickname)
                .email(email)
                .phone(phone)
                .address(address)
                .birthday(birthday)
                .password(password)
                .isEnabled(isEnabled)
                .accountNonExpired(accountNonExpired)
                .accountNonLocked(accountNonLocked)
                .credentialsNonExpired(credentialsNonExpired)
                .build();
    }

    public static FutbolistaEntity createFutbolista(String nombres, String apellidos, LocalDate fechaNacimiento, Map<String, String> caracteristicas) {
        return FutbolistaEntity.builder()
                .nombres(nombres)
                .apellidos(apellidos)
                .fechaNacimiento(fechaNacimiento)
                .caracteristicas(caracteristicas)
                .build();
    }

    public static PosicionEntity createPosicion(String nombre) {
        return PosicionEntity.builder()
                .nombre(nombre)
                .build();
    }

}