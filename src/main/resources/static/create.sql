-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- Server version:               5.7.17-log - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL 版本:                  10.1.0.5464
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for admin
CREATE DATABASE IF NOT EXISTS `admin` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `admin`;

-- Dumping structure for table admin.account
CREATE TABLE IF NOT EXISTS `account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Data exporting was unselected.
-- Dumping structure for table admin.account_role
CREATE TABLE IF NOT EXISTS `account_role` (
  `account_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.
-- Dumping structure for table admin.book
CREATE TABLE IF NOT EXISTS `book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Data exporting was unselected.
-- Dumping structure for table admin.company
CREATE TABLE IF NOT EXISTS `company` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `auth` int(11) DEFAULT NULL,
  `branch` int(11) DEFAULT NULL,
  `point` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5002 DEFAULT CHARSET=utf8;

-- Data exporting was unselected.
-- Dumping structure for table admin.menu
CREATE TABLE IF NOT EXISTS `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(50) DEFAULT NULL,
  `url` varchar(50) DEFAULT NULL,
  `method` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Data exporting was unselected.
-- Dumping structure for table admin.menu_role
CREATE TABLE IF NOT EXISTS `menu_role` (
  `menu_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.
-- Dumping structure for table admin.role
CREATE TABLE IF NOT EXISTS `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Data exporting was unselected.
-- Dumping structure for table admin.sys_menu
CREATE TABLE IF NOT EXISTS `sys_menu` (
  `id` varchar(32) NOT NULL,
  `menu_name` varchar(64) DEFAULT NULL COMMENT '菜单名',
  `url` varchar(128) DEFAULT '' COMMENT 'url',
  `parent_id` varchar(32) DEFAULT '0',
  `is_active` int(11) DEFAULT '1',
  `display` int(11) DEFAULT NULL,
  `curr_level` int(11) DEFAULT NULL,
  `menu_ico` varchar(32) DEFAULT NULL,
  `properties` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- Data exporting was unselected.
-- Dumping structure for procedure admin.test
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `test`()
BEGIN
set @order_num1=0;
set @order_num2=0;
SELECT * FROM 
(SELECT *, @order_num1:=@order_num1+1 rownum FROM company c ORDER BY c.auth ASC,c.branch=2 DESC,c.point DESC) 
as a  WHERE 5>
(SELECT COUNT(*) FROM 
(SELECT *, @order_num2:=@order_num2+1 rownum FROM company c ORDER BY c.auth ASC,c.branch=2 DESC,c.point DESC)
AS b WHERE b.type=a.type AND b.rownum<a.rownum)
ORDER BY a.TYPE,a.rownum;
END//
DELIMITER ;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
