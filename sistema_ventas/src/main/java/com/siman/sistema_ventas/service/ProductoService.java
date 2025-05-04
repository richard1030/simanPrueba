package com.siman.sistema_ventas.service;

import java.util.List;
import java.util.Optional;
import com.siman.sistema_ventas.model.Producto;
import com.siman.sistema_ventas.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    @Autowired
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    // Obtener todos los productos
    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }

    // Obtener un producto por ID
    public Optional<Producto> obtenerPorId(Long id) {
        return productoRepository.findById(id);
    }

    // Crear o actualizar un producto
    public Producto guardar(Producto producto) {
        return productoRepository.save(producto);
    }

    // Eliminar un producto
    public void eliminar(Long id) {
        productoRepository.deleteById(id);
    }
}