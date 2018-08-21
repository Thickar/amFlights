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
-- Dumping routines for database 'amflights'
--
/*!50003 DROP FUNCTION IF EXISTS `priceSurging` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `priceSurging`(flightId INT,seatType INT) RETURNS int(11)
    READS SQL DATA
    DETERMINISTIC
BEGIN
DECLARE surgedPrice,currentSeatPrice INT;

SELECT 
    (CASE seattype
        WHEN 0 THEN business_seat_price
        WHEN 1 THEN economy_seat_price
    END)
INTO currentSeatPrice FROM
    flight
WHERE
    flight_id = flightId;


IF seatType = 0 THEN

SET surgedPrice = currentSeatPrice + 200; 

ELSE 

SET surgedPrice = currentSeatPrice + 100;

END IF;



UPDATE flight 
SET 
    business_seat_price = CASE
        WHEN seattype = 0 THEN surgedPrice
        ELSE business_seat_price
    END,
    economy_seat_price = CASE
        WHEN seattype = 1 THEN surgedPrice
        ELSE economy_seat_price
    END
WHERE
    flight_id = flightId;

RETURN surgedPrice;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `booktickets` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `booktickets`(flightid       INT, 
                                seattype       INT, 
                                seatcount      INT, 
                                ismealrequired INT,
                                OUT bookingId INT)
begin 
  DECLARE seatprice, bookingcharges INT; 

SELECT  (CASE seattype
    WHEN 0 THEN business_seat_price
        WHEN 1 THEN economy_seat_price END)
  INTO seatprice
  FROM   flight 
  WHERE  flight_id = flightid;
  
SELECT seatprice;
  
  SET bookingcharges = seatcount * seatprice; 

  INSERT INTO `amflights`.`booking` 
              (`flight_id`, 
               `booking_charges`, 
               `is_meals_required`) 
  VALUES      (flightid, 
               bookingcharges, 
               ismealrequired); 

  SET bookingid = LAST_INSERT_ID(); 

  INSERT INTO `amflights`.`seat_booked` 
              (`seat_id`, 
               `flight_id`, 
               `booking_id`) 
  SELECT seat_id, 
         flightid, 
         bookingid 
  FROM   seat 
  WHERE  seat_type = seattype 
         AND seat_id NOT IN (SELECT s.seat_id 
                             FROM   booking b 
                                    INNER JOIN seat_booked s 
                                            ON b.booking_id = s.booking_id 
                                               AND b.is_cancelled = false 
                             WHERE  b.flight_id = flightid) 
  LIMIT  seatcount; 
  
  select priceSurging(flightid, seattype);
    
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-08-21  7:22:11
