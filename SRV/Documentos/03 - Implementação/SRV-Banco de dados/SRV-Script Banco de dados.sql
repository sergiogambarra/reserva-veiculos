CREATE SCHEMA IF NOT EXISTS SRV;

USE SRV;

CREATE TABLE IF NOT EXISTS funcionario(
matricula_siape VARCHAR(10),
senha VARCHAR(10),
perfil TINYINT(1),
email VARCHAR(50),
PRIMARY KEY (matricula_siape));

insert into funcionario (matricula_siape, senha, perfil, email)
values ('admin', '1234', '1', 'erpeck@restinga.ifrs.edu.br'), 
('serv', '5678', '0', 'erpeck@restinga.ifrs.edu.br');

drop database SRV;