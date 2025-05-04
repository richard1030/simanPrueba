package com.siman.sistema_ventas.dto;

public class TopProductoDTO {
    private String nombre;
    private Long totalVendidas;

    public TopProductoDTO(String nombre, Long totalVendidas) {
        this.nombre = nombre;
        this.totalVendidas = totalVendidas;
    }

    public String getNombre() {
        return nombre;
    }

    public Long getTotalVendidas() {
        return totalVendidas;
    }
}
