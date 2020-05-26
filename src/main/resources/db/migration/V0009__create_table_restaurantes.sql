CREATE SEQUENCE restaurantes_id_seq START 1 MINVALUE 1 INCREMENT 1;

create table restaurantes(
id bigint DEFAULT nextval('restaurantes_id_seq') primary key,
nome VARCHAR(100) not NULL,
taxa_frete FLOAT NOT NULL,
aberto BOOLEAN NOT NULL,
data_cadastro TIMESTAMP,
data_atualizacao TIMESTAMP,
id_cozinha BIGINT NOT NULL,
ativo BOOLEAN NOT NULL,
cep VARCHAR(10) NULL,
logradouro VARCHAR(150) NULL,
numero VARCHAR(10) NULL,
complemento VARCHAR(50) NULL,
bairro VARCHAR(50) NULL,
id_cidade BIGINT NULL,
CONSTRAINT fk_idcozinha_restaurante FOREIGN KEY (id_cozinha) REFERENCES cozinhas(id),
CONSTRAINT fk_idcidades_restaurante FOREIGN KEY (id_cidade) REFERENCES cidades(id)
);


CREATE TABLE restaurante_forma_pagamento(
id_restaurante BIGINT NOT NULL,
id_forma_pagamento BIGINT NOT NULL,
CONSTRAINT fk_id_restaurante_rfp FOREIGN KEY (id_restaurante) REFERENCES restaurantes(id),
CONSTRAINT fk_id_forma_pagamento_rfp FOREIGN KEY (id_forma_pagamento) REFERENCES formas_pagamento(id)
);