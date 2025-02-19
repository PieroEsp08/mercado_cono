package com.example.conomarket.services;

import com.example.conomarket.models.DetalleCompra;
import com.example.conomarket.repositories.DetalleCompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleCompraService {

    @Autowired
    private DetalleCompraRepository detalleCompraRepository;

    public List<DetalleCompra> getAllDetallesCompra() {
        return detalleCompraRepository.findAll();
    }

    public Optional<DetalleCompra> getDetalleCompraById(Long id) {
        return detalleCompraRepository.findById(id);
    }

    public DetalleCompra createDetalleCompra(DetalleCompra detalleCompra) {
        return detalleCompraRepository.save(detalleCompra);
    }

    public DetalleCompra updateDetalleCompra(Long id, DetalleCompra detalleCompraDetails) {
        return detalleCompraRepository.findById(id).map(detalleCompra -> {
            detalleCompra.setCantidad(detalleCompraDetails.getCantidad());
            detalleCompra.setPrecioUnitario(detalleCompraDetails.getPrecioUnitario());
            return detalleCompraRepository.save(detalleCompra);
        }).orElseThrow(() -> new RuntimeException("Detalle de compra no encontrado"));
    }

    public DetalleCompra deleteDetalleCompra(Long id) {
        return detalleCompraRepository.findById(id).map(detalleCompra -> {
            detalleCompra.setEstado(0); // Cambia el estado a inactivo
            return detalleCompraRepository.save(detalleCompra);
        }).orElseThrow(() -> new RuntimeException("Detalle de compra no encontrado"));
    }
}
