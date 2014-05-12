CREATE SCHEMA IF NOT EXISTS SRV;

USE SRV;

CREATE TABLE IF NOT EXISTS servidor(
nome VARCHAR(50) NOT NULL,
matricula_siape VARCHAR(10) NOT NULL,
senha VARCHAR(6) NOT NULL,
email VARCHAR(50) NOT NULL,
perfil TINYINT(1) NOT NULL,
sexo VARCHAR(10) NOT NULL,
data_nascimento DATE,
cpf VARCHAR(11) NOT NULL,
rg VARCHAR(15) NOT NULL,
orgao_expedidor VARCHAR(5),
cidade VARCHAR(20),
estado VARCHAR(2),
nacionalidade VARCHAR(20),
estado_civil VARCHAR(10),
telefone_comer VARCHAR(10) NOT NULL,
telefone_cel VARCHAR(10),
motorista TINYINT(1) NOT NULL,
cnh VARCHAR(11),
status_serv TINYINT(1) NOT NULL,
informacoes VARCHAR(100),
PRIMARY KEY (matricula_siape));

CREATE TABLE IF NOT EXISTS veiculo(
placa VARCHAR(7) NOT NULL,
ano VARCHAR(4) NOT NULL,
marca VARCHAR(15) NOT NULL,
modelo VARCHAR(25) NOT NULL,
combustivel VARCHAR(20) NOT NULL,
renavam VARCHAR(15) NOT NULL,
capacidade INT(2) NOT NULL,
manutencao TINYINT(1) NOT NULL,
manutencao_data_inicial DATE NULL,
manutencao_data_final DATE DEFAULT NULL,
PRIMARY KEY (placa));

USE SRV;
insert into servidor (nome, matricula_siape, senha, email, perfil, sexo,
data_nascimento, cpf, rg, orgao_expedidor, cidade, estado, nacionalidade,
estado_civil, telefone_comer, telefone_cel, motorista, cnh, status_serv, informacoes)
values ('asdf', '1234567', '123456', 'erpeck@restinga.ifrs.edu.br', '1', 'masculino', 
'2010/09/12', '12121212121', '232323232323', 'ssprs', 'viamao', 'rs', 'brasileiro', 
'solteiro', '5133333333', '5122222222', '1', '3535353535', '1', null);

insert into servidor (nome, matricula_siape, senha, email, perfil, sexo,
data_nascimento, cpf, rg, orgao_expedidor, cidade, estado, nacionalidade,
estado_civil, telefone_comer, telefone_cel, motorista, status_serv, informacoes)
values ('ana', '7654321', '123456', 'anapgsilva@restinga.ifrs.edu.br', '0', 'masculino', 
'2010/09/12', '13131313131', '2424242424', 'stjrs', 'porto alegre', 'rs', 'brasileiro', 
'feminino', '5144444444', '5166666666', '0','1', null);

USE SRV;
insert into veiculo (placa, ano, marca, modelo, combustivel, renavam, 
capacidade, manutencao, manutencao_data_inicial, manutencao_data_final)
values ('AAA2323', '2012', 'vw', 'corsa gsi 1.0', 'gasolina', '2233445566',
 5, '1', '2014/05/12', '2014/05/13');
insert into veiculo (placa, ano, marca, modelo, combustivel, renavam, 
capacidade, manutencao)
values ('BBB3434', '2014', 'fiat', 'siena epf 1.0', 'gnv', '7878787878',
 5, '0');

select * from veiculo;

select * from servidor;

drop database SRV;

