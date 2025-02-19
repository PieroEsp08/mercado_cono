package com.example.conomarket.services;

import com.example.conomarket.models.Producto;
import com.example.conomarket.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    public Optional<Producto> getProductoById(Long id) {
        return productoRepository.findById(id);
    }

    public Producto createProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public Producto updateProducto(Long id, Producto productoDetails) {
        return productoRepository.findById(id).map(producto -> {
            producto.setNombre(productoDetails.getNombre());
            producto.setDescripcion(productoDetails.getDescripcion());
            producto.setCategoria(productoDetails.getCategoria());
            producto.setMarca(productoDetails.getMarca());
            producto.setModelo(productoDetails.getModelo());
            producto.setCodigoSku(productoDetails.getCodigoSku());
            producto.setImagenUrl(productoDetails.getImagenUrl());
            return productoRepository.save(producto);
        }).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    public Producto deleteProducto(Long id) {
        return productoRepository.findById(id).map(producto -> {
            producto.setEstado(0); // Eliminación lógica
            return productoRepository.save(producto);
        }).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }
}

