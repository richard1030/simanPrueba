INSERT INTO producto (nombre, precio, cantidad_disponible) VALUES
('Café', 12.50, 100),
('Té', 8.00, 200),
('Pan', 2.30, 300),
('Galletas', 3.90, 150),
('Jugo', 4.50, 180);

INSERT INTO cliente (nombre, correo) VALUES
('Ana Morales', 'ana@example.com'),
('Luis García', 'luis@example.com'),
('Marta Díaz', 'marta@example.com'),
('José Pérez', 'jose@example.com'),
('Lucía Romero', 'lucia@example.com');

INSERT INTO venta (fecha, id_cliente) VALUES
('2025-04-01 10:00:00', 1),
('2025-04-01 12:00:00', 2),
('2025-04-02 14:00:00', 3),
('2025-04-03 16:00:00', 4),
('2025-04-04 18:00:00', 5);

INSERT INTO detalle_venta (id_venta, id_producto, cantidad, total) VALUES
(1, 1, 2, 25.00),
(1, 2, 1, 8.00),
(2, 3, 4, 9.20),
(3, 4, 3, 11.70),
(4, 5, 4, 18.00),
(5, 2, 2, 2.00);
