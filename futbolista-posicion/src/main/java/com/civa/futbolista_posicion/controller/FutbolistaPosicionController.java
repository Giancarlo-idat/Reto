package com.civa.futbolista_posicion.controller;

import com.civa.futbolista_posicion.dto.futbolistaPosicion.FutbolistaPosicionCreateRequestDTO;
import com.civa.futbolista_posicion.dto.futbolistaPosicion.FutbolistaPosicionResponseDTO;
import com.civa.futbolista_posicion.service.FutbolistaPosicionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/futbolista-posicion")
public class FutbolistaPosicionController {

    private final FutbolistaPosicionService futbolistaPosicionService;

    public FutbolistaPosicionController(FutbolistaPosicionService futbolistaPosicionService) {
        this.futbolistaPosicionService = futbolistaPosicionService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addFutbolistaPosicion(@Valid @RequestBody FutbolistaPosicionCreateRequestDTO futbolistaPosicionRequest) {
        try {
            return new ResponseEntity<>(futbolistaPosicionService.addFutbolistaPosicion(futbolistaPosicionRequest.getIdFutbolista(), futbolistaPosicionRequest.getIdPosicion()), HttpStatusCode.valueOf(201));
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatusCode.valueOf(400));
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<FutbolistaPosicionResponseDTO>> getAllFutbolistasWithPosicion() {
        try {
            return ResponseEntity.ok(futbolistaPosicionService.getAllFutbolistasWithPosicion());
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatusCode.valueOf(400));
        }
    }
}
