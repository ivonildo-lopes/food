CREATE SEQUENCE estados_id_seq START 1 MINVALUE 1 INCREMENT 1;

create table estados(
id bigint DEFAULT nextval('estados_id_seq') primary key,
nome varchar(60) not null
)