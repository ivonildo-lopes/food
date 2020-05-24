create sequence hibernate_sequence start 1 INCREMENT 1;
create table cidades (id  bigserial not null, nome varchar(60) not null, id_estado int8 not null, primary key (id));
create table clientes (id int8 not null, version int8 not null, ativo boolean, email varchar(255), nome varchar(255), telefone varchar(255), primary key (id));
create table cozinhas (id  bigserial not null, nome varchar(60) not null, primary key (id));
create table estados (id  bigserial not null, nome varchar(60) not null, primary key (id));
create table formas_pagamento (id  bigserial not null, descricao varchar(100) not null, primary key (id));
create table grupo_permissoes (id_grupo int8 not null, id_permissao int8 not NULL);
create table grupos (id int8 not null, nome varchar(60) not null, primary key (id));
create table permissoes (id  bigserial not null, descricao varchar(60) not null, nome varchar(60) not null, primary key (id));
create table produtos (id  bigserial not null, ativo boolean, descricao varchar(150), nome varchar(100) not null, preco numeric(19, 2) not null, id_restaurante int8, primary key (id));
create table restaurante_forma_pagamento (id_restaurante int8 not null, id_forma_pagamento int8 not NULL);
create table restaurantes (id  bigserial not null, aberto boolean, ativo boolean, data_atualizacao timestamp not null, data_cadastro timestamp not null, bairro varchar(50), cep varchar(60), complemento varchar(50), logradouro varchar(150), numero varchar(10), nome varchar(100) not null, taxa_frete numeric(19, 2) not null, id_cozinha int8 not null, id_cidade int8, primary key (id));
create table usuario_grupo (id_usuario int8 not null, id_grupo int8 not NULL);
create table usuarios (id  bigserial not null, data_cadastro timestamp, email varchar(100) not null, nome varchar(100) not null, primary key (id));
alter table cidades add constraint FK_id_estado_tb_cidade foreign key (id_estado) references estados;
alter table grupo_permissoes add constraint FK_id_permissao_tb_grupo_permissao foreign key (id_permissao) references permissoes;
alter table grupo_permissoes add constraint FK_id_grupo_tb_grupo_permissao foreign key (id_grupo) references grupos;
alter table produtos add constraint FK_id_restaurante_tb_produtos foreign key (id_restaurante) references restaurantes;
alter table restaurante_forma_pagamento add constraint fk_id_forma_pagamento_tb_rest_forma_pagto foreign key (id_forma_pagamento) references formas_pagamento;
alter table restaurante_forma_pagamento add constraint fk_id_restaurante_tb_rest_forma_pagto foreign key (id_restaurante) references restaurantes;
alter table restaurantes add constraint FK_id_cozinha_tb_restaurante foreign key (id_cozinha) references cozinhas;
alter table restaurantes add constraint FK_id_cidade_tb_restaurante foreign key (id_cidade) references cidades;
alter table usuario_grupo add constraint FK_id_grupo_tb_grupo_usuario foreign key (id_grupo) references grupos;
alter table usuario_grupo add constraint FK_id_usuario_tb_grupo_usuario foreign key (id_usuario) references usuarios;