CREATE SEQUENCE public.associado_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

CREATE SEQUENCE public.pauta_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

CREATE SEQUENCE public.sessao_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

CREATE SEQUENCE public.voto_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

CREATE TABLE public.associado
(
    id integer NOT NULL DEFAULT nextval('associado_id_seq'::regclass),
    cpf character varying(255) COLLATE pg_catalog."default",
    nome character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT associado_pkey PRIMARY KEY (id)
);

CREATE TABLE public.pauta
(
    id integer NOT NULL DEFAULT nextval('pauta_id_seq'::regclass),
    nome character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT pauta_pkey PRIMARY KEY (id)
);

CREATE TABLE public.sessao
(
    id integer NOT NULL DEFAULT nextval('sessao_id_seq'::regclass),
    descricao character varying(255) COLLATE pg_catalog."default",
    fim timestamp without time zone,
    inico timestamp without time zone,
    pauta_id integer,
    CONSTRAINT sessao_pkey PRIMARY KEY (id),
    CONSTRAINT fkbc3ehywka7s7yk4j1bb51hgnf FOREIGN KEY (pauta_id)
        REFERENCES public.pauta (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE public.voto
(
    id integer NOT NULL DEFAULT nextval('voto_id_seq'::regclass),
    voto boolean,
    associado_id integer,
    sessao_id integer,
    CONSTRAINT voto_pkey PRIMARY KEY (id),
    CONSTRAINT fk2g42xm45pt8fkud30a03j1kjs FOREIGN KEY (associado_id)
        REFERENCES public.associado (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkffeg0bfva5yuqkw88dfii1nn5 FOREIGN KEY (sessao_id)
        REFERENCES public.sessao (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
