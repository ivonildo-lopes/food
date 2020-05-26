CREATE SEQUENCE formas_pagamento_id_seq START 1 MINVALUE 1 INCREMENT 1;

create table formas_pagamento(
id bigint DEFAULT nextval('formas_pagamento_id_seq') primary key,
descricao varchar(100) not null
)