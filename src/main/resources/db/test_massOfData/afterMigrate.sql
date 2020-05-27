-- DEPOIS QUE RODAR PELA PRIMEIRA VEZ É NECESSARIO COLOCAR ESSE  PARA NAO DA PROBLEMA DE CONSTRAINT
delete from cozinhas where id > 2;
delete from estados where id > 5;
delete from cidades where id > 8;

insert  into cozinhas(id,nome) values (1,'TAILANDESA') ON CONFLICT ON CONSTRAINT cozinhas_pkey DO NOTHING;
SELECT NEXTVAL('cozinhas_id_seq');
insert  into cozinhas(id,nome) values (2,'INDIANA') ON CONFLICT ON CONSTRAINT cozinhas_pkey DO NOTHING;
SELECT NEXTVAL('cozinhas_id_seq');

insert  into estados(id,nome) values (4,'ALAGOAS') ON CONFLICT ON CONSTRAINT estados_pkey DO NOTHING;
SELECT NEXTVAL('estados_id_seq');
insert  into estados(id,nome) values (5,'AMAPA') ON CONFLICT ON CONSTRAINT estados_pkey DO NOTHING;
SELECT NEXTVAL('estados_id_seq');

INSERT INTO cidades(id,nome,id_estado) VALUES (5, 'AREAL',2) ON CONFLICT ON CONSTRAINT cidades_pkey DO NOTHING;
SELECT NEXTVAL('cidades_id_seq');
INSERT INTO cidades(id,nome,id_estado) VALUES (6, 'ANGRA DOS REIS',2) ON CONFLICT ON CONSTRAINT cidades_pkey DO NOTHING;
SELECT NEXTVAL('cidades_id_seq');
INSERT INTO cidades(id,nome,id_estado) VALUES (7, 'FORTALEZA',3) ON CONFLICT ON CONSTRAINT cidades_pkey DO NOTHING;
SELECT NEXTVAL('cidades_id_seq');
insert  into cidades(id,nome,id_estado) values (8,'BELO MONTE',4) ON CONFLICT ON CONSTRAINT cidades_pkey DO NOTHING;
SELECT NEXTVAL('cidades_id_seq');
insert  into cidades(id,nome,id_estado) values (9,'CAJUEIRO',4) ON CONFLICT ON CONSTRAINT cidades_pkey DO NOTHING;
SELECT NEXTVAL('cidades_id_seq');
insert  into cidades(id,nome,id_estado) values (10,'MACAPA',5) ON CONFLICT ON CONSTRAINT cidades_pkey DO NOTHING;
SELECT NEXTVAL('cidades_id_seq');

INSERT  INTO formas_pagamento(id,descricao) VALUES (1, 'DINHEIRO') ON CONFLICT ON CONSTRAINT formas_pagamento_pkey DO NOTHING;
INSERT  INTO formas_pagamento(id,descricao) VALUES (2, 'CREDITO') ON CONFLICT ON CONSTRAINT formas_pagamento_pkey DO NOTHING;
INSERT  INTO formas_pagamento(id,descricao) VALUES (3, 'DEBITO') ON CONFLICT ON CONSTRAINT formas_pagamento_pkey DO NOTHING;

INSERT  INTO restaurantes(id,nome,taxa_frete,aberto,ativo,data_cadastro, data_atualizacao,id_cozinha, CEP, LOGRADOURO, NUMERO, COMPLEMENTO, BAIRRO, ID_CIDADE) values (1,'farofa branca', 5.76, true,true,'2020-05-20 10:00:09','2020-05-20 10:00:09',2, '03110-020', 'rua monsenhor joao felipo','8','apto 142a','mooca', 1) ON CONFLICT ON CONSTRAINT restaurantes_pkey DO NOTHING;
INSERT  INTO restaurantes(id,nome,taxa_frete,aberto,ativo,data_cadastro, data_atualizacao,id_cozinha, CEP, LOGRADOURO, NUMERO, COMPLEMENTO, BAIRRO, ID_CIDADE) values (2,'ordones', 5.76, true,true,'2020-05-20 10:00:09','2020-05-20 10:00:09',1, '03110-020', 'rua monsenhor joao felipo','8','apto 143b','mooca', 1) ON CONFLICT ON CONSTRAINT restaurantes_pkey DO NOTHING;
INSERT  INTO restaurantes(id,nome,taxa_frete,aberto,ativo,data_cadastro, data_atualizacao,id_cozinha, CEP, LOGRADOURO, NUMERO, COMPLEMENTO, BAIRRO, ID_CIDADE) values (3,'dom espeto', 0, true,true,'2020-05-20 10:00:09','2020-05-20 10:00:09',1, '60360-450', 'travessa congo','42','','antonio bezerra', 7) ON CONFLICT ON CONSTRAINT restaurantes_pkey DO NOTHING;

INSERT  INTO restaurante_forma_pagamento(ID_RESTAURANTE, ID_FORMA_PAGAMENTO) VALUES (1,1) ON CONFLICT DO NOTHING;
INSERT  INTO restaurante_forma_pagamento(id_restaurante,id_forma_pagamento) VALUES (1,2) ON CONFLICT DO NOTHING;
INSERT  INTO restaurante_forma_pagamento(id_restaurante,id_forma_pagamento) VALUES (1,3) ON CONFLICT DO NOTHING;
INSERT  INTO restaurante_forma_pagamento(id_restaurante,id_forma_pagamento) VALUES (2,1) ON CONFLICT DO NOTHING;
INSERT  INTO restaurante_forma_pagamento(id_restaurante,id_forma_pagamento) VALUES (2,2) ON CONFLICT DO NOTHING;

