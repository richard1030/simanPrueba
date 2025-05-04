package com.siman.sistema_ventas.service;

import java.util.List;
import com.siman.sistema_ventas.model.Cliente;
import com.siman.sistema_ventas.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> obtenerTodos() {
        return clienteRepository.findAll();
    }

    public Page<Cliente> obtenerClientesPaginados(Pageable pageable) {
        return clienteRepository.findAll(pageable);
    }

    public Page<Cliente> buscarClientes(String filtro, Pageable pageable) {
        return clienteRepository.findByNombreContainingIgnoreCaseOrCorreoContainingIgnoreCase(filtro, filtro, pageable);
    }


    public Page<Cliente> buscarConFiltro(String nombre, String correo, Pageable pageable) {
        if (nombre != null && !nombre.isEmpty() && (correo == null || correo.isEmpty())) {
            return clienteRepository.findByNombreContainingIgnoreCase(nombre, pageable);
        }
    
        if (correo != null && !correo.isEmpty() && (nombre == null || nombre.isEmpty())) {
            return clienteRepository.findByCorreoContainingIgnoreCase(correo, pageable);
        }
    
        if (nombre != null && !nombre.isEmpty() && correo != null && !correo.isEmpty()) {
            return clienteRepository.findByNombreContainingIgnoreCaseOrCorreoContainingIgnoreCase(nombre, correo, pageable);
        }
    
        return clienteRepository.findAll(pageable);
    }
    
    
}