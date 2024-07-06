package com.civa.futbolista_posicion.controller;


import com.civa.futbolista_posicion.dto.userRole.UserRoleCreateRequestDTO;
import com.civa.futbolista_posicion.dto.userRole.UserRoleResponseDTO;
import com.civa.futbolista_posicion.service.UserRoleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-role")
public class UserRoleController {

    private final UserRoleService userRoleService;

    public UserRoleController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addUserRole(@Valid @RequestBody UserRoleCreateRequestDTO userRoleRequest) {
        try {
            return new ResponseEntity<>(userRoleService.addRoleToUser(userRoleRequest.getIdUser(), userRoleRequest.getIdRole()), HttpStatusCode.valueOf(201));
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatusCode.valueOf(400));
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserRoleResponseDTO>> getAllUsersWithRoles() {
        try {
            return ResponseEntity.ok(userRoleService.getAllUsersWithRoles());
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatusCode.valueOf(400));
        }
    }

}
