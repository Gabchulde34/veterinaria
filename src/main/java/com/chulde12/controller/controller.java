
package com.chulde12.controller;

import com.chulde12.model.Mascota;
import com.chulde12.service.MascotaService;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/api/Mascota")
public class MascotaController {

    private final MascotaService mascotaService;

    public MascotaController(MascotaService mascotaService) {
        this.mascotaService = mascotaService;
    }

    // LISTAR TODAS LAS MASCOTAS
    @GetMapping
    public List<Mascota> listarMascotas() {
        return mascotaService.traerTodos();
    }

    // TRAER UNA MASCOTA POR ID
    @GetMapping("/{id}")
    public ResponseEntity<Mascota> traerUno(@PathVariable int id) {
        return mascotaService.traerUno(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GUARDAR UNA MASCOTA
    @PostMapping
    public ResponseEntity<Mascota> guardar(@RequestBody Mascota mascota) {
        Mascota nuevaMascota = mascotaService.guardar(mascota);
        return new ResponseEntity<>(nuevaMascota, HttpStatus.CREATED);
    }

    // EDITAR UNA MASCOTA
    @PutMapping("/{id}")
    public ResponseEntity<Mascota> editar(
            @PathVariable int id,
            @RequestBody Mascota mascota) {

        return mascotaService.editar(id, mascota)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
