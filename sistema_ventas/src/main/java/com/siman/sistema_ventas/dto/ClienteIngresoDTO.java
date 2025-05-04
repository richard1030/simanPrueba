package com.siman.sistema_ventas.dto;

public class ClienteIngresoDTO {
    private String nombre;
    private Double totalComprado;

    public ClienteIngresoDTO(String nombre, Double totalComprado) {
        this.nombre = nombre;
        this.totalComprado = totalComprado;
    }

    public String getNombre() {
        return nombre;
    }

    public Double getTotalComprado() {
        return totalComprado;
    }
}