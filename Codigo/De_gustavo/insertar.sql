
crea una tabla para el dolar mep 
create table dolar_mep(
 especie varchar(20) null, 
 ultimo float null,
 variacion float null, 
 anterior float null, 
 apertura float null,  
 minimo float null,  
 maximo float null, 
 varMTD varchar(50) null, 
 varYTD varchar(50) null, 
 var6M varchar(50) null, 
 var12M varchar(50) null, 
 fecha varchar(20) null, 
 hora varchar(20) null, 
 cierre float null, 
 timestamp_1970 integer null, 
 volumen integer null
);

copio los datos del archimo DOLARMEP.csv en la tabla dolar_mep
COPY public.dolar_mep(especie, ultimo, variacion, anterior, apertura, minimo, maximo, varMTD, varYTD, var6M, var12M, fecha, hora, cierre, timestamp_1970, volumen) FROM 'R:\usuarios\alumno\escritorio\Parcial\DOLARMEP.csv' DELIMITER ';' CSV HEADER;

para crear las otra tabla (dolar_blue,dolar_oficial y dolar_ccl) las hice igual a la del dolar mep,
 asi solo le cambie el nombre de la tabla
y para insertar los archivos solo cambie la tabla donde lo copio y la ubicacion del archivo

cree la tabla donde junto en tipo de dolar(especie), el ultimo pecio del dolar(ultimo), el minimo que alcanzo(minimo), el maximo que alcanzo
y la fecha (fecha)
CREATE TABLE dolar (
    tipo_dolar VARCHAR(50) NOT NULL,
    ultimo DECIMAL(10, 2) NOT NULL,
    minimo DECIMAL(10, 2) NOT NULL,
    maximo DECIMAL(10, 2) NOT NULL,
    fecha DATE NOT NULL
);
inserta en la tabla dolar; especie ultimo minimo maximo y fecha de la tabla dolar_ccl
insert into public.dolar 
select especie,ultimo,minimo,maximo,fecha from public.dolar_ccl;

inserta en la tabla dolar; especie ultimo minimo maximo y fecha de la tabla dolar_blue
insert into public.dolar 
select especie,ultimo,minimo,maximo,fecha from public.dolar_blue;

inserta en la tabla dolar; especie ultimo minimo maximo y fecha de la tabla dolar_oficial
insert into public.dolar 
select especie,ultimo,minimo,maximo,fecha from public.dolar_oficial;

inserta en la tabla dolar; especie ultimo minimo maximo y fecha de la tabla dolar_mep
insert into public.dolar 
select especie,ultimo,minimo,maximo,fecha from public.dolar_mep;


te muestra es precio del dolar blue y oficial de cada dia y su diferencia
SELECT 
    d1.fecha,
    d1.ultimo AS dolar_oficial,
    d2.ultimo AS dolar_blue,
    (d2.ultimo - d1.ultimo) AS diferencia
FROM 
    dolar d1
JOIN 
    dolar d2 ON d1.fecha = d2.fecha
WHERE 
    d1.tipo_dolar = 'DOLAR OFICIAL'
    AND d2.tipo_dolar = 'DOLAR BLUE'
    AND DATE_PART('year', d1.fecha) = 2024
    AND DATE_PART('month', d1.fecha) = 3
ORDER BY 
    d1.fecha;
	
te muestra el precio maximo de cada tipo de dolar desde enero del 2023 hasta mayo del 2024

	SELECT 
    fecha,
    MAX(CASE WHEN tipo_dolar = 'DOLAR BLUE' THEN ultimo END) AS max_blue,
    MAX(CASE WHEN tipo_dolar = 'DOLAR MEP AL' THEN ultimo END) AS max_mep,
    MAX(CASE WHEN tipo_dolar = 'DOLAR OFICIAL' THEN ultimo END) AS max_oficial,
    MAX(CASE WHEN tipo_dolar = 'DOLAR CCL' THEN ultimo END) AS max_ccl
FROM 
    dolar
WHERE 
    DATE_PART('year', fecha) BETWEEN 2023 AND 2024
GROUP BY 
    fecha
ORDER BY 
    fecha;
	------------------------------------------------------------------------------------------------------------
	precio maximo por mes desde 2023
	SELECT 
    tipo_dolar,
    DATE_PART('year', fecha) AS year,
    DATE_PART('month', fecha) AS month,
    MAX(ultimo) AS max_precio
FROM 
    dolar
WHERE 
    DATE_PART('year', fecha) BETWEEN 2023 AND 2024
GROUP BY 
    tipo_dolar,
    DATE_PART('year', fecha),
    DATE_PART('month', fecha)
ORDER BY 
    tipo_dolar,
    year,
    month;
	--------------------------------
	precio maximo por mes desde 2024
	SELECT 
    tipo_dolar,
    DATE_PART('year', fecha) AS year,
    DATE_PART('month', fecha) AS month,
    MAX(ultimo) AS max_precio
FROM 
    dolar
WHERE 
    DATE_PART('year', fecha)= 2024
GROUP BY 
    tipo_dolar,
    DATE_PART('year', fecha),
    DATE_PART('month', fecha)
ORDER BY 
    tipo_dolar,
    year,
    month;
	
	


