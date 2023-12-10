-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 10-12-2023 a las 19:16:03
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

--
-- Volcado de datos para la tabla `autor`
--

INSERT INTO `autor` (`codigo`, `nombre`, `apellido_paterno`, `apellido_materno`, `fecha_de_nacimiento`) VALUES
('LL-5723', 'Esteban', 'Alvarez', 'Zamorano', '2010-05-22'),
('ZZ-1234', 'Daniel', 'Alvarez', 'Zamorano', '2010-05-22');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `autor_libro`
--

CREATE TABLE `autor_libro` (
  `id` int(11) NOT NULL,
  `libro_numero_de_serie` int(11) NOT NULL,
  `autor_codigo` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `autor_libro`
--

INSERT INTO `autor_libro` (`id`, `libro_numero_de_serie`, `autor_codigo`) VALUES
(1, 984723485, 'ZZ-1234'),
(2, 345756789, 'ZZ-1234'),
(3, 568346890, 'LL-5723'),
(4, 568346890, 'ZZ-1234'),
(5, 930482719, 'LL-5723'),
(6, 930482719, 'ZZ-1234'),
(7, 657465829, 'LL-5723'),
(8, 847263999, 'LL-5723'),
(10, 423512596, 'LL-5723'),
(11, 165759989, 'ZZ-1234'),
(12, 115425619, 'LL-5723'),
(13, 115425619, 'ZZ-1234'),
(14, 143556747, 'LL-5723'),
(15, 143556747, 'ZZ-1234');

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

--
-- Volcado de datos para la tabla `boleta`
--

INSERT INTO `boleta` (`folio`, `precio_neto`, `precio_con_iva`, `costo_iva`, `fecha_de_venta`, `hora_de_venta`, `metodo_de_pago_boleta_codigo`, `cliente_rut`, `trabajador_rut`, `venta_id`) VALUES
('0009482731', 58000, 69020, 19, '2020-12-13', '04:31:41', 'FL-6742', '8.456.734-5', '3.683.231-2', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria`
--

CREATE TABLE `categoria` (
  `codigo` varchar(45) NOT NULL,
  `nombre` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `categoria`
--

INSERT INTO `categoria` (`codigo`, `nombre`) VALUES
('YY-4621', 'Ciencia'),
('HH-4321', 'Manga');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria_libro`
--

CREATE TABLE `categoria_libro` (
  `id` int(11) NOT NULL,
  `categoria_codigo` varchar(45) NOT NULL,
  `libro_numero_de_serie` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `categoria_libro`
--

INSERT INTO `categoria_libro` (`id`, `categoria_codigo`, `libro_numero_de_serie`) VALUES
(1, 'HH-4321', 984723485),
(2, 'HH-4321', 345756789),
(3, 'YY-4621', 568346890),
(4, 'YY-4621', 930482719),
(5, 'HH-4321', 930482719),
(6, 'YY-4621', 657465829),
(7, 'HH-4321', 657465829),
(8, 'YY-4621', 847263999),
(9, 'HH-4321', 423512596),
(10, 'YY-4621', 165759989),
(11, 'HH-4321', 115425619),
(12, 'YY-4621', 143556747);

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

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`rut`, `nombre`, `apellido_paterno`, `apellido_materno`, `fecha_de_nacimiento`) VALUES
('17.980.501-7', 'Pedro', 'Aguilera', 'Sanchez', '1996-06-21'),
('8.456.734-5', 'Gerardo', 'Sarmiento', 'Alvarez', '1998-08-31');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `compra`
--

CREATE TABLE `compra` (
  `id` int(11) NOT NULL,
  `precio` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `compra`
--

INSERT INTO `compra` (`id`, `precio`) VALUES
(1, 50000),
(2, 58000),
(3, 32000),
(4, 170000);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `compra_libro`
--

CREATE TABLE `compra_libro` (
  `id` int(11) NOT NULL,
  `compra_id` int(11) NOT NULL,
  `libro_numero_de_serie` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `compra_libro`
--

INSERT INTO `compra_libro` (`id`, `compra_id`, `libro_numero_de_serie`) VALUES
(1, 1, 984723485),
(2, 2, 345756789),
(3, 2, 423512596),
(4, 3, 657465829),
(5, 4, 115425619),
(6, 4, 165759989);

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

--
-- Volcado de datos para la tabla `distribuidor`
--

INSERT INTO `distribuidor` (`rut`, `nombre`, `pais`, `comuna`, `calle`, `numero`, `telefono`, `year_servicio`) VALUES
('4.283.767-9', 'ChileDIS', 'Chile', 'Cartagena', 'Marina', '90', '981808412', '2010-05-22');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `editorial`
--

CREATE TABLE `editorial` (
  `codigo` varchar(45) NOT NULL,
  `nombre` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `editorial`
--

INSERT INTO `editorial` (`codigo`, `nombre`) VALUES
('KK-4621', 'Ivrea'),
('XX-1234', 'Panini');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estado_libro`
--

CREATE TABLE `estado_libro` (
  `codigo` varchar(45) NOT NULL,
  `descripcion` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `estado_libro`
--

INSERT INTO `estado_libro` (`codigo`, `descripcion`) VALUES
('di', 'Disponible'),
('nd', 'No disponible'),
('ve', 'Vendido');

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

--
-- Volcado de datos para la tabla `factura`
--

INSERT INTO `factura` (`folio`, `precio_neto`, `precio_con_iva`, `costo_iva`, `fecha_de_compra`, `hora_de_compra`, `metodo_de_pago_factura_codigo`, `distribuidor_rut`, `compra_id`) VALUES
('000462389', 50000, 59500, 19, '2010-05-22', '23:30:56', 'OO-4312', '4.283.767-9', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `idioma`
--

CREATE TABLE `idioma` (
  `codigo` varchar(45) NOT NULL,
  `nombre` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `idioma`
--

INSERT INTO `idioma` (`codigo`, `nombre`) VALUES
('es', 'Español'),
('en', 'Inglés');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `idioma_libro`
--

CREATE TABLE `idioma_libro` (
  `id` int(11) NOT NULL,
  `libro_numero_de_serie` int(11) NOT NULL,
  `idioma_codigo` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `idioma_libro`
--

INSERT INTO `idioma_libro` (`id`, `libro_numero_de_serie`, `idioma_codigo`) VALUES
(1, 984723485, 'es'),
(2, 345756789, 'es'),
(3, 568346890, 'es'),
(4, 568346890, 'en'),
(5, 930482719, 'es'),
(6, 930482719, 'en'),
(7, 657465829, 'es'),
(8, 657465829, 'en'),
(9, 847263999, 'es'),
(10, 847263999, 'en'),
(11, 423512596, 'en'),
(12, 165759989, 'es'),
(13, 115425619, 'es'),
(14, 115425619, 'en'),
(15, 143556747, 'es'),
(16, 143556747, 'en');

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

--
-- Volcado de datos para la tabla `libro`
--

INSERT INTO `libro` (`numero_de_serie`, `isbn`, `titulo`, `numero_de_paginas`, `precio_de_referencia`, `fecha_de_publicacion`, `estado_libro_codigo`, `editorial_codigo`) VALUES
(115425619, 'TT-65-LF', 'Dodoma', 40, 50000, '2016-01-23', 'di', 'KK-4621'),
(143556747, 'SD-11-GH', 'Física para la vida', 1200, 145500, '1980-09-04', 'nd', 'XX-1234'),
(165759989, 'GG-44-PD', 'Programación Avanzada', 300, 120000, '2014-04-11', 've', 'KK-4621'),
(345756789, 'GG-67-AQ', 'Tensei Shitara', 200, 35000, '2010-05-22', 've', 'XX-1234'),
(423512596, 'GW-42-KJ', 'Elfen Lied', 344, 23000, '2005-12-14', 've', 'XX-1234'),
(568346890, 'JJ-79-FR', 'Física 1', 900, 100000, '2010-05-22', 'nd', 'KK-4621'),
(657465829, 'TY-70-AS', 'Blame!', 567, 32000, '1997-02-01', 've', 'XX-1234'),
(847263999, 'JE-63-JX', 'Física Cuantica', 1333, 250000, '1995-12-03', 'nd', 'XX-1234'),
(930482719, 'GE-72-UO', 'Dr.Stone', 1404, 56000, '2010-05-22', 'nd', 'KK-4621'),
(984723485, 'AD-22-DD', 'Dimension W', 400, 50000, '2010-05-22', 've', 'XX-1234');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `metodo_de_pago_boleta`
--

CREATE TABLE `metodo_de_pago_boleta` (
  `codigo` varchar(45) NOT NULL,
  `descripcion` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `metodo_de_pago_boleta`
--

INSERT INTO `metodo_de_pago_boleta` (`codigo`, `descripcion`) VALUES
('FL-6742', 'Crédito'),
('GH-6634', 'Débito');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `metodo_de_pago_factura`
--

CREATE TABLE `metodo_de_pago_factura` (
  `codigo` varchar(45) NOT NULL,
  `descripcion` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `metodo_de_pago_factura`
--

INSERT INTO `metodo_de_pago_factura` (`codigo`, `descripcion`) VALUES
('MD-6391', 'Crédito'),
('OO-4312', 'Débito');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `telefono_cliente`
--

CREATE TABLE `telefono_cliente` (
  `id` int(11) NOT NULL,
  `telefono` varchar(45) NOT NULL,
  `cliente_rut` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `telefono_cliente`
--

INSERT INTO `telefono_cliente` (`id`, `telefono`, `cliente_rut`) VALUES
(1, '598234912', '17.980.501-7'),
(2, '783492419', '17.980.501-7'),
(3, '995001273', '8.456.734-5');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `telefono_trabajador`
--

CREATE TABLE `telefono_trabajador` (
  `id` int(11) NOT NULL,
  `telefono` varchar(45) NOT NULL,
  `trabajador_rut` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `telefono_trabajador`
--

INSERT INTO `telefono_trabajador` (`id`, `telefono`, `trabajador_rut`) VALUES
(1, '380152739', '23.573.316-4'),
(2, '777461800', '3.683.231-2');

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

--
-- Volcado de datos para la tabla `trabajador`
--

INSERT INTO `trabajador` (`rut`, `nombre`, `apellido_paterno`, `apellido_materno`, `fecha_de_contrato`) VALUES
('23.573.316-4', 'Nicolas', 'Marambio', 'Mesina', '2004-05-12'),
('3.683.231-2', 'Pablo', 'Chacón', 'Rojas', '1995-04-15');

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

--
-- Volcado de datos para la tabla `venta`
--

INSERT INTO `venta` (`id`, `precio`, `cliente_rut`, `trabajador_rut`) VALUES
(1, 58000, '8.456.734-5', '23.573.316-4'),
(2, 32000, '17.980.501-7', '23.573.316-4'),
(3, 170000, '17.980.501-7', '23.573.316-4');

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
-- Volcado de datos para la tabla `venta_libro`
--

INSERT INTO `venta_libro` (`id`, `venta_id`, `libro_numero_de_serie`) VALUES
(1, 1, 345756789),
(2, 1, 423512596),
(3, 2, 657465829),
(4, 3, 165759989),
(5, 3, 984723485);

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT de la tabla `categoria_libro`
--
ALTER TABLE `categoria_libro`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de la tabla `compra`
--
ALTER TABLE `compra`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `compra_libro`
--
ALTER TABLE `compra_libro`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT de la tabla `telefono_cliente`
--
ALTER TABLE `telefono_cliente`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `telefono_trabajador`
--
ALTER TABLE `telefono_trabajador`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `venta`
--
ALTER TABLE `venta`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `venta_libro`
--
ALTER TABLE `venta_libro`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

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
