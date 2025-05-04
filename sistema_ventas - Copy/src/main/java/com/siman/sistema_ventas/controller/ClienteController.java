package com.siman.sistema_ventas.controller;
import org.springframework.web.bind.annotation.*;
import com.siman.sistema_ventas.model.Cliente;
import com.siman.sistema_ventas.service.ClienteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;


@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*") // Opcional si tienes CORS global
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

   /* @GetMapping
    public List<Cliente> listarClientes() {
        return clienteService.obtenerTodos();
    } 
    @GetMapping("/paginado")
    public ResponseEntity<Page<Cliente>> obtenerClientesPaginados(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String sortField,
            @RequestParam(defaultValue = "asc") String sortOrder) {

        Pageable pageable;
        if (sortField != null && !sortField.isEmpty()) {
            Sort sort = sortOrder.equalsIgnoreCase("desc") ?
                    Sort.by(sortField).descending() :
                    Sort.by(sortField).ascending();
            pageable = PageRequest.of(page, size, sort);
        } else {
            pageable = PageRequest.of(page, size);
        }

        Page<Cliente> clientes = clienteService.obtenerClientesPaginados(pageable);
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/buscar")
    public ResponseEntity<Page<Cliente>> buscarClientes(
            @RequestParam String filtro,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String sortField,
            @RequestParam(defaultValue = "asc") String sortOrder) {

        Pageable pageable;
        if (sortField != null && !sortField.isEmpty()) {
            Sort sort = sortOrder.equalsIgnoreCase("desc") ?
                    Sort.by(sortField).descending() :
                    Sort.by(sortField).ascending();
            pageable = PageRequest.of(page, size, sort);
        } else {
            pageable = PageRequest.of(page, size);
        }

        Page<Cliente> clientes = clienteService.buscarClientes(filtro, pageable);
        return ResponseEntity.ok(clientes);
    } */ 


    @GetMapping
    public ResponseEntity<Page<Cliente>> listarClientes(
        @RequestParam(required = false) String nombre,
        @RequestParam(required = false) String correo,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "id") String sortField,
        @RequestParam(defaultValue = "asc") String sortOrder,
        @RequestParam(required = false) String filtro) {
    
        Sort.Direction direction = sortOrder.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(new Sort.Order(direction, sortField)));
    
        // Si hay filtro global, asignar filtro a nombre o correo
        if (filtro != null && !filtro.isEmpty()) {
            return ResponseEntity.ok(clienteService.buscarConFiltro(filtro, filtro, pageable));
        }
    
        // Si no hay filtro global, continuar con los filtros por columna
        if (nombre != null || correo != null) {
            return ResponseEntity.ok(clienteService.buscarConFiltro(nombre, correo, pageable));
        }
    
        // Si no hay filtros, obtener todos los clientes
        Page<Cliente> clientes = clienteService.obtenerClientesPaginados(pageable);
        return ResponseEntity.ok(clientes);
    }
}