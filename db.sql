-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Värd: localhost
-- Skapad: 20 maj 2013 kl 07:33
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
CREATE DATABASE `161957-airport` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `161957-airport`;

-- --------------------------------------------------------

--
-- Tabellstruktur `airplanes`
--

DROP TABLE IF EXISTS `airplanes`;
CREATE TABLE IF NOT EXISTS `airplanes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `model` varchar(150) NOT NULL,
  `seats_total` int(11) NOT NULL,
  `fuel_per_km` int(11) NOT NULL,
  `travel_speed` varchar(250) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Dumpning av Data i tabell `airplanes`
--

INSERT INTO `airplanes` (`id`, `model`, `seats_total`, `fuel_per_km`, `travel_speed`) VALUES
(1, 'Boeing 777', 550, 14, '950'),
(2, 'Boeing 747-8', 470, 17, '1050'),
(3, 'Airbus A320', 150, 6, '900'),
(4, 'Airbus 340-600', 440, 16, '910');

-- --------------------------------------------------------

--
-- Tabellstruktur `airports`
--

DROP TABLE IF EXISTS `airports`;
CREATE TABLE IF NOT EXISTS `airports` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `city` varchar(255) NOT NULL,
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
(9, 'Kairo', 'Cairo Airport'),
(10, 'Stockholm', 'Arlanda Airport'),
(11, 'Köpenhamn', 'Kastrup Airport'),
(12, 'Hawaii', 'Honolulu Airport'),
(13, 'Reykjavik', 'Keflavik Airport'),
(15, 'Frankfurt', 'Frankfurt Airport'),
(17, 'New York', 'JFK Airport'),
(18, 'Växjö', 'Smaland Airport');

-- --------------------------------------------------------

--
-- Tabellstruktur `bookings`
--

DROP TABLE IF EXISTS `bookings`;
CREATE TABLE IF NOT EXISTS `bookings` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `flight_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `confirmed` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=13 ;

--
-- Dumpning av Data i tabell `bookings`
--

INSERT INTO `bookings` (`id`, `flight_id`, `user_id`, `confirmed`) VALUES
(3, 8, 5, 0),
(4, 7, 5, 0),
(5, 8, 5, 0),
(6, 1, 6, 0),
(7, 10, 5, 0),
(8, 7, 5, 0),
(9, 8, 5, 0),
(10, 2, 5, 0),
(11, 2, 5, 0),
(12, 3, 5, 0);

-- --------------------------------------------------------

--
-- Tabellstruktur `flights`
--

DROP TABLE IF EXISTS `flights`;
CREATE TABLE IF NOT EXISTS `flights` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `route1_id` int(11) NOT NULL,
  `route2_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=11 ;

--
-- Dumpning av Data i tabell `flights`
--

INSERT INTO `flights` (`id`, `route1_id`, `route2_id`) VALUES
(1, 1, 0),
(2, 2, 0),
(3, 3, 0),
(4, 4, 0),
(5, 5, 0),
(6, 6, 0),
(7, 7, 0),
(8, 8, 0),
(9, 9, 0),
(10, 10, 0);

-- --------------------------------------------------------

--
-- Tabellstruktur `routes`
--

DROP TABLE IF EXISTS `routes`;
CREATE TABLE IF NOT EXISTS `routes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dep_id` int(11) NOT NULL,
  `dep_date` datetime NOT NULL,
  `dest_id` int(11) NOT NULL,
  `dest_date` datetime NOT NULL,
  `price` int(11) NOT NULL,
  `airplane` int(11) NOT NULL,
  `distance` int(10) NOT NULL,
  `seats_booked` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=22 ;

--
-- Dumpning av Data i tabell `routes`
--

INSERT INTO `routes` (`id`, `dep_id`, `dep_date`, `dest_id`, `dest_date`, `price`, `airplane`, `distance`, `seats_booked`) VALUES
(1, 4, '2013-06-14 08:20:00', 1, '2013-06-14 10:00:00', 500, 1, 1083, 0),
(2, 4, '2013-06-08 12:20:00', 2, '2013-06-08 14:20:00', 800, 1, 1163, 2),
(3, 4, '2013-04-09 03:00:00', 9, '2013-04-09 08:38:00', 1200, 1, 0, 1),
(4, 4, '2013-04-22 14:00:00', 10, '2013-04-22 14:50:00', 300, 2, 0, 470),
(5, 18, '2013-04-25 17:00:00', 10, '2013-04-25 17:55:00', 500, 3, 400, 470),
(6, 18, '2013-04-22 23:10:00', 2, '2013-04-23 01:20:00', 1500, 1, 0, 0),
(7, 4, '2013-04-13 19:00:00', 9, '2013-04-14 03:00:00', 5000, 1, 0, 550),
(8, 4, '2013-06-22 07:00:00', 13, '2013-06-22 10:00:00', 1000, 1, 0, 1),
(9, 13, '2013-06-22 11:00:00', 12, '2013-06-22 15:00:00', 1500, 1, 0, 0),
(10, 4, '2013-04-12 12:00:00', 9, '2013-04-16 21:00:00', 3666, 1, 0, 550),
(11, 4, '2013-05-14 12:00:00', 15, '2013-05-15 12:00:00', 111, 1, 100, 0),
(12, 4, '2013-05-17 12:00:00', 12, '2013-05-18 19:00:00', 8000, 2, 11316, 0),
(13, 3, '2013-05-22 12:00:00', 9, '2013-05-24 12:00:00', 2530, 3, 11333, 0),
(14, 4, '2013-07-15 10:00:00', 10, '2013-07-15 11:00:00', 759, 4, 450, 0),
(15, 4, '2013-07-15 10:10:00', 11, '2013-07-15 10:45:00', 670, 3, 250, 0),
(16, 4, '2013-07-15 09:50:00', 15, '2013-07-15 11:15:00', 922, 3, 700, 0),
(17, 4, '2013-07-15 07:00:00', 13, '2013-07-15 09:50:00', 2210, 3, 3000, 0),
(18, 10, '2013-07-15 13:20:00', 17, '2013-07-15 18:30:00', 2767, 1, 6280, 0),
(19, 11, '2013-07-15 15:10:00', 17, '2013-07-15 19:50:00', 2490, 1, 5500, 0),
(20, 15, '2013-07-15 16:20:00', 17, '2013-07-15 21:00:00', 2668, 1, 6000, 0),
(21, 13, '2013-07-15 10:45:00', 17, '2013-07-15 14:00:00', 1599, 1, 3000, 0);

-- --------------------------------------------------------

--
-- Tabellstruktur `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(250) NOT NULL,
  `phonenumber` varchar(50) NOT NULL,
  `first_name` varchar(250) NOT NULL,
  `last_name` varchar(250) NOT NULL,
  `admin_status` int(11) NOT NULL,
  `password` varchar(250) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=7 ;

--
-- Dumpning av Data i tabell `users`
--

INSERT INTO `users` (`id`, `email`, `phonenumber`, `first_name`, `last_name`, `admin_status`, `password`) VALUES
(2, 'almhultflyg@gmail.com', '032130', 'Giacomo', 'Palma', 1, '1234'),
(4, 'almhultflyg@gmail.com', 'test', 'test', 'test', 1, 'test'),
(5, 'almhultflyg@gmail.com', '12345', 'user', 'user', 0, 'user'),
(6, 'almhultflyg@gmail.com', '020202', 'test', 'test', 0, 'test');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
