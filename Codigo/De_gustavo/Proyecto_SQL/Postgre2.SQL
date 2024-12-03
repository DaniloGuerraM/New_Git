-- Crear la tabla `dim_ubicacion` antes de `dim_cliente`
CREATE TABLE dim_ubicacion (
    id SERIAL PRIMARY KEY,
    pais VARCHAR(255)
);

-- Crear la tabla `dim_cliente` con columna `ubicacion_id`
CREATE TABLE dim_cliente (
    id SERIAL PRIMARY KEY,
    nombre_cliente VARCHAR(255),
    ubicacion_id INTEGER REFERENCES dim_ubicacion(id)
);

-- Crear el resto de las tablas
CREATE TABLE dim_tiempo (
    id SERIAL PRIMARY KEY,
    fecha_hora TIMESTAMP
);

CREATE TABLE dim_producto (
    id SERIAL PRIMARY KEY,
    nombre_producto VARCHAR(255),
    categoria VARCHAR(255)
);

CREATE TABLE hecho_ventas (
    id SERIAL PRIMARY KEY,
    cantidad_vendida INTEGER,
    precio DECIMAL,
    total_venta DECIMAL,
    fecha_id INTEGER REFERENCES dim_tiempo(id),
    cliente_id INTEGER REFERENCES dim_cliente(id),
    producto_id INTEGER REFERENCES dim_producto(id),
    ubicacion_id INTEGER REFERENCES dim_ubicacion(id)
);

-- Cargar los datos desde CSV en `temp_retail_data`
create TABLE temp_retail_data (
    numero_factura TEXT,
    codigo_stock TEXT,
    descripcion TEXT,
    cantidad INTEGER,
    fecha_factura TIMESTAMP,
    precio_unitario TEXT,
    id_cliente INTEGER,
    pais TEXT
);

COPY temp_retail_data
FROM 'C:/Online Retail.csv'
DELIMITER ';' CSV HEADER;

-- Insertar valores únicos en las tablas de dimensión
INSERT INTO dim_ubicacion (pais)
SELECT DISTINCT pais
FROM temp_retail_data
WHERE pais IS NOT NULL;

INSERT INTO dim_cliente (id, nombre_cliente, ubicacion_id)
SELECT DISTINCT
    id_cliente,
    'dani',
    (SELECT id FROM dim_ubicacion WHERE pais = temp_retail_data.pais)
FROM temp_retail_data
WHERE id_cliente IS NOT NULL
ON CONFLICT (id) DO NOTHING;

INSERT INTO dim_tiempo (fecha_hora)
SELECT DISTINCT fecha_factura
FROM temp_retail_data
WHERE fecha_factura IS NOT NULL;



INSERT INTO dim_producto (nombre_producto, categoria)
SELECT DISTINCT descripcion, 'Sin Categoría'
FROM temp_retail_data
WHERE descripcion IS NOT NULL;

-- Insertar datos en `hecho_ventas`
INSERT INTO hecho_ventas (cantidad_vendida, precio, total_venta, fecha_id, cliente_id, producto_id, ubicacion_id)
SELECT 
    cantidad,
    REPLACE(precio_unitario, ',', '.')::DECIMAL AS precio,
    cantidad * REPLACE(precio_unitario, ',', '.')::DECIMAL AS total_venta,
    t.id AS fecha_id,
    c.id AS cliente_id,
    p.id AS producto_id,
    u.id AS ubicacion_id
FROM temp_retail_data
JOIN dim_tiempo t ON t.fecha_hora = temp_retail_data.fecha_factura
JOIN dim_cliente c ON c.id = temp_retail_data.id_cliente
JOIN dim_producto p ON p.nombre_producto = temp_retail_data.descripcion
JOIN dim_ubicacion u ON u.pais = temp_retail_data.pais
WHERE cantidad IS NOT NULL AND precio_unitario IS NOT NULL;

-- Consultas finales

-- Total de productos vendidos por país (ubicación de venta)
SELECT 
    u.pais AS pais, 
    SUM(h.cantidad_vendida) AS total_productos_vendidos
FROM hecho_ventas h
JOIN dim_ubicacion u ON h.ubicacion_id = u.id
GROUP BY u.pais
ORDER BY total_productos_vendidos DESC;

-- Total de productos vendidos por país (ubicación del cliente)
SELECT DISTINCT ON (u.pais) 
    u.pais AS pais,
    p.nombre_producto AS producto_mas_vendido,
    SUM(h.cantidad_vendida) OVER (PARTITION BY u.pais, p.id) AS cantidad_vendida
FROM hecho_ventas h
JOIN dim_producto p ON h.producto_id = p.id
JOIN dim_ubicacion u ON h.ubicacion_id = u.id
ORDER BY u.pais, cantidad_vendida DESC;


-- Mes con mayores ventas por año y país
SELECT pais,anio,mes,total_ventas 
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
