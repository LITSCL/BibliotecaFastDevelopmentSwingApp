SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `dbbibliotecafastdevelopmentswingapp` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `dbbibliotecafastdevelopmentswingapp` ;

-- -----------------------------------------------------
-- Table `dbbibliotecafastdevelopmentswingapp`.`categoria`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dbbibliotecafastdevelopmentswingapp`.`categoria` (
  `codigo` VARCHAR(45) NOT NULL ,
  `nombre` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`codigo`) ,
  UNIQUE INDEX `nombre_UNIQUE` (`nombre` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbbibliotecafastdevelopmentswingapp`.`estado_libro`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dbbibliotecafastdevelopmentswingapp`.`estado_libro` (
  `codigo` VARCHAR(45) NOT NULL ,
  `descripcion` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`codigo`) ,
  UNIQUE INDEX `descripcion_UNIQUE` (`descripcion` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbbibliotecafastdevelopmentswingapp`.`editorial`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dbbibliotecafastdevelopmentswingapp`.`editorial` (
  `codigo` VARCHAR(45) NOT NULL ,
  `nombre` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`codigo`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbbibliotecafastdevelopmentswingapp`.`libro`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dbbibliotecafastdevelopmentswingapp`.`libro` (
  `numero_de_serie` INT NOT NULL ,
  `isbn` VARCHAR(45) NOT NULL ,
  `titulo` VARCHAR(45) NOT NULL ,
  `numero_de_paginas` INT NOT NULL ,
  `precio_de_referencia` DOUBLE NOT NULL ,
  `fecha_de_publicacion` DATE NOT NULL ,
  `estado_libro_codigo` VARCHAR(45) NOT NULL ,
  `editorial_codigo` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`numero_de_serie`, `estado_libro_codigo`, `editorial_codigo`) ,
  INDEX `fk_libro_estado_libro1_idx` (`estado_libro_codigo` ASC) ,
  INDEX `fk_libro_editorial1_idx` (`editorial_codigo` ASC) ,
  CONSTRAINT `fk_libro_estado_libro1`
    FOREIGN KEY (`estado_libro_codigo` )
    REFERENCES `dbbibliotecafastdevelopmentswingapp`.`estado_libro` (`codigo` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_libro_editorial1`
    FOREIGN KEY (`editorial_codigo` )
    REFERENCES `dbbibliotecafastdevelopmentswingapp`.`editorial` (`codigo` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbbibliotecafastdevelopmentswingapp`.`categoria_libro`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dbbibliotecafastdevelopmentswingapp`.`categoria_libro` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `categoria_codigo` VARCHAR(45) NOT NULL ,
  `libro_numero_de_serie` INT NOT NULL ,
  PRIMARY KEY (`id`, `categoria_codigo`, `libro_numero_de_serie`) ,
  INDEX `fk_categoria_libro_categoria_idx` (`categoria_codigo` ASC) ,
  INDEX `fk_categoria_libro_libro1_idx` (`libro_numero_de_serie` ASC) ,
  CONSTRAINT `fk_categoria_libro_categoria`
    FOREIGN KEY (`categoria_codigo` )
    REFERENCES `dbbibliotecafastdevelopmentswingapp`.`categoria` (`codigo` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_categoria_libro_libro1`
    FOREIGN KEY (`libro_numero_de_serie` )
    REFERENCES `dbbibliotecafastdevelopmentswingapp`.`libro` (`numero_de_serie` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbbibliotecafastdevelopmentswingapp`.`cliente`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dbbibliotecafastdevelopmentswingapp`.`cliente` (
  `rut` VARCHAR(45) NOT NULL ,
  `nombre` VARCHAR(45) NOT NULL ,
  `apellido_paterno` VARCHAR(45) NOT NULL ,
  `apellido_materno` VARCHAR(45) NOT NULL ,
  `fecha_de_nacimiento` DATE NOT NULL ,
  PRIMARY KEY (`rut`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbbibliotecafastdevelopmentswingapp`.`trabajador`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dbbibliotecafastdevelopmentswingapp`.`trabajador` (
  `rut` VARCHAR(45) NOT NULL ,
  `nombre` VARCHAR(45) NOT NULL ,
  `apellido_paterno` VARCHAR(45) NOT NULL ,
  `apellido_materno` VARCHAR(45) NOT NULL ,
  `fecha_de_contrato` DATE NOT NULL ,
  PRIMARY KEY (`rut`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbbibliotecafastdevelopmentswingapp`.`arriendo`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dbbibliotecafastdevelopmentswingapp`.`arriendo` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `costo_total` DOUBLE NOT NULL ,
  `fecha_arriendo` DATE NOT NULL ,
  `fecha_devolucion_estimada` DATE NOT NULL ,
  `fecha_devolucion_real` DATE NOT NULL ,
  `dias_de_retraso` VARCHAR(45) NOT NULL ,
  `multa` DOUBLE NOT NULL ,
  `costo_de_arriendo` DOUBLE NOT NULL ,
  `cliente_rut` VARCHAR(45) NOT NULL ,
  `trabajador_rut` VARCHAR(45) NOT NULL ,
  `libro_numero_de_serie` INT NOT NULL ,
  PRIMARY KEY (`id`, `cliente_rut`, `trabajador_rut`, `libro_numero_de_serie`) ,
  INDEX `fk_arriendo_cliente1_idx` (`cliente_rut` ASC) ,
  INDEX `fk_arriendo_trabajador1_idx` (`trabajador_rut` ASC) ,
  INDEX `fk_arriendo_libro1_idx` (`libro_numero_de_serie` ASC) ,
  CONSTRAINT `fk_arriendo_cliente1`
    FOREIGN KEY (`cliente_rut` )
    REFERENCES `dbbibliotecafastdevelopmentswingapp`.`cliente` (`rut` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_arriendo_trabajador1`
    FOREIGN KEY (`trabajador_rut` )
    REFERENCES `dbbibliotecafastdevelopmentswingapp`.`trabajador` (`rut` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_arriendo_libro1`
    FOREIGN KEY (`libro_numero_de_serie` )
    REFERENCES `dbbibliotecafastdevelopmentswingapp`.`libro` (`numero_de_serie` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbbibliotecafastdevelopmentswingapp`.`correo_trabajador`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dbbibliotecafastdevelopmentswingapp`.`correo_trabajador` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `correo` VARCHAR(45) NOT NULL ,
  `trabajador_rut` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`id`, `trabajador_rut`) ,
  CONSTRAINT `fk_correo_trabajador_trabajador1`
    FOREIGN KEY (`trabajador_rut` )
    REFERENCES `dbbibliotecafastdevelopmentswingapp`.`trabajador` (`rut` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbbibliotecafastdevelopmentswingapp`.`telefono_trabajador`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dbbibliotecafastdevelopmentswingapp`.`telefono_trabajador` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `telefono` VARCHAR(45) NOT NULL ,
  `trabajador_rut` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`id`, `trabajador_rut`) ,
  CONSTRAINT `fk_telefono_trabajador_trabajador1`
    FOREIGN KEY (`trabajador_rut` )
    REFERENCES `dbbibliotecafastdevelopmentswingapp`.`trabajador` (`rut` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbbibliotecafastdevelopmentswingapp`.`direccion_trabajador`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dbbibliotecafastdevelopmentswingapp`.`direccion_trabajador` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `pais` VARCHAR(45) NOT NULL ,
  `comuna` VARCHAR(45) NOT NULL ,
  `calle` VARCHAR(45) NOT NULL ,
  `numero` VARCHAR(45) NOT NULL ,
  `trabajador_rut` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`id`, `trabajador_rut`) ,
  CONSTRAINT `fk_direccion_trabajador_trabajador1`
    FOREIGN KEY (`trabajador_rut` )
    REFERENCES `dbbibliotecafastdevelopmentswingapp`.`trabajador` (`rut` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbbibliotecafastdevelopmentswingapp`.`venta`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dbbibliotecafastdevelopmentswingapp`.`venta` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `precio` DOUBLE NOT NULL ,
  `cliente_rut` VARCHAR(45) NOT NULL ,
  `trabajador_rut` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`id`, `cliente_rut`, `trabajador_rut`) ,
  INDEX `fk_venta_cliente1_idx` (`cliente_rut` ASC) ,
  INDEX `fk_venta_trabajador1_idx` (`trabajador_rut` ASC) ,
  CONSTRAINT `fk_venta_cliente1`
    FOREIGN KEY (`cliente_rut` )
    REFERENCES `dbbibliotecafastdevelopmentswingapp`.`cliente` (`rut` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_venta_trabajador1`
    FOREIGN KEY (`trabajador_rut` )
    REFERENCES `dbbibliotecafastdevelopmentswingapp`.`trabajador` (`rut` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbbibliotecafastdevelopmentswingapp`.`correo_cliente`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dbbibliotecafastdevelopmentswingapp`.`correo_cliente` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `correo` VARCHAR(45) NOT NULL ,
  `cliente_rut` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`id`, `cliente_rut`) ,
  CONSTRAINT `fk_correo_cliente_cliente1`
    FOREIGN KEY (`cliente_rut` )
    REFERENCES `dbbibliotecafastdevelopmentswingapp`.`cliente` (`rut` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbbibliotecafastdevelopmentswingapp`.`telefono_cliente`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dbbibliotecafastdevelopmentswingapp`.`telefono_cliente` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `telefono` VARCHAR(45) NOT NULL ,
  `cliente_rut` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`id`, `cliente_rut`) ,
  CONSTRAINT `fk_telefono_cliente_cliente1`
    FOREIGN KEY (`cliente_rut` )
    REFERENCES `dbbibliotecafastdevelopmentswingapp`.`cliente` (`rut` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbbibliotecafastdevelopmentswingapp`.`direccion_cliente`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dbbibliotecafastdevelopmentswingapp`.`direccion_cliente` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `pais` VARCHAR(45) NOT NULL ,
  `comuna` VARCHAR(45) NOT NULL ,
  `calle` VARCHAR(45) NOT NULL ,
  `numero` VARCHAR(45) NOT NULL ,
  `cliente_rut` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`id`, `cliente_rut`) ,
  CONSTRAINT `fk_direccion_cliente_cliente1`
    FOREIGN KEY (`cliente_rut` )
    REFERENCES `dbbibliotecafastdevelopmentswingapp`.`cliente` (`rut` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbbibliotecafastdevelopmentswingapp`.`metodo_de_pago_boleta`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dbbibliotecafastdevelopmentswingapp`.`metodo_de_pago_boleta` (
  `codigo` VARCHAR(45) NOT NULL ,
  `descripcion` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`codigo`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbbibliotecafastdevelopmentswingapp`.`boleta`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dbbibliotecafastdevelopmentswingapp`.`boleta` (
  `folio` VARCHAR(45) NOT NULL ,
  `precio_neto` DOUBLE NOT NULL ,
  `precio_con_iva` DOUBLE NOT NULL ,
  `costo_iva` DOUBLE NOT NULL ,
  `fecha_de_venta` DATE NOT NULL ,
  `hora_de_venta` TIME NOT NULL ,
  `metodo_de_pago_boleta_codigo` VARCHAR(45) NOT NULL ,
  `cliente_rut` VARCHAR(45) NOT NULL ,
  `trabajador_rut` VARCHAR(45) NOT NULL ,
  `venta_id` INT NOT NULL ,
  PRIMARY KEY (`folio`, `metodo_de_pago_boleta_codigo`, `cliente_rut`, `trabajador_rut`, `venta_id`) ,
  INDEX `fk_boleta_metodo_de_pago_boleta1_idx` (`metodo_de_pago_boleta_codigo` ASC) ,
  INDEX `fk_boleta_cliente1_idx` (`cliente_rut` ASC) ,
  INDEX `fk_boleta_trabajador1_idx` (`trabajador_rut` ASC) ,
  INDEX `fk_boleta_venta1_idx` (`venta_id` ASC) ,
  CONSTRAINT `fk_boleta_metodo_de_pago_boleta1`
    FOREIGN KEY (`metodo_de_pago_boleta_codigo` )
    REFERENCES `dbbibliotecafastdevelopmentswingapp`.`metodo_de_pago_boleta` (`codigo` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_boleta_cliente1`
    FOREIGN KEY (`cliente_rut` )
    REFERENCES `dbbibliotecafastdevelopmentswingapp`.`cliente` (`rut` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_boleta_trabajador1`
    FOREIGN KEY (`trabajador_rut` )
    REFERENCES `dbbibliotecafastdevelopmentswingapp`.`trabajador` (`rut` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_boleta_venta1`
    FOREIGN KEY (`venta_id` )
    REFERENCES `dbbibliotecafastdevelopmentswingapp`.`venta` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbbibliotecafastdevelopmentswingapp`.`compra`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dbbibliotecafastdevelopmentswingapp`.`compra` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `precio` DOUBLE NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbbibliotecafastdevelopmentswingapp`.`metodo_de_pago_factura`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dbbibliotecafastdevelopmentswingapp`.`metodo_de_pago_factura` (
  `codigo` VARCHAR(45) NOT NULL ,
  `descripcion` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`codigo`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbbibliotecafastdevelopmentswingapp`.`distribuidor`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dbbibliotecafastdevelopmentswingapp`.`distribuidor` (
  `rut` VARCHAR(45) NOT NULL ,
  `nombre` VARCHAR(45) NOT NULL ,
  `pais` VARCHAR(45) NOT NULL ,
  `comuna` VARCHAR(45) NOT NULL ,
  `calle` VARCHAR(45) NOT NULL ,
  `numero` VARCHAR(45) NOT NULL ,
  `telefono` VARCHAR(45) NOT NULL ,
  `year_servicio` DATE NOT NULL ,
  PRIMARY KEY (`rut`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbbibliotecafastdevelopmentswingapp`.`factura`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dbbibliotecafastdevelopmentswingapp`.`factura` (
  `folio` VARCHAR(45) NOT NULL ,
  `precio_neto` DOUBLE NOT NULL ,
  `precio_con_iva` DOUBLE NOT NULL ,
  `costo_iva` DOUBLE NOT NULL ,
  `fecha_de_compra` DATE NOT NULL ,
  `hora_de_compra` TIME NOT NULL ,
  `metodo_de_pago_factura_codigo` VARCHAR(45) NOT NULL ,
  `distribuidor_rut` VARCHAR(45) NOT NULL ,
  `compra_id` INT NOT NULL ,
  PRIMARY KEY (`folio`, `metodo_de_pago_factura_codigo`, `distribuidor_rut`, `compra_id`) ,
  INDEX `fk_factura_metodo_de_pago_factura1_idx` (`metodo_de_pago_factura_codigo` ASC) ,
  INDEX `fk_factura_distribuidor1_idx` (`distribuidor_rut` ASC) ,
  INDEX `fk_factura_compra1_idx` (`compra_id` ASC) ,
  CONSTRAINT `fk_factura_metodo_de_pago_factura1`
    FOREIGN KEY (`metodo_de_pago_factura_codigo` )
    REFERENCES `dbbibliotecafastdevelopmentswingapp`.`metodo_de_pago_factura` (`codigo` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_factura_distribuidor1`
    FOREIGN KEY (`distribuidor_rut` )
    REFERENCES `dbbibliotecafastdevelopmentswingapp`.`distribuidor` (`rut` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_factura_compra1`
    FOREIGN KEY (`compra_id` )
    REFERENCES `dbbibliotecafastdevelopmentswingapp`.`compra` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbbibliotecafastdevelopmentswingapp`.`idioma`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dbbibliotecafastdevelopmentswingapp`.`idioma` (
  `codigo` VARCHAR(45) NOT NULL ,
  `nombre` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`codigo`) ,
  UNIQUE INDEX `nombre_UNIQUE` (`nombre` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbbibliotecafastdevelopmentswingapp`.`idioma_libro`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dbbibliotecafastdevelopmentswingapp`.`idioma_libro` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `libro_numero_de_serie` INT NOT NULL ,
  `idioma_codigo` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`id`, `libro_numero_de_serie`, `idioma_codigo`) ,
  INDEX `fk_idioma_libro_libro1_idx` (`libro_numero_de_serie` ASC) ,
  INDEX `fk_idioma_libro_idioma1_idx` (`idioma_codigo` ASC) ,
  CONSTRAINT `fk_idioma_libro_libro1`
    FOREIGN KEY (`libro_numero_de_serie` )
    REFERENCES `dbbibliotecafastdevelopmentswingapp`.`libro` (`numero_de_serie` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_idioma_libro_idioma1`
    FOREIGN KEY (`idioma_codigo` )
    REFERENCES `dbbibliotecafastdevelopmentswingapp`.`idioma` (`codigo` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbbibliotecafastdevelopmentswingapp`.`autor`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dbbibliotecafastdevelopmentswingapp`.`autor` (
  `codigo` VARCHAR(45) NOT NULL ,
  `nombre` VARCHAR(45) NOT NULL ,
  `apellido_paterno` VARCHAR(45) NOT NULL ,
  `apellido_materno` VARCHAR(45) NOT NULL ,
  `fecha_de_nacimiento` DATE NOT NULL ,
  PRIMARY KEY (`codigo`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbbibliotecafastdevelopmentswingapp`.`autor_libro`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dbbibliotecafastdevelopmentswingapp`.`autor_libro` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `libro_numero_de_serie` INT NOT NULL ,
  `autor_codigo` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`id`, `libro_numero_de_serie`, `autor_codigo`) ,
  INDEX `fk_autor_libro_libro1_idx` (`libro_numero_de_serie` ASC) ,
  INDEX `fk_autor_libro_autor1_idx` (`autor_codigo` ASC) ,
  CONSTRAINT `fk_autor_libro_libro1`
    FOREIGN KEY (`libro_numero_de_serie` )
    REFERENCES `dbbibliotecafastdevelopmentswingapp`.`libro` (`numero_de_serie` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_autor_libro_autor1`
    FOREIGN KEY (`autor_codigo` )
    REFERENCES `dbbibliotecafastdevelopmentswingapp`.`autor` (`codigo` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbbibliotecafastdevelopmentswingapp`.`compra_libro`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dbbibliotecafastdevelopmentswingapp`.`compra_libro` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `compra_id` INT NOT NULL ,
  `libro_numero_de_serie` INT NOT NULL ,
  PRIMARY KEY (`id`, `compra_id`, `libro_numero_de_serie`) ,
  INDEX `fk_libro_compra_compra1_idx` (`compra_id` ASC) ,
  INDEX `fk_libro_compra_libro1_idx` (`libro_numero_de_serie` ASC) ,
  CONSTRAINT `fk_libro_compra_compra1`
    FOREIGN KEY (`compra_id` )
    REFERENCES `dbbibliotecafastdevelopmentswingapp`.`compra` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_libro_compra_libro1`
    FOREIGN KEY (`libro_numero_de_serie` )
    REFERENCES `dbbibliotecafastdevelopmentswingapp`.`libro` (`numero_de_serie` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbbibliotecafastdevelopmentswingapp`.`venta_libro`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `dbbibliotecafastdevelopmentswingapp`.`venta_libro` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `venta_id` INT NOT NULL ,
  `libro_numero_de_serie` INT NOT NULL ,
  PRIMARY KEY (`id`, `venta_id`, `libro_numero_de_serie`) ,
  INDEX `fk_venta_libro_venta1_idx` (`venta_id` ASC) ,
  INDEX `fk_venta_libro_libro1_idx` (`libro_numero_de_serie` ASC) ,
  CONSTRAINT `fk_venta_libro_venta1`
    FOREIGN KEY (`venta_id` )
    REFERENCES `dbbibliotecafastdevelopmentswingapp`.`venta` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_venta_libro_libro1`
    FOREIGN KEY (`libro_numero_de_serie` )
    REFERENCES `dbbibliotecafastdevelopmentswingapp`.`libro` (`numero_de_serie` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `dbbibliotecafastdevelopmentswingapp` ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
