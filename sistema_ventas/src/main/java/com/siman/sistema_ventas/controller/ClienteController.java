package com.siman.sistema_ventas.controller;
import org.springframework.web.bind.annotation.*;
import com.siman.sistema_ventas.model.Cliente;
import com.siman.sistema_ventas.service.ClienteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/api/clientes")
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

    @GetMapping("/exportar")
    public ResponseEntity<byte[]> exportarClientes() throws IOException {
        List<Cliente> clientes = clienteService.obtenerTodos(); // Obtén todos los clientes

        // Crea un libro de trabajo de Excel
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Clientes");

        // Encabezados
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Nombre");
        headerRow.createCell(2).setCellValue("Correo");

        // Datos de los clientes
        int rowNum = 1;
        for (Cliente cliente : clientes) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(cliente.getId());
            row.createCell(1).setCellValue(cliente.getNombre());
            row.createCell(2).setCellValue(cliente.getCorreo());
        }

        // Convertir el libro a un byte array
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        workbook.write(bos);
        workbook.close();

        byte[] excelData = bos.toByteArray();

        // Configurar las cabeceras para la descarga del archivo
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=clientes.xlsx");
        
        return ResponseEntity.ok()
                .headers(headers)
                .body(excelData);
    }
}