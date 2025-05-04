package com.siman.sistema_ventas.repository;

import com.siman.sistema_ventas.model.DetalleVenta;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Pageable;

public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Long> {
    List<DetalleVenta> findByVentaId(Long ventaId);

    @Query("SELECT dv.producto.nombre AS nombre, SUM(dv.cantidad) AS totalVendidas " +
       "FROM DetalleVenta dv " +
       "GROUP BY dv.producto.nombre " +
       "ORDER BY totalVendidas DESC")
List<Object[]> encontrarTopProductos(Pageable pageable);


}
