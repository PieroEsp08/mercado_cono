package com.example.conomarket.controllers;

import com.example.conomarket.models.DetalleCompra;
import com.example.conomarket.services.DetalleCompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/detallescompra")
public class DetalleCompraController {

    @Autowired
    private DetalleCompraService detalleCompraService;

    @GetMapping
    public List<DetalleCompra> getAllDetallesCompra() {
        return detalleCompraService.getAllDetallesCompra();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleCompra> getDetalleCompraById(@PathVariable Long id) {
        Optional<DetalleCompra> detalleCompra = detalleCompraService.getDetalleCompraById(id);
        return detalleCompra.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public DetalleCompra createDetalleCompra(@RequestBody DetalleCompra detalleCompra) {
        return detalleCompraService.createDetalleCompra(detalleCompra);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalleCompra> updateDetalleCompra(@PathVariable Long id, @RequestBody DetalleCompra detalleCompraDetails) {
        try {
            DetalleCompra updatedDetalleCompra = detalleCompraService.updateDetalleCompra(id, detalleCompraDetails);
            return ResponseEntity.ok(updatedDetalleCompra);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DetalleCompra> deleteDetalleCompra(@PathVariable Long id) {
        return ResponseEntity.ok(detalleCompraService.deleteDetalleCompra(id));
    }
}
