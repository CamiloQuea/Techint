-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 23-11-2021 a las 23:43:43
-- Versión del servidor: 10.4.18-MariaDB
-- Versión de PHP: 8.0.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `techintdb`
--
CREATE DATABASE IF NOT EXISTS `techintdb` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `techintdb`;

DELIMITER $$
--
-- Funciones
--
DROP FUNCTION IF EXISTS `newConductor_id`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `newConductor_id` () RETURNS CHAR(10) CHARSET utf8mb4 BEGIN

DECLARE contador INT;

 SET contador = (SELECT  count(*) FROM conductor);

IF (1>contador) THEN
	return "C000000001";
END IF;

return (select CONCAT(LEFT(conductor_id,1),LPAD((CAST((RIGHT(conductor_id,9)) AS UNSIGNED)+1),9,"0")) from conductor ORDER BY conductor_id desc limit 1);

END$$

DROP FUNCTION IF EXISTS `newEmpresa_id`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `newEmpresa_id` () RETURNS CHAR(10) CHARSET utf8mb4 BEGIN

DECLARE contador INT;

 SET contador = (SELECT  count(*) FROM empresa);

IF (1>contador) THEN
	return "E0001";
END IF;

return (select CONCAT(LEFT(empresa_id,1),LPAD((CAST((RIGHT(empresa_id,4)) AS UNSIGNED)+1),4,"0")) from empresa ORDER BY empresa_id desc limit 1);

END$$

DROP FUNCTION IF EXISTS `newEvaluacion_id`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `newEvaluacion_id` () RETURNS CHAR(10) CHARSET utf8mb4 BEGIN

DECLARE contador INT;

 SET contador = (SELECT  count(*) FROM evaluacion);

IF (1>contador) THEN
	return "EN00000001";
END IF;

return (select CONCAT(LEFT(evaluacion_id,2),LPAD((CAST((RIGHT(evaluacion_id,8)) AS UNSIGNED)+1),8,"0")) from evaluacion ORDER BY evaluacion_id desc limit 1);

END$$

DROP FUNCTION IF EXISTS `newEvaluador_id`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `newEvaluador_id` () RETURNS CHAR(10) CHARSET utf8mb4 BEGIN

DECLARE contador INT;

 SET contador = (SELECT  count(*) FROM evaluador);

IF (1>contador) THEN
	return "ER00000001";
END IF;

return (select CONCAT(LEFT(evaluador_id,2),LPAD((CAST((RIGHT(evaluador_id,8)) AS UNSIGNED)+1),8,"0")) from evaluador ORDER BY evaluador_id desc limit 1);

END$$

DROP FUNCTION IF EXISTS `newGrupoSanguineo_id`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `newGrupoSanguineo_id` () RETURNS CHAR(10) CHARSET utf8mb4 BEGIN

DECLARE contador INT;

 SET contador = (SELECT  count(*) FROM grupo_sanguineo);

IF (1>contador) THEN
	return "GS00000001";
END IF;

return (select CONCAT(LEFT(grupoSanguineo_id,2),LPAD((CAST((RIGHT(grupoSanguineo_id,8)) AS UNSIGNED)+1),8,"0")) from grupo_sanguineo ORDER BY grupoSanguineo_id desc limit 1);

END$$

DROP FUNCTION IF EXISTS `newInfraccion_id`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `newInfraccion_id` () RETURNS CHAR(10) CHARSET utf8mb4 BEGIN

DECLARE contador INT;

 SET contador = (SELECT  count(*) FROM infraccion_conductor);

IF (1>contador) THEN
	return "I000000001";
END IF;

return (select CONCAT(LEFT(infraccion_id,1),LPAD((CAST((RIGHT(infraccion_id,9)) AS UNSIGNED)+1),9,"0")) from infraccion_conductor ORDER BY infraccion_id desc limit 1);

END$$

DROP FUNCTION IF EXISTS `newPais_id`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `newPais_id` () RETURNS CHAR(10) CHARSET utf8mb4 BEGIN

DECLARE contador INT;

 SET contador = (SELECT  count(*) FROM pais);

IF (1>contador) THEN
	return "P000000001";
END IF;

return (select CONCAT(LEFT(pais_id,1),LPAD((CAST((RIGHT(pais_id,9)) AS UNSIGNED)+1),9,"0")) from pais ORDER BY pais_id desc limit 1);

