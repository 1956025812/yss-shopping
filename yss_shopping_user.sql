/*
SQLyog Trial v13.1.5  (64 bit)
MySQL - 5.7.21 : Database - yss_shopping_user
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`yss_shopping_user` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `yss_shopping_user`;

/*Table structure for table `role_menu` */

DROP TABLE IF EXISTS `role_menu`;

CREATE TABLE `role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rid` bigint(20) NOT NULL COMMENT '角色ID',
  `mid` bigint(20) NOT NULL COMMENT '菜单ID',
  `create_info` varchar(256) DEFAULT NULL COMMENT '创建信息',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

/*Data for the table `role_menu` */

insert  into `role_menu`(`id`,`rid`,`mid`,`create_info`,`create_time`) values 
(17,3,3,'system','2020-03-06 01:51:48'),
(18,3,15,'system','2020-03-06 01:51:48'),
(19,5,13,'system','2020-03-06 01:52:00'),
(20,5,16,'system','2020-03-06 01:52:00'),
(21,6,2,'system','2020-03-06 01:52:07'),
(22,6,14,'system','2020-03-06 01:52:07'),
(23,6,28,'system','2020-03-06 01:52:07'),
(24,2,1,'system','2020-03-06 05:54:55'),
(25,2,4,'system','2020-03-06 05:54:55'),
(26,2,29,'system','2020-03-06 05:54:55'),
(27,2,35,'system','2020-03-06 05:54:55'),
(28,2,39,'system','2020-03-06 05:54:55'),
(29,2,5,'system','2020-03-06 05:54:55'),
(30,2,7,'system','2020-03-06 05:54:55'),
(31,2,8,'system','2020-03-06 05:54:55'),
(32,2,9,'system','2020-03-06 05:54:55'),
(33,2,6,'system','2020-03-06 05:54:55'),
(34,2,10,'system','2020-03-06 05:54:55');

