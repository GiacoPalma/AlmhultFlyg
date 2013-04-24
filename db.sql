-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 24, 2013 at 09:15 AM
-- Server version: 5.5.24-log
-- PHP Version: 5.4.3

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `161957-airport`
--
DROP DATABASE `161957-airport`;
CREATE DATABASE `161957-airport` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `161957-airport`;

-- --------------------------------------------------------

--
-- Table structure for table `airports`
--

CREATE TABLE IF NOT EXISTS `airports` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `city` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=15 ;

--
-- Dumping data for table `airports`
--

INSERT INTO `airports` (`id`, `city`, `name`) VALUES
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
(13, 'Reykjavik', 'Reykjavik_Airport'),
(14, 'test', 'test');

-- --------------------------------------------------------

--
-- Table structure for table `flights`
--

CREATE TABLE IF NOT EXISTS `flights` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dep_id` int(11) NOT NULL,
  `dep_date` date NOT NULL,
  `dest_id` int(11) NOT NULL,
  `dest_date` date NOT NULL,
  `price` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=10 ;

--
-- Dumping data for table `flights`
--

INSERT INTO `flights` (`id`, `dep_id`, `dep_date`, `dest_id`, `dest_date`, `price`) VALUES
(1, 4, '2013-06-14', 1, '2013-06-14', 500),
(2, 4, '2013-06-08', 2, '2013-06-08', 800),
(3, 4, '2013-04-09', 9, '2013-04-09', 1200),
(4, 4, '2013-04-22', 10, '2013-04-22', 300),
(5, 6, '2013-04-25', 10, '2013-04-25', 500),
(6, 7, '2013-04-22', 2, '2013-04-23', 1500),
(7, 4, '2013-04-13', 9, '2013-04-14', 5000),
(8, 4, '2013-06-22', 13, '2013-06-22', 1000),
(9, 13, '2013-06-22', 12, '2013-06-22', 1500);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
