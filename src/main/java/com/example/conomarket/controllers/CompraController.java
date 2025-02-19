package com.example.conomarket.controllers;

import com.example.conomarket.models.Compra;
import com.example.conomarket.services.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/compras")
public class CompraController {

    @Autowired
    private CompraService compraService;

    @GetMapping
    public List<Compra> getAllCompras() {
        return compraService.getAllCompras();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Compra> getCompraById(@PathVariable Long id) {
        Optional<Compra> compra = compraService.getCompraById(id);
        return compra.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Compra createCompra(@RequestBody Compra compra) {
        return compraService.createCompra(compra);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Compra> updateCompra(@PathVariable Long id, @RequestBody Compra compraDetails) {
        try {
            Compra updatedCompra = compraService.updateCompra(id, compraDetails);
            return ResponseEntity.ok(updatedCompra);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Compra> deleteCompra(@PathVariable Long id) {
        return ResponseEntity.ok(compraService.deleteCompra(id));
    }

}
