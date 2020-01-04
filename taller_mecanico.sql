-- phpMyAdmin SQL Dump
-- version 3.3.9
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 04-12-2019 a las 21:47:18
-- Versión del servidor: 5.5.8
-- Versión de PHP: 5.3.5

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `taller_mecanico`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reparaciones`
--

CREATE TABLE IF NOT EXISTS `reparaciones` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fecha` date NOT NULL,
  `tipos_id` int(11) NOT NULL,
  `descripcion` text,
  `costo` int(11) NOT NULL,
  `vehiculos_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=23 ;

--
-- Volcar la base de datos para la tabla `reparaciones`
--

INSERT INTO `reparaciones` (`id`, `fecha`, `tipos_id`, `descripcion`, `costo`, `vehiculos_id`) VALUES
(5, '2019-12-06', 5, '21321', 131231, 45),
(6, '2019-12-26', 6, '', 1322132132, 54),
(7, '2019-12-08', 7, '', 1212121212, 54),
(8, '2019-12-14', 7, '', 4242424, 54),
(9, '2019-12-14', 6, '', 1500, 45),
(10, '2019-12-31', 5, 'Las cosas (y las parejas) te durarán más si las cuidas y las tratas bien. Y los coches no son la excepción que confirma la regla, ni mucho menos. Puede que tener el coche descuidado, o no prestar atención a cómo funcionan determinados componentes pueda parecer un asunto trivial, pero en absoluto lo es. Algo que parece inofensivo puede convertirse, con el paso del tiempo, en un verdadero problema.', 150000, 45),
(11, '2019-12-06', 6, '13213213', 12323, 45),
(12, '2019-12-14', 6, '3123321', 12321132, 54),
(13, '2019-11-29', 7, '21323', 3321232, 45),
(15, '2019-12-12', 5, 'wwerwere', 121212, 56),
(18, '2019-12-19', 7, 'problemas en el escape con ruidos', 1400078, 57),
(20, '2019-11-30', 5, '1111111', 11111, 58),
(21, '2019-12-06', 7, 'ver descripcion', 50000, 57);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipos`
--

CREATE TABLE IF NOT EXISTS `tipos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Volcar la base de datos para la tabla `tipos`
--

INSERT INTO `tipos` (`id`, `nombre`) VALUES
(1, 'auto'),
(2, 'camion'),
(3, 'camioneta'),
(4, 'moto'),
(5, 'Mantencion'),
(6, 'Reparacion Mayor'),
(7, 'Reparacion Menor');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE IF NOT EXISTS `usuarios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(200) NOT NULL,
  `pass` varchar(200) NOT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Volcar la base de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `email`, `pass`, `nombre`) VALUES
(1, 'a@a.cl', '123', 'adrian h');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vehiculos`
--

CREATE TABLE IF NOT EXISTS `vehiculos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tipos_id` int(11) NOT NULL,
  `num_chasis` int(11) NOT NULL,
  `marca` varchar(60) NOT NULL,
  `modelo` varchar(60) NOT NULL,
  `color` varchar(60) NOT NULL,
  `patente` varchar(6) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=62 ;

--
-- Volcar la base de datos para la tabla `vehiculos`
--

INSERT INTO `vehiculos` (`id`, `tipos_id`, `num_chasis`, `marca`, `modelo`, `color`, `patente`) VALUES
(42, 2, 12321, '2343244', '3213', 'rojo', 'CWERC3'),
(49, 1, 423434, 'merceedez', '324324', 'rojo', '423432'),
(50, 3, 24324, '32432', '4432423', 'azul', '324324'),
(51, 3, 324324324, '32432432', '324324', 'rojo', 'EFSFSF'),
(53, 3, 324324, '3432432123312', '34234', 'azul', '324342'),
(55, 2, 1234, 'chev', 'tou', 'negro', 'ADVR34'),
(57, 1, 10101, 'BMW', 'Serie 1', 'azul', 'KDRS01'),
(58, 1, 1323, '323123', '32132', 'amarillo', '111111'),
(59, 2, 12212, 'mercedez', 'a2566', 'rojo', 'SRC273'),
(60, 2, 5454, '54545', '5454545', 'rojo', 'ADRI12');
