/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 100128
 Source Host           : localhost:3306
 Source Schema         : urunkategoriodev

 Target Server Type    : MySQL
 Target Server Version : 100128
 File Encoding         : 65001

 Date: 14/03/2018 22:49:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for kategoriler
-- ----------------------------
DROP TABLE IF EXISTS `kategoriler`;
CREATE TABLE `kategoriler`  (
  `kid` int(11) NOT NULL AUTO_INCREMENT,
  `kadi` varchar(255) CHARACTER SET utf8 COLLATE utf8_turkish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`kid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_turkish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of kategoriler
-- ----------------------------
INSERT INTO `kategoriler` VALUES (1, 'KIRTASIYE');
INSERT INTO `kategoriler` VALUES (2, 'TEKSTIL');
INSERT INTO `kategoriler` VALUES (3, 'deneme');

-- ----------------------------
-- Table structure for urunler
-- ----------------------------
DROP TABLE IF EXISTS `urunler`;
CREATE TABLE `urunler`  (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `ukid` int(11) NULL DEFAULT NULL,
  `uadi` varchar(255) CHARACTER SET utf8 COLLATE utf8_turkish_ci NULL DEFAULT NULL,
  `uresim` varchar(255) CHARACTER SET utf8 COLLATE utf8_turkish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_turkish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of urunler
-- ----------------------------
INSERT INTO `urunler` VALUES (24, 1, 'KALEM', '20180314220039.jpg');
INSERT INTO `urunler` VALUES (25, 1, 'ELBISE', '20180314220057.jpg');
INSERT INTO `urunler` VALUES (26, 2, 'KAZAK', '20180314220353.jpg');
INSERT INTO `urunler` VALUES (27, 1, 'SILGI', '20180314220410.jpg');

-- ----------------------------
-- View structure for viewsurunlerjoin
-- ----------------------------
DROP VIEW IF EXISTS `viewsurunlerjoin`;
CREATE ALGORITHM = UNDEFINED DEFINER = `root`@`localhost` SQL SECURITY DEFINER VIEW `viewsurunlerjoin` AS SELECT uid,uadi,kadi,uresim from urunler join kategoriler on urunler.ukid = kategoriler.kid ;

SET FOREIGN_KEY_CHECKS = 1;
