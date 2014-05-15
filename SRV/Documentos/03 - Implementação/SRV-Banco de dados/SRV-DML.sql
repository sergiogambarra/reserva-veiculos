use srv;

insert into servidor (
	  matricula_siape
	, senha
	, perfil
	, email
	, nome
	, cpf
	, cnh
	, motorista
	, rg
	, orgao_expedidor
	, estado_civil
	, sexo
	, telefone_1
	, telefone_2
	, estado
	, status_serv
	, naturalidade
	, nacionalidade
	, informacoes
	, data_nascimento)
values (
	  '1234567'
	, '123456'
	, '1'
	, 'erpeck@restinga.ifrs.edu.br'
	, null
	, null
	, null
	, null
	, null
	, null
	, null
	, null
	, null
	, null
	, null
	, null
	, null
	, null
	, null
	, null);

insert into servidor (
	  matricula_siape
	, senha
	, perfil
	, email
	, nome
	, cpf
	, cnh
	, motorista
	, rg
	, orgao_expedidor
	, estado_civil
	, sexo
	, telefone_1
	, telefone_2
	, estado
	, status_serv
	, naturalidade
	, nacionalidade
	, informacoes
	, data_nascimento)
values 
	( '7654321'
	, '123456'
	, '0'
	, 'erpeck@restinga.ifrs.edu.br'
	, null
	, null
	, null
	, null
	, null
	, null
	, null
	, null
	, null
	, null
	, null
	, null
	, null
	, null
	, null
	, null);

UPDATE `srv`.`servidor` SET `nome`='Eliana', `cpf`='123.123.123-45', `cnh`='34234', 
`motorista`='1', `rg`='1231231234', `orgao_expedidor`='sdfs', `estado_civil`='cas', 
`sexo`='f', `telefone_1`='(51)1234-1234', `telefone_2`='(51)2345-2345', `estado`='rs', `status_serv`='1', 
`naturalidade`='Porto Alegre', `nacionalidade`='Brasileiro', `informacoes`='Qualquer informação necessária.', `data_nascimento`='2010/09/12' 
WHERE `matricula_siape`='1234567';

UPDATE `srv`.`servidor` SET `nome`='Sergio', `cpf`='123.123.123-45', `cnh`='34234', 
`motorista`='0', `rg`='1231231234', `orgao_expedidor`='sdfs', `estado_civil`='cas', 
`sexo`='m', `telefone_1`='(51)1234-1234', `telefone_2`='(51)2345-2345', `estado`='rs', `status_serv`='1', 
`naturalidade`='Canoas', `nacionalidade`='Brasileiro', `informacoes`='Qualquer informação necessária.', `data_nascimento`='2010/09/12'
WHERE `matricula_siape`='7654321';

select * from destino order by 1;

INSERT INTO `srv`.`veiculo` (`placa`, `ano`, `marca`, `modelo`, `combustivel`, `renavam`, `capacidade`, 
`manutencao`, `manutencao_data_inicial`, `manutencao_data_final`) 
VALUES ('1234ana', '2014', 'marca1', 'modelo1', 'gasolina', '12345abc', '2', '1', '2014/12/12', '2014/12/24');

INSERT INTO `srv`.`veiculo` (`placa`, `ano`, `marca`, `modelo`, `combustivel`, `renavam`, `capacidade`, 
`manutencao`) VALUES ('4321tes', '2014', 'marca2', 'modelo2', 'alcool', '54321cba', '5', '0');

INSERT INTO destino 
values 
	  (null, "IFRS Campus Restinga")
	, (null, "IFRS Campus Porto Alegre")
	, (null, "IFRS Campus Bento Gonçalves")
	, (null, "IFRS Campus Rio Grande")
	, (null, "IFRS Reitoria");