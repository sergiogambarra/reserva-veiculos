SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `srv` DEFAULT CHARACTER SET latin1 ;
CREATE SCHEMA IF NOT EXISTS `srv` DEFAULT CHARACTER SET latin1 ;
USE `srv` ;

-- -----------------------------------------------------
-- Table `srv`.`veiculo`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `srv`.`veiculo` (
  `placa` VARCHAR(7) NOT NULL ,
  `ano` VARCHAR(9) NOT NULL ,
  `marca` VARCHAR(15) NOT NULL ,
  `modelo` VARCHAR(25) NOT NULL ,
  `combustivel` VARCHAR(50) NOT NULL ,
  `renavam` VARCHAR(11) NOT NULL ,
  `capacidade` INT(2) NOT NULL ,
  `manutencao` TINYINT(1) NOT NULL ,
  `manutencao_data_inicial` DATE NULL DEFAULT NULL ,
  `manutencao_data_final` DATE NULL DEFAULT NULL ,
  PRIMARY KEY (`placa`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `srv`.`servidor`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `srv`.`servidor` (
  `matricula_siape` VARCHAR(10) NOT NULL DEFAULT '' ,
  `nome` VARCHAR(50) NULL ,
  `senha` VARCHAR(6) NULL DEFAULT NULL ,
  `email` VARCHAR(50) NULL DEFAULT NULL ,
  `perfil` TINYINT(1) NULL DEFAULT NULL ,
  `sexo` VARCHAR(10) NULL DEFAULT NULL ,
  `data_nascimento` DATE NULL DEFAULT NULL ,
  `cpf` VARCHAR(14) NULL DEFAULT NULL ,
  `rg` VARCHAR(15) NULL DEFAULT NULL ,
  `orgao_expedidor` VARCHAR(10) NULL DEFAULT NULL ,
  `naturalidade` VARCHAR(30) NULL DEFAULT NULL ,
  `estado` VARCHAR(2) NULL DEFAULT NULL ,
  `nacionalidade` VARCHAR(20) NULL DEFAULT NULL ,
  `estado_civil` VARCHAR(10) NULL DEFAULT NULL ,
  `telefone_1` VARCHAR(13) NULL DEFAULT NULL ,
  `telefone_2` VARCHAR(13) NULL DEFAULT NULL ,
  `motorista` TINYINT(1) NULL DEFAULT NULL ,
  `cnh` VARCHAR(11) NULL DEFAULT NULL ,
  `status_serv` TINYINT(1) NULL DEFAULT NULL ,
  `informacoes` VARCHAR(1000) NULL DEFAULT NULL ,
  PRIMARY KEY (`matricula_siape`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `srv`.`destino`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `srv`.`destino` (
  `id_destino` INT NOT NULL AUTO_INCREMENT ,
  `nome` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`id_destino`) ,
  INDEX `nome_destino_idx` (`nome` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `srv`.`reserva`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `srv`.`reserva` (
  `id_reserva` INT NOT NULL ,
  `matricula_siape` VARCHAR(10) NOT NULL ,
  `data_saida` DATETIME NOT NULL ,
  `data_retorno` DATETIME NOT NULL ,
  `placa` VARCHAR(7) NOT NULL ,
  `modelo` VARCHAR(25) NOT NULL ,
  `condutor` BINARY NOT NULL ,
  `matricula_siape_condutor` VARCHAR(10) NOT NULL ,
  `n_ocupantes` TINYINT NOT NULL ,
  `id_destino` INT NULL ,
  `descricao_destino` VARCHAR(30) NULL ,
  `reserva_datetime` DATETIME NOT NULL ,
  PRIMARY KEY (`id_reserva`) ,
  INDEX `fk_placa_idx` (`placa` ASC) ,
  INDEX `fk_matricula_siape_idx` (`matricula_siape` ASC) ,
  INDEX `fk_matricula_siape_condutor_idx` (`matricula_siape_condutor` ASC) ,
  INDEX `fk_id_destino_idx` (`id_destino` ASC) ,
  INDEX `data_reserva_dt_idx` (`reserva_datetime` ASC) ,
  INDEX `data_saida_idx` (`data_saida` ASC) ,
  INDEX `data_retorno_idx` (`data_retorno` ASC) ,
  CONSTRAINT `fk_reserva_veiculo`
    FOREIGN KEY (`placa` )
    REFERENCES `srv`.`veiculo` (`placa` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_reserva_servidor`
    FOREIGN KEY (`matricula_siape` )
    REFERENCES `srv`.`servidor` (`matricula_siape` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_reserva_condutor`
    FOREIGN KEY (`matricula_siape_condutor` )
    REFERENCES `srv`.`servidor` (`matricula_siape` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_reserva_destino`
    FOREIGN KEY (`id_destino` )
    REFERENCES `srv`.`destino` (`id_destino` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `srv`.`log_alteracao_reserva`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `srv`.`log_alteracao_reserva` (
  `id_reserva_dados` INT NOT NULL ,
  `id_reserva` INT NOT NULL ,
  `data_saida` DATETIME NOT NULL ,
  `data_retorno` DATETIME NOT NULL ,
  `placa` INT NOT NULL ,
  `modelo` VARCHAR(25) NOT NULL ,
  `condutor` BINARY NOT NULL ,
  `matricula_siape` VARCHAR(10) NOT NULL ,
  `matricula_siape_condutor` VARCHAR(10) NOT NULL ,
  `n_ocupantes` TINYINT NOT NULL ,
  `reserva_hist_datetime` DATETIME NULL ,
  `id_destino` INT NOT NULL ,
  `destino_descricao` VARCHAR(30) NULL ,
  `log_reserva_datetime` DATETIME NULL ,
  PRIMARY KEY (`id_reserva_dados`) ,
  INDEX `log_reserva_id_reserva_idx` (`id_reserva` ASC) ,
  INDEX `log_reserva_data_reserva_hist_idx` (`reserva_hist_datetime` ASC) ,
  INDEX `log_reserva_data_saida_hist_idx` (`data_saida` ASC) ,
  INDEX `log_reserva_data_retorno_hist_idx` (`data_retorno` ASC) ,
  INDEX `log_reserva_placa` (`placa` ASC) ,
  INDEX `log_reserva_siape` (`matricula_siape` ASC) ,
  INDEX `log_reserva_condutor` (`matricula_siape_condutor` ASC) ,
  CONSTRAINT `fk_log_alteracao_reserva_id_reserva`
    FOREIGN KEY (`id_reserva` )
    REFERENCES `srv`.`reserva` (`id_reserva` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `srv` ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
