CREATE SCHEMA IF NOT EXISTS SRV;

USE SRV;

CREATE TABLE IF NOT EXISTS servidor(
matricula_siape VARCHAR(10),
senha VARCHAR(10),
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

insert into servidor (matricula_siape, senha, perfil, email, nome, cpf, cnh, motorista, rg, orgao_expedidor,
estado_civil, sexo, telefone_comer, telefone_cel, estado, status_serv, cidade, nacionalidade, informacoes, data_nascimento)
values ('admin', '1234', '1', 'erpeck@restinga.ifrs.edu.br', null, null, null, null, null, null, null, null, null, null,
null, null, null, null, null, null), 
('serv', '5678', '0', 'erpeck@restinga.ifrs.edu.br', null, null, null, null, null, null, null, null, null, null,
null, null, null, null, null, null);

UPDATE `srv`.`servidor` SET `nome`='asdf', `cpf`='234234', `cnh`='34234', `motorista`='1', `rg`='123434', `orgao_expedidor`='sdfs', `estado_civil`='casado', `sexo`='f', `telefone_comer`='12312333', `telefone_cel`='3333', `estado`='rs', `status_serv`='1', `cidade`='sdf', `nacionalidade`='dfff', `informacoes`='asdff', `data_nascimento`='2010/09/12' WHERE `matricula_siape`='admin';




drop database SRV;