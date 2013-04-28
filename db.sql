# ************************************************************
# Sequel Pro SQL dump
# Version 4004
#
# http://www.sequelpro.com/
# http://code.google.com/p/sequel-pro/
#
# Värd: 127.0.0.1 (MySQL 5.5.25)
# Databas: 161957-airport
# Genereringstid: 2013-04-28 17:18:29 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Tabelldump airports
# ------------------------------------------------------------

DROP TABLE IF EXISTS `airports`;

CREATE TABLE `airports` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `city` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `airports` WRITE;
/*!40000 ALTER TABLE `airports` DISABLE KEYS */;

INSERT INTO `airports` (`id`, `city`, `name`)
VALUES
	(1,'London','London_Airport'),
	(2,'Paris','Paris_Airport'),
	(3,'Barcelona','Barcelona_Airport'),
	(4,'Älmhult','Älmhult_Airport'),
	(5,'Rio de Janiero','Rio_Airport'),
	(6,'New York','NewYork_Airport'),
	(9,'Kairo','Egypt_Airport'),
	(10,'Stockholm','Arlanda_Airport'),
	(11,'Köpenhamn','Kastrup_Airport'),
	(12,'Hawaii','Hawaii_Airport'),
	(13,'Reykjavik','Reykjavik_Airport'),
	(14,'test','test');

/*!40000 ALTER TABLE `airports` ENABLE KEYS */;
UNLOCK TABLES;


# Tabelldump flights
# ------------------------------------------------------------

DROP TABLE IF EXISTS `flights`;

CREATE TABLE `flights` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dep_id` int(11) NOT NULL,
  `dep_date` datetime NOT NULL,
  `dest_id` int(11) NOT NULL,
  `dest_date` datetime NOT NULL,
  `price` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `flights` WRITE;
/*!40000 ALTER TABLE `flights` DISABLE KEYS */;

INSERT INTO `flights` (`id`, `dep_id`, `dep_date`, `dest_id`, `dest_date`, `price`)
VALUES
	(1,4,'2013-06-14 00:00:00',1,'2013-06-14 00:00:00',500),
	(2,4,'2013-06-08 00:00:00',2,'2013-06-08 00:00:00',800),
	(3,4,'2013-04-09 00:00:00',9,'2013-04-09 00:00:00',1200),
	(4,4,'2013-04-22 00:00:00',10,'2013-04-22 00:00:00',300),
	(5,6,'2013-04-25 00:00:00',10,'2013-04-25 00:00:00',500),
	(6,7,'2013-04-22 00:00:00',2,'2013-04-23 00:00:00',1500),
	(7,4,'2013-04-13 00:00:00',9,'2013-04-14 00:00:00',5000),
	(8,4,'2013-06-22 00:00:00',13,'2013-06-22 00:00:00',1000),
	(9,13,'2013-06-22 00:00:00',12,'2013-06-22 00:00:00',1500),
	(32,4,'2013-04-18 12:00:00',10,'2013-04-28 12:00:00',555),
	(33,4,'2013-04-11 12:00:00',6,'2013-04-28 12:00:00',2000);

/*!40000 ALTER TABLE `flights` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
