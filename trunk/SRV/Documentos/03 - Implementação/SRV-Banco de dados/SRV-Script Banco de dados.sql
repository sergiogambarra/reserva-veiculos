CREATE SCHEMA IF NOT EXISTS SRV;

USE SRV;

CREATE TABLE IF NOT EXISTS servidor(
matricula_siape VARCHAR(10),
senha VARCHAR(6),
perfil TINYINT(1),
email VARCHAR(50),
nome VARCHAR(50),
cpf VARCHAR(50),
cnh INT(15),
motorista TINYINT(1),
rg VARCHAR(15),
orgao_expedidor VARCHAR(5),
estado_civil VARCHAR(10),
sexo VARCHAR(10),
telefone_comer VARCHAR(14),
telefone_cel VARCHAR(14),
estado VARCHAR(2),
status_serv TINYINT(1),
cidade VARCHAR(20),
nacionalidade VARCHAR(20),
informacoes VARCHAR(100),
data_nascimento DATE,
PRIMARY KEY (matricula_siape));

CREATE TABLE IF NOT EXISTS veiculo(
placa VARCHAR(7) not null,
ano VARCHAR(9) not null,
marca VARCHAR(15) not null,
modelo VARCHAR(25) not null,
combustivel VARCHAR(50) not null,
renavam VARCHAR(11) not null,
capacidade INT(2) not null,
manutencao TINYINT(1) not null,
manutencao_data_inicial DATE null,
manutencao_data_final DATE default null,
PRIMARY KEY (placa));


insert into servidor (matricula_siape, senha, perfil, email, nome, cpf, cnh, motorista, rg, orgao_expedidor,
estado_civil, sexo, telefone_comer, telefone_cel, estado, status_serv, cidade, nacionalidade, informacoes, data_nascimento)
values ('1234567', '1234', '1', 'erpeck@restinga.ifrs.edu.br', null, null, null, null, null, null, null, null, null, null,
null, null, null, null, null, null), 
('7654321', '1234', '0', 'erpeck@restinga.ifrs.edu.br', null, null, null, null, null, null, null, null, null, null,
null, null, null, null, null, null);

UPDATE `srv`.`servidor` SET `nome`='asdf', `cpf`='234234', `cnh`='34234', 
`motorista`='1', `rg`='123434', `orgao_expedidor`='sdfs', `estado_civil`='cas', 
`sexo`='m', `telefone_comer`='12312333', `telefone_cel`='3333', `estado`='rs', `status_serv`='1', 
`cidade`='sdf', `nacionalidade`='dfff', `informacoes`='asdff', `data_nascimento`='2010/09/12' 
WHERE `matricula_siape`='1234567';

UPDATE `srv`.`servidor` SET `nome`='ana', `cpf`='234234', `cnh`='34234', 
`motorista`='1', `rg`='123434', `orgao_expedidor`='sdfs', `estado_civil`='sol', 
`sexo`='f', `telefone_comer`='12312333', `telefone_cel`='3333', `estado`='rs', `status_serv`='1', 
`cidade`='sdf', `nacionalidade`='brasileira', `informacoes`='teste', `data_nascimento`='2010/09/12' 
WHERE `matricula_siape`='7654321';

INSERT INTO `srv`.`veiculo` (`placa`, `ano`, `marca`, `modelo`, `combustivel`, `renavam`, `capacidade`, 
`manutencao`, `manutencao_data_inicial`, `manutencao_data_final`) 
VALUES ('1234ana', '2014', 'marca1', 'modelo1', 'gasolina', '12345abc', '2', '1', '2014/12/12', '2014/12/24');

INSERT INTO `srv`.`veiculo` (`placa`, `ano`, `marca`, `modelo`, `combustivel`, `renavam`, `capacidade`, 
`manutencao`) VALUES ('4321teste', '2014', 'marca2', 'modelo2', 'alcool', '54321cba', '5', '0');


drop database SRV;