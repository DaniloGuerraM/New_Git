-- Database: QR_Asistencias

-- DROP DATABASE IF EXISTS "QR_Asistencias";

CREATE DATABASE "QR_Asistencias"
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Spanish_Argentina.1252'
    LC_CTYPE = 'Spanish_Argentina.1252'
    LOCALE_PROVIDER = 'libc'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

-- Crea la tabla en la base de datos 
create table alumno_api(
	id serial primary key,
	nombre varchar(30),
	apellido varchar(30),
	edad integer 
)

--selecciona todos los datos de la tabla
select * from alumno_api;

-- inserta valores en la tabla
Insert into alumno_api ( nombre, apellido, edad) values ('Danilo', 'Guerra', 19);