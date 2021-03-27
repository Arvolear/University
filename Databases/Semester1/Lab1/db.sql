-- MySQL dump 10.13  Distrib 8.0.21, for Linux (x86_64)
--
-- Host: localhost    Database: university_task
-- ------------------------------------------------------
-- Server version	8.0.21-0ubuntu0.20.04.4

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `clients`
--

DROP TABLE IF EXISTS `clients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clients` (
  `id` int NOT NULL AUTO_INCREMENT,
  `full_name` varchar(100) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `ref_manager` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `constr_clients_managers` (`ref_manager`),
  CONSTRAINT `constr_clients_managers` FOREIGN KEY (`ref_manager`) REFERENCES `managers` (`card_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clients`
--

LOCK TABLES `clients` WRITE;
/*!40000 ALTER TABLE `clients` DISABLE KEYS */;
INSERT INTO `clients` VALUES (1,'Alfred Hitchcock','+20','alfred@gmail.com',2),(2,'Leonardo DiCaprio','+22','leo@gmail.com',1),(3,'Coco Chanel','+25','coco@gmail.com',3);
/*!40000 ALTER TABLE `clients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `designers`
--

DROP TABLE IF EXISTS `designers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `designers` (
  `card_id` int NOT NULL,
  `full_name` varchar(100) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `salary` float DEFAULT NULL,
  `hire_date` date DEFAULT NULL,
  `office_place` int DEFAULT NULL,
  `occupation` varchar(100) DEFAULT NULL,
  `skill_level` varchar(50) DEFAULT NULL,
  `ref_manager` int NOT NULL,
  `ref_office` int NOT NULL,
  PRIMARY KEY (`card_id`),
  KEY `constr_designers_managers` (`ref_manager`),
  KEY `constr_designers_offices` (`ref_office`),
  CONSTRAINT `constr_designers_managers` FOREIGN KEY (`ref_manager`) REFERENCES `managers` (`card_id`),
  CONSTRAINT `constr_designers_offices` FOREIGN KEY (`ref_office`) REFERENCES `offices` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `designers`
--

LOCK TABLES `designers` WRITE;
/*!40000 ALTER TABLE `designers` DISABLE KEYS */;
INSERT INTO `designers` VALUES (1,'Elon Musk','musk@gmail.com','+11','1976-01-01',10000,'2014-01-01',1,'Abode','Senior',5,4),(2,'Hillary Rodham Clinton','clinton@gmail.com','+12','1950-01-01',1000,'2019-01-01',2,'Blender','Junior',5,4),(3,'George Clooney','clooney@gmail.com','+13','1955-01-01',4000,'2018-04-01',3,'3Ds Max','Middle',3,1),(4,'Tiger Woods','woods@gmail.com','+14','1977-01-01',9000,'2019-04-01',3,'Maya','Senior',4,1),(5,'David Beckham','beckham@gmail.com','+15','1984-01-01',5000,'2020-04-01',5,'Blender','Middle',2,1);
/*!40000 ALTER TABLE `designers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `managers`
--

DROP TABLE IF EXISTS `managers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `managers` (
  `card_id` int NOT NULL,
  `full_name` varchar(100) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `salary` float DEFAULT NULL,
  `hire_date` date DEFAULT NULL,
  `office_place` int DEFAULT NULL,
  `occupation` varchar(100) DEFAULT NULL,
  `skill_level` varchar(50) DEFAULT NULL,
  `ref_supervisor` int DEFAULT NULL,
  `ref_office` int NOT NULL,
  PRIMARY KEY (`card_id`),
  KEY `constr_managers_managers` (`ref_supervisor`),
  KEY `constr_managers_offices` (`ref_office`),
  CONSTRAINT `constr_managers_managers` FOREIGN KEY (`ref_supervisor`) REFERENCES `managers` (`card_id`),
  CONSTRAINT `constr_managers_offices` FOREIGN KEY (`ref_office`) REFERENCES `offices` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `managers`
--

LOCK TABLES `managers` WRITE;
/*!40000 ALTER TABLE `managers` DISABLE KEYS */;
INSERT INTO `managers` VALUES (1,'Donald Trump','donald@gmail.com','+1','1970-01-01',10000,'2020-01-01',1,'Project manager','Senior',NULL,2),(2,'Angelina Jolie','jolie@gmail.com','+2','1980-01-01',5000,'2020-01-01',2,'Project manager','Senior',NULL,2),(3,'Abraham Linkoln','linkoln@gmail.com','+3','1809-01-01',2000,'2020-01-01',3,'Sales manager','Middle',1,2),(4,'Brad Pitt','pitt@gmail.com','+4','1970-02-01',500,'2010-01-01',4,'Sales manager','Junior',3,1),(5,'Will Simth','smith@gmail.com','+5','1960-02-01',750,'2016-01-01',1,'Sales manager','Junior',3,1);
/*!40000 ALTER TABLE `managers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `offices`
--

DROP TABLE IF EXISTS `offices`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `offices` (
  `id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(100) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `max_workers` int DEFAULT NULL,
  `workers` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `offices`
--

LOCK TABLES `offices` WRITE;
/*!40000 ALTER TABLE `offices` DISABLE KEYS */;
INSERT INTO `offices` VALUES (1,'Earth','Green office',20000,7),(2,'Venus','Sweaty office',10000,3),(3,'Mars','Windy office',10000,3),(4,'Pluto','Slippery office',10000,2),(5,'Jupiter','HUGE office',100000,0);
/*!40000 ALTER TABLE `offices` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `programmers`
--

DROP TABLE IF EXISTS `programmers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `programmers` (
  `card_id` int NOT NULL,
  `full_name` varchar(100) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `salary` float DEFAULT NULL,
  `hire_date` date DEFAULT NULL,
  `office_place` int DEFAULT NULL,
  `occupation` varchar(100) DEFAULT NULL,
  `skill_level` varchar(50) DEFAULT NULL,
  `ref_supervisor` int DEFAULT NULL,
  `ref_manager` int NOT NULL,
  `ref_office` int NOT NULL,
  PRIMARY KEY (`card_id`),
  KEY `constr_programmers_managers` (`ref_manager`),
  KEY `constr_programmers_programmers` (`ref_supervisor`),
  KEY `constr_programmers_offices` (`ref_office`),
  CONSTRAINT `constr_programmers_managers` FOREIGN KEY (`ref_manager`) REFERENCES `managers` (`card_id`),
  CONSTRAINT `constr_programmers_offices` FOREIGN KEY (`ref_office`) REFERENCES `offices` (`id`),
  CONSTRAINT `constr_programmers_programmers` FOREIGN KEY (`ref_supervisor`) REFERENCES `programmers` (`card_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `programmers`
--

LOCK TABLES `programmers` WRITE;
/*!40000 ALTER TABLE `programmers` DISABLE KEYS */;
INSERT INTO `programmers` VALUES (1,'Bill Gates','gates@gmail.com','+6','1960-02-01',400,'2018-01-01',2,'C++ programmer','Junior',2,2,1),(2,'Linus Torvalds','linus@gmail.com','+7','1965-02-01',10000,'2009-01-01',3,'C programmer','Senior',NULL,1,1),(3,'Alan Turing','turing@gmail.com','+8','1900-01-01',8000,'2007-01-01',1,'Machine code','Senior',NULL,3,3),(4,'Larry Page','page@gmail.com','+9','1985-01-01',2000,'2007-01-01',4,'Java','Middle',3,4,3),(5,'Mark Zuckerberg','mark@gmail.com','+10','1986-01-01',1000,'2010-01-01',5,'Solidity','Junior',2,1,3);
/*!40000 ALTER TABLE `programmers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project_designers`
--

DROP TABLE IF EXISTS `project_designers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `project_designers` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ref_project` int DEFAULT NULL,
  `ref_designer` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `constr_project_designers_projects` (`ref_project`),
  KEY `constr_project_designers_designers` (`ref_designer`),
  CONSTRAINT `constr_project_designers_designers` FOREIGN KEY (`ref_designer`) REFERENCES `designers` (`card_id`),
  CONSTRAINT `constr_project_designers_projects` FOREIGN KEY (`ref_project`) REFERENCES `projects` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project_designers`
--

LOCK TABLES `project_designers` WRITE;
/*!40000 ALTER TABLE `project_designers` DISABLE KEYS */;
INSERT INTO `project_designers` VALUES (1,5,2),(2,5,3),(3,1,1),(4,2,2),(5,3,4);
/*!40000 ALTER TABLE `project_designers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project_managers`
--

DROP TABLE IF EXISTS `project_managers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `project_managers` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ref_project` int DEFAULT NULL,
  `ref_manager` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `constr_project_managers_projects` (`ref_project`),
  KEY `constr_project_managers_managers` (`ref_manager`),
  CONSTRAINT `constr_project_managers_managers` FOREIGN KEY (`ref_manager`) REFERENCES `managers` (`card_id`),
  CONSTRAINT `constr_project_managers_projects` FOREIGN KEY (`ref_project`) REFERENCES `projects` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project_managers`
--

LOCK TABLES `project_managers` WRITE;
/*!40000 ALTER TABLE `project_managers` DISABLE KEYS */;
INSERT INTO `project_managers` VALUES (1,1,2),(2,2,1),(3,3,3),(4,3,4),(5,4,5);
/*!40000 ALTER TABLE `project_managers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project_programmers`
--

DROP TABLE IF EXISTS `project_programmers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `project_programmers` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ref_project` int DEFAULT NULL,
  `ref_programmer` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `constr_project_programmers_projects` (`ref_project`),
  KEY `constr_project_programmers_programmers` (`ref_programmer`),
  CONSTRAINT `constr_project_programmers_programmers` FOREIGN KEY (`ref_programmer`) REFERENCES `programmers` (`card_id`),
  CONSTRAINT `constr_project_programmers_projects` FOREIGN KEY (`ref_project`) REFERENCES `projects` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project_programmers`
--

LOCK TABLES `project_programmers` WRITE;
/*!40000 ALTER TABLE `project_programmers` DISABLE KEYS */;
INSERT INTO `project_programmers` VALUES (1,1,1),(2,1,2),(3,2,3),(4,4,5),(5,5,4),(6,2,2);
/*!40000 ALTER TABLE `project_programmers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `projects`
--

DROP TABLE IF EXISTS `projects`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `projects` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `client_payed` float DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `finish_date` date DEFAULT NULL,
  `is_finished` tinyint(1) DEFAULT NULL,
  `ref_client` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `constr_projects_clients` (`ref_client`),
  CONSTRAINT `constr_projects_clients` FOREIGN KEY (`ref_client`) REFERENCES `clients` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `projects`
--

LOCK TABLES `projects` WRITE;
/*!40000 ALTER TABLE `projects` DISABLE KEYS */;
INSERT INTO `projects` VALUES (1,'Journey to Mars',1000000,0,'2019-09-10','2025-09-10',0,1),(2,'Cool down the Sun',10000000,0,'2020-09-10','2050-09-10',0,2),(3,'2+2=5',10000,0,'2020-10-10','2020-11-10',0,3),(4,'Rialbit.com',50000,0,'2020-10-01','2021-10-01',0,1),(5,'Google.com',1000000,0,'2000-04-01',NULL,0,3);
/*!40000 ALTER TABLE `projects` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-09-28 21:59:46
