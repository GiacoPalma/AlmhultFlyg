-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Värd: localhost
-- Skapad: 30 apr 2013 kl 08:53
-- Serverversion: 5.5.24-log
-- PHP-version: 5.3.13

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Databas: `161957-airport`
--
DROP DATABASE `161957-airport`;
CREATE DATABASE `161957-airport` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `161957-airport`;

-- --------------------------------------------------------

--
-- Tabellstruktur `airports`
--

CREATE TABLE IF NOT EXISTS `airports` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `city` text CHARACTER SET utf8 COLLATE utf8_swedish_ci,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=19 ;

--
-- Dumpning av Data i tabell `airports`
--

INSERT INTO `airports` (`id`, `city`, `name`) VALUES
(1, 'London', 'Heathrow Airport'),
(2, 'Paris', 'Charles de Gaulle Airport'),
(3, 'Barcelona', 'El Prat Airport'),
(4, 'Älmhult', 'IKEA Airport'),
(5, 'Rio de Janiero', 'Galeão Airport'),
(6, 'Kairo', 'Cairo Airport'),
(7, 'Stockholm', 'Arlanda Airport'),
(8, 'Köpenhamn', 'Kastrup Airport'),
(9, 'Hawaii', 'Honolulu Airport'),
(10, 'Reykjavik', 'Keflavik Airport'),
(11, 'Frankfurt', 'Frankfurt Airport'),
(12, 'New York', 'JFK Airport'),
(13, 'Växjö', 'Smaland Airport');

-- --------------------------------------------------------

--
-- Tabellstruktur `flights`
--

CREATE TABLE IF NOT EXISTS `flights` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dep_id` int(11) NOT NULL,
  `dep_date` datetime NOT NULL,
  `dest_id` int(11) NOT NULL,
  `dest_date` datetime NOT NULL,
  `price` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=11 ;

--
-- Dumpning av Data i tabell `flights`
--

INSERT INTO `flights` (`id`, `dep_id`, `dep_date`, `dest_id`, `dest_date`, `price`) VALUES
(1, 4, '2013-06-14 08:20:00', 1, '2013-06-14 10:00:00', 500),
(2, 4, '2013-06-08 12:20:00', 2, '2013-06-08 14:20:00', 800),
(3, 4, '2013-04-09 03:00:00', 9, '2013-04-09 08:38:00', 1200),
(4, 4, '2013-04-22 14:00:00', 10, '2013-04-22 14:50:00', 300),
(5, 6, '2013-04-25 17:00:00', 10, '2013-04-25 18:40:00', 500),
(6, 7, '2013-04-22 23:10:00', 2, '2013-04-23 01:20:00', 1500),
(7, 4, '2013-04-13 19:00:00', 9, '2013-04-14 03:00:00', 5000),
(8, 4, '2013-06-22 07:00:00', 13, '2013-06-22 10:00:00', 1000),
(9, 13, '2013-06-22 11:00:00', 12, '2013-06-22 15:00:00', 1500),
(10, 4, '2013-04-18 12:00:00', 3, '2013-04-18 15:00:00', 1000);

-- --------------------------------------------------------

--
-- Tabellstruktur `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(250) NOT NULL,
  `phonenumber` varchar(50) NOT NULL,
  `first_name` varchar(250) NOT NULL,
  `last_name` varchar(250) NOT NULL,
  `admin_status` int(11) NOT NULL,
  `password` varchar(250) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Dumpning av Data i tabell `users`
--

INSERT INTO `users` (`id`, `email`, `phonenumber`, `first_name`, `last_name`, `admin_status`, `password`) VALUES
(1, 'te', 'sda', 'Giaco', 'Palma', 1, 'ssss'),
(2, 'gipa', '00202', 'test1', 'test2', 0, '1234'),
(3, 'hej', '112', 'Lukas', 'Andersson', 0, 'hej');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
