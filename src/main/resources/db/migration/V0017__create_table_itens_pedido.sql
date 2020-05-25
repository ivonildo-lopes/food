create table itens_pedido (
id  bigserial not NULL PRIMARY key,
quantidade INT4 NOT NULL,
preco_unitario numeric(19, 2) not NULL,
preco_total numeric(19, 2) not NULL,
observacao varchar(255),
id_pedido bigint not NULL,
id_produto bigint not NULL,
constraint uk_item_pedido_produto unique (id_pedido, id_produto),
constraint fk_item_pedido_pedido foreign KEY (id_pedido) references pedidos (id),
constraint fk_item_pedido_produto foreign KEY (id_produto) references produtos (id)

)