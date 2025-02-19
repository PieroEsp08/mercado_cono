package com.example.conomarket.services;

import com.example.conomarket.models.Pago;
import com.example.conomarket.repositories.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    public List<Pago> getAllPagos() {
        return pagoRepository.findAll();
    }

    public Optional<Pago> getPagoById(Long id) {
        return pagoRepository.findById(id);
    }

    public Pago createPago(Pago pago) {
        return pagoRepository.save(pago);
    }

    public Pago updatePago(Long id, Pago pagoDetails) {
        return pagoRepository.findById(id).map(pago -> {
            pago.setMetodoPago(pagoDetails.getMetodoPago());
            pago.setEstado(pagoDetails.getEstado());
            return pagoRepository.save(pago);
        }).orElseThrow(() -> new RuntimeException("Pago no encontrado"));
    }

    public Pago deletePago(Long id) {
        return pagoRepository.findById(id).map(pago -> {
            pago.setEstado(0); // Cambia el estado a inactivo
            return pagoRepository.save(pago);
        }).orElseThrow(() -> new RuntimeException("Pago no encontrado"));
    }
}
