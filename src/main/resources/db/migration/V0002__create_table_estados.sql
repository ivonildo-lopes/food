CREATE SEQUENCE estados_id_seq;

create table estados(
id bigint DEFAULT nextval('estados_id_seq') primary key,
nome varchar(60) not null
)