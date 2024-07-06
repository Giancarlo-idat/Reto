package com.civa.futbolista_posicion.controller;


import com.civa.futbolista_posicion.dto.futbolista.FutbolistaCreateRequestDTO;
import com.civa.futbolista_posicion.dto.futbolista.FutbolistaResponseDTO;
import com.civa.futbolista_posicion.service.FutbolistaService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/futbolista")
public class FutbolistaController {

    private final FutbolistaService futbolistaService;

    public FutbolistaController(FutbolistaService futbolistaService) {
        this.futbolistaService = futbolistaService;
    }


    @GetMapping("/all")
    public Page<FutbolistaResponseDTO> getAllFutbolistas(@RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "5") int size) {
        return futbolistaService.getAllFutbolistasPaginated(page, size);
    }

    @GetMapping("/find")
    public ResponseEntity<Optional<FutbolistaResponseDTO>> getFutbolistaById(@RequestParam("id") UUID id) {
        try {
            return new ResponseEntity<>(futbolistaService.findById(id), HttpStatusCode.valueOf(200));
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatusCode.valueOf(400));
        }
    }

    @GetMapping("/findByName")
    public ResponseEntity<List<FutbolistaResponseDTO>> getFutbolistaByName(@RequestParam("name") String name) {
        try {
            return new ResponseEntity<>(futbolistaService.findByNombresOrApellidos(name), HttpStatusCode.valueOf(200));
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatusCode.valueOf(400));
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addFutbolista(@Valid @RequestBody FutbolistaCreateRequestDTO futbolista) {
        try {
            return new ResponseEntity<>(futbolistaService.save(futbolista), HttpStatusCode.valueOf(201));
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatusCode.valueOf(400));
        }
    }

    @PutMapping("/update")
    public ResponseEntity<FutbolistaCreateRequestDTO> updateFutbolista(@Valid @RequestBody FutbolistaCreateRequestDTO futbolista, @PathVariable UUID id) {
        try {
            return new ResponseEntity<>(futbolistaService.update(futbolista, id), HttpStatusCode.valueOf(200));
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatusCode.valueOf(400));
        }
    }

    @PutMapping("/disable")
    public ResponseEntity<Void> disableFutbolista(@PathVariable UUID id) {
        try {
            futbolistaService.disable(id);
            return new ResponseEntity<>(HttpStatusCode.valueOf(204));
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatusCode.valueOf(400));
        }
    }

    @PutMapping("/enable")
    public ResponseEntity<FutbolistaResponseDTO> enableFutbolista(@PathVariable UUID id) {
        try {
            return new ResponseEntity<>(futbolistaService.enable(id), HttpStatusCode.valueOf(200));
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatusCode.valueOf(400));
        }
    }

}
