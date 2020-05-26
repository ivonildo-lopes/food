CREATE SEQUENCE formas_pagamento_id_seq;

create table formas_pagamento(
id bigint DEFAULT nextval('formas_pagamento_id_seq') primary key,
descricao varchar(100) not null
)