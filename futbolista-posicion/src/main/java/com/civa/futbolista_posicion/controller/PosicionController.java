package com.civa.futbolista_posicion.controller;


import com.civa.futbolista_posicion.dto.posicion.PosicionCreateRequestDTO;
import com.civa.futbolista_posicion.dto.posicion.PosicionResponseDTO;
import com.civa.futbolista_posicion.service.PosicionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/posicion")
public class PosicionController {

    private final PosicionService posicionService;

    public PosicionController(PosicionService posicionService) {
        this.posicionService = posicionService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<PosicionResponseDTO>> getAllPosiciones() {
        try {
            return new ResponseEntity<>(posicionService.findAll(), HttpStatusCode.valueOf(200));
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatusCode.valueOf(400));
        }
    }

    @GetMapping("/find")
    public ResponseEntity<Optional<PosicionResponseDTO>> getByIdPosicion(@RequestParam("id") UUID id) {
        try {
            return new ResponseEntity<>(posicionService.findById(id), HttpStatusCode.valueOf(200));
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatusCode.valueOf(400));
        }
    }

    @GetMapping("/findByName")
    public ResponseEntity<List<PosicionResponseDTO>> getByNamePosicion(@RequestParam("name") String name) {
        try {
            return new ResponseEntity<>(posicionService.findByNombre(name), HttpStatusCode.valueOf(200));
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatusCode.valueOf(400));
        }
    }

    @PostMapping("/add")
    public ResponseEntity<PosicionCreateRequestDTO> addPosicion(@RequestBody PosicionCreateRequestDTO posicion) {
        try {
            return new ResponseEntity<>(posicionService.save(posicion), HttpStatusCode.valueOf(201));
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatusCode.valueOf(400));
        }
    }

    @PutMapping("/update")
    public ResponseEntity<PosicionCreateRequestDTO> updatePosicion(@Valid @PathVariable UUID id, @RequestBody PosicionCreateRequestDTO posicion) {
        try {
            return new ResponseEntity<>(posicionService.update(posicion, id), HttpStatusCode.valueOf(200));
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatusCode.valueOf(400));
        }
    }

    @PutMapping("/disable")
    public ResponseEntity<Void> disablePosicion(@PathVariable UUID id) {
        try {
            posicionService.disable(id);
            return new ResponseEntity<>(HttpStatusCode.valueOf(204));
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatusCode.valueOf(400));
        }
    }

    @PutMapping("/enable")
    public ResponseEntity<Void> enablePosicion(@PathVariable UUID id) {
        try {
            posicionService.enable(id);
            return new ResponseEntity<>(HttpStatusCode.valueOf(200));
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatusCode.valueOf(400));
        }
    }
}
