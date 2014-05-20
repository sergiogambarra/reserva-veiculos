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


-- -----------------------------------------------------

-- Table `mydb`.`destino`

-- -----------------------------------------------------

CREATE  TABLE IF NOT EXISTS `destino` (

  `id_destino` INT NOT NULL AUTO_INCREMENT ,

  `nome` VARCHAR(45) NOT NULL ,

  PRIMARY KEY (`id_destino`) )

ENGINE = InnoDB;



-- -----------------------------------------------------

-- Table `mydb`.`reserva`

-- -----------------------------------------------------

CREATE  TABLE IF NOT EXISTS `reserva` (

  `id_reserva` INT NOT NULL ,

  `matricula_siape` VARCHAR(7) NOT NULL ,

  `data_saida` DATETIME NOT NULL ,

  `data_retorno` DATETIME NOT NULL ,

  `placa` VARCHAR(7) NOT NULL ,

  `condutor` BINARY NOT NULL ,

  `matricula_siape_condutor` VARCHAR(7) NOT NULL ,

  `id_destino` INT NULL ,

  `descricao_destino` VARCHAR(30) NULL ,

  `reserva_datetime` DATETIME NOT NULL ,

  PRIMARY KEY (`id_reserva`) ,

  INDEX `fk_reserva_servidor_idx` (`matricula_siape` ASC) ,

  INDEX `fk_reserva_veiculo_idx` (`placa` ASC) ,

  INDEX `reserva_data_saida_idx` (`data_saida` ASC) ,

  INDEX `reserva_data_retorno_idx` (`data_retorno` ASC) ,

  INDEX `fk_reserva_servidor_condutor_idx` (`matricula_siape_condutor` ASC) ,

  INDEX `fk_reserva_destino_idx` (`id_destino` ASC) ,

  CONSTRAINT `fk_reserva_servidor`

    FOREIGN KEY (`matricula_siape` )

    REFERENCES `srv`.`servidor` (`matricula_siape` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION,

  CONSTRAINT `fk_reserva_veiculo1`

    FOREIGN KEY (`placa` )

    REFERENCES `srv`.`veiculo` (`placa` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION,

  CONSTRAINT `fk_reserva_servidor1`

    FOREIGN KEY (`matricula_siape_condutor` )

    REFERENCES `srv`.`servidor` (`matricula_siape` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION,

  CONSTRAINT `fk_reserva_destino1`

    FOREIGN KEY (`id_destino` )

    REFERENCES `destino` (`id_destino` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION)

ENGINE = InnoDB;
