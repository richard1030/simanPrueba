package com.siman.sistema_ventas.service;

import com.siman.sistema_ventas.model.DetalleVenta;
import com.siman.sistema_ventas.repository.DetalleVentaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleVentaService {

    private final DetalleVentaRepository detalleVentaRepository;

    public DetalleVentaService(DetalleVentaRepository detalleVentaRepository) {
        this.detalleVentaRepository = detalleVentaRepository;
    }

    public List<DetalleVenta> listarDetalles() {
        return detalleVentaRepository.findAll();
    }


    public List<DetalleVenta> obtenerPorVentaId(Long ventaId) {
        
        return detalleVentaRepository.findByVentaId(ventaId);
    }

    public DetalleVenta guardarDetalle(DetalleVenta detalle) {
        return detalleVentaRepository.save(detalle);
    }

    public void eliminarDetalle(Long id) {
        detalleVentaRepository.deleteById(id);
    }
}