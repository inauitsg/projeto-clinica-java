insert into tb_especialidade(especialidade) values ('oftalmologia');
insert into tb_especialidade(especialidade) values ('pediatria');
insert into tb_especialidade(especialidade) values ('psiquiatria');
insert into tb_especialidade(especialidade) values ('ortopedia');

insert into tb_medico(nome, crm, telefone, dias, especialidade_id, created_At) values ('Maria dos Santos Souza','1234','3333333','sexta', 1, NOW());

insert into tb_usuario(nome, email, senha) values ('José Augusto Santos', 'jose', '698dc19d489c4e4db73e28a713eab07b');