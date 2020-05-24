create table grupos(
id serial primary key not null,
nome varchar(60) not null
);

create TABLE permissoes(
id serial primary key not null,
descricao varchar(100) not null
);


CREATE TABLE grupo_permissoes(
id_grupo BIGINT NOT NULL,
id_permissao BIGINT NOT NULL,
CONSTRAINT fk_id_grupo_gp FOREIGN KEY (id_grupo) REFERENCES grupos(id),
CONSTRAINT fk_id_permissao_gp FOREIGN KEY (id_permissao) REFERENCES permissoes(id)
);