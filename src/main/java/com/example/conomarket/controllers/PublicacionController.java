package com.example.conomarket.controllers;

import com.example.conomarket.models.Publicacion;
import com.example.conomarket.services.PublicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/publicaciones")
@CrossOrigin(origins = "*")
public class PublicacionController {

    @Autowired
    private PublicacionService publicacionService;

    @GetMapping
    public List<Publicacion> getAllPublicaciones() {
        return publicacionService.getAllPublicaciones();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Publicacion> getPublicacionById(@PathVariable Long id) {
        Optional<Publicacion> publicacion = publicacionService.getPublicacionById(id);
        return publicacion.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Publicacion createPublicacion(@RequestBody Publicacion publicacion) {
        return publicacionService.createPublicacion(publicacion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Publicacion> updatePublicacion(@PathVariable Long id, @RequestBody Publicacion publicacionDetails) {
        try {
            Publicacion updatedPublicacion = publicacionService.updatePublicacion(id, publicacionDetails);
            return ResponseEntity.ok(updatedPublicacion);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Publicacion> deletePublicacion(@PathVariable Long id) {
        return ResponseEntity.ok(publicacionService.deletePublicacion(id));
    }


}
