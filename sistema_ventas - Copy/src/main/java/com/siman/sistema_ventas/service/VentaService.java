package com.siman.sistema_ventas.service;

import com.siman.sistema_ventas.dto.ClienteIngresoDTO;
import com.siman.sistema_ventas.dto.DetalleVentaRequestDTO;
import com.siman.sistema_ventas.dto.TopProductoDTO;
import com.siman.sistema_ventas.dto.VentaRequestDTO;
import com.siman.sistema_ventas.model.Cliente;
import com.siman.sistema_ventas.model.DetalleVenta;
import com.siman.sistema_ventas.model.Producto;
import com.siman.sistema_ventas.model.Venta;
import com.siman.sistema_ventas.repository.ClienteRepository;
import com.siman.sistema_ventas.repository.DetalleVentaRepository;
import com.siman.sistema_ventas.repository.ProductoRepository;
import com.siman.sistema_ventas.repository.VentaRepository;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class VentaService {

    private final VentaRepository ventaRepository;
    private final ClienteRepository clienteRepository;
    private final ProductoRepository productoRepository;
    private final DetalleVentaRepository detalleVentaRepository;

    public VentaService(VentaRepository ventaRepository, ClienteRepository clienteRepository,
                        ProductoRepository productoRepository, DetalleVentaRepository detalleVentaRepository) {
        this.ventaRepository = ventaRepository;
        this.clienteRepository = clienteRepository;
        this.productoRepository = productoRepository;
        this.detalleVentaRepository = detalleVentaRepository;
    }

    public List<Venta> listarVentas() {
        return ventaRepository.findAll();
    }

    public Venta obtenerPorId(Integer id) {
        return ventaRepository.findById(id).orElse(null);
    }

    public Venta guardarVenta(Venta venta) {
        return ventaRepository.save(venta);
    }

    public void eliminarVenta(Integer id) {
        ventaRepository.deleteById(id);
    }


    public Venta guardarVentaDesdeDTO(VentaRequestDTO dto) {
        Cliente cliente = clienteRepository.findById(dto.getIdCliente())
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));

        Venta venta = new Venta();
        venta.setCliente(cliente);
        venta.setFecha(LocalDateTime.now()); 

        // Guardamos la venta primero para obtener el ID
        Venta ventaGuardada = ventaRepository.save(venta);

        List<DetalleVenta> detalles = new ArrayList<>();

        for (DetalleVentaRequestDTO detalleDTO : dto.getDetallesVenta()) {
            Producto producto = productoRepository.findById(detalleDTO.getIdProducto())
                    .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));

            DetalleVenta detalle = new DetalleVenta();
            detalle.setVenta(ventaGuardada);
            detalle.setProducto(producto);
            detalle.setCantidad(detalleDTO.getCantidad());
            detalle.setTotal(detalleDTO.getTotal());

            detalles.add(detalle);
        }

        detalleVentaRepository.saveAll(detalles);

        return ventaGuardada;
    }

    public List<TopProductoDTO> obtenerTopProductosMasVendidos() {
    Pageable top3 = PageRequest.of(0, 3);
    List<Object[]> resultados = detalleVentaRepository.encontrarTopProductos(top3);

    return resultados.stream()
        .map(obj -> new TopProductoDTO((String) obj[0], (Long) obj[1]))
        .toList();
    }

    public ClienteIngresoDTO obtenerClienteMayorIngreso() {
        List<ClienteIngresoDTO> resultados = ventaRepository.obtenerClientesPorIngresoDesc();
        return resultados.isEmpty() ? null : resultados.get(0);
    }

    public Double obtenerIngresoUltimoMes() {
    return ventaRepository.obtenerIngresoUltimoMes();
    }
}