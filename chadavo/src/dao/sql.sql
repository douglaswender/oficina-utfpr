create table usuario(
    id serial not null,
    login varchar(40),
    senha varchar(40),
    nome varchar(40),
    email varchar(254),
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

create database Cadastro_de_Chás

<<<<<<< HEAD
create table Chas (Cod_cha serial primary key, nome_cha varchar(60) unique not null, descricao_cha text not null, modo_preparo text not null);
=======
create table Chas (Cod_cha serial primary key, nome_cha varchar(60) unique not null, descricao_cha text not null, modo_preparo text not null, imgcha bytea);
>>>>>>> 7e0decc9d4bb164de609d138c1b4921daef0ee3d
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

-- Insert dos ingredientes
insert into ingredientes(nome_ingrediente) values('água');
insert into ingredientes(nome_ingrediente) values('camomila');
insert into ingredientes(nome_ingrediente) values('canela');
insert into ingredientes(nome_ingrediente) values('manjericao');
insert into ingredientes(nome_ingrediente) values('cravo');
insert into ingredientes(nome_ingrediente) values('cebola');
insert into ingredientes(nome_ingrediente) values('alho');
insert into ingredientes(nome_ingrediente) values('chá verde');
insert into ingredientes(nome_ingrediente) values('pitanga');
insert into ingredientes(nome_ingrediente) values('abacaxi');
insert into ingredientes(nome_ingrediente) values('gengibre');
insert into ingredientes(nome_ingrediente) values('hibisco');
insert into ingredientes(nome_ingrediente) values('maça');
insert into ingredientes(nome_ingrediente) values('maracuja');
insert into ingredientes(nome_ingrediente) values('limão');
insert into ingredientes(nome_ingrediente) values('hortela');
insert into ingredientes(nome_ingrediente) values('leite');
insert into ingredientes(nome_ingrediente) values('amendoin');
insert into ingredientes(nome_ingrediente) values('menta');
insert into ingredientes(nome_ingrediente) values('folha de bergamoteira');
insert into ingredientes(nome_ingrediente) values('folha de laranjeira');
insert into ingredientes(nome_ingrediente) values('espinheira santa');

--Insert das contra indicações
insert into contra_indicacao(nome_contra, descricao_contra) values('náuseas', 'O consumo elevado desse cha pode provocar esse sintoma.');
insert into contra_indicacao(nome_contra, descricao_contra) values('insônia', 'O consumo elevado desse cha pode provocar esse sintoma.');
insert into contra_indicacao(nome_contra, descricao_contra) values('problemas hepáticos', 'Quem tem problemas hepáticos deve evitar consumir esse chá.');
insert into contra_indicacao(nome_contra, descricao_contra) values('hipertensos', 'Quem tem problemas com hipertensão deve evitar consumir esse chá.');
insert into contra_indicacao(nome_contra, descricao_contra) values('diabeticos', 'Pessoas com diabetes devem evitar consumir esse chá.');
insert into contra_indicacao(nome_contra, descricao_contra) values('gestantes', 'Gestantes devem evitar consumir esse chá.');
insert into contra_indicacao(nome_contra, descricao_contra) values('insuficiência renal', 'Pessoas com esse problema devem evitar consumir esse chá.');
insert into contra_indicacao(nome_contra, descricao_contra) values('insuficiência cardíaca', 'Pessoas com esse problema devem evitar consumir esse chá.');
insert into contra_indicacao(nome_contra, descricao_contra) values('lactante', 'Mulheres que estão em período de amamentação devem evitar consumir esse chá');
insert into contra_indicacao(nome_contra, descricao_contra) values('fígado', 'O consumo elevado desse cha pode provocar problemas no fígado.');