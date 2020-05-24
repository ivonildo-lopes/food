create table produtos(
id serial primary key not null,
nome VARCHAR(100) not NULL,
descricao VARCHAR(150) not NULL,
preco FLOAT NOT NULL,
ativo BOOLEAN NOT NULL,
id_restaurante BIGINT NULL,
CONSTRAINT fk_id_restaurante_prdt FOREIGN KEY (id_restaurante) REFERENCES restaurantes(id)
)