INSERT  INTO produtos(id,nome,descricao,preco,ativo,id_restaurante) VALUES (1,'hamburguer triplo bacon','recheado com muito bacon e cheddar',35.50,true,1) ON CONFLICT ON CONSTRAINT produtos_pkey DO NOTHING;


-- MERGE INTO cozinhas(id,nome) values (1,'TAILANDESA');
-- MERGE INTO cozinhas(id,nome) values (2,'INDIANA');
--
-- MERGE INTO formas_pagamento(id,descricao) VALUES (1, 'DINHEIRO');
-- MERGE INTO formas_pagamento(id,descricao) VALUES (2, 'CREDITO');
-- MERGE INTO formas_pagamento(id,descricao) VALUES (3, 'DEBITO') ;
--
-- MERGE INTO restaurantes(id,nome,taxa_frete,aberto,ativo,data_cadastro, data_atualizacao,id_cozinha, CEP, LOGRADOURO, NUMERO, COMPLEMENTO, BAIRRO, ID_CIDADE) values (1,'farofa branca', 5.76, true,true,'2020-05-20 10:00:09','2020-05-20 10:00:09',2, '03110-020', 'rua monsenhor joao felipo','8','apto 142a','mooca', 1);
-- MERGE INTO restaurantes(id,nome,taxa_frete,aberto,ativo,data_cadastro, data_atualizacao,id_cozinha, CEP, LOGRADOURO, NUMERO, COMPLEMENTO, BAIRRO, ID_CIDADE) values (2,'ordones', 5.76, true,true,'2020-05-20 10:00:09','2020-05-20 10:00:09',1, '03110-020', 'rua monsenhor joao felipo','8','apto 143b','mooca', 1) ;
-- MERGE INTO restaurantes(id,nome,taxa_frete,aberto,ativo,data_cadastro, data_atualizacao,id_cozinha, CEP, LOGRADOURO, NUMERO, COMPLEMENTO, BAIRRO, ID_CIDADE) values (3,'dom espeto', 0, true,true,'2020-05-20 10:00:09','2020-05-20 10:00:09',1, '60360-450', 'travessa congo','42','','antonio bezerra', 7) ;

-- MERGE INTO restaurante_forma_pagamento(id_restaurante,id_forma_pagamento) VALUES (1,1);
-- MERGE INTO restaurante_forma_pagamento(id_restaurante,id_forma_pagamento) VALUES (1,2) ;
-- MERGE INTO restaurante_forma_pagamento(id_restaurante,id_forma_pagamento) VALUES (1,3);
-- MERGE INTO restaurante_forma_pagamento(id_restaurante,id_forma_pagamento) VALUES (2,1);
-- MERGE INTO restaurante_forma_pagamento(id_restaurante,id_forma_pagamento) VALUES (2,2);

-- MERGE INTO produtos(id,nome,descricao,preco,ativo,id_restaurante) VALUES (1,'hamburguer triplo bacon','recheado com muito bacon e cheddar',35.50,true,1);



-- mysql insert example
-- insert ignore  into cozinhas(id,nome) values (1,'TAILANDESA');
-- insert ignore into cozinhas(id,nome) values (2,'INDIANA');
--
-- INSERT ignore  INTO formas_pagamento(id,descricao) VALUES (1, 'DINHEIRO');
-- INSERT ignore INTO formas_pagamento(id,descricao) VALUES (2, 'CREDITO');
-- INSERT ignore  INTO formas_pagamento(id,descricao) VALUES (3, 'DEBITO') ;
--
-- INSERT ignore  INTO restaurantes(id,nome,taxa_frete,aberto,ativo,data_cadastro, data_atualizacao,id_cozinha, CEP, LOGRADOURO, NUMERO, COMPLEMENTO, BAIRRO, ID_CIDADE) values (1,'farofa branca', 5.76, true,true,'2020-05-20 10:00:09','2020-05-20 10:00:09',2, '03110-020', 'rua monsenhor joao felipo','8','apto 142a','mooca', 1);
-- INSERT ignore  INTO restaurantes(id,nome,taxa_frete,aberto,ativo,data_cadastro, data_atualizacao,id_cozinha, CEP, LOGRADOURO, NUMERO, COMPLEMENTO, BAIRRO, ID_CIDADE) values (2,'ordones', 5.76, true,true,'2020-05-20 10:00:09','2020-05-20 10:00:09',1, '03110-020', 'rua monsenhor joao felipo','8','apto 143b','mooca', 1) ;
-- INSERT ignore  INTO restaurantes(id,nome,taxa_frete,aberto,ativo,data_cadastro, data_atualizacao,id_cozinha, CEP, LOGRADOURO, NUMERO, COMPLEMENTO, BAIRRO, ID_CIDADE) values (3,'dom espeto', 0, true,true,'2020-05-20 10:00:09','2020-05-20 10:00:09',1, '60360-450', 'travessa congo','42','','antonio bezerra', 7) ;
--
-- INSERT ignore  INTO restaurante_forma_pagamento(ID_RESTAURANTE, ID_FORMA_PAGAMENTO) VALUES (1,1);
-- INSERT ignore  INTO restaurante_forma_pagamento(id_restaurante,id_forma_pagamento) VALUES (1,2) ;
-- INSERT ignore  INTO restaurante_forma_pagamento(id_restaurante,id_forma_pagamento) VALUES (1,3);
-- INSERT ignore  INTO restaurante_forma_pagamento(id_restaurante,id_forma_pagamento) VALUES (2,1);
-- INSERT ignore  INTO restaurante_forma_pagamento(id_restaurante,id_forma_pagamento) VALUES (2,2);
--
-- INSERT ignore INTO produtos(id,nome,descricao,preco,ativo,id_restaurante) VALUES (1,'hamburguer triplo bacon','recheado com muito bacon e cheddar',35.50,true,1);
