CREATE SEQUENCE pedidos_id_seq START 1 MINVALUE 1 INCREMENT 1;

CREATE TABLE pedidos (
id bigint DEFAULT nextval('pedidos_id_seq') primary key,
subtotal NUMERIC(19, 2) NOT NULL,
taxa_frete NUMERIC(19, 2) NOT NULL,
valor_total NUMERIC(19, 2) NOT NULL,
id_usuario BIGINT NOT NULL,
id_forma_pagamento BIGINT NOT NULL,
id_restaurante BIGINT NOT NULL,
id_cidade BIGINT NOT NULL,
cep VARCHAR(60) NOT NULL,
logradouro VARCHAR(150) NOT NULL,
numero VARCHAR(10) NOT NULL,
complemento VARCHAR(50) NULL,
bairro VARCHAR(50) NOT NULL,

STATUS VARCHAR(10) NOT NULL,
data_criacao TIMESTAMP NOT NULL,
data_cancelamento TIMESTAMP,
data_confirmacao TIMESTAMP,
data_entrega TIMESTAMP,
constraint fk_pedido_restaurante foreign key (id_restaurante) references restaurantes (id),
constraint fk_pedido_usuario_cliente foreign key (id_usuario) references usuarios (id),
constraint fk_pedido_forma_pagamento foreign key (id_forma_pagamento) references formas_pagamento (id),
constraint fk_pedido_cidade foreign key (id_cidade) references cidades (id)

);
