-- MySQL dump 10.13  Distrib 8.0.12, for Win64 (x86_64)
--
-- Host: localhost    Database: amflights
-- ------------------------------------------------------
-- Server version	8.0.11

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `seat_booked`
--

DROP TABLE IF EXISTS `seat_booked`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `seat_booked` (
  `seat_booking_id` int(11) NOT NULL AUTO_INCREMENT,
  `seat_id` int(11) NOT NULL,
  `flight_id` int(11) NOT NULL,
  `booking_id` int(11) NOT NULL,
  PRIMARY KEY (`seat_booking_id`),
  KEY `seat_id` (`seat_id`),
  KEY `flight_id` (`flight_id`),
  KEY `booking_id` (`booking_id`),
  CONSTRAINT `seat_booked_ibfk_1` FOREIGN KEY (`seat_id`) REFERENCES `seat` (`seat_id`),
  CONSTRAINT `seat_booked_ibfk_2` FOREIGN KEY (`flight_id`) REFERENCES `flight` (`flight_id`),
  CONSTRAINT `seat_booked_ibfk_3` FOREIGN KEY (`booking_id`) REFERENCES `booking` (`booking_id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seat_booked`
--

LOCK TABLES `seat_booked` WRITE;
/*!40000 ALTER TABLE `seat_booked` DISABLE KEYS */;
INSERT INTO `seat_booked` VALUES (1,67,3,15),(2,68,3,15),(4,69,3,16),(5,70,3,16),(6,71,3,16),(7,67,2,17),(8,68,2,17),(10,69,2,18),(11,70,2,18),(12,67,5,19),(13,68,5,20),(14,69,5,21),(15,67,4,22),(16,68,4,22),(18,69,4,23),(19,70,4,23),(21,71,2,24),(22,72,2,24),(24,70,5,25),(25,71,5,25),(27,71,4,26),(28,72,4,26),(30,72,3,27),(31,73,2,28),(32,74,2,28),(34,73,5,29),(35,74,5,29),(36,75,5,29),(37,76,5,29),(38,67,2,30),(39,68,2,30),(40,69,2,30),(41,70,2,30),(42,71,2,30),(43,72,2,30),(45,67,2,31),(46,68,2,31),(47,69,2,31),(48,70,2,31),(49,71,2,31),(50,72,2,31);
/*!40000 ALTER TABLE `seat_booked` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-08-24 15:13:26
