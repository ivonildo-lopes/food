insert into cozinhas(id,nome) values (1,'TAILANDESA');
insert into cozinhas(id,nome) values (2,'INDIANA');

INSERT INTO estados(id,nome) VALUES (1, 'SÃO PAULO');
INSERT INTO estados(id,nome) VALUES (2, 'RIO DE JANEIRO');
INSERT INTO estados(id,nome) VALUES (3, 'CEARA');

INSERT INTO cidades(id,nome,id_estado) VALUES (1, 'SÃO PAULO',1);
INSERT INTO cidades(id,nome,id_estado) VALUES (2, 'BARUERI',1);
INSERT INTO cidades(id,nome,id_estado) VALUES (3, 'SAO CAETANO',1);

INSERT INTO cidades(id,nome,id_estado) VALUES (4, 'RIO DE JANEIRO',2);
INSERT INTO cidades(id,nome,id_estado) VALUES (5, 'AREAL',2);
INSERT INTO cidades(id,nome,id_estado) VALUES (6, 'ANGRA DOS REIS',2);

INSERT INTO cidades(id,nome,id_estado) VALUES (7, 'FORTALEZA',3);
INSERT INTO cidades(id,nome,id_estado) VALUES (8, 'CAUCAIA',3);
INSERT INTO cidades(id,nome,id_estado) VALUES (9, 'MARANGUAPE',3);
INSERT INTO cidades(id,nome,id_estado) VALUES (10, 'MARACANAU',3);
INSERT INTO cidades(id,nome,id_estado) VALUES (11, 'EUSEBIO',3);
INSERT INTO cidades(id,nome,id_estado) VALUES (12, 'URUOCA',3);

INSERT INTO formas_pagamento(id,descricao) VALUES (1, 'DINHEIRO');
INSERT INTO formas_pagamento(id,descricao) VALUES (2, 'CREDITO');
INSERT INTO formas_pagamento(id,descricao) VALUES (3, 'DEBITO');

INSERT INTO restaurantes(id,nome,taxa_frete,aberto,ativo,data_cadastro, data_atualizacao,id_cozinha, CEP, LOGRADOURO, NUMERO, COMPLEMENTO, BAIRRO, ID_CIDADE) values (1,'farofa branca', 5.76, true,true,'2020-05-20 10:00:09','2020-05-20 10:00:09',2, '03110-020', 'rua monsenhor joao felipo','8','apto 142a','mooca', 1);
INSERT INTO restaurantes(id,nome,taxa_frete,aberto,ativo,data_cadastro, data_atualizacao,id_cozinha, CEP, LOGRADOURO, NUMERO, COMPLEMENTO, BAIRRO, ID_CIDADE) values (2,'ordones', 5.76, true,true,'2020-05-20 10:00:09','2020-05-20 10:00:09',1, '03110-020', 'rua monsenhor joao felipo','8','apto 143b','mooca', 1);
INSERT INTO restaurantes(id,nome,taxa_frete,aberto,ativo,data_cadastro, data_atualizacao,id_cozinha, CEP, LOGRADOURO, NUMERO, COMPLEMENTO, BAIRRO, ID_CIDADE) values (3,'dom espeto', 0, true,true,'2020-05-20 10:00:09','2020-05-20 10:00:09',1, '60360-450', 'travessa congo','42','','antonio bezerra', 7);


INSERT INTO restaurante_forma_pagamento(ID_RESTAURANTE, ID_FORMA_PAGAMENTO) VALUES (1,1);
INSERT INTO restaurante_forma_pagamento(id_restaurante,id_forma_pagamento) VALUES (1,2);
INSERT INTO restaurante_forma_pagamento(id_restaurante,id_forma_pagamento) VALUES (1,3);
INSERT INTO restaurante_forma_pagamento(id_restaurante,id_forma_pagamento) VALUES (2,1);
INSERT INTO restaurante_forma_pagamento(id_restaurante,id_forma_pagamento) VALUES (2,2);




