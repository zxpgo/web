/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50155
Source Host           : localhost:3306
Source Database       : sprintboot-vue3

Target Server Type    : MYSQL
Target Server Version : 50155
File Encoding         : 65001

Date: 2023-07-23 22:54:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `date` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `address` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_num` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '张三', '2023-06-11', '安徽省合肥市', null);
INSERT INTO `user` VALUES ('2', '平戈', '2023-06-11', '上海', null);
