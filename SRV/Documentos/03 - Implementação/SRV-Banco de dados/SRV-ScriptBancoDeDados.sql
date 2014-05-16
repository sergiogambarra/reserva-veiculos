CREATE SCHEMA IF NOT EXISTS srv;

USE srv;

CREATE TABLE IF NOT EXISTS servidor(
matricula_siape VARCHAR(7)not null,
nome VARCHAR(50)not null,
senha VARCHAR(6)not null,
email VARCHAR(50)not null,
perfil TINYINT(1)not null,
sexo VARCHAR(10)not null,
data_nascimento DATE not null,
cpf VARCHAR(14)not null,
rg VARCHAR(15)not null,
orgao_expedidor VARCHAR(10)not null,
naturalidade VARCHAR(30),
estado VARCHAR(2),
nacionalidade VARCHAR(20),
estado_civil VARCHAR(15),
telefone1 VARCHAR(13)not null,
telefone2 VARCHAR(13),
motorista TINYINT(1)not null,
cnh VARCHAR(11),
status_serv TINYINT(1)not null,
informacoes VARCHAR(1000),
PRIMARY KEY (matricula_siape));

CREATE TABLE IF NOT EXISTS veiculo(
placa VARCHAR(7) not null,
ano VARCHAR(9) not null,
marca VARCHAR(15) not null,
modelo VARCHAR(30) not null,
combustivel VARCHAR(50) not null,
renavam VARCHAR(11) not null,
capacidade INT(2) not null,
manutencao TINYINT(1) not null,
manutencao_data_inicial DATE null default null,
manutencao_data_final DATE null default null,
PRIMARY KEY (placa));

insert into servidor 
values ('1234567', 'admin', '123456', 'admin1@gmail.com', '1',
 'masculino', '2010/10/09','1212', '4353', 'sde',
 'porto', 'rs', 'brasileiro', 'casado', '(51)2324-2324', '(43)3434-3434',
 '1', '12345678911', '1', 'teste admin');

select * from servidor;
INSERT INTO veiculo 
VALUES ('car1234', '2014', 'fiat', 'siena 1.0', 'gasolina', '12345678900', '2', '1', '2014/12/12', '2014/12/24');

select * from veiculo;

drop database srv;
