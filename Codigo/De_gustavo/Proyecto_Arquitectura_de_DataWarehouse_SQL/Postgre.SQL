-- Crear la tabla de dimensión `dim_tiempo` para almacenar información de tiempo.
CREATE TABLE dim_tiempo (
    id SERIAL PRIMARY KEY,         -- Clave primaria con valor autoincremental para identificar cada registro.
    fecha_hora TIMESTAMP           -- Columna para almacenar la fecha y hora del evento de venta.
);

-- Consultar todos los registros de `dim_tiempo`.
select * from dim_tiempo;

-- Crear la tabla de dimensión `dim_cliente` para almacenar información sobre los clientes.
CREATE TABLE dim_cliente (
    id SERIAL PRIMARY KEY,          -- Clave primaria autoincremental para identificar cada cliente.
    nombre_cliente VARCHAR(255),     -- Nombre del cliente (en este caso está vacío y se puede omitir).
    pais VARCHAR(255)                -- País del cliente.
);

-- Consultar todos los registros de `dim_cliente`.
select * from dim_cliente;

-- Crear la tabla de dimensión `dim_producto` para almacenar información de productos.
CREATE TABLE dim_producto (
    id SERIAL PRIMARY KEY,           -- Clave primaria autoincremental para identificar cada producto.
    nombre_producto VARCHAR(255),    -- Nombre del producto.
    categoria VARCHAR(255)           -- Categoría del producto, asignada aquí como "Sin Categoría".
);

-- Consultar todos los registros de `dim_producto`.
select * from dim_producto;

-- Crear la tabla de dimensión `dim_ubicacion` para almacenar información de ubicaciones (países).
CREATE TABLE dim_ubicacion (
    id SERIAL PRIMARY KEY,           -- Clave primaria autoincremental para identificar cada ubicación.
    pais VARCHAR(255)                -- País asociado a la ubicación de la venta.
);

-- Consultar todos los registros de `dim_ubicacion`.
select * from dim_ubicacion;

-- Crear la tabla de hechos `hecho_ventas` para almacenar datos de ventas.
CREATE TABLE hecho_ventas (
    id SERIAL PRIMARY KEY,           -- Clave primaria autoincremental para identificar cada transacción de venta.
    cantidad_vendida INTEGER,        -- Cantidad de productos vendidos en la transacción.
    precio DECIMAL,                  -- Precio unitario de cada producto.
    total_venta DECIMAL,             -- Total de la venta (cantidad * precio).
    fecha_id INTEGER REFERENCES dim_tiempo(id),    -- ID de tiempo que referencia a la tabla `dim_tiempo`.
    cliente_id INTEGER REFERENCES dim_cliente(id), -- ID del cliente que referencia a la tabla `dim_cliente`.
    producto_id INTEGER REFERENCES dim_producto(id),-- ID del producto que referencia a la tabla `dim_producto`.
    ubicacion_id INTEGER REFERENCES dim_ubicacion(id) -- ID de ubicación que referencia a la tabla `dim_ubicacion`.
);

-- Consultar todos los registros de `hecho_ventas`.
select * from hecho_ventas;

-- Eliminar la tabla temporal `temp_retail_data` si existe.
drop TABLE IF EXISTS temp_retail_data;

-- Crear la tabla temporal `temp_retail_data` para cargar los datos de la fuente CSV.
create TABLE temp_retail_data (
    numero_factura TEXT,         -- Número de factura como texto.
    codigo_stock TEXT,           -- Código del producto.
    descripcion TEXT,            -- Descripción del producto.
    cantidad INTEGER,            -- Cantidad vendida.
    fecha_factura TIMESTAMP,     -- Fecha y hora de la transacción.
    precio_unitario TEXT,        -- Precio unitario en texto (se convertirá más adelante).
    id_cliente INTEGER,          -- ID del cliente.
    pais TEXT                    -- País de la transacción.
);

-- Cargar datos desde un archivo CSV en la tabla temporal `temp_retail_data`.
COPY temp_retail_data
FROM 'C:/Online Retail.csv'
DELIMITER ';' CSV HEADER;