/*Table structure for table `sys_menu` */

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `menu_type` tinyint(4) NOT NULL COMMENT '菜单类型：1-页面，2-按钮',
  `menu_code` varchar(128) NOT NULL COMMENT '菜单代码',
  `menu_name` varchar(128) NOT NULL COMMENT '菜单名称',
  `menu_url` varchar(128) DEFAULT NULL COMMENT 'URL地址',
  `parent_id` bigint(20) NOT NULL COMMENT '父菜单ID，顶级为0',
  `level` tinyint(4) NOT NULL COMMENT '层级：从1开始',
  `state` tinyint(4) NOT NULL COMMENT '状态：0-删除，1-启用，2-禁用',
  `remark` varchar(256) DEFAULT NULL COMMENT '备注',
  `create_info` varchar(256) DEFAULT NULL COMMENT '创建信息',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_info` varchar(256) DEFAULT NULL COMMENT '修改信息',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;

/*Data for the table `sys_menu` */

insert  into `sys_menu`(`id`,`menu_type`,`menu_code`,`menu_name`,`menu_url`,`parent_id`,`level`,`state`,`remark`,`create_info`,`create_time`,`update_info`,`update_time`) values 
(1,1,'userManager','用户管理','',0,1,1,'用户管理','system','2020-02-08 21:28:48','system','2020-02-08 21:28:50'),
(2,1,'goodsManager','商品管理','',0,1,1,'商品管理','system','2020-02-08 21:30:02',NULL,NULL),
(3,1,'orderManager','订单管理','',0,1,1,'订单管理','system','2020-02-08 21:30:54',NULL,NULL),
(4,1,'userPage','用户列表','/user/list',1,2,1,'用户管理-用户列表','system','2020-02-08 21:31:40','system','2020-02-19 13:16:15'),
(5,1,'menuPage','菜单列表','/menu/list',1,2,1,'用户管理-菜单列表','system','2020-02-08 21:32:55',NULL,NULL),
(6,1,'rolePage','角色列表','/role/list',1,2,1,'用户管理-角色列表','system','2020-02-08 21:35:57',NULL,NULL),
(7,2,'saveMenu','新增菜单','/menu/save',5,3,1,'用户管理-菜单相关-新增菜单','system','2020-02-08 21:36:48',NULL,NULL),
(8,2,'updateMenu','修改菜单','/menu/update',5,3,1,'用户管理-菜单相关-修改菜单','system','2020-02-08 21:37:52',NULL,NULL),
(9,2,'delMenu','删除菜单','/menu/del',5,3,1,'用户管理-菜单相关-删除菜单','system','2020-02-08 21:38:39',NULL,NULL),
(10,2,'saveRole','新增角色','/role/add',6,3,1,'用户管理-角色列表-新增角色','system','2020-02-13 13:26:12',NULL,NULL),
(13,1,'logisticsManager','物流管理','',0,1,1,'物流管理','system','2020-02-15 07:42:46',NULL,NULL),
(14,1,'goodsPage','商品列表','/goods/list',2,2,1,'商品管理-商品列表','system','2020-02-15 07:48:15',NULL,NULL),
(15,1,'orderPage','订单列表','/order/list',3,2,1,'订单管理-订单列表','system','2020-02-15 07:53:48',NULL,NULL),
(16,1,'logisticsProviderPage','物流商列表','/logistics/provider/list',13,2,1,'物流管理-物流商列表','system','2020-02-15 07:57:01',NULL,NULL),
(28,2,'saveGoods','新增商品','/goods/save',14,3,1,'商品管理-商品列表-新增商品','system','2020-02-15 14:44:47','system','2020-02-19 13:19:40'),
(29,2,'saveUser','新增用户','/user/save',4,3,1,'用户管理-用户列表-新增用户','system','2020-02-15 14:48:12','system','2020-02-19 13:48:29'),
(35,2,'delUser','删除用户','/user/del',4,3,1,'用户管理-用户列表-删除用户','system','2020-02-17 13:06:37',NULL,NULL),
(39,2,'updateUser','修改用户','/user/update',4,3,1,'用户管理-用户列表-修改用户','system','2020-02-19 13:17:16',NULL,NULL);

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(128) NOT NULL COMMENT '角色名称',
  `level` tinyint(4) NOT NULL COMMENT '层级：从1开始',
  `parent_id` bigint(20) NOT NULL COMMENT '父角色ID，顶级为0',
  `state` tinyint(4) NOT NULL COMMENT '状态：0-删除，1-启用，2-禁用',
  `remark` varchar(256) DEFAULT NULL COMMENT '备注',
  `create_info` varchar(256) DEFAULT NULL COMMENT '创建信息',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_info` varchar(256) DEFAULT NULL COMMENT '修改信息',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

/*Data for the table `sys_role` */