END$$

DROP FUNCTION IF EXISTS `newProyecto_id`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `newProyecto_id` () RETURNS CHAR(10) CHARSET utf8mb4 BEGIN

DECLARE contador INT;

 SET contador = (SELECT  count(*) FROM proyecto);

IF (1>contador) THEN
	return "PR00000001";
END IF;

return (select CONCAT(LEFT(proyecto_id,2),LPAD((CAST((RIGHT(proyecto_id,8)) AS UNSIGNED)+1),8,"0")) from proyecto ORDER BY proyecto_id desc limit 1);

END$$

DROP FUNCTION IF EXISTS `newRol_id`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `newRol_id` () RETURNS CHAR(10) CHARSET utf8mb4 BEGIN

DECLARE contador INT;

 SET contador = (SELECT  count(*) FROM rol);

IF (1>contador) THEN
	return "R000000001";
END IF;

return (select CONCAT(LEFT(rol_id,1),LPAD((CAST((RIGHT(rol_id,9)) AS UNSIGNED)+1),9,"0")) from rol ORDER BY rol_id desc limit 1);


END$$

DROP FUNCTION IF EXISTS `newTipoDocumentoIdentidad_id`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `newTipoDocumentoIdentidad_id` () RETURNS CHAR(10) CHARSET utf8mb4 BEGIN

DECLARE contador INT;

 SET contador = (SELECT  count(*) FROM tipo_documento_identidad);

IF (1>contador) THEN
	return "TDIN000001";
END IF;

return (select CONCAT(LEFT(tipoDocumentoIdentidad_id,3),LPAD((CAST((RIGHT(tipoDocumentoIdentidad_id,7)) AS UNSIGNED)+1),7,"0")) from tipo_documento_identidad ORDER BY tipoDocumentoIdentidad_id desc limit 1);

END$$

DROP FUNCTION IF EXISTS `newTipoInfraccion_id`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `newTipoInfraccion_id` () RETURNS CHAR(10) CHARSET utf8mb4 BEGIN

DECLARE contador INT;

 SET contador = (SELECT  count(*) FROM tipo_infraccion);

IF (1>contador) THEN
	return "TI00000001";
END IF;

return (select CONCAT(LEFT(tipoInfraccion_id,2),LPAD((CAST((RIGHT(tipoInfraccion_id,8)) AS UNSIGNED)+1),8,"0")) from tipo_infraccion ORDER BY tipoInfraccion_id desc limit 1);

END$$

DROP FUNCTION IF EXISTS `newTipoLicenciaInterna_id`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `newTipoLicenciaInterna_id` () RETURNS CHAR(10) CHARSET utf8mb4 BEGIN

DECLARE contador INT;

 SET contador = (SELECT  count(*) FROM tipo_licencia_interna);

IF (1>contador) THEN
	return "TLI0000001";
END IF;

return (select CONCAT(LEFT(tipoLicenciaInterna_id,3),LPAD((CAST((RIGHT(tipoLicenciaInterna_id,7)) AS UNSIGNED)+1),7,"0")) from tipo_licencia_interna ORDER BY tipoLicenciaInterna_id desc limit 1);

END$$

DROP FUNCTION IF EXISTS `newTipoLicenciaOficial_id`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `newTipoLicenciaOficial_id` () RETURNS CHAR(10) CHARSET utf8mb4 BEGIN

DECLARE contador INT;

 SET contador = (SELECT  count(*) FROM tipo_licencia_oficial);

IF (1>contador) THEN
	return "TLO0000001";
END IF;

return (select CONCAT(LEFT(tipoLicenciaOficial_id,3),LPAD((CAST((RIGHT(tipoLicenciaOficial_id,7)) AS UNSIGNED)+1),7,"0")) from tipo_licencia_oficial ORDER BY tipoLicenciaOficial_id desc limit 1);

END$$

DROP FUNCTION IF EXISTS `newTipoVehiculo_id`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `newTipoVehiculo_id` () RETURNS CHAR(10) CHARSET utf8mb4 BEGIN

DECLARE contador INT;

 SET contador = (SELECT  count(*) FROM tipo_vehiculo);

IF (1>contador) THEN
	return "TV00000001";
