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



SELECT 
    u.pais AS pais, -- Selecciona la columna "pais" de la tabla dim_ubicacion.
    SUM(h.cantidad_vendida) AS total_productos_vendidos -- Suma la cantidad total de productos vendidos en cada país.
FROM hecho_ventas h
JOIN dim_ubicacion u ON h.ubicacion_id = u.id -- Une la tabla hecho_ventas con dim_ubicacion usando la clave foránea "ubicacion_id".
GROUP BY u.pais -- Agrupa los resultados por país.
ORDER BY total_productos_vendidos DESC; -- Ordena los resultados en orden descendente por la cantidad total de productos vendidos.



SELECT DISTINCT ON (u.pais) -- Devuelve solo una fila por país (priorizando el producto con mayor cantidad vendida).
    u.pais AS pais, -- Selecciona el país de la tabla dim_ubicacion.
    p.nombre_producto AS producto_mas_vendido, -- Selecciona el nombre del producto más vendido.
    SUM(h.cantidad_vendida) OVER (PARTITION BY u.pais, p.id) AS cantidad_vendida -- Calcula la cantidad total de ese producto vendido por país.
FROM hecho_ventas h
JOIN dim_producto p ON h.producto_id = p.id -- Une la tabla hecho_ventas con dim_producto para obtener los nombres de los productos.
JOIN dim_ubicacion u ON h.ubicacion_id = u.id -- Une la tabla hecho_ventas con dim_ubicacion para obtener los países.
ORDER BY u.pais, cantidad_vendida DESC; -- Ordena los productos dentro de cada país por la cantidad vendida en orden descendente.



SELECT 
    pais,
    anio,
    mes,
    total_ventas
FROM (
    SELECT 
        u.pais AS pais,
        DATE_PART('year', t.fecha_hora) AS anio,
        DATE_PART('month', t.fecha_hora) AS mes,
        SUM(h.total_venta) AS total_ventas,
        ROW_NUMBER() OVER (PARTITION BY u.pais, DATE_PART('year', t.fecha_hora) ORDER BY SUM(h.total_venta) DESC) AS rn
    FROM hecho_ventas h
    JOIN dim_tiempo t ON h.fecha_id = t.id
    JOIN dim_ubicacion u ON h.ubicacion_id = u.id
    GROUP BY u.pais, DATE_PART('year', t.fecha_hora), DATE_PART('month', t.fecha_hora)
) ventas_por_mes
WHERE rn = 1
ORDER BY pais, anio;
