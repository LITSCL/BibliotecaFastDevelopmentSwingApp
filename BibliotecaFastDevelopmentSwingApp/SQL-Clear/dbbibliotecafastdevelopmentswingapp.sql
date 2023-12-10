-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 10-12-2023 a las 19:10:07
-- Versión del servidor: 10.4.17-MariaDB
-- Versión de PHP: 8.0.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `dbbibliotecafastdevelopmentswingapp`
--
CREATE DATABASE IF NOT EXISTS `dbbibliotecafastdevelopmentswingapp` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `dbbibliotecafastdevelopmentswingapp`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `arriendo`
--

CREATE TABLE `arriendo` (
  `id` int(11) NOT NULL,
  `costo_total` double NOT NULL,
  `fecha_arriendo` date NOT NULL,
  `fecha_devolucion_estimada` date NOT NULL,
  `fecha_devolucion_real` date NOT NULL,
  `dias_de_retraso` varchar(45) NOT NULL,
  `multa` double NOT NULL,
  `costo_de_arriendo` double NOT NULL,
  `cliente_rut` varchar(45) NOT NULL,
  `trabajador_rut` varchar(45) NOT NULL,
  `libro_numero_de_serie` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `autor`
--

CREATE TABLE `autor` (
  `codigo` varchar(45) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `apellido_paterno` varchar(45) NOT NULL,
  `apellido_materno` varchar(45) NOT NULL,
  `fecha_de_nacimiento` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `autor_libro`
--

CREATE TABLE `autor_libro` (
  `id` int(11) NOT NULL,
  `libro_numero_de_serie` int(11) NOT NULL,
  `autor_codigo` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `boleta`
--

CREATE TABLE `boleta` (
  `folio` varchar(45) NOT NULL,
  `precio_neto` double NOT NULL,
  `precio_con_iva` double NOT NULL,
  `costo_iva` double NOT NULL,
  `fecha_de_venta` date NOT NULL,
  `hora_de_venta` time NOT NULL,
  `metodo_de_pago_boleta_codigo` varchar(45) NOT NULL,
  `cliente_rut` varchar(45) NOT NULL,
  `trabajador_rut` varchar(45) NOT NULL,
  `venta_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria`
--

CREATE TABLE `categoria` (
  `codigo` varchar(45) NOT NULL,
  `nombre` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria_libro`
--

CREATE TABLE `categoria_libro` (
  `id` int(11) NOT NULL,
  `categoria_codigo` varchar(45) NOT NULL,
  `libro_numero_de_serie` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `rut` varchar(45) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `apellido_paterno` varchar(45) NOT NULL,
  `apellido_materno` varchar(45) NOT NULL,
  `fecha_de_nacimiento` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `compra`
--

CREATE TABLE `compra` (
  `id` int(11) NOT NULL,
  `precio` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `compra_libro`
--

CREATE TABLE `compra_libro` (
  `id` int(11) NOT NULL,
  `compra_id` int(11) NOT NULL,
  `libro_numero_de_serie` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `correo_cliente`
--

CREATE TABLE `correo_cliente` (
  `id` int(11) NOT NULL,
  `correo` varchar(45) NOT NULL,
  `cliente_rut` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `correo_trabajador`
--

CREATE TABLE `correo_trabajador` (
  `id` int(11) NOT NULL,
  `correo` varchar(45) NOT NULL,
  `trabajador_rut` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `direccion_cliente`
--

CREATE TABLE `direccion_cliente` (
  `id` int(11) NOT NULL,
  `pais` varchar(45) NOT NULL,
  `comuna` varchar(45) NOT NULL,
  `calle` varchar(45) NOT NULL,
  `numero` varchar(45) NOT NULL,
  `cliente_rut` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `direccion_trabajador`
--

CREATE TABLE `direccion_trabajador` (
  `id` int(11) NOT NULL,
  `pais` varchar(45) NOT NULL,
  `comuna` varchar(45) NOT NULL,
  `calle` varchar(45) NOT NULL,
  `numero` varchar(45) NOT NULL,
  `trabajador_rut` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `distribuidor`
--

CREATE TABLE `distribuidor` (
  `rut` varchar(45) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `pais` varchar(45) NOT NULL,
  `comuna` varchar(45) NOT NULL,
  `calle` varchar(45) NOT NULL,
  `numero` varchar(45) NOT NULL,
  `telefono` varchar(45) NOT NULL,
  `year_servicio` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `editorial`
--

CREATE TABLE `editorial` (
  `codigo` varchar(45) NOT NULL,
  `nombre` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estado_libro`
--

CREATE TABLE `estado_libro` (
  `codigo` varchar(45) NOT NULL,
  `descripcion` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `factura`
--

CREATE TABLE `factura` (
  `folio` varchar(45) NOT NULL,
  `precio_neto` double NOT NULL,
  `precio_con_iva` double NOT NULL,
  `costo_iva` double NOT NULL,
  `fecha_de_compra` date NOT NULL,
  `hora_de_compra` time NOT NULL,
  `metodo_de_pago_factura_codigo` varchar(45) NOT NULL,
  `distribuidor_rut` varchar(45) NOT NULL,
  `compra_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `idioma`
--

CREATE TABLE `idioma` (
  `codigo` varchar(45) NOT NULL,
  `nombre` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `idioma_libro`
--

CREATE TABLE `idioma_libro` (
  `id` int(11) NOT NULL,
  `libro_numero_de_serie` int(11) NOT NULL,
  `idioma_codigo` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `libro`
--

CREATE TABLE `libro` (
  `numero_de_serie` int(11) NOT NULL,
  `isbn` varchar(45) NOT NULL,
  `titulo` varchar(45) NOT NULL,
  `numero_de_paginas` int(11) NOT NULL,
  `precio_de_referencia` double NOT NULL,
  `fecha_de_publicacion` date NOT NULL,
  `estado_libro_codigo` varchar(45) NOT NULL,
  `editorial_codigo` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `metodo_de_pago_boleta`
--

CREATE TABLE `metodo_de_pago_boleta` (
  `codigo` varchar(45) NOT NULL,
  `descripcion` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `metodo_de_pago_factura`
--

CREATE TABLE `metodo_de_pago_factura` (
  `codigo` varchar(45) NOT NULL,
  `descripcion` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `telefono_cliente`
--

CREATE TABLE `telefono_cliente` (
  `id` int(11) NOT NULL,
  `telefono` varchar(45) NOT NULL,
  `cliente_rut` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `telefono_trabajador`
--

CREATE TABLE `telefono_trabajador` (
  `id` int(11) NOT NULL,
  `telefono` varchar(45) NOT NULL,
  `trabajador_rut` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `trabajador`
--

CREATE TABLE `trabajador` (
  `rut` varchar(45) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `apellido_paterno` varchar(45) NOT NULL,
  `apellido_materno` varchar(45) NOT NULL,
  `fecha_de_contrato` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `venta`
--

CREATE TABLE `venta` (
  `id` int(11) NOT NULL,
  `precio` double NOT NULL,
  `cliente_rut` varchar(45) NOT NULL,
  `trabajador_rut` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `venta_libro`
--

CREATE TABLE `venta_libro` (
  `id` int(11) NOT NULL,
  `venta_id` int(11) NOT NULL,
  `libro_numero_de_serie` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `arriendo`
--
ALTER TABLE `arriendo`
  ADD PRIMARY KEY (`id`,`cliente_rut`,`trabajador_rut`,`libro_numero_de_serie`),
  ADD KEY `fk_arriendo_cliente1_idx` (`cliente_rut`),
  ADD KEY `fk_arriendo_trabajador1_idx` (`trabajador_rut`),
  ADD KEY `fk_arriendo_libro1_idx` (`libro_numero_de_serie`);

--
-- Indices de la tabla `autor`
--
ALTER TABLE `autor`
  ADD PRIMARY KEY (`codigo`);

--
-- Indices de la tabla `autor_libro`
--
ALTER TABLE `autor_libro`
  ADD PRIMARY KEY (`id`,`libro_numero_de_serie`,`autor_codigo`),
  ADD KEY `fk_autor_libro_libro1_idx` (`libro_numero_de_serie`),
  ADD KEY `fk_autor_libro_autor1_idx` (`autor_codigo`);

--
-- Indices de la tabla `boleta`
--
ALTER TABLE `boleta`
  ADD PRIMARY KEY (`folio`,`metodo_de_pago_boleta_codigo`,`cliente_rut`,`trabajador_rut`,`venta_id`),
  ADD KEY `fk_boleta_metodo_de_pago_boleta1_idx` (`metodo_de_pago_boleta_codigo`),
  ADD KEY `fk_boleta_cliente1_idx` (`cliente_rut`),
  ADD KEY `fk_boleta_trabajador1_idx` (`trabajador_rut`),
  ADD KEY `fk_boleta_venta1_idx` (`venta_id`);

--
-- Indices de la tabla `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`codigo`),
  ADD UNIQUE KEY `nombre_UNIQUE` (`nombre`);

--
-- Indices de la tabla `categoria_libro`
--
ALTER TABLE `categoria_libro`
  ADD PRIMARY KEY (`id`,`categoria_codigo`,`libro_numero_de_serie`),
  ADD KEY `fk_categoria_libro_categoria_idx` (`categoria_codigo`),
  ADD KEY `fk_categoria_libro_libro1_idx` (`libro_numero_de_serie`);

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`rut`);

--
-- Indices de la tabla `compra`
--
ALTER TABLE `compra`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `compra_libro`
--
ALTER TABLE `compra_libro`
  ADD PRIMARY KEY (`id`,`compra_id`,`libro_numero_de_serie`),
  ADD KEY `fk_libro_compra_compra1_idx` (`compra_id`),
  ADD KEY `fk_libro_compra_libro1_idx` (`libro_numero_de_serie`);

--
-- Indices de la tabla `correo_cliente`
--
ALTER TABLE `correo_cliente`
  ADD PRIMARY KEY (`id`,`cliente_rut`),
  ADD KEY `fk_correo_cliente_cliente1` (`cliente_rut`);

--
-- Indices de la tabla `correo_trabajador`
--
ALTER TABLE `correo_trabajador`
  ADD PRIMARY KEY (`id`,`trabajador_rut`),
  ADD KEY `fk_correo_trabajador_trabajador1` (`trabajador_rut`);

--
-- Indices de la tabla `direccion_cliente`
--
ALTER TABLE `direccion_cliente`
  ADD PRIMARY KEY (`id`,`cliente_rut`),
  ADD KEY `fk_direccion_cliente_cliente1` (`cliente_rut`);

--
-- Indices de la tabla `direccion_trabajador`
--
ALTER TABLE `direccion_trabajador`
  ADD PRIMARY KEY (`id`,`trabajador_rut`),
  ADD KEY `fk_direccion_trabajador_trabajador1` (`trabajador_rut`);

--
-- Indices de la tabla `distribuidor`
--
ALTER TABLE `distribuidor`
  ADD PRIMARY KEY (`rut`);

--
-- Indices de la tabla `editorial`
--
ALTER TABLE `editorial`
  ADD PRIMARY KEY (`codigo`);

--
-- Indices de la tabla `estado_libro`
--
ALTER TABLE `estado_libro`
  ADD PRIMARY KEY (`codigo`),
  ADD UNIQUE KEY `descripcion_UNIQUE` (`descripcion`);

--
-- Indices de la tabla `factura`
--
ALTER TABLE `factura`
  ADD PRIMARY KEY (`folio`,`metodo_de_pago_factura_codigo`,`distribuidor_rut`,`compra_id`),
  ADD KEY `fk_factura_metodo_de_pago_factura1_idx` (`metodo_de_pago_factura_codigo`),
  ADD KEY `fk_factura_distribuidor1_idx` (`distribuidor_rut`),
  ADD KEY `fk_factura_compra1_idx` (`compra_id`);

--
-- Indices de la tabla `idioma`
--
ALTER TABLE `idioma`
  ADD PRIMARY KEY (`codigo`),
  ADD UNIQUE KEY `nombre_UNIQUE` (`nombre`);

--
-- Indices de la tabla `idioma_libro`
--
ALTER TABLE `idioma_libro`
  ADD PRIMARY KEY (`id`,`libro_numero_de_serie`,`idioma_codigo`),
  ADD KEY `fk_idioma_libro_libro1_idx` (`libro_numero_de_serie`),
  ADD KEY `fk_idioma_libro_idioma1_idx` (`idioma_codigo`);

--
-- Indices de la tabla `libro`
--
ALTER TABLE `libro`
  ADD PRIMARY KEY (`numero_de_serie`,`estado_libro_codigo`,`editorial_codigo`),
  ADD KEY `fk_libro_estado_libro1_idx` (`estado_libro_codigo`),
  ADD KEY `fk_libro_editorial1_idx` (`editorial_codigo`);

--
-- Indices de la tabla `metodo_de_pago_boleta`
--
ALTER TABLE `metodo_de_pago_boleta`
  ADD PRIMARY KEY (`codigo`);

--
-- Indices de la tabla `metodo_de_pago_factura`
--
ALTER TABLE `metodo_de_pago_factura`
  ADD PRIMARY KEY (`codigo`);

--
-- Indices de la tabla `telefono_cliente`
--
ALTER TABLE `telefono_cliente`
  ADD PRIMARY KEY (`id`,`cliente_rut`),
  ADD KEY `fk_telefono_cliente_cliente1` (`cliente_rut`);

--
-- Indices de la tabla `telefono_trabajador`
--
ALTER TABLE `telefono_trabajador`
  ADD PRIMARY KEY (`id`,`trabajador_rut`),
  ADD KEY `fk_telefono_trabajador_trabajador1` (`trabajador_rut`);

--
-- Indices de la tabla `trabajador`
--
ALTER TABLE `trabajador`
  ADD PRIMARY KEY (`rut`);

--
-- Indices de la tabla `venta`
--
ALTER TABLE `venta`
  ADD PRIMARY KEY (`id`,`cliente_rut`,`trabajador_rut`),
  ADD KEY `fk_venta_cliente1_idx` (`cliente_rut`),
  ADD KEY `fk_venta_trabajador1_idx` (`trabajador_rut`);

--
-- Indices de la tabla `venta_libro`
--
ALTER TABLE `venta_libro`
  ADD PRIMARY KEY (`id`,`venta_id`,`libro_numero_de_serie`),
  ADD KEY `fk_venta_libro_venta1_idx` (`venta_id`),
  ADD KEY `fk_venta_libro_libro1_idx` (`libro_numero_de_serie`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `arriendo`
--
ALTER TABLE `arriendo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `autor_libro`
--
ALTER TABLE `autor_libro`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `categoria_libro`
--
ALTER TABLE `categoria_libro`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `compra`
--
ALTER TABLE `compra`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `compra_libro`
--
ALTER TABLE `compra_libro`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `correo_cliente`
--
ALTER TABLE `correo_cliente`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `correo_trabajador`
--
ALTER TABLE `correo_trabajador`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `direccion_cliente`
--
ALTER TABLE `direccion_cliente`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `direccion_trabajador`
--
ALTER TABLE `direccion_trabajador`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `idioma_libro`
--
ALTER TABLE `idioma_libro`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `telefono_cliente`
--
ALTER TABLE `telefono_cliente`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `telefono_trabajador`
--
ALTER TABLE `telefono_trabajador`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `venta`
--
ALTER TABLE `venta`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `venta_libro`
--
ALTER TABLE `venta_libro`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `arriendo`
--
ALTER TABLE `arriendo`
  ADD CONSTRAINT `fk_arriendo_cliente1` FOREIGN KEY (`cliente_rut`) REFERENCES `cliente` (`rut`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_arriendo_libro1` FOREIGN KEY (`libro_numero_de_serie`) REFERENCES `libro` (`numero_de_serie`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_arriendo_trabajador1` FOREIGN KEY (`trabajador_rut`) REFERENCES `trabajador` (`rut`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `autor_libro`
--
ALTER TABLE `autor_libro`
  ADD CONSTRAINT `fk_autor_libro_autor1` FOREIGN KEY (`autor_codigo`) REFERENCES `autor` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_autor_libro_libro1` FOREIGN KEY (`libro_numero_de_serie`) REFERENCES `libro` (`numero_de_serie`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `boleta`
--
ALTER TABLE `boleta`
  ADD CONSTRAINT `fk_boleta_cliente1` FOREIGN KEY (`cliente_rut`) REFERENCES `cliente` (`rut`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_boleta_metodo_de_pago_boleta1` FOREIGN KEY (`metodo_de_pago_boleta_codigo`) REFERENCES `metodo_de_pago_boleta` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_boleta_trabajador1` FOREIGN KEY (`trabajador_rut`) REFERENCES `trabajador` (`rut`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_boleta_venta1` FOREIGN KEY (`venta_id`) REFERENCES `venta` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `categoria_libro`
--
ALTER TABLE `categoria_libro`
  ADD CONSTRAINT `fk_categoria_libro_categoria` FOREIGN KEY (`categoria_codigo`) REFERENCES `categoria` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_categoria_libro_libro1` FOREIGN KEY (`libro_numero_de_serie`) REFERENCES `libro` (`numero_de_serie`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `compra_libro`
--
ALTER TABLE `compra_libro`
  ADD CONSTRAINT `fk_libro_compra_compra1` FOREIGN KEY (`compra_id`) REFERENCES `compra` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_libro_compra_libro1` FOREIGN KEY (`libro_numero_de_serie`) REFERENCES `libro` (`numero_de_serie`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `correo_cliente`
--
ALTER TABLE `correo_cliente`
  ADD CONSTRAINT `fk_correo_cliente_cliente1` FOREIGN KEY (`cliente_rut`) REFERENCES `cliente` (`rut`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `correo_trabajador`
--
ALTER TABLE `correo_trabajador`
  ADD CONSTRAINT `fk_correo_trabajador_trabajador1` FOREIGN KEY (`trabajador_rut`) REFERENCES `trabajador` (`rut`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `direccion_cliente`
--
ALTER TABLE `direccion_cliente`
  ADD CONSTRAINT `fk_direccion_cliente_cliente1` FOREIGN KEY (`cliente_rut`) REFERENCES `cliente` (`rut`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `direccion_trabajador`
--
ALTER TABLE `direccion_trabajador`
  ADD CONSTRAINT `fk_direccion_trabajador_trabajador1` FOREIGN KEY (`trabajador_rut`) REFERENCES `trabajador` (`rut`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `factura`
--
ALTER TABLE `factura`
  ADD CONSTRAINT `fk_factura_compra1` FOREIGN KEY (`compra_id`) REFERENCES `compra` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_factura_distribuidor1` FOREIGN KEY (`distribuidor_rut`) REFERENCES `distribuidor` (`rut`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_factura_metodo_de_pago_factura1` FOREIGN KEY (`metodo_de_pago_factura_codigo`) REFERENCES `metodo_de_pago_factura` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `idioma_libro`
--
ALTER TABLE `idioma_libro`
  ADD CONSTRAINT `fk_idioma_libro_idioma1` FOREIGN KEY (`idioma_codigo`) REFERENCES `idioma` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_idioma_libro_libro1` FOREIGN KEY (`libro_numero_de_serie`) REFERENCES `libro` (`numero_de_serie`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `libro`
--
ALTER TABLE `libro`
  ADD CONSTRAINT `fk_libro_editorial1` FOREIGN KEY (`editorial_codigo`) REFERENCES `editorial` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_libro_estado_libro1` FOREIGN KEY (`estado_libro_codigo`) REFERENCES `estado_libro` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `telefono_cliente`
--
ALTER TABLE `telefono_cliente`
  ADD CONSTRAINT `fk_telefono_cliente_cliente1` FOREIGN KEY (`cliente_rut`) REFERENCES `cliente` (`rut`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `telefono_trabajador`
--
ALTER TABLE `telefono_trabajador`
  ADD CONSTRAINT `fk_telefono_trabajador_trabajador1` FOREIGN KEY (`trabajador_rut`) REFERENCES `trabajador` (`rut`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `venta`
--
ALTER TABLE `venta`
  ADD CONSTRAINT `fk_venta_cliente1` FOREIGN KEY (`cliente_rut`) REFERENCES `cliente` (`rut`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_venta_trabajador1` FOREIGN KEY (`trabajador_rut`) REFERENCES `trabajador` (`rut`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `venta_libro`
--
ALTER TABLE `venta_libro`
  ADD CONSTRAINT `fk_venta_libro_libro1` FOREIGN KEY (`libro_numero_de_serie`) REFERENCES `libro` (`numero_de_serie`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_venta_libro_venta1` FOREIGN KEY (`venta_id`) REFERENCES `venta` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
