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

 Date: 17/06/2021 09:02:52
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
INSERT INTO `item` VALUES (1001, '护柱', 100.00);

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
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_item
-- ----------------------------
INSERT INTO `order_item` VALUES (1, 1622794619330, 2021060300010004, 3.00);
INSERT INTO `order_item` VALUES (2, 1622794619330, 2021060300010005, 3.00);
INSERT INTO `order_item` VALUES (9, 1623141101151, 2021060300010016, 3.00);
INSERT INTO `order_item` VALUES (10, 1623141101151, 2021060300010017, 100.00);
INSERT INTO `order_item` VALUES (11, 1623291522215, 2021060300010014, 3.00);
INSERT INTO `order_item` VALUES (12, 1623291522215, 2021060300010015, 3.00);
INSERT INTO `order_item` VALUES (13, 1623291952968, 2021060300010013, 3.00);
INSERT INTO `order_item` VALUES (14, 1623294392520, 2021060300010012, 3.00);
INSERT INTO `order_item` VALUES (15, 1623294558144, 2021060300010011, 3.00);
INSERT INTO `order_item` VALUES (16, 1623310011351, 2021060300010006, 3.00);
INSERT INTO `order_item` VALUES (17, 1623310011351, 2021060300010010, 3.00);

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
  `discount_type` int(0) NOT NULL COMMENT '0为无优惠，1为满减，2为直减，3为折扣',
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_table
-- ----------------------------
INSERT INTO `order_table` VALUES (1000, 95000.00, '2020-12-12 04:30:30', '2020-12-12 04:30:30', 0, 0, 1, 0);
INSERT INTO `order_table` VALUES (1622794619330, 6.00, '2021-06-04 16:16:59', '2021-06-04 16:16:59', 0, 0, 1, 0);
INSERT INTO `order_table` VALUES (1623141101151, 93.00, '2021-06-08 16:31:41', '2021-06-08 16:31:41', 0, 0, 1, 1);
INSERT INTO `order_table` VALUES (1623291522215, 5.40, '2021-06-10 10:18:42', '2021-06-10 10:18:42', 0, 0, 1, 3);
INSERT INTO `order_table` VALUES (1623291952968, 1.00, '2021-06-10 10:25:53', '2021-06-10 10:25:53', 0, 0, 1, 2);
INSERT INTO `order_table` VALUES (1623294392520, 2.70, '2021-06-10 11:06:33', '2021-06-10 11:06:33', 0, 0, 1, 3);
INSERT INTO `order_table` VALUES (1623294558144, 1.00, '2021-06-10 11:09:18', '2021-06-10 11:09:18', 0, 0, 1, 2);
INSERT INTO `order_table` VALUES (1623310011351, 1.00, '2021-06-10 15:26:51', '2021-06-10 15:26:51', 0, 0, 1, 2);

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
INSERT INTO `product` VALUES (2021060300010006, '哇哈哈矿泉水', 3.00, 1000, '2021-06-03 15:51:55', '立方', 1, 1);
INSERT INTO `product` VALUES (2021060300010010, '哇哈哈矿泉水', 3.00, 1000, '2021-06-04 16:44:04', '立方', 1, 1);
INSERT INTO `product` VALUES (2021060300010011, '哇哈哈矿泉水', 3.00, 1000, '2021-06-04 16:44:12', '立方', 1, 1);
INSERT INTO `product` VALUES (2021060300010012, '哇哈哈矿泉水', 3.00, 1000, '2021-06-04 16:44:20', '立方', 1, 1);
INSERT INTO `product` VALUES (2021060300010013, '哇哈哈矿泉水', 3.00, 1000, '2021-06-04 16:44:26', '立方', 1, 1);
INSERT INTO `product` VALUES (2021060300010014, '哇哈哈矿泉水', 3.00, 1000, '2021-06-04 16:44:30', '立方', 1, 1);
INSERT INTO `product` VALUES (2021060300010015, '哇哈哈矿泉水', 3.00, 1000, '2021-06-04 16:44:34', '立方', 1, 1);
INSERT INTO `product` VALUES (2021060300010016, '哇哈哈矿泉水', 3.00, 1000, '2021-06-08 10:42:02', '立方', 1, 1);
INSERT INTO `product` VALUES (2021060300010017, '护柱', 100.00, 1001, '2021-06-08 15:08:41', '立方', 1, 1);

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
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of store
-- ----------------------------
INSERT INTO `store` VALUES (1, '哇哈哈库存', 0, 1000, 100, 25);
INSERT INTO `store` VALUES (3, '护柱库存', 0, 1001, 100, 2);

SET FOREIGN_KEY_CHECKS = 1;
