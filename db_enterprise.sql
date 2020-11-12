/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.28-log : Database - db_enterprise
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_enterprise` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `db_enterprise`;

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Pname` char(30) NOT NULL,
  `Ptype` char(20) DEFAULT NULL,
  `Pnum` int(11) DEFAULT NULL,
  `Pprice` double DEFAULT NULL,
  PRIMARY KEY (`id`,`Pname`),
  KEY `Pname` (`Pname`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Data for the table `product` */

LOCK TABLES `product` WRITE;

insert  into `product`(`id`,`Pname`,`Ptype`,`Pnum`,`Pprice`) values (6,'农夫山泉','冷饮',84,2.5),(9,'水杯','家用品',46,25),(10,'台灯','家用品',32,39),(12,'卫生纸','家用品',52,28),(14,'鼠标','电子产品',42,45),(15,'手机','电子产品',7,3000);

UNLOCK TABLES;

/*Table structure for table `recored` */

DROP TABLE IF EXISTS `recored`;

CREATE TABLE `recored` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cName` char(20) DEFAULT NULL,
  `BPnum` char(50) NOT NULL,
  `BuyRecored` char(100) DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  `Amount` double DEFAULT NULL,
  `time` char(20) DEFAULT NULL,
  `suggestion` char(255) DEFAULT NULL,
  PRIMARY KEY (`id`,`BPnum`),
  KEY `BuyRecored` (`BuyRecored`),
  KEY `cName` (`cName`),
  CONSTRAINT `recored_ibfk_1` FOREIGN KEY (`cName`) REFERENCES `t_user` (`cName`),
  CONSTRAINT `recored_ibfk_2` FOREIGN KEY (`BuyRecored`) REFERENCES `product` (`Pname`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

/*Data for the table `recored` */

LOCK TABLES `recored` WRITE;

insert  into `recored`(`id`,`cName`,`BPnum`,`BuyRecored`,`count`,`Amount`,`time`,`suggestion`) values (5,'张晨星','20191228149484','水杯',1,25,'2019-12-28',NULL),(7,'ITcolors','201912281523111','农夫山泉',10,2.5,'2019-12-28','便宜，挺不错的'),(14,'张晨星','201912281559004','台灯',1,39,'2019-12-28',NULL),(15,'张晨星','201912281559042','台灯',1,39,'2019-12-28','灯亮太暗！！'),(16,'张晨星','201912281559587','台灯',1,39,'2019-12-28',NULL),(17,'张晨星','201912281601220','卫生纸',1,28,'2019-12-28',NULL),(18,'张晨星','201912281601252','卫生纸',1,28,'2019-12-28',NULL),(19,'ITcolors','201912281605400','水杯',1,25,'2019-12-28','hasf'),(20,'ITcolors','201912281606186','手机',1,20000,'2019-12-28','实在太贵了！！！'),(21,'ITcolors','201912281607241','卫生纸',2,56,'2019-12-28','便宜'),(22,'ITcolors','201912281832001','台灯',2,78,'2019-12-28','太他妈贵了'),(23,'张晨星','201912282051030','鼠标',1,45,'2019-12-28',NULL),(24,'张晨星','201912282216438','手机',1,20000,'2019-12-28',NULL),(25,'张晨星','201912290116300','台灯',1,39,'2019-12-29',NULL),(26,'ITcolors','201912290835315','鼠标',2,90,'2019-12-29',NULL),(27,'ITcolors','201912290850437','水杯',2,50,'2019-12-29',NULL),(28,'123','20191229123391','卫生纸',1,28,'2019-12-29','便宜'),(29,'123','201912291233192','台灯',2,78,'2019-12-29',NULL),(30,'操星驰','201912291930356','卫生纸',1,28,'2019-12-29','纸张有些薄，不厚实'),(31,'操星驰','201912291932167','鼠标',1,45,'2019-12-29','用得还不错，手感挺好的'),(32,'cxc','201912301908352','鼠标',1,45,'2019-12-30',NULL);

UNLOCK TABLES;

/*Table structure for table `staff` */

DROP TABLE IF EXISTS `staff`;

CREATE TABLE `staff` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Sname` char(20) NOT NULL,
  `sex` char(10) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `edu` char(20) DEFAULT NULL,
  `STel` char(20) DEFAULT NULL,
  `department` char(20) DEFAULT NULL,
  `SworkingDate` date DEFAULT NULL,
  `Spostion` char(20) DEFAULT NULL,
  `salary` double DEFAULT NULL,
  `password` char(20) DEFAULT NULL,
  `address` char(30) DEFAULT NULL,
  `weight` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`,`Sname`),
  KEY `Sname` (`Sname`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

/*Data for the table `staff` */

LOCK TABLES `staff` WRITE;

insert  into `staff`(`id`,`Sname`,`sex`,`age`,`edu`,`STel`,`department`,`SworkingDate`,`Spostion`,`salary`,`password`,`address`,`weight`) values (8,'colorful','女',21,'本科','18327583317','人事部','2019-12-18','经理',7000,'11111','xiangyang',5),(10,'员工1','女',21,'本科','18371206897','财务部','2019-12-04','会计',8000,'12345','北京',5),(11,'员工2','女',23,'本科','18273940345','行政部','2019-12-02','人事专员',5000,'12345','襄阳',5),(13,'刘韦佳','男',21,'本科','18463490923','客服部','2019-12-10','客服',8080,'12345','襄阳',5),(16,'操星驰','男',22,'本科','18371206897','董事会','2019-12-27','董事长',100000,'12345','孝感',5),(17,'李斯','女',21,'本科','18923845064','人事部','2019-12-05','经理',9000,'11111','湖北咸宁',5),(21,'员工5','女',21,'本科','14837493636','人事部','2019-12-03','经理',8000,'11111','襄阳',5),(22,'张锦涛','男',21,'本科','19875982235','财务部','2019-12-02','会计',7000,'12345','襄阳湖北文理',5);

UNLOCK TABLES;

/*Table structure for table `staffworkr` */

DROP TABLE IF EXISTS `staffworkr`;

CREATE TABLE `staffworkr` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Sname` char(20) DEFAULT NULL,
  `WType` char(10) DEFAULT NULL,
  `department` char(20) DEFAULT NULL,
  `Spostion` char(20) DEFAULT NULL,
  `ATime` datetime DEFAULT NULL,
  `ETime` datetime DEFAULT NULL,
  `STel` char(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `Sname` (`Sname`),
  CONSTRAINT `staffworkr_ibfk_1` FOREIGN KEY (`Sname`) REFERENCES `staff` (`Sname`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Data for the table `staffworkr` */

LOCK TABLES `staffworkr` WRITE;

insert  into `staffworkr`(`id`,`Sname`,`WType`,`department`,`Spostion`,`ATime`,`ETime`,`STel`) values (1,'colorful','早退','人事部','经理','2019-12-28 17:14:19','2019-12-28 21:14:26','18327583317'),(3,'员工1','早退','财务部','会计','2019-12-29 00:52:54','2019-12-29 08:51:40','18371206897'),(4,'员工2','正常','行政部','人事专员','2019-12-29 01:00:09',NULL,'18273940345'),(7,'操星驰','早退','董事会','董事长','2019-12-29 01:11:10','2019-12-29 01:11:20','18371206897'),(8,'员工1','早退','财务部','会计','2019-12-29 08:36:32','2019-12-29 08:51:40','18371206897'),(9,'员工1','早退','财务部','会计','2019-12-29 08:51:24','2019-12-29 08:51:40','18371206897'),(11,'员工5','早退','人事部','经理','2019-12-29 12:44:41','2019-12-29 14:30:17','14837493636'),(12,'张锦涛','正常','财务部','会计','2019-12-29 14:56:55','2019-12-29 19:34:02','19875982235');

UNLOCK TABLES;

/*Table structure for table `t_admin` */

DROP TABLE IF EXISTS `t_admin`;

CREATE TABLE `t_admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Aname` char(20) DEFAULT NULL,
  `Apassword` char(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_admin` */

LOCK TABLES `t_admin` WRITE;

UNLOCK TABLES;

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cName` char(20) NOT NULL,
  `cTel` char(20) DEFAULT NULL,
  `cAddress` char(30) DEFAULT NULL,
  `sex` char(10) DEFAULT NULL,
  `cEmail` char(20) DEFAULT NULL,
  `password` char(20) DEFAULT NULL,
  `weight` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`,`cName`),
  KEY `cName` (`cName`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `t_user` */

LOCK TABLES `t_user` WRITE;

insert  into `t_user`(`id`,`cName`,`cTel`,`cAddress`,`sex`,`cEmail`,`password`,`weight`) values (1,'ITcolors','1837209845','湖北','女','174332@qq.com','54321',1),(2,'2017117102','18371299998','武汉大学','女','zjt@a.com','54321',1),(4,'admin','18888888888','where am I','男','admin@admin.com','admin',10),(5,'张晨星','189234203552','襄阳','女','deijing@deijing.com','12345',1),(6,'程春丽','18372834676','襄阳','女','xiaochun@qq.com','11111',1),(7,'操星驰','18974822435','湖北','男','adhj@aa.com','11111',1),(8,'123','123','123','女','123@123.com','123',1),(9,'zjt','187235632865','','男','','12345',1),(10,'cxc','1789723590','孝感','男','123@qq.com','12345',1);

UNLOCK TABLES;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
