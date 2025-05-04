package com.siman.sistema_ventas.repository;
import com.siman.sistema_ventas.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Page<Cliente> findAll(Pageable pageable);
    Page<Cliente> findByNombreContainingIgnoreCase(String nombre, Pageable pageable);
    Page<Cliente> findByCorreoContainingIgnoreCase(String correo, Pageable pageable);
    Page<Cliente> findByNombreContainingIgnoreCaseOrCorreoContainingIgnoreCase(String nombre, String correo, Pageable pageable);

}

