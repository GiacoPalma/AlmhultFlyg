# ************************************************************
# Sequel Pro SQL dump
# Version 4096
#
# http://www.sequelpro.com/
# http://code.google.com/p/sequel-pro/
#
# Värd: 127.0.0.1 (MySQL 5.5.25)
# Databas: 161957-airport
# Genereringstid: 2013-05-15 19:00:52 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Tabelldump airplanes
# ------------------------------------------------------------

DROP TABLE IF EXISTS `airplanes`;

CREATE TABLE `airplanes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `model` varchar(150) NOT NULL,
  `seats_total` int(11) NOT NULL,
  `fuel_per_km` int(11) NOT NULL,
  `travel_speed` varchar(250) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `airplanes` WRITE;
/*!40000 ALTER TABLE `airplanes` DISABLE KEYS */;

INSERT INTO `airplanes` (`id`, `model`, `seats_total`, `fuel_per_km`, `travel_speed`)
VALUES
	(1,'Boeing 777',550,14,'950'),
	(2,'Boeing 747-8',470,17,'1050'),
	(3,'Airbus A320',150,6,'900'),
	(4,'Airbus 340-600',440,16,'910');

/*!40000 ALTER TABLE `airplanes` ENABLE KEYS */;
UNLOCK TABLES;


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
	(15,'Frankfurt','Frankfurt_Airport'),
	(16,'Stockholm','Arlanda_Airport'),
	(17,'New York','JFK Airport');

/*!40000 ALTER TABLE `airports` ENABLE KEYS */;
UNLOCK TABLES;


# Tabelldump bookings
# ------------------------------------------------------------

DROP TABLE IF EXISTS `bookings`;

CREATE TABLE `bookings` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `flight_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `confirmed` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `bookings` WRITE;
/*!40000 ALTER TABLE `bookings` DISABLE KEYS */;

INSERT INTO `bookings` (`id`, `flight_id`, `user_id`, `confirmed`)
VALUES
	(3,8,5,0),
	(4,7,5,0),
	(5,8,5,0),
	(6,1,6,0),
	(7,10,5,0),
	(8,7,5,0),
	(9,8,5,0),
	(10,2,5,0),
	(11,2,5,0),
	(12,3,5,0);

/*!40000 ALTER TABLE `bookings` ENABLE KEYS */;
UNLOCK TABLES;


# Tabelldump flights
# ------------------------------------------------------------

DROP TABLE IF EXISTS `flights`;

CREATE TABLE `flights` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `route1_id` int(11) NOT NULL,
  `route2_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `flights` WRITE;
/*!40000 ALTER TABLE `flights` DISABLE KEYS */;

INSERT INTO `flights` (`id`, `route1_id`, `route2_id`)
VALUES
	(1,1,0),
	(2,2,0),
	(3,3,0),
	(4,4,0),
	(5,5,0),
	(6,6,0),
	(7,7,0),
	(8,8,0),
	(9,9,0),
	(10,10,0);

/*!40000 ALTER TABLE `flights` ENABLE KEYS */;
UNLOCK TABLES;


# Tabelldump routes
# ------------------------------------------------------------

DROP TABLE IF EXISTS `routes`;

CREATE TABLE `routes` (
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `routes` WRITE;
/*!40000 ALTER TABLE `routes` DISABLE KEYS */;

INSERT INTO `routes` (`id`, `dep_id`, `dep_date`, `dest_id`, `dest_date`, `price`, `airplane`, `distance`, `seats_booked`)
VALUES
	(1,4,'2013-06-14 08:20:00',1,'2013-06-14 10:00:00',500,1,1083,0),
	(2,4,'2013-06-08 12:20:00',2,'2013-06-08 14:20:00',800,1,1163,2),
	(3,4,'2013-04-09 03:00:00',9,'2013-04-09 08:38:00',1200,1,0,1),
	(4,4,'2013-04-22 14:00:00',10,'2013-04-22 14:50:00',300,2,0,470),
	(5,6,'2013-04-25 17:00:00',10,'2013-04-25 18:40:00',500,2,0,470),
	(6,7,'2013-04-22 23:10:00',2,'2013-04-23 01:20:00',1500,1,0,0),
	(7,4,'2013-04-13 19:00:00',9,'2013-04-14 03:00:00',5000,1,0,550),
	(8,4,'2013-06-22 07:00:00',13,'2013-06-22 10:00:00',1000,1,0,1),
	(9,13,'2013-06-22 11:00:00',12,'2013-06-22 15:00:00',1500,1,0,0),
	(10,4,'2013-04-12 12:00:00',9,'2013-04-16 21:00:00',3666,1,0,550),
	(11,4,'2013-05-14 12:00:00',15,'2013-05-15 12:00:00',111,1,100,0),
	(12,4,'2013-05-17 12:00:00',12,'2013-05-18 19:00:00',8000,2,11316,0),
	(13,3,'2013-05-22 12:00:00',9,'2013-05-24 12:00:00',2530,3,11333,0);

/*!40000 ALTER TABLE `routes` ENABLE KEYS */;
UNLOCK TABLES;


# Tabelldump users
# ------------------------------------------------------------

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(250) NOT NULL,
  `phonenumber` varchar(50) NOT NULL,
  `first_name` varchar(250) NOT NULL,
  `last_name` varchar(250) NOT NULL,
  `admin_status` int(11) NOT NULL,
  `password` varchar(250) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;

INSERT INTO `users` (`id`, `email`, `phonenumber`, `first_name`, `last_name`, `admin_status`, `password`)
VALUES
	(2,'almhultflyg@gmail.com','032130','Giacomo','Palma',1,'1234'),
	(4,'almhultflyg@gmail.com','test','test','test',1,'test'),
	(5,'almhultflyg@gmail.com','12345','user','user',0,'user'),
	(6,'almhultflyg@gmail.com','020202','test','test',0,'test');

/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
