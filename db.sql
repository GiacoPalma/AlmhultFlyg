-- phpMyAdmin SQL Dump
-- version 3.4.10.1deb1
-- http://www.phpmyadmin.net
--
-- Host: 193.17.218.178
-- Generation Time: Apr 23, 2013 at 01:30 PM
-- Server version: 5.5.29
-- PHP Version: 5.3.10-1ubuntu3.4

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `161957-airport`
--
CREATE DATABASE `161957-airport` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `161957-airport`;

-- --------------------------------------------------------

--
-- Table structure for table `Airports`
--

CREATE TABLE IF NOT EXISTS `Airports` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `city` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=14 ;

--
-- Dumping data for table `Airports`
--

INSERT INTO `Airports` (`id`, `city`, `name`) VALUES
(1, 'London', 'London_Airport'),
(2, 'Paris', 'Paris_Airport'),
(3, 'Barcelona', 'Barcelona_Airport'),
(4, 'Älmhult', 'Älmhult_Airport'),
(5, 'Rio de Janiero', 'Rio_Airport'),
(6, 'New York', 'NewYork_Airport'),
(9, 'Kairo', 'Egypt_Airport'),
(10, 'Stockholm', 'Arlanda_Airport'),
(11, 'Köpenhamn', 'Kastrup_Airport'),
(12, 'Hawaii', 'Hawaii_Airport'),
(13, 'Reykjavik', 'Reykjavik_Airport');

-- --------------------------------------------------------

--
-- Table structure for table `Flights`
--

CREATE TABLE IF NOT EXISTS `Flights` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dep_id` int(11) NOT NULL,
  `dep_date` datetime NOT NULL,
  `dest_id` int(11) NOT NULL,
  `dest_date` datetime NOT NULL,
  `price` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=10 ;

--
-- Dumping data for table `Flights`
--

INSERT INTO `Flights` (`id`, `dep_id`, `dep_date`, `dest_id`, `dest_date`, `price`) VALUES
(1, 4, '2013-06-14 08:20:00', 1, '2013-06-14 10:00:00', 500),
(2, 4, '2013-06-08 12:20:00', 2, '2013-06-08 14:20:00', 800),
(3, 4, '2013-04-09 03:00:00', 9, '2013-04-09 08:38:00', 1200),
(4, 4, '2013-04-22 14:00:00', 10, '2013-04-22 14:50:00', 300),
(5, 6, '2013-04-25 17:00:00', 10, '2013-04-25 18:40:00', 500),
(6, 7, '2013-04-22 23:10:00', 2, '2013-04-23 01:20:00', 1500),
(7, 4, '2013-04-13 19:00:00', 9, '2013-04-14 03:00:00', 5000),
(8, 4, '2013-06-22 07:00:00', 13, '2013-06-22 10:00:00', 1000),
(9, 13, '2013-06-22 11:00:00', 12, '2013-06-22 15:00:00', 1500);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
