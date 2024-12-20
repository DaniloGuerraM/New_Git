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


-- se crea la tabla registro evento para guardar lo gmail y el nombre que se le pase
create table public.registro_evento(
   	identificador serial primary key,
	idandroid varchar(100)UNIQUE null,
    gmail varchar(100) UNIQUE not null,
    nombre varchar(30)
);

create table public.asistencia_evento(
    registro_id serial primary key,
    gmail varchar(100) not null references public.registro_evento(gmail),
    fecha bigint
);
