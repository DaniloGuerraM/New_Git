CREATE TABLE dim_tiempo (
    id SERIAL PRIMARY KEY,         
    fecha_hora TIMESTAMP           
);

select * from dim_tiempo;

CREATE TABLE dim_cliente (
    id SERIAL PRIMARY KEY,
    nombre_cliente VARCHAR(255),     
    pais VARCHAR(255)                
);

select * from dim_cliente;

CREATE TABLE dim_producto (
    id SERIAL PRIMARY KEY,           
    nombre_producto VARCHAR(255),    
    categoria VARCHAR(255)           
);

select * from dim_producto;

CREATE TABLE dim_ubicacion (
    id SERIAL PRIMARY KEY,           
    pais VARCHAR(255)                
);


select * from dim_ubicacion;

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

select * from hecho_ventas;


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

INSERT INTO dim_tiempo (fecha_hora)
SELECT DISTINCT fecha_factura
FROM temp_retail_data
WHERE fecha_factura IS NOT NULL;

INSERT INTO dim_cliente (id, nombre_cliente, ubicacion_id)
SELECT DISTINCT 
    id_cliente, 
    'dani',
    (SELECT id FROM dim_ubicacion WHERE pais = temp_retail_data.pais)  
FROM temp_retail_data
WHERE id_cliente IS NOT NULL
ON CONFLICT (id) DO NOTHING;

INSERT INTO dim_producto (nombre_producto, categoria)
SELECT DISTINCT descripcion, 'Sin Categoría'
FROM temp_retail_data
WHERE descripcion IS NOT NULL;

INSERT INTO dim_ubicacion (pais)
SELECT DISTINCT pais
FROM temp_retail_data
WHERE pais IS NOT NULL;

INSERT INTO hecho_ventas (cantidad_vendida, precio, total_venta, fecha_id, cliente_id, producto_id, ubicacion_id)
SELECT 
    cantidad, 
    REPLACE(precio_unitario, ',', '.')::DECIMAL AS precio,      
    cantidad * REPLACE(precio_unitario, ',', '.')::DECIMAL AS total_venta, 
    (SELECT id FROM dim_tiempo WHERE fecha_hora = temp_retail_data.fecha_factura), 
    (SELECT id FROM dim_cliente WHERE id = temp_retail_data.id_cliente),           
    (SELECT id FROM dim_producto WHERE nombre_producto = temp_retail_data.descripcion),
    (SELECT id FROM dim_ubicacion WHERE pais = temp_retail_data.pais)              
FROM temp_retail_data
WHERE cantidad IS NOT NULL AND precio_unitario IS NOT NULL;

SELECT u.pais, SUM(h.cantidad_vendida) AS total_vendidos
FROM hecho_ventas h
JOIN dim_ubicacion u ON h.ubicacion_id = u.id
GROUP BY u.pais;

SELECT u.pais, SUM(h.cantidad_vendida) AS total_vendidos
FROM hecho_ventas h
JOIN dim_cliente c ON h.cliente_id = c.id
JOIN dim_ubicacion u ON c.ubicacion_id = u.id
GROUP BY u.pais;


SELECT 
    EXTRACT(YEAR FROM t.fecha_hora) AS año,       
    EXTRACT(MONTH FROM t.fecha_hora) AS mes,         
    u.pais,                                         
    SUM(h.total_venta) AS total_ventas               
FROM hecho_ventas h
JOIN dim_tiempo t ON h.fecha_id = t.id              
JOIN dim_ubicacion u ON h.ubicacion_id = u.id        
GROUP BY año, mes, u.pais                           
ORDER BY total_ventas DESC;                          
