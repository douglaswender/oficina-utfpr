create table usuario(
    id serial not null,
    login varchar(40),
    senha varchar(40),
    nome varchar(40),
    constraint pk_usuario primary key(id),
    constraint uc_login unique(login)
)

create table cha(
  codigo serial,
  nome character varying(60),
  breve_descricao character varying(60),
  detalhes character varying(60),
  especificacao_tecnica character varying(60),
  contra_indicacao character varying(60),
  dicas character varying(60),
  prevencao character varying(60),
  imgcha bytea
  constraint pk_cha primary key(codigo)
)

-- tabelas refentes ao cadastro de chas

create database ChaDaVo

create table Chas (Cod_cha serial primary key, nome_cha varchar(60) not null, descricao_cha text not null);
create table Beneficios (Cod_beneficio serial primary key, nome_beneficio varchar(60) not null);
create table Contra_Indicacao (Cod_Contra serial primary key, nome_contra varchar(60) not null, descricao_contra text not null);
create table Modo_preparo (Cod_preparo serial primary key, texto_preparo text not null);
create table Ingredientes (Cod_ingrediente serial primary key, nome_ingrediente varchar(60) not null);

create table benecha (chave_beneficio int not null, chave_benecha int not null);
create table preparocha (chave_preparo int not null , chave_preparocha int not null);
create table ingrecha (chave_ingre int not null, chave_ingrecha int not null);
create table contracha (chave_contra int not null, chave_contracha int not null);

alter table benecha add constraint pk_benecha primary key (chave_beneficio, chave_benecha);
alter table preparocha add constraint pk_preparocha primary key (chave_preparo, chave_preparocha);
alter table ingrecha add constraint pk_ingrecha primary key (chave_ingre, chave_ingrecha);
alter table contracha add constraint pk_contracha primary key (chave_contra, chave_contracha);

alter table benecha add constraint fk_ch_beneficio foreign key (chave_beneficio) references beneficios(cod_beneficio);
alter table benecha add constraint fk_ch_benecha foreign key (chave_benecha) references chas(cod_cha);
alter table preparocha add constraint fk_ch_preparo foreign key (chave_preparo) references modo_preparo(cod_preparo);
alter table preparocha add constraint fk_ch_preparocha foreign key (chave_preparocha) references chas(cod_cha);
alter table ingrecha add constraint fk_ch_ingre foreign key (chave_ingre) references ingredientes(cod_ingrediente);
alter table ingrecha add constraint fk_ch_ingrecha foreign key (chave_ingrecha) references chas(cod_cha);
alter table contracha add constraint fk_ch_contra foreign key (chave_contra) references Contra_indicacao(cod_contra);
alter table contracha add constraint fk_ch_contracha foreign key (chave_contracha) references chas(cod_cha)  