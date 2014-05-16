CREATE SCHEMA IF NOT EXISTS srv;

USE srv;

CREATE TABLE IF NOT EXISTS servidor(
matricula_siape VARCHAR(7)not null,
nome VARCHAR(50)not null,
senha VARCHAR(6)not null,
email VARCHAR(50)not null,
perfil TINYINT(1)not null,
cpf VARCHAR(50)not null,
cnh INT(15),
motorista TINYINT(1)not null,
rg VARCHAR(15)not null,
orgao_expedidor VARCHAR(5)not null,
estado_civil VARCHAR(10),
sexo VARCHAR(10)not null,
telefone_comer VARCHAR(14)not null,
telefone_cel VARCHAR(14),
estado VARCHAR(2),
status_serv TINYINT(1)not null,
cidade VARCHAR(20),
nacionalidade VARCHAR(20),
informacoes VARCHAR(100),
data_nascimento DATE not null,
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


insert into servidor 
values ('1234567', 'admin', '123456', 'admin1@gmail.com', '1',
  '1212', '3434', '1', '4353', 'sde', 'casado', 'masculino', 
 '(51)2324-2324', '(43)3434-3434', 'rs', '1', 'porto', 'brasileiro', 
 'teste admin', '2010/10/09');

select * from servidor;


INSERT INTO `srv`.`veiculo` (`placa`, `ano`, `marca`, `modelo`, `combustivel`, `renavam`, `capacidade`, 
`manutencao`, `manutencao_data_inicial`, `manutencao_data_final`) 
VALUES ('1234ana', '2014', 'marca1', 'modelo1', 'gasolina', '12345abc', '2', '1', '2014/12/12', '2014/12/24');

INSERT INTO `srv`.`veiculo` (`placa`, `ano`, `marca`, `modelo`, `combustivel`, `renavam`, `capacidade`, 
`manutencao`) VALUES ('4321tes', '2014', 'marca2', 'modelo2', 'alcool', '54321cba', '5', '0');


drop database srv;