-- Insertar valores únicos de fecha en la tabla `dim_tiempo`.
INSERT INTO dim_tiempo (fecha_hora)
SELECT DISTINCT fecha_factura
FROM temp_retail_data
WHERE fecha_factura IS NOT NULL;

-- Insertar valores únicos de cliente en la tabla `dim_cliente` con clave foránea `ubicacion_id`.
INSERT INTO dim_cliente (id, nombre_cliente, ubicacion_id)
SELECT DISTINCT 
    id_cliente, 
    'dani',
    (SELECT id FROM dim_ubicacion WHERE pais = temp_retail_data.pais)  -- Obtener el ID del país desde `dim_ubicacion`.
FROM temp_retail_data
WHERE id_cliente IS NOT NULL
ON CONFLICT (id) DO NOTHING;  -- Evitar duplicados si el cliente ya existe.
-- Insertar productos únicos en la tabla `dim_producto`.
INSERT INTO dim_producto (nombre_producto, categoria)
SELECT DISTINCT descripcion, 'Sin Categoría'
FROM temp_retail_data
WHERE descripcion IS NOT NULL;

-- Insertar ubicaciones únicas en la tabla `dim_ubicacion`.
INSERT INTO dim_ubicacion (pais)
SELECT DISTINCT pais
FROM temp_retail_data
WHERE pais IS NOT NULL;

-- Insertar datos en la tabla de hechos `hecho_ventas`, enlazando dimensiones.
INSERT INTO hecho_ventas (cantidad_vendida, precio, total_venta, fecha_id, cliente_id, producto_id, ubicacion_id)
SELECT 
    cantidad, 
    REPLACE(precio_unitario, ',', '.')::DECIMAL AS precio,      -- Convertir `precio_unitario` a DECIMAL.
    cantidad * REPLACE(precio_unitario, ',', '.')::DECIMAL AS total_venta, -- Calcular el total de la venta.
    (SELECT id FROM dim_tiempo WHERE fecha_hora = temp_retail_data.fecha_factura), -- Obtener `id` de `dim_tiempo`.
    (SELECT id FROM dim_cliente WHERE id = temp_retail_data.id_cliente),           -- Obtener `id` de `dim_cliente`.
    (SELECT id FROM dim_producto WHERE nombre_producto = temp_retail_data.descripcion), -- Obtener `id` de `dim_producto`.
    (SELECT id FROM dim_ubicacion WHERE pais = temp_retail_data.pais)              -- Obtener `id` de `dim_ubicacion`.
FROM temp_retail_data
WHERE cantidad IS NOT NULL AND precio_unitario IS NOT NULL;

-- Consulta para obtener la cantidad total de productos vendidos por país.
SELECT u.pais, SUM(h.cantidad_vendida) AS total_vendidos
FROM hecho_ventas h
JOIN dim_ubicacion u ON h.ubicacion_id = u.id
GROUP BY u.pais;

-- Consulta para obtener la cantidad total de productos vendidos por país utilizando `dim_cliente.ubicacion_id`.
SELECT u.pais, SUM(h.cantidad_vendida) AS total_vendidos
FROM hecho_ventas h
JOIN dim_cliente c ON h.cliente_id = c.id
JOIN dim_ubicacion u ON c.ubicacion_id = u.id
GROUP BY u.pais;

-- Consulta para obtener el mes con mayores ventas por año y país.
SELECT 
    EXTRACT(YEAR FROM t.fecha_hora) AS año,          -- Extraer el año de la columna `fecha_hora`.
    EXTRACT(MONTH FROM t.fecha_hora) AS mes,         -- Extraer el mes de la columna `fecha_hora`.
    u.pais,                                          -- País de la transacción.
    SUM(h.total_venta) AS total_ventas               -- Sumar el total de ventas.
FROM hecho_ventas h
JOIN dim_tiempo t ON h.fecha_id = t.id               -- Unir `hecho_ventas` con `dim_tiempo`.
JOIN dim_ubicacion u ON h.ubicacion_id = u.id        -- Unir `hecho_ventas` con `dim_ubicacion`.
GROUP BY año, mes, u.pais                            -- Agrupar por año, mes y país.
ORDER BY total_ventas DESC;                          -- Ordenar en orden descendente de ventas.
