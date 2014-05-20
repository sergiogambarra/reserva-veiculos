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
ano VARCHAR(4) not null,
marca VARCHAR(15) not null,
modelo VARCHAR(30) not null,
combustivel VARCHAR(10) not null,
renavam VARCHAR(11) not null,
capacidade INT(2) not null,
manutencao TINYINT(1) not null,
manutencao_data_inicial DATE null default null,
manutencao_data_final DATE null default null,
PRIMARY KEY (placa));

insert into servidor 
values ('1234567', 'admin', '123456', 'admin1@gmail.com', '1',
 'm', '2010/10/09','00000000000', '0000000000', 'ssp/rs',
 'porto alegre', 'rs', 'brasileiro', 'cas', '(51)2324-2324', '(43)3434-3434',
 '1', '12345678911', '1', 'teste admin'),
 ('7654321', 'servidor1', '123456', 'serv1@restinga.ifrs.edu.br', '0',
 'f', '1980/02/23', '111.111.111-11', '1111111111', 'stj/rs',
 'alvorada', 'rs', 'brasileiro', 'sol', '(51)1111-1111', '(52)2222-2222',
'1', '11111111111', '1', 'teste servidor 1'),
 ('7654322', 'servidor2', '123456', 'serv2@restinga.ifrs.edu.br', '0',
 'm', '1985/10/04', '222.222.222-02', '2222222222', 'stj/rj',
 'nova friburgo', 'rj', 'brasileiro', 'sep', '(43)3333-3333', '(46)4444-4444',
 '0', null, '1', 'teste servidor 2');
 


select * from servidor;

INSERT INTO veiculo VALUES 
('car1111', '2014', 'fiat', 'siena 1.0', 'gasolina', 
'12345678900', '5', '1', '2014/12/12', '2014/12/24'),
('car2222', '2013', 'gm', 'corsa sedan', 'gnv',
'12345678901', '5', '0', null, null),
('car3333', '2012', 'ford', 'f-100', 'diesel',
'12345678902', '3', '1', '2014/10/20', '2014/10/21');

select * from veiculo;

drop database srv;
