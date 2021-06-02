/*
 Navicat Premium Data Transfer

 Source Server         : MySql
 Source Server Type    : MySQL
 Source Server Version : 80025
 Source Host           : localhost:3306
 Source Schema         : springboottest

 Target Server Type    : MySQL
 Target Server Version : 80025
 File Encoding         : 65001

 Date: 02/06/2021 17:25:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for order_table2
-- ----------------------------
DROP TABLE IF EXISTS `order_table2`;
CREATE TABLE `order_table2`  (
  `order_id` int(0) NOT NULL,
  `order_title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `order_price` bigint(0) NOT NULL COMMENT '实际付款，保存为”分“',
  `order_time` datetime(0) NOT NULL,
  `order_paystatus` int(0) NOT NULL COMMENT '支付状态，0未支付，1已支付',
  `order_deletestatus` int(0) NOT NULL COMMENT '删除状态，0未删除，1已删除，默认为0',
  `product_id` int(0) NOT NULL,
  `product_count` int(0) NOT NULL COMMENT '商品数量',
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_table2
-- ----------------------------
INSERT INTO `order_table2` VALUES (0, '测试订单', 1425000, '2021-05-17 15:30:30', 0, 0, 0, 0);
INSERT INTO `order_table2` VALUES (1, '测试订单', 1425000, '2021-05-17 15:30:30', 0, 0, 0, 0);
INSERT INTO `order_table2` VALUES (2, '测试订单', 10000, '2021-05-17 15:30:30', 0, 0, 0, 0);
INSERT INTO `order_table2` VALUES (3, '测试订单', 18905, '2021-05-17 15:30:30', 0, 0, 0, 0);
INSERT INTO `order_table2` VALUES (4, '测试订单', 9900, '2021-05-17 15:30:30', 0, 0, 0, 0);
INSERT INTO `order_table2` VALUES (5, '测试订单', 9900, '2021-05-17 15:30:30', 0, 0, 0, 0);
INSERT INTO `order_table2` VALUES (6, '测试订单', 9900, '2021-05-17 15:30:30', 0, 0, 0, 0);
INSERT INTO `order_table2` VALUES (7, '测试订单', 9900, '2021-05-17 15:30:30', 0, 0, 0, 0);
INSERT INTO `order_table2` VALUES (8, '测试订单', 9900, '2021-05-17 15:30:30', 0, 0, 0, 0);
INSERT INTO `order_table2` VALUES (9, '测试订单', 49495, '2021-05-17 15:30:30', 0, 0, 0, 0);
INSERT INTO `order_table2` VALUES (10, '测试订单', 10, '2021-05-17 15:30:30', 0, 1, 0, 0);
INSERT INTO `order_table2` VALUES (12, '测试订单', 49495, '2021-05-17 15:30:30', 0, 0, 0, 0);
INSERT INTO `order_table2` VALUES (18, '测试订单', 49495, '2021-05-17 15:30:30', 0, 0, 0, 0);
INSERT INTO `order_table2` VALUES (21, '测试订单21', 49495, '2021-05-24 17:40:30', 0, 0, 0, 0);
INSERT INTO `order_table2` VALUES (24, '测试订单', 49495, '2021-05-24 15:30:30', 0, 0, 0, 0);
INSERT INTO `order_table2` VALUES (25, '测试订单25', 49495, '2021-05-26 17:40:30', 0, 1, 0, 0);
INSERT INTO `order_table2` VALUES (30, 'test', 10000, '2021-05-26 13:54:26', 0, 0, 0, 0);
INSERT INTO `order_table2` VALUES (101, '测试订单', 1425000, '2021-05-17 15:30:30', 0, 0, 0, 0);
INSERT INTO `order_table2` VALUES (103, '测试订单', 1425000, '2021-05-17 15:30:30', 0, 0, 0, 0);

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `product_id` int(0) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`product_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (1000, '哇哈哈');

-- ----------------------------
-- Table structure for store
-- ----------------------------
DROP TABLE IF EXISTS `store`;
CREATE TABLE `store`  (
  `store_id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `count` int(0) NOT NULL COMMENT '库存',
  `product_id` int(0) NOT NULL COMMENT '商品id',
  PRIMARY KEY (`store_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of store
-- ----------------------------
INSERT INTO `store` VALUES (1, '哇哈哈库存', 10, 1000);

SET FOREIGN_KEY_CHECKS = 1;
