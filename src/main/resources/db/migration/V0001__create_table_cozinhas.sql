CREATE SEQUENCE cozinhas_id_seq START 1 MINVALUE 1 INCREMENT 1;

create table cozinhas(
id bigint DEFAULT nextval('cozinhas_id_seq') primary key,
nome varchar(60) not null
)