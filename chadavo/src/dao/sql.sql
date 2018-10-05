create table usuario(
    id serial not null,
    login varchar(40),
    senha varchar(40),
    nome varchar(40),
    constraint pk_usuario primary key(id),
    constraint uc_login unique(login)
)

-- Table: public.chas

-- DROP TABLE public.chas;

CREATE TABLE public.chas
(
  cod_cha serial,
  nome_cha character varying(60),
  descricao_cha character varying(60),
  beneficios character varying(60),
  ingredientes character varying(60),
  contra_indicacao character varying(60),
  modo_preparo character varying(60),
  imgcha bytea,
  CONSTRAINT pk_cha PRIMARY KEY (cod_cha)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.chas
  OWNER TO postgres;

-- tabelas refentes ao cadastro de chas

create database ChaDaVo

create table Chas (Cod_cha serial primary key, nome_cha varchar(60) unique not null, descricao_cha text not null, modo_preparo text not null);
create table Beneficios (Cod_beneficio serial primary key, nome_beneficio varchar(60) not null);
create table Contra_Indicacao (Cod_Contra serial primary key, nome_contra varchar(60) not null, descricao_contra text not null);
create table Ingredientes (Cod_ingrediente serial primary key, nome_ingrediente varchar(60) not null);

create table benecha (chave_beneficio int not null, chave_benecha int not null);
create table ingrecha (chave_ingre int not null, chave_ingrecha int not null);
create table contracha (chave_contra int not null, chave_contracha int not null);

alter table benecha add constraint pk_benecha primary key (chave_beneficio, chave_benecha);
alter table ingrecha add constraint pk_ingrecha primary key (chave_ingre, chave_ingrecha);
alter table contracha add constraint pk_contracha primary key (chave_contra, chave_contracha);

alter table benecha add constraint fk_ch_beneficio foreign key (chave_beneficio) references beneficios(cod_beneficio);
alter table benecha add constraint fk_ch_benecha foreign key (chave_benecha) references chas(cod_cha);
alter table ingrecha add constraint fk_ch_ingre foreign key (chave_ingre) references ingredientes(cod_ingrediente);
alter table ingrecha add constraint fk_ch_ingrecha foreign key (chave_ingrecha) references chas(cod_cha);
alter table contracha add constraint fk_ch_contra foreign key (chave_contra) references Contra_indicacao(cod_contra);
alter table contracha add constraint fk_ch_contracha foreign key (chave_contracha) references chas(cod_cha) 