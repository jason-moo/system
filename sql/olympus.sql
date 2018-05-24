/*
 Navicat Premium me.gacl.utils.Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 50718
 Source Host           : localhost
 Source Database       : olympus

 Target Server Type    : MySQL
 Target Server Version : 50718
 File Encoding         : utf-8

 Date: 10/19/2017 13:32:53 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `c_user`
-- ----------------------------
DROP TABLE IF EXISTS `c_user`;
CREATE TABLE `c_user` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(40) DEFAULT NULL,
  `password` varchar(40) DEFAULT NULL,
  `type` tinyint(4) NOT NULL DEFAULT '0',
  `scert` varchar(40) DEFAULT NULL,
  `last_login_time` datetime DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `phone` varchar(40) DEFAULT NULL,
  `deleted` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `dessy`
-- ----------------------------
DROP TABLE IF EXISTS `dessy`;
CREATE TABLE `dessy` (
  `id` bigint(12) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(12) NOT NULL,
  `content` varchar(400) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `discuss`
-- ----------------------------
DROP TABLE IF EXISTS `discuss`;
CREATE TABLE `discuss` (
  `id` bigint(12) NOT NULL,
  `user_id` bigint(12) NOT NULL,
  `parent_id` bigint(12) NOT NULL DEFAULT '0',
  `content` varchar(400) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `type` varchar(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