END IF;

return (select CONCAT(LEFT(tipoVehiculo_id,2),LPAD((CAST((RIGHT(tipoVehiculo_id,8)) AS UNSIGNED)+1),8,"0")) from tipo_vehiculo ORDER BY tipoVehiculo_id desc limit 1);

END$$

DROP FUNCTION IF EXISTS `newUser_id`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `newUser_id` () RETURNS CHAR(10) CHARSET utf8mb4 BEGIN

DECLARE contador INT;

 SET contador = (SELECT  count(*) FROM usuario);

IF (1>contador) THEN
	return "U000000001";
END IF;

return (select CONCAT(LEFT(usuario_id,1),LPAD((CAST((RIGHT(usuario_id,9)) AS UNSIGNED)+1),9,"0")) from usuario ORDER BY usuario_id desc limit 1);

END$$

DROP FUNCTION IF EXISTS `newVehiculo_id`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `newVehiculo_id` () RETURNS CHAR(10) CHARSET utf8mb4 BEGIN

DECLARE contador INT;

 SET contador = (SELECT  count(*) FROM vehiculo);

IF (1>contador) THEN
	return "V000000001";
END IF;

return (select CONCAT(LEFT(vehiculo_id,1),LPAD((CAST((RIGHT(vehiculo_id,9)) AS UNSIGNED)+1),9,"0")) from vehiculo ORDER BY vehiculo_id desc limit 1);

END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `conductor`
--

