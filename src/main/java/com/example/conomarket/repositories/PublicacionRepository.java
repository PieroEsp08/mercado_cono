package com.example.conomarket.repositories;

import com.example.conomarket.models.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicacionRepository extends JpaRepository<Publicacion,Long> {

}
