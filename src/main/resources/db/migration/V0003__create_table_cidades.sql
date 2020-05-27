CREATE SEQUENCE cidades_id_seq START 1 MINVALUE 1 INCREMENT 1;

CREATE TABLE cidades (
id bigint DEFAULT nextval('cidades_id_seq') primary key,
nome VARCHAR(60) NOT NULL,
id_estado bigint not null,
CONSTRAINT fk_id_estado_cidades FOREIGN KEY (id_estado) REFERENCES estados(id)
)