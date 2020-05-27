insert  into estados(id,nome) values (1,'SAO PAULO') ON CONFLICT ON CONSTRAINT estados_pkey DO NOTHING;
SELECT NEXTVAL('estados_id_seq');
insert  into estados(id,nome) values (2,'RIO DE JANEIRO') ON CONFLICT ON CONSTRAINT estados_pkey DO NOTHING;
SELECT NEXTVAL('estados_id_seq');
insert  into estados(id,nome) values (3,'CEARA') ON CONFLICT ON CONSTRAINT estados_pkey DO NOTHING;
SELECT NEXTVAL('estados_id_seq');