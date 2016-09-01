/*
Navicat MySQL Data Transfer

Source Server         : ccbhs
Source Server Version : 50551
Source Host           : 192.168.0.104:3306
Source Database       : tuitanews

Target Server Type    : MYSQL
Target Server Version : 50551
File Encoding         : 65001

Date: 2016-09-01 11:17:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for api_channel
-- ----------------------------
DROP TABLE IF EXISTS `api_channel`;
CREATE TABLE `api_channel` (
  `id` int(22) NOT NULL AUTO_INCREMENT,
  `channel_id` varchar(50) DEFAULT NULL,
  `channel_name` varchar(50) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `status` int(4) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `index_name` (`channel_id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for news_api_channel
-- ----------------------------
DROP TABLE IF EXISTS `news_api_channel`;
CREATE TABLE `news_api_channel` (
  `id` int(22) NOT NULL AUTO_INCREMENT,
  `api_channel_id` varchar(50) DEFAULT NULL,
  `news_channel_id` int(22) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `status` int(4) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `index_name` (`api_channel_id`,`news_channel_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for news_bean
-- ----------------------------
DROP TABLE IF EXISTS `news_bean`;
CREATE TABLE `news_bean` (
  `id` int(22) NOT NULL AUTO_INCREMENT,
  `nid` varchar(50) DEFAULT NULL,
  `title` varchar(200) DEFAULT NULL,
  `content` mediumtext,
  `content_with_imgs` mediumtext,
  `source` varchar(50) DEFAULT NULL,
  `html` mediumtext,
  `face_url` varchar(500) DEFAULT NULL,
  `desc` varchar(500) DEFAULT NULL,
  `channel_id` varchar(50) DEFAULT NULL,
  `channel_name` varchar(50) DEFAULT NULL,
  `link` varchar(200) DEFAULT NULL,
  `imageurls` varchar(1000) DEFAULT NULL,
  `pub_date` datetime DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `title` (`title`),
  KEY `index_name` (`channel_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4039 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for news_channel
-- ----------------------------
DROP TABLE IF EXISTS `news_channel`;
CREATE TABLE `news_channel` (
  `id` int(22) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `status` int(4) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
