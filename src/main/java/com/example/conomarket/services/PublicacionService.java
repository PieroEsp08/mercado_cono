package com.example.conomarket.services;

import com.example.conomarket.models.Publicacion;
import com.example.conomarket.repositories.PublicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublicacionService {

    @Autowired
    private PublicacionRepository publicacionRepository;

    public List<Publicacion> getAllPublicaciones() {
        return publicacionRepository.findAll();
    }

    public Optional<Publicacion> getPublicacionById(Long id) {
        return publicacionRepository.findById(id);
    }

    public Publicacion createPublicacion(Publicacion publicacion) {
        return publicacionRepository.save(publicacion);
    }

    public Publicacion updatePublicacion(Long id, Publicacion publicacionDetails) {
        return publicacionRepository.findById(id).map(publicacion -> {
            publicacion.setPrecio(publicacionDetails.getPrecio());
            publicacion.setStock(publicacionDetails.getStock());
            publicacion.setEstado(publicacionDetails.getEstado());
            return publicacionRepository.save(publicacion);
        }).orElseThrow(() -> new RuntimeException("Publicación no encontrada"));
    }

    public Publicacion deletePublicacion(Long id) {
        return publicacionRepository.findById(id).map(publicacion -> {
            publicacion.setEstado(0); // Cambia el estado a inactivo
            return publicacionRepository.save(publicacion);
        }).orElseThrow(() -> new RuntimeException("Publicación no encontrada"));
    }

}
