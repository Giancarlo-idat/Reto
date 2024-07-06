package com.civa.futbolista_posicion.controller;

import com.civa.futbolista_posicion.dto.role.RoleCreateRequestDTO;
import com.civa.futbolista_posicion.dto.role.RoleResponseDTO;
import com.civa.futbolista_posicion.service.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(@Autowired RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<RoleResponseDTO>> getAllRoles() {
        return new ResponseEntity<>(roleService.findAll(), HttpStatusCode.valueOf(200));
    }

    @GetMapping("/id")
    public ResponseEntity<Optional<RoleResponseDTO>> getRoleById(@RequestParam UUID roleId) {
        return new ResponseEntity<>(roleService.findById(roleId), HttpStatusCode.valueOf(200));
    }

    @GetMapping("/name")
    public ResponseEntity<List<RoleResponseDTO>> getRoleByName(@RequestParam String roleName) {
        return new ResponseEntity<>(roleService.findByName(roleName), HttpStatusCode.valueOf(200));
    }


    @PostMapping("/save")

    public ResponseEntity<?> saveRole(@RequestBody @Valid RoleCreateRequestDTO roleRequest) {
        try {
            return new ResponseEntity<>(roleService.save(roleRequest), HttpStatusCode.valueOf(201));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(400));
        }
    }


    @PutMapping("/update")

    public ResponseEntity<?> updateRole(@RequestBody RoleCreateRequestDTO roleRequest, @RequestParam UUID roleId) {
        try {
            return new ResponseEntity<>(roleService.update(roleRequest, roleId), HttpStatusCode.valueOf(200));
        } catch (Exception e) {
            return new ResponseEntity<>(("Error al actualizar el rol " + (e.getMessage())), HttpStatusCode.valueOf(400));
        }

    }

    @PutMapping("/disable")
    public ResponseEntity<?> disableRole(@PathVariable UUID roleId) {
        try {
            roleService.disable(roleId);
            return new ResponseEntity<>(HttpStatusCode.valueOf(204));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(400));
        }
    }

    @PutMapping("/enable")
    public ResponseEntity<?> enableRole(@RequestParam UUID roleId) {
        try {
            return new ResponseEntity<>(roleService.enable(roleId), HttpStatusCode.valueOf(200));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(400));
        }
    }
}
