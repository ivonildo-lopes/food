CREATE SEQUENCE grupo_id_seq START 1 MINVALUE 1 INCREMENT 1;

create table grupos(
id bigint DEFAULT nextval('grupo_id_seq') primary key,
nome varchar(60) not null
);

CREATE SEQUENCE permissoes_id_seq START 1 MINVALUE 1 INCREMENT 1;
create TABLE permissoes(
id bigint DEFAULT nextval('permissoes_id_seq') primary key,
descricao varchar(100) not null
);


CREATE TABLE grupo_permissoes(
id_grupo BIGINT NOT NULL,
id_permissao BIGINT NOT NULL,
CONSTRAINT fk_id_grupo_gp FOREIGN KEY (id_grupo) REFERENCES grupos(id),
CONSTRAINT fk_id_permissao_gp FOREIGN KEY (id_permissao) REFERENCES permissoes(id)
);
