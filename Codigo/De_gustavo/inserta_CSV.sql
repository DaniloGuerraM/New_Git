
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
