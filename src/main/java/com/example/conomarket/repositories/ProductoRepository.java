package com.example.conomarket.repositories;

import com.example.conomarket.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto,Long> {


}
