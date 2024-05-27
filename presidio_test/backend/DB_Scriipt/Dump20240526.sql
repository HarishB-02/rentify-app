-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: presidio
-- ------------------------------------------------------
-- Server version	8.0.37

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `like_count`
--

DROP TABLE IF EXISTS `like_count`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `like_count` (
  `id` int NOT NULL AUTO_INCREMENT,
  `like_count` int NOT NULL,
  `property_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `like_count`
--

LOCK TABLES `like_count` WRITE;
/*!40000 ALTER TABLE `like_count` DISABLE KEYS */;
INSERT INTO `like_count` VALUES (4,0,9,5);
/*!40000 ALTER TABLE `like_count` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `property`
--

DROP TABLE IF EXISTS `property`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `property` (
  `property_id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `bathrooms` int DEFAULT NULL,
  `bedrooms` int DEFAULT NULL,
  `nearby_landmark` varchar(255) DEFAULT NULL,
  `place` varchar(255) DEFAULT NULL,
  `seller_id` int DEFAULT NULL,
  `property_name` varchar(255) DEFAULT NULL,
  `property_rent` varchar(255) DEFAULT NULL,
  `property_type` varchar(255) DEFAULT NULL,
  `sq_ft` varchar(255) DEFAULT NULL,
  `likes` int NOT NULL,
  PRIMARY KEY (`property_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `property`
--

LOCK TABLES `property` WRITE;
/*!40000 ALTER TABLE `property` DISABLE KEYS */;
INSERT INTO `property` VALUES (9,'Namakkal',1,1,'PKS apartment',NULL,6,'House 1','100000',NULL,'10000',0),(10,'Namakkal',2,2,'Four roads',NULL,6,'House 2','20000',NULL,'2000',0),(11,'Namakkal',3,3,'Kpatty',NULL,6,'House 3','30000',NULL,'3000',0),(12,'Namakkal',4,4,'PKS apartment',NULL,6,'House 4','40000',NULL,'4000',0),(13,'Namakkal',5,5,'PKS apartment',NULL,6,'House 5','50000',NULL,'5000',0),(14,'Namakkal',6,6,'KS Theatre',NULL,6,'House 6','60000',NULL,'6000',0),(15,'Namakkal',7,7,'Salem road',NULL,6,'House 7','70000',NULL,'7000',0),(16,'Namakkal',8,8,'GH',NULL,6,'House 8','80000',NULL,'8000',0),(17,'Namakkal',4,4,'Five roads',NULL,9,'New house','40000',NULL,'7000',0);
/*!40000 ALTER TABLE `property` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `buyer` bit(1) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `seller` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,_binary '','harish@gmail.com','Harish',NULL,'54321','1234567890',_binary '\0'),(4,_binary '','harish1223@gmail.com','Harish','B','54321','1234567890',_binary '\0'),(5,_binary '','123@gmail.com','Harish','B','54321','234567890',_binary '\0'),(6,_binary '\0','rajharish.cse@gmail.com','Harish','B','rooban','9500833898',_binary ''),(7,_binary '','99rooban@gmail.com','Rooban','Raj','rooban','9500990196',_binary '\0'),(8,_binary '','vinoth@gmail.com','Vinoth','Kumar','vinoth','9988990077',_binary '\0'),(9,_binary '\0','jamuna@gmail.com','Jamuna','Rani','jamuna','8899889988',_binary '');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-26 17:13:18
