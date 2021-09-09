-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: librarysystem
-- ------------------------------------------------------
-- Server version	8.0.22

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
-- Table structure for table `author`
--

DROP TABLE IF EXISTS `author`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `author` (
  `id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `author`
--

LOCK TABLES `author` WRITE;
/*!40000 ALTER TABLE `author` DISABLE KEYS */;
INSERT INTO `author` VALUES (1,'还没有描述','1'),(2,'还没有描述',NULL),(3,'还没有描述','谢希仁'),(4,'还没有描述','5'),(5,'还没有描述','Christian Nagel'),(6,'还没有描述','孙艺'),(7,'还没有描述','李文生'),(8,'还没有描述','彭木根、刘雅琼、闫实、赵中原'),(9,'还没有描述','罗鹏飞，张文明'),(10,'还没有描述','白中英'),(11,'还没有描述','马克思主义基本原理概论编写组'),(12,'还没有描述','北京邮电大学数学系'),(13,'还没有描述','June Jamrich Parsons'),(14,'还没有描述','杨立民'),(15,'还没有描述','艾文宝等'),(16,'还没有描述','王保令 韩凌'),(17,'还没有描述','石霞等');
/*!40000 ALTER TABLE `author` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book` (
  `isbn` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `popularity` int DEFAULT NULL,
  `price` double DEFAULT NULL,
  `publisher` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `year` int DEFAULT NULL,
  `author_id` int DEFAULT NULL,
  PRIMARY KEY (`isbn`),
  KEY `FKklnrv3weler2ftkweewlky958` (`author_id`),
  CONSTRAINT `FKklnrv3weler2ftkweewlky958` FOREIGN KEY (`author_id`) REFERENCES `author` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES ('1111111111111',NULL,0,0,'1','1',0,1),('9787030369093',NULL,88888,0,'科学出版社','数字逻辑',0,10),('9787040494792',NULL,99999,0,'高等教育出版社','马克思主义基本原理概论',0,11),('9787111633099',NULL,88889,0,'机械工业出版社','New Perspectives on Computer Concepts 2018（计算机文化英文版第20版）',0,13),('9787121411748','',888888,50,'电子工业出版社','计算机网络',2021,3),('9787302279174',NULL,0,0,'清华大学出版社','随机信号分析与处理',0,9),('9787302441410',NULL,0,0,'清华大学出版社','编译原理与技术',0,7),('9787302522560',NULL,888888,0,'清华大学出版社','C#高级编程',2019,5),('9787521318913',NULL,0,0,'外语教学与研究出版社','现代大学英语3',0,14),('9787563527328',NULL,999999,0,'北京邮电大学出版社','Advanced mathematics(I)',0,15),('9787563552641',NULL,888888,0,'北京邮电大学出版社','Engineering Mathematics',2017,17),('9787563552658',NULL,88888,0,'北京邮电大学出版社','高等数学',0,12),('9787563558476',NULL,888888,0,'北京邮电大学出版社','物联网基础与应用',0,8),('9787563562640',NULL,111111,0,'北京邮电大学出版社','新编英汉互译教程',0,16),('9877121369964',NULL,9999999,0,'电子工业出版社','互联网前端技术开发',0,6);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book_in_library`
--

DROP TABLE IF EXISTS `book_in_library`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book_in_library` (
  `id` int NOT NULL,
  `location` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `book_isbn` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKa8xutauv7e9i3wh8501q5cbff` (`book_isbn`),
  CONSTRAINT `FKa8xutauv7e9i3wh8501q5cbff` FOREIGN KEY (`book_isbn`) REFERENCES `book` (`isbn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book_in_library`
--

LOCK TABLES `book_in_library` WRITE;
/*!40000 ALTER TABLE `book_in_library` DISABLE KEYS */;
INSERT INTO `book_in_library` VALUES (1,'1','已借出','1111111111111'),(8,'2','在库','1111111111111'),(9,'北栋四层000000000x','已借出','9787121411748'),(13,'四楼北区36号','在库','9787302522560'),(14,'四楼北区32号','在库','9877121369964'),(16,'四楼北区32号','在库','9877121369964'),(17,'三楼北区20号','在库','9787302441410'),(18,'南区10','在库','9787563558476'),(19,'南区11','在库','9787302279174'),(20,'南区11','在库','9787302279174'),(21,'南区9','在库','9787030369093'),(22,'南区9','在库','9787030369093'),(23,'南区10','在库','9787040494792'),(24,'北区89','在库','9787563552658'),(25,'南区65','在库','9787111633099'),(26,'南区63','在库','9787111633099'),(27,'南区50','在库','9787521318913'),(28,'南区27','在库','9787563527328'),(29,'南区27','在库','9787563527328'),(30,'南区20','在库','9787563562640'),(31,'北区70','在库','9877121369964'),(32,'南区60','在库','9787563552641');
/*!40000 ALTER TABLE `book_in_library` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `borrow_item`
--

DROP TABLE IF EXISTS `borrow_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `borrow_item` (
  `id` int NOT NULL,
  `borrow_time` datetime DEFAULT NULL,
  `return_time` datetime DEFAULT NULL,
  `book_id` int DEFAULT NULL,
  `reader_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKp2x5g1mpa2gjctgt4av97mw87` (`book_id`),
  KEY `FK1b0wu8lp9ofprtk506swhjk96` (`reader_id`),
  CONSTRAINT `FK1b0wu8lp9ofprtk506swhjk96` FOREIGN KEY (`reader_id`) REFERENCES `reader` (`id`),
  CONSTRAINT `FKp2x5g1mpa2gjctgt4av97mw87` FOREIGN KEY (`book_id`) REFERENCES `book_in_library` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `borrow_item`
--

LOCK TABLES `borrow_item` WRITE;
/*!40000 ALTER TABLE `borrow_item` DISABLE KEYS */;
INSERT INTO `borrow_item` VALUES (2,'2021-09-08 08:24:19','2021-09-09 14:34:49',1,1),(11,'2021-09-09 14:08:49',NULL,9,1),(12,'2021-09-09 14:37:21',NULL,1,1);
/*!40000 ALTER TABLE `borrow_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reader`
--

DROP TABLE IF EXISTS `reader`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reader` (
  `id` int NOT NULL AUTO_INCREMENT,
  `credit` int DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `cart_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkcy1mfctaup55t05rlnqw1e4g` (`cart_id`),
  CONSTRAINT `FKkcy1mfctaup55t05rlnqw1e4g` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reader`
--

LOCK TABLES `reader` WRITE;
/*!40000 ALTER TABLE `reader` DISABLE KEYS */;
INSERT INTO `reader` VALUES (1,100,'11',NULL,'1',NULL),(2,100,'1',NULL,'1',NULL),(3,100,'1',NULL,'1',NULL),(4,100,'1',NULL,'1',NULL),(5,100,'1',NULL,'1',NULL),(6,100,'1',NULL,'1',NULL),(7,100,'2',NULL,'2',NULL),(33,100,'123',NULL,'12312331233',NULL);
/*!40000 ALTER TABLE `reader` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-09-09 16:18:31