DROP TABLE IF EXISTS `conductor`;
CREATE TABLE `conductor` (
  `conductor_id` char(10) NOT NULL,
  `empresa_id` char(10) DEFAULT NULL,
  `grupoSanguineo_id` char(10) DEFAULT NULL,
  `tipoDocumentoIdentidad_id` char(10) DEFAULT NULL,
  `pais_id` char(10) DEFAULT NULL,
  `conductor_documento` varchar(20) DEFAULT NULL,
  `conductor_nombre` varchar(50) DEFAULT NULL,
  `conductor_apellido` varchar(50) DEFAULT NULL,
  `activo` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `conductor`
--

INSERT INTO `conductor` (`conductor_id`, `empresa_id`, `grupoSanguineo_id`, `tipoDocumentoIdentidad_id`, `pais_id`, `conductor_documento`, `conductor_nombre`, `conductor_apellido`, `activo`) VALUES
('C000000001', 'E0001', 'GS00000001', 'TDI0000001', 'P000000001', '123', 'Camilo', 'Quea', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `conductor_licenciainterna`
--

DROP TABLE IF EXISTS `conductor_licenciainterna`;
CREATE TABLE `conductor_licenciainterna` (
  `conductor_id` char(10) NOT NULL,
  `tipoLicenciaInterna_id` char(10) NOT NULL,
  `conductorLicenciaInterna_fechaV` date DEFAULT NULL,
  `conductorLicenciaInterna_fechaE` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `conductor_licenciaoficial`
--

DROP TABLE IF EXISTS `conductor_licenciaoficial`;
CREATE TABLE `conductor_licenciaoficial` (
  `conductor_id` char(10) NOT NULL,
  `tipoLicenciaOficial_id` char(10) NOT NULL,
  `licenciaOficial_fechaV` date DEFAULT NULL,
  `licenciaOficial_fechaE` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `conductor_proyecto`
--

DROP TABLE IF EXISTS `conductor_proyecto`;
CREATE TABLE `conductor_proyecto` (
  `conductor_id` char(10) NOT NULL,
  `proyecto_id` char(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empresa`
--

DROP TABLE IF EXISTS `empresa`;
CREATE TABLE `empresa` (
  `empresa_id` char(5) NOT NULL,
  `empresa_ruc` char(11) DEFAULT NULL,
  `empresa_nombre` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `empresa`
--

INSERT INTO `empresa` (`empresa_id`, `empresa_ruc`, `empresa_nombre`) VALUES
('E0001', '111', 'Techint');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `evaluacion`
--

DROP TABLE IF EXISTS `evaluacion`;
CREATE TABLE `evaluacion` (
  `evaluacion_id` char(10) NOT NULL,
  `conductor_id` char(10) NOT NULL,
  `evaluador_id` char(10) DEFAULT NULL,
  `evaluacion_examTeorico` double(10,2) DEFAULT NULL,
  `evaluacion_examPractico` double(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `evaluador`
--

DROP TABLE IF EXISTS `evaluador`;
CREATE TABLE `evaluador` (
  `evaluador_id` char(10) NOT NULL,
  `empresa_id` char(5) DEFAULT NULL,
  `evaluador_nombre` varchar(50) DEFAULT NULL,
  `evaluador_apellido` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `evaluador`
--

INSERT INTO `evaluador` (`evaluador_id`, `empresa_id`, `evaluador_nombre`, `evaluador_apellido`) VALUES
('ER00000001', 'E0001', 'Bustamante', 'Brandon');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `grupo_sanguineo`
--

DROP TABLE IF EXISTS `grupo_sanguineo`;
CREATE TABLE `grupo_sanguineo` (
  `grupoSanguineo_id` char(10) NOT NULL,
  `grupoSanguineo_nombre` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `grupo_sanguineo`
--

INSERT INTO `grupo_sanguineo` (`grupoSanguineo_id`, `grupoSanguineo_nombre`) VALUES
('GS00000001', 'A+'),
('GS00000002', 'B');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `infraccion_conductor`
--

DROP TABLE IF EXISTS `infraccion_conductor`;
CREATE TABLE `infraccion_conductor` (
  `infraccion_id` char(10) NOT NULL,
  `conductor_id` char(10) DEFAULT NULL,
  `usuario_id` char(10) DEFAULT NULL,
  `vehiculo_id` char(10) DEFAULT NULL,
  `tipoInfraccion_id` char(10) DEFAULT NULL,
  `infraccion_comentarioInfraccion` varchar(255) DEFAULT NULL,
  `infraccion_comentarioRespuesta` varchar(255) DEFAULT NULL,
  `infraccion_fechaDesvio` date DEFAULT NULL,
  `infraccion_fecharAlerta` date DEFAULT NULL,
  `infraccion_fechaRespuesta` date DEFAULT NULL,
  `infraccion_direccionSuceso` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pais`
--

DROP TABLE IF EXISTS `pais`;
CREATE TABLE `pais` (
  `pais_id` char(10) NOT NULL,
  `pais_iso` char(20) DEFAULT NULL,
  `pais_nombre` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `pais`
--

INSERT INTO `pais` (`pais_id`, `pais_iso`, `pais_nombre`) VALUES
('P000000001', 'PE', 'Peru');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proyecto`
--

DROP TABLE IF EXISTS `proyecto`;
CREATE TABLE `proyecto` (
  `proyecto_id` char(10) NOT NULL,
  `proyecto_cod` char(50) DEFAULT NULL,
  `usuario_id` char(10) NOT NULL,
  `proyecto_nombre` char(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rol`
--

DROP TABLE IF EXISTS `rol`;
CREATE TABLE `rol` (
  `rol_id` char(10) NOT NULL,
  `rol_nombre` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `rol`
--

INSERT INTO `rol` (`rol_id`, `rol_nombre`) VALUES
('R000000001', 'Super Administrador'),
('R000000002', 'Administrador');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_documento_identidad`
--

DROP TABLE IF EXISTS `tipo_documento_identidad`;
CREATE TABLE `tipo_documento_identidad` (
  `tipoDocumentoIdentidad_id` char(10) NOT NULL,
  `tipoDocumentoIdentidad_nombre` char(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `tipo_documento_identidad`
--

INSERT INTO `tipo_documento_identidad` (`tipoDocumentoIdentidad_id`, `tipoDocumentoIdentidad_nombre`) VALUES
('TDI0000001', 'Pasaporte'),
('TDI0000002', 'DNI');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_infraccion`
--

DROP TABLE IF EXISTS `tipo_infraccion`;
CREATE TABLE `tipo_infraccion` (
  `tipoInfraccion_id` char(10) NOT NULL,
  `tipoInfraccion_nombre` varchar(50) DEFAULT NULL,
  `tipoInfraccion_detalle` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_licencia_interna`
--

DROP TABLE IF EXISTS `tipo_licencia_interna`;
CREATE TABLE `tipo_licencia_interna` (
  `tipoLicenciaInterna_id` char(10) NOT NULL,
  `tipoLicenciaInterna_nombre` char(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `tipo_licencia_interna`
--

INSERT INTO `tipo_licencia_interna` (`tipoLicenciaInterna_id`, `tipoLicenciaInterna_nombre`) VALUES
('TLI0000001', 'IPESADO');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_licencia_oficial`
--

DROP TABLE IF EXISTS `tipo_licencia_oficial`;
CREATE TABLE `tipo_licencia_oficial` (
  `tipoLicenciaOficial_id` char(10) NOT NULL,
  `pais_id` char(10) DEFAULT NULL,
  `tipoLicenciaOficial_nombre` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `tipo_licencia_oficial`
--

INSERT INTO `tipo_licencia_oficial` (`tipoLicenciaOficial_id`, `pais_id`, `tipoLicenciaOficial_nombre`) VALUES
('TLO0000001', 'P000000001', 'A1'),
('TLO0000002', 'P000000001', 'A2');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_vehiculo`
--

DROP TABLE IF EXISTS `tipo_vehiculo`;
CREATE TABLE `tipo_vehiculo` (
  `tipoVehiculo_id` char(10) NOT NULL,
  `tipoVehiculo_cod` char(10) DEFAULT NULL,
  `tipoVehiculo_detalle` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

DROP TABLE IF EXISTS `usuario`;
CREATE TABLE `usuario` (
  `usuario_id` char(10) NOT NULL,
  `usuario_apellido` varchar(50) DEFAULT NULL,
  `usuario_nombre` varchar(50) DEFAULT NULL,
  `usuario_email` varchar(50) DEFAULT NULL,
  `usuario_password` varchar(100) DEFAULT NULL,
  `rol_id` char(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`usuario_id`, `usuario_apellido`, `usuario_nombre`, `usuario_email`, `usuario_password`, `rol_id`) VALUES
('U000000001', 'Quea', 'Camilo', 'camilo@gmail.com', '$2a$12$DhSMg65PkF9TLz4msooxfOuxYJEp6geeuVV6g1IS8ndCCtCFe9tGy', 'R000000001'),
('U000000002', 'Quea', 'Carlo', 'carlo@gmail.com', '$2a$12$ChQQX1ZeoIqFJTYyJYDWOegE2aTfF7/HnIj37IlARK2ydBGQpOMkS', 'R000000002');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vehiculo`
--

DROP TABLE IF EXISTS `vehiculo`;
CREATE TABLE `vehiculo` (
  `vehiculo_id` char(10) NOT NULL,
  `tipoVehiculo_id` char(10) DEFAULT NULL,
  `vehiculo_placa` char(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vehiculo_conductor`
--

DROP TABLE IF EXISTS `vehiculo_conductor`;
CREATE TABLE `vehiculo_conductor` (
  `conductor_id` char(10) NOT NULL,
  `vehiculo_id` char(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `conductor`
--
ALTER TABLE `conductor`
  ADD PRIMARY KEY (`conductor_id`),
  ADD KEY `grupoSanguineo_id` (`grupoSanguineo_id`),
  ADD KEY `tipoDocumentoIdentidad_id` (`tipoDocumentoIdentidad_id`),
  ADD KEY `empresa_id` (`empresa_id`),
  ADD KEY `pais_id` (`pais_id`);

--
-- Indices de la tabla `conductor_licenciainterna`
--
ALTER TABLE `conductor_licenciainterna`
  ADD PRIMARY KEY (`conductor_id`,`tipoLicenciaInterna_id`),
  ADD KEY `tipoLicenciaInterna_id` (`tipoLicenciaInterna_id`);

--
-- Indices de la tabla `conductor_licenciaoficial`
--
ALTER TABLE `conductor_licenciaoficial`
  ADD PRIMARY KEY (`conductor_id`,`tipoLicenciaOficial_id`),
  ADD KEY `tipoLicenciaOficial_id` (`tipoLicenciaOficial_id`);

--
-- Indices de la tabla `conductor_proyecto`
--
ALTER TABLE `conductor_proyecto`
  ADD PRIMARY KEY (`conductor_id`,`proyecto_id`),
  ADD KEY `proyecto_id` (`proyecto_id`);

--
-- Indices de la tabla `empresa`
--
ALTER TABLE `empresa`
  ADD PRIMARY KEY (`empresa_id`);

--
-- Indices de la tabla `evaluacion`
--
ALTER TABLE `evaluacion`
  ADD PRIMARY KEY (`evaluacion_id`),
  ADD KEY `conductor_id` (`conductor_id`),
  ADD KEY `evaluador_id` (`evaluador_id`);

--
-- Indices de la tabla `evaluador`
--
ALTER TABLE `evaluador`
  ADD PRIMARY KEY (`evaluador_id`),
  ADD KEY `empresa_id` (`empresa_id`);

--
-- Indices de la tabla `grupo_sanguineo`
--
ALTER TABLE `grupo_sanguineo`
  ADD PRIMARY KEY (`grupoSanguineo_id`);

--
-- Indices de la tabla `infraccion_conductor`
--
ALTER TABLE `infraccion_conductor`
  ADD PRIMARY KEY (`infraccion_id`),
  ADD KEY `usuario_id` (`usuario_id`),
  ADD KEY `vehiculo_id` (`vehiculo_id`),
  ADD KEY `conductor_id` (`conductor_id`),
  ADD KEY `tipoInfraccion_id` (`tipoInfraccion_id`);

--
-- Indices de la tabla `pais`
--
ALTER TABLE `pais`
  ADD PRIMARY KEY (`pais_id`);

--
-- Indices de la tabla `proyecto`
--
ALTER TABLE `proyecto`
  ADD PRIMARY KEY (`proyecto_id`),
  ADD KEY `usuario_id` (`usuario_id`);

--
-- Indices de la tabla `rol`
--
ALTER TABLE `rol`
  ADD PRIMARY KEY (`rol_id`);

--
-- Indices de la tabla `tipo_documento_identidad`
--
ALTER TABLE `tipo_documento_identidad`
  ADD PRIMARY KEY (`tipoDocumentoIdentidad_id`);

--
-- Indices de la tabla `tipo_infraccion`
--
ALTER TABLE `tipo_infraccion`
  ADD PRIMARY KEY (`tipoInfraccion_id`);

--
-- Indices de la tabla `tipo_licencia_interna`
--
ALTER TABLE `tipo_licencia_interna`
  ADD PRIMARY KEY (`tipoLicenciaInterna_id`);

--
-- Indices de la tabla `tipo_licencia_oficial`
--
ALTER TABLE `tipo_licencia_oficial`
  ADD PRIMARY KEY (`tipoLicenciaOficial_id`),
  ADD KEY `pais_id` (`pais_id`);

--
-- Indices de la tabla `tipo_vehiculo`
--
ALTER TABLE `tipo_vehiculo`
  ADD PRIMARY KEY (`tipoVehiculo_id`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`usuario_id`),
  ADD KEY `rol_id` (`rol_id`);

--
-- Indices de la tabla `vehiculo`
--
ALTER TABLE `vehiculo`
  ADD PRIMARY KEY (`vehiculo_id`),
  ADD KEY `tipoVehiculo_id` (`tipoVehiculo_id`);

--
-- Indices de la tabla `vehiculo_conductor`
--
ALTER TABLE `vehiculo_conductor`
  ADD PRIMARY KEY (`conductor_id`,`vehiculo_id`),
  ADD KEY `vehiculo_id` (`vehiculo_id`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `conductor`
--
ALTER TABLE `conductor`
  ADD CONSTRAINT `conductor_ibfk_1` FOREIGN KEY (`grupoSanguineo_id`) REFERENCES `grupo_sanguineo` (`grupoSanguineo_id`),
  ADD CONSTRAINT `conductor_ibfk_2` FOREIGN KEY (`tipoDocumentoIdentidad_id`) REFERENCES `tipo_documento_identidad` (`tipoDocumentoIdentidad_id`),
  ADD CONSTRAINT `conductor_ibfk_3` FOREIGN KEY (`empresa_id`) REFERENCES `empresa` (`empresa_id`),
  ADD CONSTRAINT `conductor_ibfk_4` FOREIGN KEY (`pais_id`) REFERENCES `pais` (`pais_id`);

--
-- Filtros para la tabla `conductor_licenciainterna`
--
ALTER TABLE `conductor_licenciainterna`
  ADD CONSTRAINT `conductor_licenciainterna_ibfk_1` FOREIGN KEY (`conductor_id`) REFERENCES `conductor` (`conductor_id`),
  ADD CONSTRAINT `conductor_licenciainterna_ibfk_2` FOREIGN KEY (`tipoLicenciaInterna_id`) REFERENCES `tipo_licencia_interna` (`tipoLicenciaInterna_id`);

--
-- Filtros para la tabla `conductor_licenciaoficial`
--
ALTER TABLE `conductor_licenciaoficial`
  ADD CONSTRAINT `conductor_licenciaoficial_ibfk_1` FOREIGN KEY (`tipoLicenciaOficial_id`) REFERENCES `tipo_licencia_oficial` (`tipoLicenciaOficial_id`),
  ADD CONSTRAINT `conductor_licenciaoficial_ibfk_2` FOREIGN KEY (`conductor_id`) REFERENCES `conductor` (`conductor_id`);

--
-- Filtros para la tabla `conductor_proyecto`
--
ALTER TABLE `conductor_proyecto`
  ADD CONSTRAINT `conductor_proyecto_ibfk_1` FOREIGN KEY (`conductor_id`) REFERENCES `conductor` (`conductor_id`),
  ADD CONSTRAINT `conductor_proyecto_ibfk_2` FOREIGN KEY (`proyecto_id`) REFERENCES `proyecto` (`proyecto_id`);

--
-- Filtros para la tabla `evaluacion`
--
ALTER TABLE `evaluacion`
  ADD CONSTRAINT `evaluacion_ibfk_1` FOREIGN KEY (`conductor_id`) REFERENCES `conductor` (`conductor_id`),
  ADD CONSTRAINT `evaluacion_ibfk_2` FOREIGN KEY (`evaluador_id`) REFERENCES `evaluador` (`evaluador_id`);

--
-- Filtros para la tabla `evaluador`
--
ALTER TABLE `evaluador`
  ADD CONSTRAINT `evaluador_ibfk_1` FOREIGN KEY (`empresa_id`) REFERENCES `empresa` (`empresa_id`);

--
-- Filtros para la tabla `infraccion_conductor`
--
ALTER TABLE `infraccion_conductor`
  ADD CONSTRAINT `infraccion_conductor_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`usuario_id`),
  ADD CONSTRAINT `infraccion_conductor_ibfk_2` FOREIGN KEY (`vehiculo_id`) REFERENCES `vehiculo` (`vehiculo_id`),
  ADD CONSTRAINT `infraccion_conductor_ibfk_3` FOREIGN KEY (`tipoInfraccion_id`) REFERENCES `tipo_infraccion` (`tipoInfraccion_id`),
  ADD CONSTRAINT `infraccion_conductor_ibfk_4` FOREIGN KEY (`conductor_id`) REFERENCES `conductor` (`conductor_id`),
  ADD CONSTRAINT `infraccion_conductor_ibfk_5` FOREIGN KEY (`tipoInfraccion_id`) REFERENCES `tipo_infraccion` (`tipoInfraccion_id`);

--
-- Filtros para la tabla `proyecto`
--
ALTER TABLE `proyecto`
  ADD CONSTRAINT `proyecto_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`usuario_id`);

--
-- Filtros para la tabla `tipo_licencia_oficial`
--
ALTER TABLE `tipo_licencia_oficial`
  ADD CONSTRAINT `tipo_licencia_oficial_ibfk_1` FOREIGN KEY (`pais_id`) REFERENCES `pais` (`pais_id`);

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`rol_id`) REFERENCES `rol` (`rol_id`);

--
-- Filtros para la tabla `vehiculo`
--
ALTER TABLE `vehiculo`
  ADD CONSTRAINT `vehiculo_ibfk_1` FOREIGN KEY (`tipoVehiculo_id`) REFERENCES `tipo_vehiculo` (`tipoVehiculo_id`);

--
-- Filtros para la tabla `vehiculo_conductor`
--
ALTER TABLE `vehiculo_conductor`
  ADD CONSTRAINT `vehiculo_conductor_ibfk_1` FOREIGN KEY (`conductor_id`) REFERENCES `conductor` (`conductor_id`),
  ADD CONSTRAINT `vehiculo_conductor_ibfk_2` FOREIGN KEY (`vehiculo_id`) REFERENCES `vehiculo` (`vehiculo_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
