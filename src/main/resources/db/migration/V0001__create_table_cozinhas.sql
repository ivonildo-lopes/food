CREATE SEQUENCE cozinhas_id_seq;

create table cozinhas(
id bigint DEFAULT nextval('cozinhas_id_seq') primary key,
nome varchar(60) not null
)