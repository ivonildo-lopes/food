ALTER TABLE cidades ADD COLUMN id_estado BIGINT DEFAULT 0 NOT NULL;

UPDATE cidades  SET id_estado = (SELECT e.id FROM estados e WHERE e.nome = nome_estado);

ALTER TABLE cidades ADD CONSTRAINT fk_cidade_estado FOREIGN KEY (id_estado) REFERENCES estados(id);

ALTER TABLE cidades DROP COLUMN nome_estado;