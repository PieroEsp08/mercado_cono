package com.example.conomarket.services;

import com.example.conomarket.models.Compra;
import com.example.conomarket.repositories.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompraService {

    @Autowired
    private CompraRepository compraRepository;

    public List<Compra> getAllCompras() {
        return compraRepository.findAll();
    }

    public Optional<Compra> getCompraById(Long id) {
        return compraRepository.findById(id);
    }

    public Compra createCompra(Compra compra) {
        return compraRepository.save(compra);
    }

    public Compra updateCompra(Long id, Compra compraDetails) {
        return compraRepository.findById(id).map(compra -> {
            compra.setTotal(compraDetails.getTotal());
            return compraRepository.save(compra);
        }).orElseThrow(() -> new RuntimeException("Compra no encontrada"));
    }

    public Compra deleteCompra(Long id) {
        return compraRepository.findById(id).map(compra -> {
            compra.setEstado(0); // Cambia el estado a inactivo
            return compraRepository.save(compra);
        }).orElseThrow(() -> new RuntimeException("Compra no encontrada"));
    }
}
