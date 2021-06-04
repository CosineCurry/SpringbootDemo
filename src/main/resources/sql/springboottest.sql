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

 Date: 04/06/2021 16:52:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for item
-- ----------------------------
DROP TABLE IF EXISTS `item`;
CREATE TABLE `item`  (
  `item_id` int(0) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `price` decimal(10, 2) NOT NULL,
  PRIMARY KEY (`item_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of item
-- ----------------------------
INSERT INTO `item` VALUES (1000, '哇哈哈矿泉水', 3.00);

-- ----------------------------
-- Table structure for order_item
-- ----------------------------
DROP TABLE IF EXISTS `order_item`;
CREATE TABLE `order_item`  (
  `orderitem_id` bigint(0) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(0) NOT NULL,
  `product_id` bigint(0) NOT NULL,
  `price` decimal(10, 2) NOT NULL COMMENT '商品单价',
  PRIMARY KEY (`orderitem_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_item
-- ----------------------------
INSERT INTO `order_item` VALUES (1, 1622794619330, 2021060300010004, 3.00);
INSERT INTO `order_item` VALUES (2, 1622794619330, 2021060300010005, 3.00);

-- ----------------------------
-- Table structure for order_table
-- ----------------------------
DROP TABLE IF EXISTS `order_table`;
CREATE TABLE `order_table`  (
  `order_id` bigint(0) NOT NULL,
  `order_price` decimal(10, 2) NOT NULL,
  `create_time` datetime(0) NOT NULL,
  `update_time` datetime(0) NOT NULL,
  `pay_status` int(0) NOT NULL COMMENT '支付状态，0未支付，1已支付',
  `order_status` int(0) NOT NULL COMMENT '订单状态，0正常，1取消订单',
  `user_id` int(0) NOT NULL,
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_table
-- ----------------------------
INSERT INTO `order_table` VALUES (1000, 95000.00, '2020-12-12 04:30:30', '2020-12-12 04:30:30', 0, 0, 1);
INSERT INTO `order_table` VALUES (1622794619330, 6.00, '2021-06-04 16:16:59', '2021-06-04 16:16:59', 0, 0, 1);

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `product_id` bigint(0) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `price` decimal(10, 2) NOT NULL COMMENT '商品单价',
  `item_id` int(0) NOT NULL COMMENT '类目id',
  `create_time` datetime(0) NOT NULL,
  `create_factory` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建工厂',
  `status` int(0) NOT NULL COMMENT '状态，0为未卖出，1为卖出',
  `version` int(0) NOT NULL COMMENT '版本号',
  PRIMARY KEY (`product_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (2021060300010001, '哇哈哈矿泉水', 3.00, 1000, '2021-06-03 14:08:04', '立方', 1, 1);
INSERT INTO `product` VALUES (2021060300010002, '哇哈哈矿泉水', 3.00, 1000, '2021-06-03 14:14:07', '立方', 1, 1);
INSERT INTO `product` VALUES (2021060300010003, '哇哈哈矿泉水', 3.00, 1000, '2021-06-03 15:51:41', '立方', 1, 1);
INSERT INTO `product` VALUES (2021060300010004, '哇哈哈矿泉水', 3.00, 1000, '2021-06-03 15:51:46', '立方', 1, 1);
INSERT INTO `product` VALUES (2021060300010005, '哇哈哈矿泉水', 3.00, 1000, '2021-06-03 15:51:51', '立方', 1, 1);
INSERT INTO `product` VALUES (2021060300010006, '哇哈哈矿泉水', 3.00, 1000, '2021-06-03 15:51:55', '立方', 0, 0);
INSERT INTO `product` VALUES (2021060300010010, '哇哈哈矿泉水', 3.00, 1000, '2021-06-04 16:44:04', '立方', 0, 0);
INSERT INTO `product` VALUES (2021060300010011, '哇哈哈矿泉水', 3.00, 1000, '2021-06-04 16:44:12', '立方', 0, 0);
INSERT INTO `product` VALUES (2021060300010012, '哇哈哈矿泉水', 3.00, 1000, '2021-06-04 16:44:20', '立方', 0, 0);
INSERT INTO `product` VALUES (2021060300010013, '哇哈哈矿泉水', 3.00, 1000, '2021-06-04 16:44:26', '立方', 0, 0);
INSERT INTO `product` VALUES (2021060300010014, '哇哈哈矿泉水', 3.00, 1000, '2021-06-04 16:44:30', '立方', 0, 0);
INSERT INTO `product` VALUES (2021060300010015, '哇哈哈矿泉水', 3.00, 1000, '2021-06-04 16:44:34', '立方', 0, 0);

-- ----------------------------
-- Table structure for store
-- ----------------------------
DROP TABLE IF EXISTS `store`;
CREATE TABLE `store`  (
  `store_id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `count` int(0) NOT NULL COMMENT '库存',
  `item_id` int(0) NOT NULL COMMENT '商品id',
  `maxCount` int(0) NOT NULL COMMENT '库存最大值',
  `version` int(0) NOT NULL COMMENT '版本号',
  PRIMARY KEY (`store_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of store
-- ----------------------------
INSERT INTO `store` VALUES (1, '哇哈哈库存', 7, 1000, 100, 16);

SET FOREIGN_KEY_CHECKS = 1;
