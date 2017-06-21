/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.7.10-log : Database - js_datacenter
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`js_datacenter` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */;

USE `js_datacenter`;

/*Table structure for table `stf_right_menu` */

DROP TABLE IF EXISTS `stf_right_menu`;

CREATE TABLE `stf_right_menu` (
  `RIGHT_ID` int(11) NOT NULL COMMENT '权限唯一ID号',
  `MENU_ID` int(11) NOT NULL COMMENT '系统菜单唯一ID号',
  PRIMARY KEY (`RIGHT_ID`,`MENU_ID`),
  KEY `FK_STF_RIGHT_MENU2` (`MENU_ID`),
  CONSTRAINT `FK_STF_RIGHT_MENU` FOREIGN KEY (`RIGHT_ID`) REFERENCES `stf_right` (`ID`),
  CONSTRAINT `FK_STF_RIGHT_MENU2` FOREIGN KEY (`MENU_ID`) REFERENCES `stf_res_menu` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

/*Data for the table `stf_right_menu` */

insert  into `stf_right_menu`(`RIGHT_ID`,`MENU_ID`) values (6,6);
insert  into `stf_right_menu`(`RIGHT_ID`,`MENU_ID`) values (6,57);
insert  into `stf_right_menu`(`RIGHT_ID`,`MENU_ID`) values (6,84);
insert  into `stf_right_menu`(`RIGHT_ID`,`MENU_ID`) values (6,86);
insert  into `stf_right_menu`(`RIGHT_ID`,`MENU_ID`) values (6,87);
insert  into `stf_right_menu`(`RIGHT_ID`,`MENU_ID`) values (6,88);
insert  into `stf_right_menu`(`RIGHT_ID`,`MENU_ID`) values (6,89);
insert  into `stf_right_menu`(`RIGHT_ID`,`MENU_ID`) values (6,90);
insert  into `stf_right_menu`(`RIGHT_ID`,`MENU_ID`) values (6,91);
insert  into `stf_right_menu`(`RIGHT_ID`,`MENU_ID`) values (6,93);
insert  into `stf_right_menu`(`RIGHT_ID`,`MENU_ID`) values (6,94);
insert  into `stf_right_menu`(`RIGHT_ID`,`MENU_ID`) values (6,96);
insert  into `stf_right_menu`(`RIGHT_ID`,`MENU_ID`) values (6,97);
insert  into `stf_right_menu`(`RIGHT_ID`,`MENU_ID`) values (6,98);
insert  into `stf_right_menu`(`RIGHT_ID`,`MENU_ID`) values (6,99);
insert  into `stf_right_menu`(`RIGHT_ID`,`MENU_ID`) values (6,100);
insert  into `stf_right_menu`(`RIGHT_ID`,`MENU_ID`) values (6,101);
insert  into `stf_right_menu`(`RIGHT_ID`,`MENU_ID`) values (6,102);
insert  into `stf_right_menu`(`RIGHT_ID`,`MENU_ID`) values (6,103);
insert  into `stf_right_menu`(`RIGHT_ID`,`MENU_ID`) values (6,106);
insert  into `stf_right_menu`(`RIGHT_ID`,`MENU_ID`) values (6,107);
insert  into `stf_right_menu`(`RIGHT_ID`,`MENU_ID`) values (6,108);
insert  into `stf_right_menu`(`RIGHT_ID`,`MENU_ID`) values (6,109);
insert  into `stf_right_menu`(`RIGHT_ID`,`MENU_ID`) values (6,110);
insert  into `stf_right_menu`(`RIGHT_ID`,`MENU_ID`) values (6,111);
insert  into `stf_right_menu`(`RIGHT_ID`,`MENU_ID`) values (6,112);
insert  into `stf_right_menu`(`RIGHT_ID`,`MENU_ID`) values (6,113);
insert  into `stf_right_menu`(`RIGHT_ID`,`MENU_ID`) values (6,114);
insert  into `stf_right_menu`(`RIGHT_ID`,`MENU_ID`) values (6,115);
insert  into `stf_right_menu`(`RIGHT_ID`,`MENU_ID`) values (6,116);
insert  into `stf_right_menu`(`RIGHT_ID`,`MENU_ID`) values (6,117);
insert  into `stf_right_menu`(`RIGHT_ID`,`MENU_ID`) values (6,118);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
