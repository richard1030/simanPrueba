package com.siman.sistema_ventas.repository;

import com.siman.sistema_ventas.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    //colocar consultas personalizadas
}
