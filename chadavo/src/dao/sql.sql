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
  indicacao character varying(60),
  contra_indicacao character varying(60),
  dicas character varying(60),
  prevencao character varying(60),
  imgcha bytea
  constraint pk_cha primary key(codigo)
)