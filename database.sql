-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.0.45-community-nt - MySQL Community Edition (GPL)
-- Server OS:                    Win32
-- HeidiSQL Version:             10.1.0.5464
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for homecredit
DROP DATABASE IF EXISTS `homecredit`;
CREATE DATABASE IF NOT EXISTS `homecredit` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `homecredit`;

-- Dumping structure for table homecredit.category
DROP TABLE IF EXISTS `category`;
CREATE TABLE IF NOT EXISTS `category` (
  `category_id` bigint(20) NOT NULL auto_increment,
  `created_date` datetime NOT NULL,
  `name` varchar(255) NOT NULL,
  `updated_date` datetime NOT NULL,
  PRIMARY KEY  (`category_id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- Dumping data for table homecredit.category: 5 rows
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
REPLACE INTO `category` (`category_id`, `created_date`, `name`, `updated_date`) VALUES
	(1, '2019-06-29 07:32:48', 'PROMO', '2019-06-29 07:32:48'),
	(2, '2019-06-29 07:33:03', 'CATEGORY', '2019-06-29 07:33:03'),
	(3, '2019-06-29 07:33:07', 'FLASHSALE', '2019-06-29 07:33:07'),
	(4, '2019-06-29 07:33:12', 'HISTORY', '2019-06-29 07:33:12'),
	(5, '2019-06-29 07:33:19', 'NEWS', '2019-06-29 07:33:19');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;

-- Dumping structure for table homecredit.user
DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `user_id` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY  (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- Dumping data for table homecredit.user: 1 rows
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
REPLACE INTO `user` (`user_id`, `name`) VALUES
	('usera', 'USER A'),
	('userb', 'USER B'),
	('userc', 'USER C');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

-- Dumping structure for table homecredit.user_category_filter
DROP TABLE IF EXISTS `user_category_filter`;
CREATE TABLE IF NOT EXISTS `user_category_filter` (
  `id` bigint(20) NOT NULL auto_increment,
  `created_date` datetime NOT NULL,
  `order_id` int(11) NOT NULL,
  `updated_date` datetime NOT NULL,
  `category_id` bigint(20) default NULL,
  `user_id` varchar(255) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK2t2hoegsduhgd1x5iu1dirtbu` (`category_id`),
  KEY `FKk1mg5w85ytufheg57cxcm13eu` (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- Dumping data for table homecredit.user_category_filter: 5 rows
/*!40000 ALTER TABLE `user_category_filter` DISABLE KEYS */;
REPLACE INTO `user_category_filter` (`id`, `created_date`, `order_id`, `updated_date`, `category_id`, `user_id`) VALUES
	(1, '2019-06-29 07:35:12', 1, '2019-06-29 07:35:12', 1, 'usera'),
	(2, '2019-06-29 07:35:20', 2, '2019-06-29 07:35:20', 2, 'usera'),
	(3, '2019-06-29 07:35:26', 3, '2019-06-29 07:35:26', 3, 'usera'),
	(4, '2019-06-29 07:35:33', 4, '2019-06-29 07:35:33', 4, 'usera'),
	(5, '2019-06-29 07:35:37', 5, '2019-06-29 07:35:37', 5, 'usera'),
	(6, '2019-06-29 07:46:30', 1, '2019-06-29 07:46:30', 1, 'userb'),
	(7, '2019-06-29 07:46:30', 2, '2019-06-29 07:46:30', 5, 'userb'),
	(8, '2019-06-29 07:46:30', 3, '2019-06-29 07:46:30', 3, 'userb'),
	(9, '2019-06-29 07:46:30', 4, '2019-06-29 07:46:30', 2, 'userb'),
	(10, '2019-06-29 07:46:30', 5, '2019-06-29 07:46:30', 5, 'userb'),
	(11, '2019-06-29 07:47:18', 1, '2019-06-29 07:47:18', 1, 'userc'),
	(12, '2019-06-29 07:47:18', 2, '2019-06-29 07:47:18', 3, 'userc'),
	(13, '2019-06-29 07:47:18', 3, '2019-06-29 07:47:18', 2, 'userc'),
	(14, '2019-06-29 07:47:18', 4, '2019-06-29 07:47:18', 5, 'userc'),
	(15, '2019-06-29 07:47:18', 5, '2019-06-29 07:47:18', 4, 'userc');
/*!40000 ALTER TABLE `user_category_filter` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
