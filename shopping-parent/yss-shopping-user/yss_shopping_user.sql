/*
SQLyog Trial v13.1.5  (64 bit)
MySQL - 8.0.17 : Database - yss_shopping_user
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`yss_shopping_user` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `yss_shopping_user`;

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(32) NOT NULL COMMENT '账号',
  `password` varchar(128) NOT NULL COMMENT 'MD5加密的密码',
  `nickname` varchar(128) DEFAULT NULL COMMENT '昵称',
  `email` varchar(64) DEFAULT NULL COMMENT '邮箱',
  `status` tinyint(4) NOT NULL COMMENT '状态：0-删除，1-启用，2-禁用',
  `create_user` varchar(64) DEFAULT NULL COMMENT '创建人信息',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_user` varchar(64) DEFAULT NULL COMMENT '修改人信息',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='用户表';

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
