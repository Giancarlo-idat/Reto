package com.civa.futbolista_posicion.controller;


import com.civa.futbolista_posicion.dto.user.UserCreateRequestDTO;
import com.civa.futbolista_posicion.dto.user.UserResponseDTO;
import com.civa.futbolista_posicion.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserResponseDTO>> findAllUsers() {
        try {
            return new ResponseEntity<>(userService.findAll(), HttpStatusCode.valueOf(200));
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatusCode.valueOf(400));
        }
    }


    @GetMapping("/userId")
    public ResponseEntity<UserResponseDTO> getUserById(@RequestParam UUID id) {
        try {
            return new ResponseEntity<>(userService.findById(id).orElse(null), HttpStatusCode.valueOf(200));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatusCode.valueOf(404));
        }
    }


    @GetMapping("/email")
    public ResponseEntity<Optional<UserResponseDTO>> getUserByEmail(@RequestParam String e) {
        try {
            return new ResponseEntity<>(userService.findByEmail(e), HttpStatusCode.valueOf(200));
        } catch (Exception ex) {
            System.out.println("ERROR " + ex.getMessage());
            return new ResponseEntity<>(HttpStatusCode.valueOf(404));
        }
    }


    @PostMapping("/save")
    public ResponseEntity<?> saveUser(@RequestBody @Valid UserCreateRequestDTO user) {
        try {
            return new ResponseEntity<>(userService.save(user), HttpStatusCode.valueOf(201));
        } catch (Exception ex) {
            System.out.println("ERROR " + ex.getMessage());
            return new ResponseEntity<>(ex.getMessage(), HttpStatusCode.valueOf(400));
        }
    }


    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody @Valid UserCreateRequestDTO user, @RequestParam UUID id) {
        try {
            return new ResponseEntity<>(userService.update(user, id), HttpStatusCode.valueOf(200));
        } catch (Exception ex) {
            System.out.println("ERROR " + ex.getMessage());
            return new ResponseEntity<>(("ERROR " + ex.getMessage()), HttpStatusCode.valueOf(400));
        }
    }


    @DeleteMapping("/delete")
    public ResponseEntity<Void> disable(@PathVariable UUID id) {
        try {
            userService.disable(id);
            return new ResponseEntity<>(HttpStatusCode.valueOf(200));
        } catch (Exception ex) {
            System.out.println("ERROR " + ex.getMessage());
            return new ResponseEntity<>(HttpStatusCode.valueOf(400));
        }
    }


    @PutMapping("/enable")
    public ResponseEntity<UserResponseDTO> enableUser(@PathVariable UUID id) {
        try {
            return new ResponseEntity<>(userService.enable(id), HttpStatusCode.valueOf(200));
        } catch (Exception ex) {
            System.out.println("ERROR " + ex.getMessage());
            return new ResponseEntity<>(HttpStatusCode.valueOf(400));
        }
    }
}
