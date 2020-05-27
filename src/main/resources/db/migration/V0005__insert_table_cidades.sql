INSERT INTO cidades(id,nome,id_estado) VALUES (1, 'SÃO PAULO',1) ON CONFLICT ON CONSTRAINT cidades_pkey DO NOTHING;
SELECT NEXTVAL('cidades_id_seq');
INSERT INTO cidades(id,nome,id_estado) VALUES (2, 'BARUERI',1) ON CONFLICT ON CONSTRAINT cidades_pkey DO NOTHING;
SELECT NEXTVAL('cidades_id_seq');
INSERT INTO cidades(id,nome,id_estado) VALUES (3, 'SAO CAETANO',1) ON CONFLICT ON CONSTRAINT cidades_pkey DO NOTHING;
SELECT NEXTVAL('cidades_id_seq');
INSERT INTO cidades(id,nome,id_estado) VALUES (4, 'RIO DE JANEIRO',2) ON CONFLICT ON CONSTRAINT cidades_pkey DO NOTHING;
SELECT NEXTVAL('cidades_id_seq');