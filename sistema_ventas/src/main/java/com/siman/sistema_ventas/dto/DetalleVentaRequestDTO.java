package com.siman.sistema_ventas.dto;

public class DetalleVentaRequestDTO {
    private Long idProducto;
    private int cantidad;
    private double total;

    // Constructor vacío
    public DetalleVentaRequestDTO() {}

    // Constructor con parámetros
    public DetalleVentaRequestDTO(Long idProducto, int cantidad, double total) {
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.total = total;
    }

    // Getters y setters
    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    // Método toString (opcional)
    @Override
    public String toString() {
        return "DetalleVentaRequestDTO{" +
                "idProducto=" + idProducto +
                ", cantidad=" + cantidad +
                ", total=" + total +
                '}';
    }
}