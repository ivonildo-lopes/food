create sequence hibernate_sequence start 1 increment 1
create table cidades (id  bigserial not null, nome varchar(60) not null, id_estado int8 not null, primary key (id))
create table clientes (id int8 not null, version int8 not null, ativo boolean, email varchar(255), nome varchar(255), telefone varchar(255), primary key (id))
create table cozinhas (id  bigserial not null, nome varchar(60) not null, primary key (id))
create table estados (id  bigserial not null, nome varchar(60) not null, primary key (id))
create table formas_pagamento (id  bigserial not null, descricao varchar(100) not null, primary key (id))
create table grupo_permissoes (id_grupo int8 not null, id_permissao int8 not null)
create table grupos (id int8 not null, nome varchar(60) not null, primary key (id))
create table itens_pedido (id  bigserial not null, observacao varchar(255), preco_total numeric(19, 2) not null, preco_unitario numeric(19, 2) not null, quantidade int4 not null, id_pedido int8 not null, id_produto int8 not null, primary key (id))
create table pedidos (id  bigserial not null, data_cancelamento timestamp, data_confirmacao timestamp, data_criacao timestamp not null, data_entrega timestamp, bairro varchar(50), cep varchar(60), complemento varchar(50), logradouro varchar(150), numero varchar(10), status int4 not null, subtotal numeric(19, 2) not null, taxa_frete numeric(19, 2) not null, valor_total numeric(19, 2) not null, id_usuario int8 not null, id_cidade int8, id_forma_pagamento int8 not null, id_restaurante int8 not null, primary key (id))
create table permissoes (id  bigserial not null, descricao varchar(60) not null, nome varchar(60) not null, primary key (id))
create table produtos (id  bigserial not null, ativo boolean, descricao varchar(150), nome varchar(100) not null, preco numeric(19, 2) not null, id_restaurante int8, primary key (id))
create table restaurante_forma_pagamento (id_restaurante int8 not null, id_forma_pagamento int8 not null)
create table restaurantes (id  bigserial not null, aberto boolean, ativo boolean, data_atualizacao timestamp not null, data_cadastro timestamp not null, bairro varchar(50), cep varchar(60), complemento varchar(50), logradouro varchar(150), numero varchar(10), nome varchar(100) not null, taxa_frete numeric(19, 2) not null, id_cozinha int8 not null, id_cidade int8, primary key (id))
create table usuario_grupo (id_usuario int8 not null, id_grupo int8 not null)
create table usuarios (id  bigserial not null, data_cadastro timestamp, email varchar(100) not null, nome varchar(100) not null, primary key (id))
alter table cidades add constraint FK2v6yxmoyu4kcx5r48accn901h foreign key (id_estado) references estados
alter table grupo_permissoes add constraint FK6mq5dhmpenybijpe3ucopil5y foreign key (id_permissao) references permissoes
alter table grupo_permissoes add constraint FK57ghfhxs8tgbrfqr4krryrw34 foreign key (id_grupo) references grupos
alter table itens_pedido add constraint FKsf6v1y1ssvgc4o6n7htptrcay foreign key (id_pedido) references pedidos
alter table itens_pedido add constraint FKcic1aufppp64ge2cyqt9bp5lq foreign key (id_produto) references produtos
alter table pedidos add constraint FK4a0lfwlpmytywxpwjfa1a3ar2 foreign key (id_usuario) references usuarios
alter table pedidos add constraint FKoxc3wueytpp5k54youf72cnp9 foreign key (id_cidade) references cidades
alter table pedidos add constraint FKekxwejldrpg4rclh987ybp43m foreign key (id_forma_pagamento) references formas_pagamento
alter table pedidos add constraint FKa5nobkskmmmses3qsp75mtfoy foreign key (id_restaurante) references restaurantes
alter table produtos add constraint FK3a37b1661qxr5vht9b4m78w2j foreign key (id_restaurante) references restaurantes
alter table restaurante_forma_pagamento add constraint FKlgi9bgh84b25txk01c2o8dccu foreign key (id_forma_pagamento) references formas_pagamento
alter table restaurante_forma_pagamento add constraint FK65ira3pw6uvje17csn1kspr5y foreign key (id_restaurante) references restaurantes
alter table restaurantes add constraint FKprpqfbhu24oq980h1oeiueggh foreign key (id_cozinha) references cozinhas
alter table restaurantes add constraint FKkv62tr958lkaqmqpi2uocs8u6 foreign key (id_cidade) references cidades
alter table usuario_grupo add constraint FKpy161liudc6qm8t1qmfmob3ju foreign key (id_grupo) references grupos
alter table usuario_grupo add constraint FKifutb4rlxy5i3n8slkgjn43d5 foreign key (id_usuario) references usuarios
