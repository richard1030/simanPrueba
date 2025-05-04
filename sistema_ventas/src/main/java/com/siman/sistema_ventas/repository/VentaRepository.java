package com.siman.sistema_ventas.repository;

import com.siman.sistema_ventas.dto.ClienteIngresoDTO;
import com.siman.sistema_ventas.model.Venta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface VentaRepository extends JpaRepository<Venta, Integer> {
       @Query("SELECT new com.siman.sistema_ventas.dto.ClienteIngresoDTO(c.nombre, SUM(d.total)) " +
       "FROM Venta v JOIN v.cliente c JOIN v.detalles d " +
       "GROUP BY c.nombre ORDER BY SUM(d.total) DESC")
       List<ClienteIngresoDTO> obtenerClientesPorIngresoDesc();


       @Query(value = "SELECT SUM(d.total) " +
       "FROM detalle_venta d " +
       "JOIN venta v ON d.id_venta = v.id " +
       "WHERE v.fecha >= CURRENT_DATE - INTERVAL '1 month'", nativeQuery = true)
       Double obtenerIngresoUltimoMes();

   }