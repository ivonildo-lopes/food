CREATE SEQUENCE cidades_id_seq;

CREATE TABLE cidades (
id bigint DEFAULT nextval('cidades_id_seq') primary key,
nome VARCHAR(60) NOT NULL,
nome_estado VARCHAR(60) NOT null
)