insert  into `sys_role`(`id`,`role_name`,`level`,`parent_id`,`state`,`remark`,`create_info`,`create_time`,`update_info`,`update_time`) values 
(1,'天眼',1,0,1,'上帝角色，初始化时创建，拥有所有子系统的权限','system','2020-02-20 16:48:08','system','2020-03-04 07:55:38'),
(2,'用户系统',2,1,1,'天眼-用户系统','system','2020-03-04 07:50:43','system','2020-03-04 07:55:50'),
(3,'订单系统',2,1,1,'天眼-订单系统','system','2020-03-04 07:52:10',NULL,NULL),
(4,'内容管理系统',2,1,1,'天眼-内容管理系统','system','2020-03-04 07:52:39','system','2020-03-06 07:50:35'),
(5,'物流系统',2,1,1,'天眼-物流系统','system','2020-03-04 07:52:57',NULL,NULL),
(6,'商品系统',2,1,1,'天眼-商品系统','system','2020-03-04 07:53:42',NULL,NULL),
(7,'用户系统超管',3,2,1,'天眼-用户系统-用户系统超管','system','2020-03-04 07:54:27',NULL,NULL),
(17,'订单系统超管',3,3,1,'天眼-订单系统-订单系统超管','system','2020-03-04 07:57:43',NULL,NULL),
(18,'内容管理系统超管',3,4,1,'天眼-内容管理系统-内容管理系统超管','system','2020-03-04 07:58:17','system','2020-03-06 07:50:53'),
(19,'物流系统超管',3,5,1,'天眼-物流系统-物流系统超管','system','2020-03-04 08:00:29',NULL,NULL),
(20,'商品系统超管',3,6,1,'天眼-商品系统-商品系统超管','system','2020-03-04 08:00:55',NULL,NULL),
(21,'库存系统',2,1,1,'天眼-库存系统','system','2020-03-06 07:49:30',NULL,NULL),
(22,'库存系统超管',3,21,1,'天眼-库存系统-库存系统超管','system','2020-03-06 07:49:59',NULL,NULL),
(23,'公共数据系统',2,1,1,'天眼-公共数据系统','system','2020-03-06 07:51:26',NULL,NULL),
(24,'公共数据系统超管',3,23,1,'天眼-公共数据系统-公共数据系统超管','system','2020-03-06 07:52:04',NULL,NULL);

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(32) NOT NULL COMMENT '账号',
  `password` varchar(128) NOT NULL COMMENT 'MD5加密的密码',
  `nickname` varchar(128) DEFAULT NULL COMMENT '昵称',
  `email` varchar(64) NOT NULL COMMENT '邮箱',
  `head_img_url` varchar(256) DEFAULT NULL COMMENT '头像路径',
  `state` tinyint(4) NOT NULL COMMENT '状态：0-删除，1-启用，2-禁用',
  `register_source` tinyint(4) NOT NULL COMMENT '注册来源：1-系统注册，2-用户注册，3-QQ，4-WX',
  `create_info` varchar(64) DEFAULT NULL COMMENT '创建信息',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_info` varchar(64) DEFAULT NULL COMMENT '修改信息',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='用户表';

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`username`,`password`,`nickname`,`email`,`head_img_url`,`state`,`register_source`,`create_info`,`create_time`,`update_info`,`update_time`) values 
(1,'yss001','E10ADC3949BA59ABBE56E057F20F883E','猿叔叔001','yss@001.com',NULL,1,1,'system','2019-12-09 13:52:21','system','2019-12-09 10:26:52'),
(2,'yss002','E10ADC3949BA59ABBE56E057F20F883E','猿叔叔002','yss@002.com',NULL,1,1,NULL,'2019-12-09 08:29:06',NULL,NULL),
(5,'yss003','FCEA920F7412B5DA7BE0CF42B8C93759','','yss@5566.com','qwerwqe',1,1,NULL,'2020-01-16 08:46:50',NULL,'2020-01-17 08:08:27'),
(8,'yss004','E10ADC3949BA59ABBE56E057F20F883E','猿叔叔004','yss@004.com','string',1,1,NULL,'2020-01-16 09:37:25',NULL,'2020-01-19 03:57:11'),
(9,'yss005','E10ADC3949BA59ABBE56E057F20F883E','猿叔叔005','yss@005.com','',1,1,NULL,'2020-01-19 03:55:28',NULL,NULL),
(10,'admin','E10ADC3949BA59ABBE56E057F20F883E','超级管理员','admin@qq.com','',1,1,NULL,'2020-01-19 03:55:28',NULL,NULL),
(11,'yss006','E10ADC3949BA59ABBE56E057F20F883E','猿叔叔006','yss@006.com',NULL,1,1,NULL,'2020-04-03 02:32:05',NULL,NULL),
(12,'yss007','E10ADC3949BA59ABBE56E057F20F883E','猿叔叔007','yss@007.com',NULL,2,1,NULL,'2020-04-03 02:39:33',NULL,NULL),
(13,'yss008','E10ADC3949BA59ABBE56E057F20F883E','猿叔叔008','yss@008.com',NULL,1,1,NULL,'2020-04-03 02:56:13',NULL,NULL),
(14,'yss009','E10ADC3949BA59ABBE56E057F20F883E','猿叔叔009','yss@009.com',NULL,1,1,NULL,'2020-04-03 02:58:17',NULL,NULL),
(15,'yss010','E10ADC3949BA59ABBE56E057F20F883E','猿叔叔010','yss@010.com',NULL,1,1,NULL,'2020-04-03 03:01:33',NULL,NULL),
(16,'yss011','E10ADC3949BA59ABBE56E057F20F883E','猿叔叔011','yss@011.com',NULL,1,1,NULL,'2020-04-03 03:02:27',NULL,NULL);

/*Table structure for table `user_role` */

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uid` bigint(20) NOT NULL COMMENT '用户ID',
  `rid` bigint(20) NOT NULL COMMENT '角色ID',
  `create_info` varchar(256) DEFAULT NULL COMMENT '创建信息',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user_role` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
