CREATE SEQUENCE produtos_id_seq;

create table produtos(
id bigint DEFAULT nextval('produtos_id_seq') primary key,
nome VARCHAR(100) not NULL,
descricao VARCHAR(150) not NULL,
preco FLOAT NOT NULL,
ativo BOOLEAN NOT NULL,
id_restaurante BIGINT NULL,
CONSTRAINT fk_id_restaurante_prdt FOREIGN KEY (id_restaurante) REFERENCES restaurantes(id)
)