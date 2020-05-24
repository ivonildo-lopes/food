create TABLE usuarios(
id serial primary key not null,
nome varchar(100) not NULL,
email VARCHAR(100) NOT NULL,
data_cadastro TIMESTAMP NOT NULL
);

CREATE TABLE usuario_grupo(
id_usuario BIGINT NOT NULL,
id_grupo BIGINT NOT NULL,
CONSTRAINT fk_id_usuario_ug FOREIGN KEY (id_usuario) REFERENCES usuarios(id),
CONSTRAINT fk_id_grupo_ug FOREIGN KEY (id_grupo) REFERENCES grupos(id)
);