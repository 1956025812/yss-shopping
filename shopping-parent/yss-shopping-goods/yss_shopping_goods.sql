/*
SQLyog Trial v13.1.5  (64 bit)
MySQL - 8.0.17 : Database - yss_shopping_goods
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`yss_shopping_goods` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `yss_shopping_goods`;

/*Table structure for table `goods` */

DROP TABLE IF EXISTS `goods`;

CREATE TABLE `goods` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `goods_name` varchar(256) NOT NULL COMMENT '商品名称',
  `goods_remark` varchar(256) DEFAULT NULL COMMENT '商品描述',
  `status` tinyint(4) NOT NULL COMMENT '状态：0-删除，1-上架，2-下架',
  `create_user` varchar(64) DEFAULT NULL COMMENT '创建人信息',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_user` varchar(64) NOT NULL COMMENT '修改人信息',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='商品表';

/*Data for the table `goods` */

insert  into `goods`(`id`,`goods_name`,`goods_remark`,`status`,`create_user`,`create_time`,`update_user`,`update_time`) values 
(1,'手机','一加手机',1,'system','2019-12-16 14:31:02','system','2019-12-16 14:31:07');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
