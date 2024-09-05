-- Table: public.producto

-- DROP TABLE IF EXISTS public.producto;

CREATE TABLE IF NOT EXISTS public.producto
(
    producto_id integer NOT NULL,
    codigo_ean text COLLATE pg_catalog."default" NOT NULL,
    descripcion text COLLATE pg_catalog."default",
    tipo_producto text COLLATE pg_catalog."default",
    precio_unitario double precision,
    porcentaje_iva double precision,
    CONSTRAINT producto_pkey PRIMARY KEY (codigo_ean)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.producto
    OWNER to postgres;