-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: javawork
-- ------------------------------------------------------
-- Server version	8.0.33

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
-- Table structure for table `j_blog`
--

DROP TABLE IF EXISTS `j_blog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `j_blog` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `content` text,
  `description` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  `published` tinyint(1) DEFAULT NULL COMMENT 'tiniint来表示bool',
  `views` int DEFAULT NULL COMMENT '观看次数',
  `comment_count` int DEFAULT NULL,
  `type_id` int DEFAULT NULL COMMENT '博客类型标识符',
  `user_id` int DEFAULT NULL COMMENT '博客所属用户标识',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '作者用户名',
  `class_id` int DEFAULT NULL COMMENT '博客目录，用户管理（只有一个）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `j_blog`
--

LOCK TABLES `j_blog` WRITE;
/*!40000 ALTER TABLE `j_blog` DISABLE KEYS */;
INSERT INTO `j_blog` VALUES (1,'myblog3','# 仓库地址（Java）\n---\n[github location](https://github.com/hyn1426536274/javawork)','Java 仓库地址','2023-11-17 11:03:17','2023-11-20 00:29:09',1,0,0,1,3,'aaa',18),(2,'myblog2','# myblog','description','2023-11-17 11:07:55','2023-11-17 11:07:55',0,0,0,0,3,'aaa',0),(3,'myblog3','# 我的示例博客\r\n\r\n欢迎来到我的示例博客！这里是一个使用 Markdown 编写的简单示例。\r\n\r\n## 介绍\r\n\r\n我是一个开发者，喜欢编程、技术和分享。这个博客将包含一些有趣的内容，技术教程和个人见解。\r\n\r\n## 技术教程\r\n\r\n### 如何使用 Markdown\r\n\r\nMarkdown 是一种轻量级标记语言，用于简化将文本转换为 HTML。以下是一个简单的 Markdown 示例：\r\n\r\n```markdown\r\n# 标题\r\n## 二级标题\r\n### 三级标题\r\n\r\n- 列表项 1\r\n- 列表项 2\r\n- 列表项 3\r\n\r\n**加粗文本**\r\n\r\n*斜体文本*\r\n\r\n[链接](https://www.example.com)  \r\n\r\n','description','2023-11-17 11:12:13','2023-11-17 11:12:13',1,0,0,0,5,'ccc',0),(4,'myblog3','# myblog','description','2023-11-17 16:14:48','2023-11-17 16:14:48',1,0,0,0,6,'user2',0),(5,'myblog2','# myblog','description','2023-11-17 16:14:57','2023-11-17 16:14:57',1,0,0,0,6,'user2',0),(6,'myblog1','# myblog','description','2023-11-18 11:38:42','2023-11-18 11:38:42',1,0,0,0,3,'aaa',0),(7,'myblog2','aaaaa','22222','2023-11-18 19:07:45','2023-11-18 21:58:29',1,0,0,0,13,'bbb',0),(8,'myblog1','## headers\n\n* list1\n* list2\n\n**keypoint**\n','a new blog','2023-11-18 20:57:16','2023-11-18 20:57:16',1,0,0,0,13,'bbb',0),(9,'myblog','## Coding Languages\n\n---\n\ncontent\n\n* C\n* Cpp\n* python\n* Nodejs\n---','some computer languages','2023-11-18 22:09:21','2023-11-18 22:09:21',1,0,0,0,13,'bbb',0),(10,'myblog3','## Coding Languages(2)\n\n---\n\ncontent\n\n* Java\n* Php\n\n---','some computer languages','2023-11-18 22:10:15','2023-11-18 22:10:15',1,0,0,0,13,'bbb',0),(11,'myblog3','### New Blog','description','2023-11-19 09:41:17','2023-11-19 09:41:17',1,0,0,0,7,'aaaw',0),(12,'myblog2','## C++简介\n---\n\n* C++ is a powerful, general-purpose programming language with a focus on performance and efficiency, commonly used in game development, system programming, and high-performance applications.\n\n* C++ 是一种功能强大的通用编程语言，注重性能和效率，常用于游戏开发、系统编程和高性能应用。\n\n\n','C++ 简介','2023-11-19 09:47:23','2023-11-19 09:47:23',1,0,0,0,7,'aaaw',0),(14,'myblog','### test Class','','2023-11-19 23:40:25','2023-11-19 23:40:25',1,0,0,0,3,'aaa',18);
/*!40000 ALTER TABLE `j_blog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `j_class`
--

DROP TABLE IF EXISTS `j_class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `j_class` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户自定义的文章分类',
  `user_id` int DEFAULT NULL COMMENT '该目录的所属用户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `j_class`
--

LOCK TABLES `j_class` WRITE;
/*!40000 ALTER TABLE `j_class` DISABLE KEYS */;
INSERT INTO `j_class` VALUES (9,'game',3),(18,'film2',3),(22,'file',13);
/*!40000 ALTER TABLE `j_class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `j_type`
--

DROP TABLE IF EXISTS `j_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `j_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '文章类别，系统定义的',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `j_type`
--

LOCK TABLES `j_type` WRITE;
/*!40000 ALTER TABLE `j_type` DISABLE KEYS */;
INSERT INTO `j_type` VALUES (1,'java'),(2,'cpp'),(3,'python'),(4,'html');
/*!40000 ALTER TABLE `j_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL COMMENT '个人描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','111111',NULL,NULL),(2,'user1','123',NULL,NULL),(3,'aaa','123','1426536274@qq.com','Myname is aaa'),(4,'aab','123',NULL,NULL),(5,'ccc','123','1426536274@qq.com',NULL),(6,'user2','123','1426536274@qq.com',NULL),(7,'aaaw','123','1426536274@qq.com',NULL),(8,'aaaq','123','1426536274@qq.com',NULL),(9,'aaa1','123','1426536274@qq.com',NULL),(10,'aaa2','123','1426536274@qq.com',NULL),(11,'aaa4','123','1426536274@qq.com',NULL),(12,'qqq','123','1426536274@qq.com',NULL),(13,'bbb','123','1426536274@qq.com',NULL),(14,'bbb2','123','1426536274@qq.com',NULL),(15,'testuser','123','1426536274@qq.com',NULL),(16,'bbb1','123','1426536274@qq.com',NULL),(17,'bbb3','123','1426536274@qq.com',NULL);
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

-- Dump completed on 2023-11-27 21:45:35
