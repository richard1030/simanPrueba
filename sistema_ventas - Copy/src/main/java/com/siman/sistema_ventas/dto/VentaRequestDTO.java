package com.siman.sistema_ventas.dto;

import java.util.List;

public class VentaRequestDTO {
    private Long idCliente;
    private List<DetalleVentaRequestDTO> detallesVenta;

    // Constructor vacío (requerido por algunos frameworks)
    public VentaRequestDTO() {}

    // Constructor con parámetros
    public VentaRequestDTO(Long idCliente, List<DetalleVentaRequestDTO> detallesVenta) {
        this.idCliente = idCliente;
        this.detallesVenta = detallesVenta;
    }

    // Getters y setters
    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public List<DetalleVentaRequestDTO> getDetallesVenta() {
        return detallesVenta;
    }

    public void setDetallesVenta(List<DetalleVentaRequestDTO> detallesVenta) {
        this.detallesVenta = detallesVenta;
    }

    // Método para representar el DTO como una cadena
    @Override
    public String toString() {
        return "VentaRequestDTO{" +
                "idCliente=" + idCliente +
                ", detallesVenta=" + detallesVenta +
                '}';
    }
}