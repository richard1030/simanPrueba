package com.siman.sistema_ventas.controller;


import com.siman.sistema_ventas.dto.ClienteIngresoDTO;
import com.siman.sistema_ventas.dto.TopProductoDTO;
import com.siman.sistema_ventas.service.VentaService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    private final VentaService ventaService;

    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    // Endpoint para obtener los 3 productos mas vendidos
    @GetMapping("/top-productos")
    public ResponseEntity<List<TopProductoDTO>> obtenerTopProductos() {
        List<TopProductoDTO> topProductos = ventaService.obtenerTopProductosMasVendidos();
        return ResponseEntity.ok(topProductos);
    }


    //endpoint para cliente mayor compra
    @GetMapping("/mayor-cliente")
    public ResponseEntity<ClienteIngresoDTO> obtenerClienteMayorIngreso() {
    ClienteIngresoDTO dto = ventaService.obtenerClienteMayorIngreso();
    if (dto == null) {
        return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(dto);
    }

    // ingreso total ultimo mes
    @GetMapping("/ingreso-ultimo-mes")
    public ResponseEntity<Double> ingresoUltimoMes() {
    return ResponseEntity.ok(ventaService.obtenerIngresoUltimoMes());
    }

}
