create table usuario(
    id serial not null,
    login varchar(40),
    senha varchar(40),
    constraint pk_usuario primary key(id)
)
