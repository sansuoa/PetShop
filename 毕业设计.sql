/*
Navicat MySQL Data Transfer

Source Server         : 主机
Source Server Version : 50719
Source Host           : localhost:3306
Source Database       : 毕业设计

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2023-06-11 12:08:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '收货地址id',
  `uid` bigint(20) DEFAULT NULL COMMENT '归属的用户id',
  `name` varchar(20) DEFAULT NULL COMMENT '收货人姓名',
  `province_code` varchar(15) DEFAULT NULL COMMENT '省-行政代号',
  `city_code` varchar(15) DEFAULT NULL COMMENT '市-行政代号',
  `area_code` varchar(15) DEFAULT NULL COMMENT '区-行政代号',
  `zip` char(6) DEFAULT NULL COMMENT '邮政编码',
  `address` varchar(50) DEFAULT NULL COMMENT '详细地址',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机',
  `tel` varchar(20) DEFAULT NULL COMMENT '固话',
  `tag` varchar(6) DEFAULT NULL COMMENT '标签',
  `is_default` int(11) DEFAULT NULL COMMENT '是否默认：0-不默认，1-默认',
  `create_user` varchar(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(20) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES ('4', '1', 'controller', '北京市', '北京市市辖区', '东城区', '', '北京大学', '18649208896', '', '学校', '1', null, '2023-04-07 11:17:43', null, '2023-04-28 00:00:12');

-- ----------------------------
-- Table structure for cart
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '购物车数据id',
  `uid` bigint(20) NOT NULL COMMENT '用户id',
  `pid` bigint(20) NOT NULL COMMENT '商品id',
  `price` decimal(10,0) DEFAULT NULL COMMENT '加入时商品单价',
  `num` int(11) DEFAULT NULL COMMENT '商品数量',
  `create_user` varchar(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(20) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cart
-- ----------------------------
INSERT INTO `cart` VALUES ('17', '1', '87', '19300', '1', null, '2023-04-28 21:00:09', null, '2023-04-28 21:00:09');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type` int(11) DEFAULT NULL COMMENT '类型   1 菜品分类 2 套餐分类',
  `name` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '分类名称',
  `sort` int(11) NOT NULL DEFAULT '0' COMMENT '顺序',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_user` bigint(20) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `idx_category_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='菜品及套餐分类';

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1', '1', '宠物狗', '1', '2023-04-06 11:14:11', '2023-05-17 00:28:59', '1', '1');
INSERT INTO `category` VALUES ('4', '1', '宠物猫', '2', '2023-04-10 10:49:43', '2023-04-21 13:28:37', '1', '1');
INSERT INTO `category` VALUES ('7', '1', '家养鱼', '3', '2023-04-10 11:00:42', '2023-04-21 13:28:43', '1', '1');
INSERT INTO `category` VALUES ('9', '1', '家养鸟', '4', '2023-04-10 11:04:19', '2023-04-21 13:28:51', '1', '1');
INSERT INTO `category` VALUES ('16', '1', '猫粮', '50001', '2023-04-20 21:07:18', '2023-04-20 21:07:18', '1', '1');
INSERT INTO `category` VALUES ('17', '1', '狗粮', '50002', '2023-04-20 21:07:24', '2023-04-20 21:07:24', '1', '1');
INSERT INTO `category` VALUES ('18', '1', '宠物玩具', '50003', '2023-04-20 21:07:56', '2023-04-20 21:07:56', '1', '1');
INSERT INTO `category` VALUES ('19', '1', '宠物用品', '50004', '2023-04-20 21:08:03', '2023-04-20 21:08:03', '1', '1');
INSERT INTO `category` VALUES ('20', '1', '宠物服饰', '50005', '2023-04-20 21:08:12', '2023-04-20 21:08:12', '1', '1');
INSERT INTO `category` VALUES ('21', '2', '狗狗大礼包', '100001', '2023-04-22 23:24:14', '2023-04-22 23:24:14', '1', '1');
INSERT INTO `category` VALUES ('22', '1', '111', '111', '2023-05-29 16:09:04', '2023-05-29 16:09:04', '1', '1');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pid` bigint(20) DEFAULT NULL,
  `cid` bigint(20) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  `content` varchar(400) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('5', '58', null, 'controller', '感觉很不错', '2023-04-20 19:25:32', '2023-04-20 19:25:32');
INSERT INTO `comment` VALUES ('6', '58', null, 'controller', '真的很可爱，强烈推荐', '2023-04-20 20:48:31', '2023-04-20 20:48:31');
INSERT INTO `comment` VALUES ('7', '58', null, 'controller', '我已经买了，真不错', '2023-04-20 20:48:43', '2023-04-20 20:48:43');
INSERT INTO `comment` VALUES ('8', '69', null, 'controller', '你好吗', '2023-04-25 14:59:33', '2023-04-25 14:59:33');
INSERT INTO `comment` VALUES ('10', null, '3', 'controller', '你好吗', null, null);
INSERT INTO `comment` VALUES ('12', '88', null, 'controller', '商品不错，非常建议购买', '2023-04-28 20:07:33', '2023-04-28 20:07:33');
INSERT INTO `comment` VALUES ('13', '104', null, 'controller', '·11', '2023-05-29 16:06:36', '2023-05-29 16:06:36');

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '姓名',
  `username` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '用户名',
  `password` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '密码',
  `phone` varchar(11) COLLATE utf8_bin NOT NULL COMMENT '手机号',
  `sex` varchar(2) COLLATE utf8_bin NOT NULL COMMENT '性别',
  `id_number` varchar(18) COLLATE utf8_bin NOT NULL COMMENT '身份证号',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态 0:禁用，1:正常',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_user` bigint(20) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `idx_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='员工信息';

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES ('1', 'admin', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '11451419198', '1', '120110200104163050', '1', '2023-04-06 00:00:00', '2023-04-06 11:13:14', '1', '1');
INSERT INTO `employee` VALUES ('2', '博丽灵梦', '幻想乡', 'e10adc3949ba59abbe56e057f20f883e', '11451419198', '0', '120110200104163050', '1', '2023-04-06 11:13:48', '2023-05-28 19:57:49', '1', '1');

-- ----------------------------
-- Table structure for leave_condition
-- ----------------------------
DROP TABLE IF EXISTS `leave_condition`;
CREATE TABLE `leave_condition` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `leave_id` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '礼包id ',
  `sale_id` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '商品id',
  `name` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '礼包名称 （冗余字段）',
  `price` decimal(10,2) DEFAULT NULL COMMENT '商品原价（冗余字段）',
  `copies` int(11) NOT NULL COMMENT '份数',
  `sort` int(11) NOT NULL DEFAULT '0' COMMENT '排序',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_user` bigint(20) DEFAULT NULL COMMENT '修改人',
  `is_deleted` int(11) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='套餐菜品关系';

-- ----------------------------
-- Records of leave_condition
-- ----------------------------
INSERT INTO `leave_condition` VALUES ('7', '3', '58', '拉布拉多犬', '178900.00', '1', '0', '2023-04-22 23:43:14', '2023-04-22 23:43:14', '1', '1', '0');
INSERT INTO `leave_condition` VALUES ('8', '3', '59', '网易严选 狗粮 无谷牛肉鸡肉三拼全价犬粮', '6900.00', '2', '0', '2023-04-22 23:43:14', '2023-04-22 23:43:14', '1', '1', '0');
INSERT INTO `leave_condition` VALUES ('9', '3', '61', '力狼（LILANG） 狗粮 金毛阿拉斯加', '32900.00', '2', '0', '2023-04-22 23:43:14', '2023-04-22 23:43:14', '1', '1', '0');
INSERT INTO `leave_condition` VALUES ('10', '4', '58', '拉布拉多犬', '178900.00', '2', '0', '2023-04-24 14:22:37', '2023-04-24 14:22:37', '1', '1', '0');
INSERT INTO `leave_condition` VALUES ('11', '5', '58', '拉布拉多犬', '178900.00', '1', '0', '2023-04-24 14:23:31', '2023-04-24 14:23:31', '1', '1', '0');
INSERT INTO `leave_condition` VALUES ('12', '5', '61', '力狼（LILANG） 狗粮 金毛阿拉斯加', '32900.00', '2', '0', '2023-04-24 14:23:31', '2023-04-24 14:23:31', '1', '1', '0');
INSERT INTO `leave_condition` VALUES ('29', '14', '87', '皇家狗粮 贵宾成犬狗粮 犬粮 小型犬 PD30 通用粮 10月-8岁 3KG', '19300.00', '3', '0', '2023-04-28 00:10:48', '2023-04-28 00:10:48', '1', '1', '0');
INSERT INTO `leave_condition` VALUES ('30', '14', '88', '台派狗粮20斤装通用型金毛泰迪比熊拉布拉多40小型成犬大型幼犬粮10kg', '7990.00', '3', '0', '2023-04-28 00:10:48', '2023-04-28 00:10:48', '1', '1', '0');
INSERT INTO `leave_condition` VALUES ('31', '14', '94', ' 萌嘟星宠 自动逗猫球猫咪玩具激光灯USB充电自动变向', '3900.00', '1', '0', '2023-04-28 00:10:48', '2023-04-28 00:10:48', '1', '1', '0');
INSERT INTO `leave_condition` VALUES ('32', '14', '92', '尖叫玩具鸡狗狗玩具发声泰迪中小型犬绝望鸡咕咕鸡惨叫鸡尖叫鸡宠物用品批发 尖叫鸡 大号40cm', '2300.00', '1', '0', '2023-04-28 00:10:48', '2023-04-28 00:10:48', '1', '1', '0');
INSERT INTO `leave_condition` VALUES ('33', '14', '58', '拉布拉多犬', '178900.00', '1', '0', '2023-04-28 00:10:48', '2023-04-28 00:10:48', '1', '1', '0');
INSERT INTO `leave_condition` VALUES ('34', '15', '104', '柴墨 金毛幼犬宠物狗狗活体大型寻回犬智商高聪明温顺导盲犬小狗仔 金毛幼犬', '149900.00', '1', '0', '2023-04-28 16:13:23', '2023-04-28 16:13:23', '1', '1', '0');
INSERT INTO `leave_condition` VALUES ('35', '15', '87', '皇家狗粮 贵宾成犬狗粮 犬粮 小型犬 PD30 通用粮 10月-8岁 3KG', '19300.00', '2', '0', '2023-04-28 16:13:23', '2023-04-28 16:13:23', '1', '1', '0');
INSERT INTO `leave_condition` VALUES ('36', '15', '66', '花欧利两（LILANG） 狗粮 金毛阿拉斯加', '32900.00', '2', '0', '2023-04-28 16:13:23', '2023-04-28 16:13:23', '1', '1', '0');

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `description` varchar(2000) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES ('1', '宠物商城基本介绍', '宠物商店（Pet Shop ）是位于居民区附近，以经营宠物衣食住行各方面商品为主，满足宠民需要为主旨的小型商店。宠物商店，最早起源于美国，之后随全国各地的宠物经济发展而发展，最终流传于世界各地。\n\n而在互联网+的时代,它它宠物商城随着新技术踏步而来，宠物商城是一家专门销售宠物衣食住行等各种宠物用品的店铺。宠物商城始终秉承着宠物健康生活的理念，为广大宠物提供健康可爱的产品与服务。在这里，您可以购买到口感优良的宠物食品，柔软舒适的宠物寝具，漂亮时尚的宠物服饰，各种有趣的玩具和宠物训练用具等。我们致力于为您提供优质商品和满意的购物体验。此外，我们还会定期举行各种关于宠物健康生活的讲座和活动，让您更好地了解如何照顾您的宠物，保持宠物的健康和快乐。欢迎光临宠物商城，让我们一起为宠物打造更美好的生活。\n\n我们网上商城是有很多优势的，比如：\n\n宠物专家指导：我们拥有一支经验丰富的宠物专家团队，他们将为您提供宠物饮食健康、日常护理、行为训练等方面的专业指导，让您在照顾宠物上更加得心应手。\n\n宠物送货上门：在宠物商城购物，您可以选择送货上门的服务，省去了您去店里购买的时间和精力，让您更轻松地享受购物乐趣。\n\n线上线下同步：宠物商城不仅在实体店面提供服务，同时我们也在网上，您可以通过我们的官方网站或者移动应用程序进行购物和咨询，随时随地获取您需要的服务。\n\n欢迎来到宠物商城，希望你享受在这里购物的体验！');
INSERT INTO `notice` VALUES ('2', '基本功能介绍', '以下是宠物商城的一些基本功能介绍：\n\n商品浏览：用户可以在宠物商城的首页上浏览不同类别的宠物用品以及宠物食品商品，也可以通过搜索栏搜索关键字来查找特定的商品。\n\n商品详情：用户可以点击商品卡片查看商品详情，包括商品描述、价格、评价、库存等信息，进一步了解该商品的信息。\n\n商品购买：用户可以将感兴趣的商品添加到购物车中，并在购物车页面结算并购买商品。\n\n评价功能：用户可以对已购买的商品进行评价并给出评分，同时也可以查看其他用户对该商品的评价。\n\n我的订单：用户可以查看自己的订单记录，并在订单详情中查看订单的具体信息、收货地址等。\n\n订单支付：用户需要在确认订单后进行支付，支付方式包括在线支付、货到付款等。\n\n商品收货：用户可以在收到商品后对商品进行签收，并在订单页面确认收货。\n\n订单取消：用户可以在订单页面申请取消订单，商家也可以根据情况选择取消订单。');
INSERT INTO `notice` VALUES ('3', '客户服务公告', '尊敬的用户：\n\n感谢您选择进入我们的宠物商\n\n\n我们的管理员将全天候为您提供优质的服务，帮助您解决问题，并不断完善我们的服务。感谢您的支持和信任。\n\n此致，\n\n敬礼！\n\n它它宠物商城');
INSERT INTO `notice` VALUES ('4', '最新公告', '尊敬的宠物家长们，非常感谢您一直以来的支持与信任！为了满足您和毛孩的需求，我们特别选择了多种宠物商品供您选购，涵盖了从宠物饮食、日常用品到玩具、训练等各个方面。无论您是喜欢猫咪还是狗狗，或者有其他小伙伴，我们都会提供最最合适的商品，满足不同品种宠物的需求。同时，我们站在您的角度，提供专业的养宠指南，让您为毛孩创造一个健康、舒适、快乐的生活环境。更好的消息是：我们的商品和服务完全符合您的预算，毛孩的生活从此在宠物商城变得更加完美！');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `uid` bigint(20) NOT NULL COMMENT '用户id',
  `recv_name` varchar(20) NOT NULL COMMENT '收货人姓名',
  `recv_phone` varchar(20) DEFAULT NULL COMMENT '收货人电话',
  `recv_province` varchar(15) DEFAULT NULL COMMENT '收货人所在省',
  `recv_city` varchar(15) DEFAULT NULL COMMENT '收货人所在市',
  `recv_area` varchar(15) DEFAULT NULL COMMENT '收货人所在区',
  `recv_address` varchar(50) DEFAULT NULL COMMENT '收货详细地址',
  `total_price` bigint(20) DEFAULT NULL COMMENT '总价',
  `status` int(11) DEFAULT NULL COMMENT '状态：0-未支付，1-已支付，2-已到货，3-已收货，4-已取消 5-已退款',
  `is_leave` int(11) DEFAULT NULL,
  `order_time` datetime DEFAULT NULL COMMENT '下单时间',
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
  `create_user` varchar(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(20) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('64', '1', 'controller', '1145149901', '北京市', '北京市市辖区', '东城区', '北京大学', '149900', '0', null, '2023-05-28 14:59:59', '2023-05-28 15:00:03', '1', '2023-05-28 14:59:59', null, '2023-05-28 15:22:01');
INSERT INTO `orders` VALUES ('65', '1', 'controller', '1145149901', '北京市', '北京市市辖区', '东城区', '北京大学', '149900', '2', null, '2023-05-28 15:06:05', '2023-05-28 15:06:10', null, '2023-05-28 15:06:05', null, '2023-05-28 15:06:26');
INSERT INTO `orders` VALUES ('66', '1', 'controller', '1145149901', '北京市', '北京市市辖区', '东城区', '北京大学', '149900', '2', null, '2023-05-28 15:19:39', '2023-05-28 15:19:44', null, '2023-05-28 15:19:39', null, '2023-05-28 15:20:06');
INSERT INTO `orders` VALUES ('67', '1', 'controller', '1145149901', '北京市', '北京市市辖区', '东城区', '北京大学', '169200', '5', null, '2023-05-28 15:24:05', '2023-05-28 15:24:08', null, '2023-05-28 15:24:05', '1', '2023-05-28 15:24:51');
INSERT INTO `orders` VALUES ('68', '1', 'controller', '1145149901', '北京市', '北京市市辖区', '东城区', '北京大学', '26800', '5', null, '2023-05-28 15:31:24', '2023-05-28 15:31:30', null, '2023-05-28 15:31:24', null, '2023-05-28 15:31:47');
INSERT INTO `orders` VALUES ('69', '1', 'controller', '1145149901', '北京市', '北京市市辖区', '东城区', '北京大学', '2530000', '0', '1', '2023-05-28 15:44:13', null, null, '2023-05-28 15:44:13', null, '2023-05-28 15:44:13');
INSERT INTO `orders` VALUES ('70', '1', 'controller', '1145149901', '北京市', '北京市市辖区', '东城区', '北京大学', '26800', '2', null, '2023-05-28 15:54:05', '2023-05-28 21:18:25', null, '2023-05-28 15:54:05', null, '2023-05-29 16:10:11');
INSERT INTO `orders` VALUES ('71', '1', 'controller', '1145149901', '北京市', '北京市市辖区', '东城区', '北京大学', '2070000', '1', '1', '2023-05-28 15:54:32', '2023-05-28 15:54:37', null, '2023-05-28 15:54:32', null, '2023-05-28 15:54:38');
INSERT INTO `orders` VALUES ('72', '1', 'controller', '1145149901', '北京市', '北京市市辖区', '东城区', '北京大学', '1150000', '5', '1', '2023-05-28 15:59:11', '2023-05-28 15:59:15', null, '2023-05-28 15:59:11', '1', '2023-05-28 16:01:03');
INSERT INTO `orders` VALUES ('73', '1', 'controller', '1145149901', '北京市', '北京市市辖区', '东城区', '北京大学', '460000', '3', '1', '2023-05-28 16:00:32', '2023-05-28 16:00:36', null, '2023-05-28 16:00:32', null, '2023-05-29 16:07:54');
INSERT INTO `orders` VALUES ('74', '1', 'controller', '1145149901', '北京市', '北京市市辖区', '东城区', '北京大学', '19300', '1', null, '2023-05-29 16:07:32', '2023-05-29 16:07:42', null, '2023-05-29 16:07:32', null, '2023-05-29 16:07:42');

-- ----------------------------
-- Table structure for order_detail
-- ----------------------------
DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '订单中的商品记录的id',
  `oid` bigint(20) NOT NULL COMMENT '所归属的订单的id',
  `pid` bigint(20) NOT NULL COMMENT '商品的id',
  `name` varchar(100) NOT NULL COMMENT '商品标题',
  `image` varchar(500) DEFAULT NULL COMMENT '商品图片',
  `price` bigint(20) DEFAULT NULL COMMENT '商品价格',
  `num` int(11) DEFAULT NULL COMMENT '购买数量',
  `create_user` varchar(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(20) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order_detail
-- ----------------------------
INSERT INTO `order_detail` VALUES ('78', '64', '104', '柴墨 金毛幼犬宠物狗狗活体大型寻回犬智商高聪明温顺导盲犬小狗仔 金毛幼犬', '420de016-29ed-45f9-9963-ab39b8e38058.png,03a1886f-c7bd-419b-ab0c-8a6e066b7d11.avif,6e1791c1-77f5-4833-bf5f-d2ac77a3a5c9.png,c63191ef-db7c-4fdf-9754-01a91a281489.png', '149900', '1', '1', '2023-05-28 15:00:00', '1', '2023-05-28 15:00:00');
INSERT INTO `order_detail` VALUES ('79', '65', '104', '柴墨 金毛幼犬宠物狗狗活体大型寻回犬智商高聪明温顺导盲犬小狗仔 金毛幼犬', '420de016-29ed-45f9-9963-ab39b8e38058.png,03a1886f-c7bd-419b-ab0c-8a6e066b7d11.avif,6e1791c1-77f5-4833-bf5f-d2ac77a3a5c9.png,c63191ef-db7c-4fdf-9754-01a91a281489.png', '149900', '1', null, '2023-05-28 15:06:05', null, '2023-05-28 15:06:05');
INSERT INTO `order_detail` VALUES ('80', '66', '104', '柴墨 金毛幼犬宠物狗狗活体大型寻回犬智商高聪明温顺导盲犬小狗仔 金毛幼犬', '420de016-29ed-45f9-9963-ab39b8e38058.png,03a1886f-c7bd-419b-ab0c-8a6e066b7d11.avif,6e1791c1-77f5-4833-bf5f-d2ac77a3a5c9.png,c63191ef-db7c-4fdf-9754-01a91a281489.png', '149900', '1', null, '2023-05-28 15:19:39', null, '2023-05-28 15:19:39');
INSERT INTO `order_detail` VALUES ('81', '67', '87', '皇家狗粮 贵宾成犬狗粮 犬粮 小型犬 PD30 通用粮 10月-8岁 3KG', '871c2e50-d4c5-415a-97ef-2ea6d55d046a.avif,0b7b57c0-8ec9-4a03-b856-10c9d07e922d.avif,81175067-0303-4894-aba4-bba6325620e7.avif,beff50ed-e0bc-4835-a1a8-6d8cec05a77e.avif', '19300', '1', null, '2023-05-28 15:24:05', null, '2023-05-28 15:24:05');
INSERT INTO `order_detail` VALUES ('82', '67', '104', '柴墨 金毛幼犬宠物狗狗活体大型寻回犬智商高聪明温顺导盲犬小狗仔 金毛幼犬', '420de016-29ed-45f9-9963-ab39b8e38058.png,03a1886f-c7bd-419b-ab0c-8a6e066b7d11.avif,6e1791c1-77f5-4833-bf5f-d2ac77a3a5c9.png,c63191ef-db7c-4fdf-9754-01a91a281489.png', '149900', '1', null, '2023-05-28 15:24:05', null, '2023-05-28 15:24:05');
INSERT INTO `order_detail` VALUES ('83', '68', '79', '派旺（PETWANT）外挂笼式自动喂食器宠物猫咪狗狗粮兔子兔粮悬挂式定时定量投食器 悬挂式笼内喂食器', '0a0363fd-7b15-43bf-b5ca-17047bfa50a8.avif,16962221-b398-4260-9bdf-10fb74bc61b1.avif,da193804-c3c4-445d-92e0-584bd2937813.avif,024ff6d8-6a43-4107-bc6f-d33416aff344.avif', '26800', '1', null, '2023-05-28 15:31:24', null, '2023-05-28 15:31:24');
INSERT INTO `order_detail` VALUES ('84', '69', '15', '狗狗欢乐包', '3c07ef2e-9db6-49fb-a60e-5589c190b5d2.png,a1e4db94-24ea-4064-a307-3770092012f2.png,e061631b-b73c-40e7-9a27-0c1511801252.png,94afc9b8-27cb-4054-9b0d-53910c8fcc00.png', '230000', '11', null, '2023-05-28 15:44:13', null, '2023-05-28 15:44:13');
INSERT INTO `order_detail` VALUES ('85', '70', '79', '派旺（PETWANT）外挂笼式自动喂食器宠物猫咪狗狗粮兔子兔粮悬挂式定时定量投食器 悬挂式笼内喂食器', '0a0363fd-7b15-43bf-b5ca-17047bfa50a8.avif,16962221-b398-4260-9bdf-10fb74bc61b1.avif,da193804-c3c4-445d-92e0-584bd2937813.avif,024ff6d8-6a43-4107-bc6f-d33416aff344.avif', '26800', '1', null, '2023-05-28 15:54:05', null, '2023-05-28 15:54:05');
INSERT INTO `order_detail` VALUES ('86', '71', '15', '狗狗欢乐包', '3c07ef2e-9db6-49fb-a60e-5589c190b5d2.png,a1e4db94-24ea-4064-a307-3770092012f2.png,e061631b-b73c-40e7-9a27-0c1511801252.png,94afc9b8-27cb-4054-9b0d-53910c8fcc00.png', '230000', '9', null, '2023-05-28 15:54:32', null, '2023-05-28 15:54:32');
INSERT INTO `order_detail` VALUES ('87', '72', '15', '狗狗欢乐包', '3c07ef2e-9db6-49fb-a60e-5589c190b5d2.png,a1e4db94-24ea-4064-a307-3770092012f2.png,e061631b-b73c-40e7-9a27-0c1511801252.png,94afc9b8-27cb-4054-9b0d-53910c8fcc00.png', '230000', '5', null, '2023-05-28 15:59:12', null, '2023-05-28 15:59:12');
INSERT INTO `order_detail` VALUES ('88', '73', '15', '狗狗欢乐包', '3c07ef2e-9db6-49fb-a60e-5589c190b5d2.png,a1e4db94-24ea-4064-a307-3770092012f2.png,e061631b-b73c-40e7-9a27-0c1511801252.png,94afc9b8-27cb-4054-9b0d-53910c8fcc00.png', '230000', '2', null, '2023-05-28 16:00:32', null, '2023-05-28 16:00:32');
INSERT INTO `order_detail` VALUES ('89', '74', '87', '皇家狗粮 贵宾成犬狗粮 犬粮 小型犬 PD30 通用粮 10月-8岁 3KG', '871c2e50-d4c5-415a-97ef-2ea6d55d046a.avif,0b7b57c0-8ec9-4a03-b856-10c9d07e922d.avif,81175067-0303-4894-aba4-bba6325620e7.avif,beff50ed-e0bc-4835-a1a8-6d8cec05a77e.avif', '19300', '1', null, '2023-05-29 16:07:32', null, '2023-05-29 16:07:32');

-- ----------------------------
-- Table structure for pet_leave
-- ----------------------------
DROP TABLE IF EXISTS `pet_leave`;
CREATE TABLE `pet_leave` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `category_id` bigint(20) NOT NULL COMMENT '菜品分类id',
  `name` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '套餐名称',
  `price` decimal(10,2) NOT NULL COMMENT '套餐价格',
  `status` int(11) DEFAULT NULL COMMENT '状态 0:停用 1:启用',
  `description` varchar(512) COLLATE utf8_bin DEFAULT NULL COMMENT '描述信息',
  `image` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '图片',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_user` bigint(20) DEFAULT NULL COMMENT '修改人',
  `is_deleted` int(11) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `amout` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `idx_setmeal_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='套餐';

-- ----------------------------
-- Records of pet_leave
-- ----------------------------
INSERT INTO `pet_leave` VALUES ('3', '21', '拉布拉多犬礼包', '219900.00', '1', '拉布拉多犬大礼包！礼包中含有一只拉布拉多，两包网易严选 狗粮 无谷牛肉鸡肉三拼全价犬粮，和两包力狼（LILANG） 狗粮 金毛阿拉斯加犬粮，合起来共有50kg，给狗狗梦幻般的享受，更有黑色拉布拉多温顺可爱', 'afa432bd-ed12-4bb3-a7ee-e7c3f82430a1.jpg,e256fea6-6dc6-4623-a335-7bb9b0b478ac.jpg,64dc241e-4ab7-4192-a0f5-4d66744100e5.jpg,0ac139d3-407a-4710-aef5-544fad1a3506.jpg', '2023-04-22 23:43:14', '2023-04-22 23:43:14', '1', '1', '0', '10');
INSERT INTO `pet_leave` VALUES ('4', '21', '礼包2', '150000.00', '1', '这是为了凑齐礼包测试的四个里的一个，拉布拉多犬大礼包！礼包中含有一只拉布拉多，两包网易严选 狗粮 无谷牛肉鸡肉三拼全价犬粮，和两包力狼（LILANG） 狗粮 金毛阿拉斯加犬粮，合起来共有50kg，给狗狗梦幻般的享受，更有黑色拉布拉多温顺可爱', '5d80f22c-dae2-4689-a472-c17deae08400.jpg,28780454-9f38-4e8b-b62b-f5fa78e2d321.jpg,ece47c9e-601f-4f09-93c6-7c62884cb823.jpg,bae069f1-14c8-40eb-a684-1e5d175fd9c8.jpg', '2023-04-24 14:22:37', '2023-04-24 14:22:37', '1', '1', '0', '10');
INSERT INTO `pet_leave` VALUES ('5', '21', '礼包3', '140000.00', '1', '这是为了测试礼包里的四个里的第三个，拉布拉多犬大礼包！礼包中含有一只拉布拉多，两包网易严选 狗粮 无谷牛肉鸡肉三拼全价犬粮，和两包力狼（LILANG） 狗粮 金毛阿拉斯加犬粮，合起来共有50kg，给狗狗梦幻般的享受，更有黑色拉布拉多温顺可爱', 'd3b2ce97-23a4-4240-8aaa-a92a669934ee.jpg,2dcd33c5-8eda-4cfb-84ca-65b3be8dad39.jpg,aac415fa-b8fa-4138-b539-fde2a94ad35c.jpg,a577ba51-32b0-496c-92a2-8ff79b8f8d62.jpg', '2023-04-24 14:23:31', '2023-04-24 14:23:31', '1', '1', '0', '10');
INSERT INTO `pet_leave` VALUES ('14', '21', '狗狗欢乐大礼包', '230000.00', '1', '宠物商城携最大诚意带着狗狗大礼包走来！\n2300即可收获包括狗狗玩具，狗狗主粮和一条可可爱爱的狗，还在等什么，快点行动起来吧', 'c2d1c5cb-9c66-4956-87b1-49008ad5d55d.avif,eefb1b9f-efa8-498f-a4e5-8c2ac000a950.avif,ab2e080f-a50b-48f3-a57f-ab186b86c6d0.avif,81557398-9cc6-4e00-ad35-7d046505ed8b.avif,0931e5d1-8983-45fe-914f-869782b3e547.avif', '2023-04-28 00:10:48', '2023-04-28 00:10:48', '1', '1', '0', '10');
INSERT INTO `pet_leave` VALUES ('15', '21', '狗狗欢乐包', '230000.00', '1', '这是一个礼包测试，可见礼包内商品有一只金毛，两包皇家狗粮和两包花欧狗粮。仅供测试', '3c07ef2e-9db6-49fb-a60e-5589c190b5d2.png,a1e4db94-24ea-4064-a307-3770092012f2.png,e061631b-b73c-40e7-9a27-0c1511801252.png,94afc9b8-27cb-4054-9b0d-53910c8fcc00.png', '2023-04-28 16:13:20', '2023-05-28 16:00:36', '1', null, '0', '3');

-- ----------------------------
-- Table structure for pet_sale
-- ----------------------------
DROP TABLE IF EXISTS `pet_sale`;
CREATE TABLE `pet_sale` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '菜品名称',
  `category_id` bigint(20) NOT NULL COMMENT '菜品分类id',
  `price` decimal(10,2) DEFAULT NULL COMMENT '菜品价格',
  `image` varchar(200) COLLATE utf8_bin NOT NULL COMMENT '图片',
  `description` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '描述信息',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '0 停售 1 起售',
  `sort` int(11) NOT NULL DEFAULT '0' COMMENT '顺序',
  `amout` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_user` bigint(20) DEFAULT NULL COMMENT '修改人',
  `is_deleted` int(11) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `idx_dish_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=105 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='菜品管理';

-- ----------------------------
-- Records of pet_sale
-- ----------------------------
INSERT INTO `pet_sale` VALUES ('50', '狗3', '1', '6000.00', '7862e13f-36b1-4654-9fcf-4df9d492eaf4.jpg', '真的很可爱', '1', '0', '10', '2023-04-14 16:51:33', '2023-04-14 16:51:33', '1', '1', '0');
INSERT INTO `pet_sale` VALUES ('51', '狗4', '1', '7000.00', '57b4fa81-e0ac-43e2-9e50-41411edf56c9.jpeg', '', '1', '0', '10', '2023-04-14 16:51:54', '2023-04-14 16:51:54', '1', '1', '0');
INSERT INTO `pet_sale` VALUES ('58', '拉布拉多犬', '1', '178900.00', '84766d24-7c12-4501-bb93-74af56546f06.jpg,44a4ea3e-8a9e-458a-b7f6-c4b5f74214eb.jpg,b8369bb1-fd30-4edc-9e60-547cd38da32c.jpg,964a75f5-193a-4535-a78b-a023c32d7af8.jpg', '拉布拉多幼犬宠物狗狗活体 纯种拉布拉多犬小狗仔导盲犬幼崽 中大型寻回猎犬拉拉狗子 警犬搜救犬家庭伴侣 黑色\n犬瘟、细小质保丨纯种质保丨驱虫、疫苗已做丨视频一对一挑选丨全国发货丨终身饲养指导丨\n品牌： 本秋\n商品名称：拉布拉多幼犬宠物狗狗活体 纯种拉布拉多犬小狗仔导盲犬幼崽 中大型寻回猎犬拉拉狗子 警犬搜救犬家庭伴侣 黑色商品编号：10054057460843店铺： 伴西西宠物专营店商品毛重：11.', '1', '0', '10', '2023-04-20 18:57:50', '2023-04-20 18:57:50', '1', '1', '0');
INSERT INTO `pet_sale` VALUES ('59', '网易严选 狗粮 无谷牛肉鸡肉三拼全价犬粮', '17', '6900.00', '13c2b9b6-7f75-48b2-bb84-4dbc1b5aaac4.jpg,e42b2908-0c3a-4673-81a6-bb35f9f4ccce.jpg,af14bd72-7748-4f45-8224-d666647c927e.jpg,91f4f5c8-f288-4b71-b111-e9a8ecdb3f57.jpg', '品牌： 网易严选\n商品名称：网易严选全价三拼犬粮 2kg商品编号：100020361092商品毛重：2.05kg商品产地：安徽省宣城市货号：300279286水溶性氯化物（以Cl-计）：≥0.45%赖氨酸：≥1.2%总磷：≥1%粗脂肪：≥15%粗蛋白质：≥28%钙：≥1.2%粗灰分：≤10%配方：无谷粮单件净含量：501g-3kg粗纤维：≤5%水分：≤10%适用体型：通用适用阶段：全阶段口味：混合', '1', '0', '9', '2023-04-21 13:41:44', '2023-05-28 14:54:55', '1', null, '0');
INSERT INTO `pet_sale` VALUES ('66', '花欧利两（LILANG） 狗粮 金毛阿拉斯加', '17', '32900.00', '51c820ee-a99e-42d2-8a55-7b2c27bff6a1.avif,450b672f-7920-496f-8a2d-b4f75b067292.avif,09e63ba1-ac79-4099-a17d-773880eebc42.avif,21c989da-dec9-4af5-b93d-fbd44ae2ad0c.avif', '品牌： 力狼（LILANG）\n商品名称：力狼（LILANG） 狗粮 金毛阿拉斯加40拉布拉多萨摩耶马犬德牧大型犬 鲜肉配方｜肉粒双拼粮｜成犬20kg商品编号：10034633388967店铺： 力狼官方旗舰店商品毛重：10.0kg商品产地：中国大陆配方：鲜肉粮适用体型：通用适用阶段：全阶段口味：混合口味适用品种：通用，金毛，拉布拉多，阿拉斯加国产/进口：国产\n产品升级，假一赔十', '1', '0', '10', '2023-04-21 14:02:50', '2023-04-21 14:02:50', '1', '1', '0');
INSERT INTO `pet_sale` VALUES ('69', '拉布拉多（长）', '1', '200000.00', '98f79ef2-75f1-41a6-ac19-19d86831becc.jpg,d0503ced-b08b-4111-b545-87a704f63b02.jpg,54e95266-c513-4b55-878a-9b1ba42e85f9.jpg,c30943b6-48f8-4b22-a62c-cf8c2cdee3f4.jpg', '拉布拉多幼犬宠物狗狗活体 纯种拉布拉多犬小狗仔导盲犬幼崽 中大型寻回猎犬拉拉狗子 警犬搜救犬家庭伴侣 黑色\n犬瘟、细小质保丨纯种质保丨驱虫、疫苗已做丨视频一对一挑选丨全国发货丨终身饲养指导丨\n品牌： 本秋\n商品名称：拉布拉多幼犬宠物狗狗活体 纯种拉布拉多犬小狗仔导盲犬幼崽 中大型寻回猎犬拉拉狗子 警犬搜救犬家庭伴侣 黑色商品编号：10054057460843店铺： 伴西西宠物专营店商品毛重：11.', '1', '0', '10', '2023-04-24 18:11:28', '2023-04-24 18:11:28', '1', '1', '0');
INSERT INTO `pet_sale` VALUES ('71', '热带观赏鱼 皇后塘澧 澳洲火焰塘澧宠物鱼', '7', '560.00', '5239e2d8-e163-4b3e-8df3-f7f619b97801.jpeg,352c4f2e-2bfb-46f0-aa0c-b987ed694c7e.jpeg,e1233933-4688-444e-8244-529567dc242f.jpeg,97882118-d23e-4815-a20b-4c9575dad024.jpeg', '品牌： 热带观赏鱼 皇后塘澧 澳洲火焰塘澧宠物鱼\n商品名称：热带观赏鱼 皇后塘澧 澳洲火焰塘澧宠物鱼 商品编号：42583590276店铺： 鱼多乐宠物用品专营店商品毛重：3.0kg混养类型：可混养类别：冷水鱼/原生鱼适合温度：冷水适用范围：淡水', '1', '0', '10', '2023-04-26 00:18:53', '2023-04-26 00:18:53', '1', '1', '0');
INSERT INTO `pet_sale` VALUES ('72', '萌嘟星宠 狗狗玩具磨牙棒骨头耐咬洁齿棒牛', '18', '2680.00', '00cd35f5-abd6-41b1-aa4f-348a522afffa.avif,b12371fe-e401-424d-950f-1a911af4b2c9.avif,78fd8dd5-644c-4290-8539-6bd67201ec76.avif', '品牌： 萌嘟星宠\n商品名称：萌嘟星宠磨牙棒商品编号：100040663565商品毛重：150.00g商品产地：江苏徐州材质：PP(聚丙烯)适用体型：小型犬，中大型犬形状：骨头形类别：磨牙玩具，训练玩具，互动玩具国产/进口：国产', '1', '0', '10', '2023-04-26 00:23:25', '2023-04-26 00:23:25', '1', '1', '0');
INSERT INTO `pet_sale` VALUES ('73', '喵然心动 英国短毛猫活体幼猫金渐层银渐层蓝白蓝猫乳色乳白曼基康矮脚猫 英短蓝', '4', '199900.00', '21e3c1f0-25af-43c2-ab06-b94301a75cf3.jpg,9b9dc34c-b7f4-447c-b837-a5b2a6ea1209.jpg,bb0c47c6-abcb-4a30-9b78-510e058420f9.jpg,320647e7-2ef7-47e3-bedb-2ed77e63f8ef.jpg', '品牌： 喵然心动\n商品名称：喵然心动 英国短毛猫活体幼猫金渐层银渐层蓝白蓝猫乳色乳白曼基康矮脚猫 英短蓝白(联系客服选宠后下单)商品编号：10061873619418店铺： 喵然心动旗舰店商品毛重：1.5kg商品产地：中国大陆品种：金渐层宠物年龄：幼年宠物级别：血统级疫苗：1针是否注射芯片：未注射芯片换毛程度：正常换毛宠物性别：公驱虫次数：1次', '1', '0', '10', '2023-04-26 10:48:10', '2023-04-26 10:48:10', '1', '1', '0');
INSERT INTO `pet_sale` VALUES ('74', 'X5宠物消毒液猫瘟犬瘟狗狗室内环境细小用品500ML', '19', '8000.00', 'a9611a23-a776-424d-998b-92210e987b29.jpg,821e43e5-7457-4d97-9038-402d8fb83e86.jpg,1cf35fc7-f277-4570-ac56-39ad4c6f289e.jpg,a16e4abf-0747-40c5-8d39-fc2cdbde79d8.jpg', '品牌： X5\n商品名称：X5宠物消毒液猫瘟犬瘟狗狗室内环境细小用品500ML商品编号：24152264043店铺： 拜臣宠物用品专营店商品毛重：500.00g功效：杀菌适用对象：猫狗通用\n超浓缩光谱消毒液，消灭猫瘟犬疫，真菌细小', '1', '0', '10', '2023-04-26 16:40:35', '2023-04-26 16:40:35', '1', '1', '0');
INSERT INTO `pet_sale` VALUES ('75', 'HELLOJOY狗厕所蓝色大号大型犬泰迪狗狗用品尿盆便盆屎小型犬中型宠物冲水', '19', '2680.00', 'ddeaa03b-1010-430c-99ce-0677e6944361.jpg,98e2f051-613e-407d-a255-5eeac807a95e.jpg,7685c211-40f7-49a0-b07f-5b3db0e343cb.jpg,d1f4ab75-c45b-4360-8722-735290097b06.jpg', '品牌： HELLOJOY\n商品名称：HELLOJOY围栏狗厕所商品编号：100013443468商品毛重：0.79kg商品产地：中国大陆是否可折叠：不可折叠宽度：<55cm颜色：蓝色\n主体\n包装尺寸\n长50cm；宽39cm；高12.5cm\n包装清单\nHELLOJOY新款围栏狗厕所蓝色*1', '1', '0', '10', '2023-04-26 21:31:06', '2023-04-26 21:31:06', '1', '1', '0');
INSERT INTO `pet_sale` VALUES ('76', '派乐特 狗绳3件套狗链子红黑牵引绳S宠物狗狗圈胸背带遛小型犬泰迪用品', '19', '1500.00', '645cea7e-cba6-4fba-80b7-977f8179209d.avif,9dc69f70-f691-4fc4-b08a-0892b1479950.avif,5be4a073-7f5d-4a18-af34-e9e0e11b3085.avif,05d1ae98-6acf-4c10-8868-0d89a0842b1c.avif', '品牌： 派乐特\n商品名称：派乐特红黑三件套   S商品编号：100001463557\n商品毛重：300.00g   商品产地：中国大陆\n类别：普通牵引绳    功能：可调节背带\n材质：金属牵引头    数量：单头\n国产/进口：国产\n是否需要电源/电池：不需要\n适用对象：猫狗通用', '1', '0', '10', '2023-04-26 21:34:22', '2023-04-26 21:38:00', '1', '1', '0');
INSERT INTO `pet_sale` VALUES ('77', '联想（Lenovo）小新宠物智能一体机自动喂食器饮水机猫咪小型犬饭盆水碗远程视频', '19', '109900.00', '39ddff79-4327-4f69-86cd-0baf84569f24.avif,25d42bab-108d-4d0f-96a8-55ed12f98699.avif,8ae30a47-f0b9-4a29-8ab7-b51656429ee7.avif,661739c9-a392-468a-b0ec-fa128232c953.avif', '品牌： 联想（Lenovo）\n商品名称：联想小新宠物智能一体机商品         编号：100043621778       商品毛重：8.2kg\n商品产地：中国大陆       类别：饮水机，喂食器       是否支持APP操作：支持  \n功能：适配冻干          是否需要电源/电池：需要      适用对象：猫，小型犬\n主体\n包装尺寸\n长420mm；宽340mm；高200mm\n包装清单\n主机*1，净水桶*1，污水桶*1，粮桶*1，滤芯*1，水碗*1，食碗托盘*1，食碗*1，电源线*1', '1', '0', '10', '2023-04-26 21:41:02', '2023-04-26 21:41:02', '1', '1', '0');
INSERT INTO `pet_sale` VALUES ('78', 'Petmate美国进口Chuckit夜间宠物狗玩具球防丢夜光弹力球挥捡球杆 夜光弹球2只装（中）6.', '18', '10700.00', '8c8d5f65-baa6-4830-8eb8-bc2e36401f97.avif,174bebb6-4e83-4631-9079-914f7f6f6e4b.avif,bdd9f419-d0ec-41b3-bf7d-9981082f0f2b.avif,2b46ff86-ddc1-4f5f-9144-f7ee7d70e961.avif', '品牌： petmate\n商品名称：Petmate 美国进口Chuckit夜间宠物狗玩具球防丢夜光弹力球挥捡球杆 夜光弹球2只装（中）6.4cm\n商品编号：10023124534177     店铺： 都宠宠物用品专营店     商品毛重：100.00g\n类别：训练玩具，漏食玩具，互动玩具      适用体型：小型犬，中大型犬\n形状：球形       材质：橡胶       国产/进口：进口\n主体\n产品尺寸\n长10cm；宽10cm；高10cm', '1', '0', '10', '2023-04-26 21:43:29', '2023-04-26 21:43:29', '1', '1', '0');
INSERT INTO `pet_sale` VALUES ('79', '派旺（PETWANT）外挂笼式自动喂食器宠物猫咪狗狗粮兔子兔粮悬挂式定时定量投食器 悬挂式笼内喂食器', '19', '26800.00', '0a0363fd-7b15-43bf-b5ca-17047bfa50a8.avif,16962221-b398-4260-9bdf-10fb74bc61b1.avif,da193804-c3c4-445d-92e0-584bd2937813.avif,024ff6d8-6a43-4107-bc6f-d33416aff344.avif', '品牌： 派旺（PETWANT）\n\n商品名称：派旺（PETWANT）F4      商品编号：10030475239366       店铺： 派旺宠物生活旗舰店\n商品毛重：1.35kg        货号：F4LCD         类别：喂食器        适用对象：猫，小型犬，中型犬\n材质：ABS         是否支持APP操作：不支持国产/进口：国产       是否需要电源/电池：需要       \n功能：双电源供电，防卡粮\n包装清单\n电源适配器*1 喂食器主机*1 说明书*1', '1', '0', '9', '2023-04-26 21:46:59', '2023-05-28 21:18:25', '1', '1', '0');
INSERT INTO `pet_sale` VALUES ('80', 'KimPets狗窝四季通用可拆洗猫窝沙发睡觉用冬季保暖隧道狗床垫子宠物用品 浅绿色-升级加高款深睡窝', '19', '3680.00', '68afa0b3-2036-497c-9de7-54d8553933ce.avif,a953aa01-84e3-4f8d-9685-16810ba6c547.avif,c6ae507d-eb48-4587-98f5-c8a868f8c294.avif,d3d6b460-3dc6-4dd2-b0af-185133846bfe.avif', '品牌： KimPets\n\n商品名称：KimPets狗窝四季通用可拆洗猫窝沙发睡觉用冬季保暖隧道狗床垫子宠物用品 浅绿色-升级加高款深睡窝 L-60*45cm(15斤内宠物)\n商品编号：10060732681196       店铺： 锦和宠物专营店         商品毛重：1.0kg\n商品产地：中国大陆          类别：窝功能：可拆洗，恒温加热      适用季节：秋，春，冬，夏\n是否需要电源/电池：需要        适用对象：猫狗通用\n包装清单   暂无', '1', '0', '10', '2023-04-26 21:50:01', '2023-04-26 21:58:15', '1', '1', '0');
INSERT INTO `pet_sale` VALUES ('81', '鱼多趣 硝化细菌 525ml 硝化细菌鱼缸净水剂 养鱼用品活菌', '19', '2280.00', '991c8d9e-9978-4aef-86c5-e039252faa99.avif,730400d5-8a80-47a5-8207-b73c77d47947.avif,9bedfe5e-0d94-47fc-bd4e-11b910b2a812.avif,edaadb42-9bc0-4eea-bd5f-9b4d38b92837.avif', '品牌： 鱼多趣\n商品名称：鱼多趣XHXJ      商品编号：100011884391      商品毛重：0.6kg    商品产地：中国大陆 \n类别：净水剂\n宠物生活---水族---水族药剂鱼多趣XHXJ\n包装清单\n硝化细菌 525ml', '1', '0', '10', '2023-04-26 21:52:36', '2023-04-26 21:57:53', '1', '1', '0');
INSERT INTO `pet_sale` VALUES ('82', '澜凯过硫酸氢钾复合盐水产养殖改底净水王鱼虾塘分解淤泥降氨氮增氧片 【10%过硫~5kg/桶】可用25', '19', '5500.00', '2bc81da0-6743-4fe9-916f-365dfa7a0440.avif,55a73892-941e-4234-bc37-89cf5553fe0f.avif,4d0dbed7-a631-4e63-8719-7a076054b5dc.avif,39ab67f0-d887-4294-ac32-765da1f0147a.avif', '品牌： 澜凯\n商品名称：澜凯过硫酸氢钾复合盐      商品编号：10048708436638      店铺： 澜凯官方旗舰店\n商品毛重：10.2kg         商品产地：中国大陆        货号：过硫酸氢钾复合盐\n类别：水族盐              剂型：片剂           国产/进口：国产\n总净含量：>1000mL         适用场景：室外池塘\n包装清单    暂无', '1', '0', '10', '2023-04-26 21:54:43', '2023-04-26 21:57:32', '1', '1', '0');
INSERT INTO `pet_sale` VALUES ('83', ' 派得（prominent）派得狗粮 金毛拉布拉多哈士奇马犬萨摩耶德牧 中大型犬通用粮 成犬20kg', '17', '29400.00', '41673e6a-0ce8-4860-abb9-af147e80fc3e.avif,7f45e4f5-c094-4488-b378-59606eeea4de.avif,5602a5b7-ef19-4586-ba84-460cdd5c576e.avif,cce5223b-8cf3-4ea2-ae52-08bb7ffced8e.avif', '品牌： 派得（prominent）\n商品名称：派得（prominent）派得狗粮 金毛拉布拉多哈士奇马犬萨摩耶德牧 中大型犬通用粮 成犬20kg40斤\n商品编号：23867129337      店铺： 派得官方旗舰店       商品毛重：20.1kg    商品产地：中国大陆\n货号：PDCG-4204         \n水溶性氯化物（以Cl-计）：≥0.45%总磷：≥0.8%粗脂肪：≥10%单件净含量：15.1-30kg水分：≤10%\n适用阶段：成犬             口味：牛肉味赖氨酸：≥1%功效：肠胃调理钙：≥1%\n适用品种：通用粗灰分：≤9%       适用体型：大型犬粗纤维：≤4.5%粗蛋白质：≥23%\n配方：天然粮          国产/进口：国产\n主体  保质期\n18个月\n包装规格 1袋\n单件净含量 20kg\n包装清单 暂无', '1', '0', '10', '2023-04-26 22:14:50', '2023-04-26 22:14:50', '1', '1', '0');
INSERT INTO `pet_sale` VALUES ('84', '红狗RedDog 肉食风暴双拼冻干狗粮 鸡肉粗蛋白 全期全价成犬幼犬粮 肉食风暴犬粮8kg', '17', '34400.00', '6c10da5e-0bc1-42a7-a0da-791246935318.avif,2453dc05-b90a-4169-a74e-d65d42568049.avif,a2aaf63e-659c-458b-bfb8-335745cc5ef7.avif,698470ed-840a-43bb-bcb7-876aee878277.avif', '品牌： 红狗（RedDog）\n商品名称：红狗RedDog 肉食风暴双拼冻干狗粮 鸡肉粗蛋白 全期全价成犬幼犬粮 肉食风暴犬粮8kg\n商品编号：10067425387205         店铺： 红狗旗舰店        商品毛重：1.8kg\n配方：冻干粮赖氨酸：≥1%粗灰分：≤10%粗脂肪：≥12%钙：≥1%      适用品种：通用，贵宾，金毛，柯基\n粗纤维：≤5%粗蛋白质：≥31%水溶性氯化物（以Cl-计）：≥0.45%水分：≤10%\n适用体型：通用        适用阶段：全阶段       口味：鸡肉味总磷：≥0.9%      国产/进口：国产\n主体\n保质期  18个月\n包装清单   暂无', '1', '0', '10', '2023-04-26 22:17:12', '2023-04-26 22:21:44', '1', '1', '0');
INSERT INTO `pet_sale` VALUES ('85', '宝路狗粮泰迪狗粮中小型犬通用狗干粮 中小型犬牛肉7.5kg', '17', '15390.00', '38238b56-4c6f-409a-acfe-71cd48dfce0c.avif,6e39add2-1480-4eaf-9b8d-8b999147b621.avif,c0d1b0cb-e9e5-46d8-aa50-9c656bbdf254.avif,c206367a-bab7-46ef-8f3a-d68d23a1b217.avif', '品牌： 宝路（Pedigree）\n商品名称：宝路狗粮泰迪狗粮中小型犬通用狗干粮 中小型犬牛肉7.5kg        商品编号：65469954197\n店铺： 臻爱天元宠物用品旗舰店            商品毛重：1.8kg     水溶性氯化物（以Cl-计）：≥0.2%        赖氨酸：≥0.1%粗灰分：≤10%        适用品种：通用适用阶段：成犬总磷：≥0.5%粗脂肪：≥11%\n配方：通用粮              单件净含量：7.1-15kg      钙：≥0.8%    水分：≤10%\n适用体型：中小型犬       粗纤维：≤1%     口味：牛肉味粗蛋白质：≥23%     国产/进口：国产\n主体    保质期     18个月    \n包装规格    1袋\n单件净含量   7.5kg\n包装清单    暂无', '1', '0', '10', '2023-04-26 22:25:21', '2023-04-26 22:25:21', '1', '1', '0');
INSERT INTO `pet_sale` VALUES ('86', '法氏公爵小鹿犬狗粮专用成犬幼犬通用小型犬补钙美毛天然粮斤装 【牛肉粒+蛋黄粮】小鹿犬幼犬粮10斤', '17', '5280.00', '2c04f727-d706-41be-a775-c7ee5cdf93e7.avif,6fe5dfa4-e4c9-4baf-8ea2-878008b677bd.avif,f842aafd-c3f3-4bb7-9032-3179fe8ec6fd.avif,22065bed-7744-44cc-924d-4e3b2084a196.avif', '品牌： 法氏公爵\n商品名称：法氏公爵小鹿犬狗粮专用成犬幼犬通用小型犬补钙美毛天然粮斤装 【牛肉粒+蛋黄粮】小鹿犬幼犬粮10斤\n商品编号：10055511507113        店铺： 法氏公爵大迈专卖店       商品毛重：1.0kg\n功效：补钙水溶性氯化物（以Cl-计）：≥1.2%       适用阶段：全阶段    适用品种：通用\n粗蛋白质：≥23%总磷：≥1%粗脂肪：≥14%粗灰分：≤5%单件净含量：3.1-7kg钙：≥0.7%水分：≤7%\n适用体型：通用        粗纤维：≤1.5%       口味：牛肉味      配方：天然粮    赖氨酸：≥1%\n主体\n保质期   18个月\n原料    见包装\n添加剂   见包装\n详情   \n包装规格   1袋\n单件净含量   5kg\n包装清单   暂无', '1', '0', '10', '2023-04-26 22:29:13', '2023-04-26 22:29:13', '1', '1', '0');
INSERT INTO `pet_sale` VALUES ('87', '皇家狗粮 贵宾成犬狗粮 犬粮 小型犬 PD30 通用粮 10月-8岁 3KG', '17', '19300.00', '871c2e50-d4c5-415a-97ef-2ea6d55d046a.avif,0b7b57c0-8ec9-4a03-b856-10c9d07e922d.avif,81175067-0303-4894-aba4-bba6325620e7.avif,beff50ed-e0bc-4835-a1a8-6d8cec05a77e.avif', '品牌： 皇家（ROYAL CANIN）\n商品名称：皇家狗粮       商品编号：598279       商品毛重：3.07kg      商品产地：中国大陆\n功效：护肤美毛   赖氨酸：≥1%总磷：≥0.5%粗脂肪：≥17%粗蛋白质：≥28%钙：≥0.6%粗灰分：≤8%\n适用品种：贵宾          粗纤维：≤5%水溶性氯化物（以Cl-计）：≥0.4%水分：≤11%\n适用体型：小型犬       适用阶段：成犬      口味：混合口味     配方：通用粮    国产/进口：国产\n主体\n保质期    540天\n包装清单\nPD30贵宾泰迪成犬狗粮 全价粮 3kg X 1', '1', '0', '7', '2023-04-26 22:34:06', '2023-05-29 16:07:42', '1', null, '0');
INSERT INTO `pet_sale` VALUES ('88', '台派狗粮20斤装通用型金毛泰迪比熊拉布拉多40小型成犬大型幼犬粮10kg', '17', '7990.00', 'b7111646-b845-4229-9609-6378fb6d34c2.avif,dce245ea-af97-41ce-a0d6-00f386c0c878.avif,575ef008-00c1-4c12-84b3-45e5af3f375c.avif,65fd3d50-ce5d-4489-88b2-d2581485d73b.avif', '品牌： 台派（TAIPAI）\n商品名称：台派TAIPAI10kg-01       商品编号：100009285827       商品毛重：10.0kg\n商品产地：中国大陆             货号：6971207453835             配方：通用粮\n总磷：≥0.8%粗灰分：≤10%单件净含量：7.1-15kg水分：≤10%粗蛋白质：≥22%\n口味：牛肉味       赖氨酸：≥0.7%      功效：口腔护理钙：≥1%     适用品种：通用\n水溶性氯化物（以Cl-计）：≥0.4%        适用体型：通用粗纤维：≤9%\n适用阶段：全阶段     粗脂肪：≥10%         国产/进口：国产\n主体\n包装规格   1袋\n单件净含量    10kg\n保质期     18个月\n包装清单\n台派狗粮10kg1包', '1', '0', '10', '2023-04-26 22:38:08', '2023-04-26 22:38:08', '1', '1', '0');
INSERT INTO `pet_sale` VALUES ('89', ' HOUYA激光逗猫棒激光灯逗猫激光笔玩具自嗨解闷幼猫玩具用品红外线', '18', '3990.00', '01595370-301a-4f9f-b8a5-752a492ac35c.avif,4710c050-c6e5-4eb1-b22f-78c0e3cb5d57.avif,afbaf6f3-0a65-44b4-9be7-6165afad736a.avif,1885178e-9608-4ad2-ad64-7198b4a57ccd.avif', '品牌： HOUYA\n商品名称：HOUYAHY0645       商品编号：100037037982       商品毛重：108.00g\n商品产地：浙江              货号：HY0645                 类别：宠物公仔玩偶\n宠物生活---猫狗日用---其它日用HOUYAHY0645\n包装清单\nHOUYA好雅 猫玩具猫咪玩具激光钓猫两用逗猫棒伸缩长杆钓猫棒猫咪用品', '1', '0', '10', '2023-04-26 22:41:32', '2023-04-26 22:47:40', '1', '1', '0');
INSERT INTO `pet_sale` VALUES ('90', 'petmate狗狗户外玩具大小型犬训练互动狗足球橄榄球 足球(小号)14.5cm', '18', '15100.00', '518eb620-df4c-4e22-a31e-9ecb4bdbda8d.avif,eb01aeff-eb96-448a-83f6-c7041fe6c94d.avif,1171c6d3-a7c9-42c5-81f8-fb1f5d71d6bf.avif,1c6b205a-1294-4c49-92f6-ea7b8e6ccf7e.avif', '品牌： petmate\n商品名称：petmate狗狗户外玩具大小型犬训练互动狗足球橄榄球 足球(小号)14.5cm\n商品编号：67819235932         店铺： 都宠宠物用品专营店           商品毛重：300.00g\n商品产地：美国         形状：球形           适用犬型：小型犬，中大型犬\n材质：橡胶             国产/进口：进口\n包装清单\n暂无', '1', '0', '10', '2023-04-26 22:46:59', '2023-04-26 22:46:59', '1', '1', '0');
INSERT INTO `pet_sale` VALUES ('91', '星记狗狗训练用品训犬玩具球边牧飞盘软飞碟耐咬不伤牙 星记黄色-飞盘 标准号', '18', '5800.00', '65900971-9bb7-4406-bdd6-d6d74894e696.avif,57368d6c-a3a9-412e-9edc-14a3b8f44e5d.avif,5409d189-82ad-4833-9276-ca39aff8ae22.avif,1b441410-c37b-4d46-8dac-487ebbdb9779.avif', '品牌： 星记（STARMARK）\n商品名称：星记狗狗训练用品训犬玩具球边牧飞盘软飞碟耐咬不伤牙 星记黄色-飞盘 标准号\n商品编号：27826258404      店铺： 都宠宠物用品专营店    商品毛重：300.00g\n国产/进口：国产\n包装清单\n请输入...', '1', '0', '10', '2023-04-26 22:50:10', '2023-04-26 22:50:10', '1', '1', '0');
INSERT INTO `pet_sale` VALUES ('92', '尖叫玩具鸡狗狗玩具发声泰迪中小型犬绝望鸡咕咕鸡惨叫鸡尖叫鸡宠物用品批发 尖叫鸡 大号40cm', '18', '2300.00', 'b8d5ccb9-37b5-4583-a98b-11508c5871c1.avif,d0fd61e6-badc-4aa3-9301-811cfa7fe088.avif,7edfed59-544a-4431-808e-b2039ae2ea7b.avif,6d301f42-6669-4aa4-8f77-4c08020be930.avif', '品牌： 古仕龙\n商品名称：尖叫玩具鸡狗狗玩具发声泰迪中小型犬绝望鸡咕咕鸡惨叫鸡尖叫鸡宠物用品批发 尖叫鸡 大号40cm\n商品编号：10066732367621       店铺： 森美母婴玩具专营店        商品毛重：200.00g\n货号：cjj          类别：其他材质：其他       适用年龄：19个月-2岁，11-14岁\n包装清单\n暂无', '1', '0', '10', '2023-04-26 22:54:59', '2023-04-26 23:00:45', '1', '1', '0');
INSERT INTO `pet_sale` VALUES ('93', '猫零食猫薄荷球旋转球猫咪用品猫糖逗猫玩具逗猫棒木天蓼磨牙棒 可旋转猫薄荷舔舔乐球', '18', '590.00', '3fd8ef98-3d68-4329-a28c-69e950689e92.avif,acf99998-573c-45c5-a5fc-4ee52140aa66.avif,618d1926-0e76-492f-b340-e4aec5a92f12.avif,f0343ca8-0e2d-4024-87ea-885f339c56dc.avif', '品牌： 熹宠\n商品名称：猫零食猫薄荷球旋转球猫咪用品猫糖逗猫玩具逗猫棒木天蓼磨牙棒 可旋转猫薄荷舔舔乐球\n商品编号：54780725903         店铺： 凤恩宠物用品专营店             商品毛重：60.00g\n商品产地：中国大陆            适用阶段：全阶段         口味：混合口味\n总净含量：0-99g             国产/进口：国产\n包装清单\n暂无', '1', '0', '10', '2023-04-26 23:00:10', '2023-04-26 23:00:10', '1', '1', '0');
INSERT INTO `pet_sale` VALUES ('94', ' 萌嘟星宠 自动逗猫球猫咪玩具激光灯USB充电自动变向', '18', '3900.00', 'fe705127-57d9-4ad1-8c1f-9a7f95c5b974.avif,c656d47a-05b8-4902-a804-bb72785dc13c.avif,c40c24ec-8973-4eef-aa47-4fb8c160a323.avif,13274011-fb90-4cb3-876c-a51e70106b91.avif', '品牌： 萌嘟星宠\n商品名称：萌嘟星宠自动逗猫球        商品编号：100022027550       商品毛重：100.00g\n商品产地：江苏徐州         类别：智能玩具         适用阶段：幼猫，成猫，老龄猫\n形状：球形        材质：ABS                国产/进口：国产\n包装清单\n自动逗猫球*1+USB充电线*1\n', '1', '0', '10', '2023-04-26 23:05:01', '2023-04-26 23:05:01', '1', '1', '0');
INSERT INTO `pet_sale` VALUES ('95', '网易严选 全价猫粮 居家宠物主粮幼猫成猫全价粮猫咪食品 1.8KG', '16', '8800.00', '261eceb8-2239-48b8-9575-97c17f7cd5b6.avif,fa59273a-30f5-47e4-bf37-a8b0b2b8233e.avif,84da1fb3-27e1-44f8-8165-8f601e6bedee.avif,ac381800-ea47-40bc-bb66-c1529997acb4.avif', '品牌： 网易严选\n商品名称：网易严选猫粮 1.8kg商品编号：100002977536商品毛重：1.85kg商品产地：安徽省宣城市货号：1365004水溶性氯化物（以Cl-计）：≥0.3%牛磺酸：≥0.2%配方：鲜肉粮适用品种：通用总磷：≥0.9%粗脂肪：≥16%粗蛋白质：≥41%单件净含量：501g-3kg适用阶段：全阶段水分：≤10%粗灰分：≤10%粗纤维：≤5%口味：混合口味钙：≥1%国产/进口：国产\n主体\n保质期\n18个月\n包装规格\n1袋\n单件净含量\n1.8kg\n包装清单\n猫粮1.8KG*1', '1', '0', '10', '2023-04-26 23:34:51', '2023-04-26 23:34:51', '1', '1', '0');
INSERT INTO `pet_sale` VALUES ('96', '凯锐思鲜肉配方高蛋白全价猫粮成猫猫粮【20%鲜肉含量】 3斤', '16', '5500.00', 'd80f8943-f36c-404b-98aa-28cc0d2ca24b.avif,1b3ae841-f609-4979-8803-1af1272c7b7c.avif,5ba5d7aa-e811-4ef6-8b66-3cd02a185b2a.avif,2937e5e7-5078-4bfe-b291-0ff52ca98baf.avif', '品牌： 凯锐思\n商品名称：凯锐思20%鲜肉成猫粮 1.5kg商品编号：100049424540商品毛重：1.547kg商品产地：中国大陆配方：鲜肉粮牛磺酸：≥0.1%粗脂肪：≥12%适用品种：通用钙：≥1%粗灰分：≤10%粗蛋白质：≥35%单件净含量：501g-3kg适用阶段：全阶段水分：≤10%水溶性氯化物（以Cl-计）：≥0.3%粗纤维：≤4%口味：鸡肉味总磷：≥0.8%国产/进口：国产\n主体\n单件净含量\n1.5kg\n保质期\n540天\n包装规格\n1袋\n包装清单\n发货清单和商品明细', '1', '0', '10', '2023-04-26 23:36:59', '2023-04-26 23:36:59', '1', '1', '0');
INSERT INTO `pet_sale` VALUES ('97', 'Orijen渴望六种鱼猫粮5.4kg 深海鱼无谷猫粮（新老包装随机发货）', '16', '69900.00', '88cee4a6-c150-4086-972f-93d2d9afee38.avif,8cfa78d1-dd51-4595-937c-d2b98c4a1311.avif,dd5b8059-632e-4afa-8126-f6fd72678dbd.avif,78712a77-a951-4278-9f2e-61902cb7a17f.avif', '品牌： Orijen\n商品名称：Orijen六鱼大包商品编号：7615835商品毛重：5.49kg商品产地：加拿大货号：I11ori64992281540配方：天然粮牛磺酸：≥0.3%适用阶段：全阶段总磷：≥1.3%适用品种：通用粗脂肪：≥20%粗灰分：≤9%粗纤维：≤3%水分：≤10%水溶性氯化物（以Cl-计）：≥0.1%粗蛋白质：≥42%口味：鱼肉味钙：≥1.7%国产/进口：进口\n主体\n保质期\n18个月\n包装清单\nOrijen渴望六种鱼天然猫粮全猫粮12磅/5.4Kg', '1', '0', '10', '2023-04-26 23:39:02', '2023-04-26 23:39:02', '1', '1', '0');
INSERT INTO `pet_sale` VALUES ('98', '朗仕猫粮 鲜鸭肉金枪鱼配方 成幼猫全阶段全价猫粮 10kg', '16', '20710.00', 'b95c63b3-a812-4ec0-b880-37372581abed.avif,a6ccc186-fe59-427a-9077-91af8e91d0b1.avif,041539c6-e368-4802-9c89-662bcbb087c8.avif,52320ec1-6afb-4f09-824e-091b6fdf7020.avif', '品牌： 朗仕\n商品名称：朗仕猫粮 鲜鸭肉金枪鱼配方 成幼猫全阶段全价猫粮 10kg商品编号：17342038970店铺： 吉时同光宠物专营店商品毛重：10.0kg配方：通用粮牛磺酸：≥0.1%适用阶段：全阶段总磷：≥0.9%适用品种：通用粗灰分：≤9%粗脂肪：≥11%粗纤维：≤5%水分：≤10%水溶性氯化物（以Cl-计）：≥0.45%粗蛋白质：≥29%口味：混合口味钙：≥1%国产/进口：国产\n主体\n保质期\n18个月\n包装清单\n暂无', '1', '0', '10', '2023-04-26 23:42:34', '2023-04-26 23:44:00', '1', '1', '0');
INSERT INTO `pet_sale` VALUES ('99', ' 京东京造鲜肉无谷全价猫粮(鸡肉盛宴)6kg【高鲜肉0肉粉】鸡肉配方通用粮', '16', '22800.00', '8368e3e2-5d27-4d3d-b35f-f8081008f109.avif,cb754f39-934b-484a-b9f8-260814ab7a04.avif,2e05a7e7-6cee-49e2-b1dd-f5c6bdc3ab5e.avif,f242d82e-c32b-4c1d-9ca9-8ee7d684b95a.avif', '品牌： 京东京造\n商品名称：京东京造单一肉源鸡肉配方全价全期无谷猫粮6kg商品编号：100011898673商品毛重：6.14kg商品产地：中国大陆配方：鲜肉粮牛磺酸：≥0.3%粗纤维：≤5%总磷：≥1%粗脂肪：≥18%水溶性氯化物（以Cl-计）：≥0.3%适用品种：通用适用阶段：全阶段水分：≤10%钙：≥1.2%粗蛋白质：≥42%口味：鸡肉味粗灰分：≤10%国产/进口：国产\n主体\n保质期\n540天\n包装清单\n鲜肉无谷全价猫粮（鸡肉盛宴）6kg*1', '1', '0', '10', '2023-04-26 23:45:59', '2023-04-26 23:45:59', '1', '1', '0');
INSERT INTO `pet_sale` VALUES ('100', ' 网易严选 宠爱相伴全价猫粮 居家宠物主粮幼猫成猫全价粮猫咪食品 1.8kg', '16', '3600.00', 'cde855f3-c889-4ba4-a785-c2727ca4179a.avif,3d2880e2-65d4-4c4b-b382-6e1e6318767f.avif,fbf7119e-485b-40b8-a2a0-08c3332c9d10.avif,b0f5e835-db1d-4490-976f-f06cb7f60244.avif', '品牌： 网易严选\n商品名称：网易严选宠爱相伴全价猫粮 1.8kg商品编号：100011349127商品毛重：1.86kg商品产地：山东省临沂市货号：300306753水溶性氯化物（以Cl-计）：≥0.3%牛磺酸：≥0.15%钙：≥1%适用品种：通用总磷：≥0.8%粗灰分：≤10%适用阶段：全阶段单件净含量：501g-3kg粗蛋白质：≥30%水分：≤10%配方：通用粮粗纤维：≤5%口味：混合口味粗脂肪：≥12%国产/进口：国产\n主体\n保质期\n18个月\n包装规格\n1袋\n单件净含量\n1.8kg\n包装清单\n宠爱相伴全价猫粮 1.8kg', '1', '0', '10', '2023-04-26 23:47:36', '2023-04-26 23:47:36', '1', '1', '0');
INSERT INTO `pet_sale` VALUES ('101', '珍鱼堡猫粮10斤成猫幼猫英短蓝猫橘猫全价猫粮5kg全阶段猫咪通用型猫粮', '16', '4590.00', 'a1354224-bc68-4c95-849a-0c15b4b0eac4.avif,c88a7e58-c92d-473d-9e95-3a242689116f.avif,4cbf3ee9-00fd-47c5-8fab-a56876c46a1e.avif,e3a1b49c-dd50-4266-ba73-275caeaf28fd.avif', '品牌： 珍鱼堡\n商品名称：珍鱼堡猫粮商品编号：100036168764商品毛重：5.1kg商品产地：中国大陆配方：天然粮牛磺酸：≥0.1%钙：≥1%水溶性氯化物（以Cl-计）：≥0.3%总磷：≥0.8%粗灰分：≤10%适用阶段：全阶段单件净含量：3.1-7kg粗蛋白质：≥28%水分：≤12%适用品种：通用粗纤维：≤9%口味：海鲜味粗脂肪：≥9%国产/进口：国产\n主体\n单件净含量\n5kg\n包装规格\n1袋\n保质期\n12个月\n包装清单\n珍鱼堡猫粮5kg*1', '1', '0', '10', '2023-04-26 23:50:00', '2023-04-26 23:50:28', '1', '1', '0');
INSERT INTO `pet_sale` VALUES ('102', ' 麦富迪全价猫粮 鲜肉夹心双拼成猫幼猫全阶段折耳英短美短通用型 升级款10kg(含20%三文鱼夹心)', '16', '23000.00', 'b24a93a3-f9fd-4e94-a31f-7bfefd32791d.avif,5bfd846c-5a85-4ef8-83f5-2d2bd9bc809f.avif,a77d1c21-5ca0-4b46-82ae-7a40acc65ce0.avif,75224b69-12fc-4649-be73-532660e0028f.avif', '品牌： 麦富迪（Myfoodie）\n商品名称：麦富迪（Myfoodie）麦富迪三文鱼夹心猫粮商品编号：10026314108656店铺： 麦富迪鲜派专卖店商品毛重：10.1kg商品产地：中国大陆货号：麦富迪三文鱼夹心猫粮水溶性氯化物（以Cl-计）：≥0.3%牛磺酸：≥0.1%钙：≥1%总磷：≥0.6%适用品种：通用粗灰分：≤10%配方：天然粮适用阶段：成猫水分：≤10%粗纤维：≤5%粗蛋白质：≥34%口味：鱼肉味粗脂肪：≥16%国产/进口：国产\n主体\n保质期\n18个月\n包装清单\n暂无', '1', '0', '10', '2023-04-26 23:52:24', '2023-04-26 23:52:24', '1', '1', '0');
INSERT INTO `pet_sale` VALUES ('103', '冰海精灵 鸟活体虎皮鹦鹉活体鸟手玩鸟学舌牡丹鸟宠物小鸟玄风鹦鹉活幼鸟 绿色虎皮+蓝色虎皮', '9', '6380.00', 'd1b903d4-eda9-4c89-b6bb-c0027933f41c.png,f1c9922d-2845-4e6f-ac73-addcebb57942.png,07112f16-15b1-4d43-9179-7ae708b45e91.jpg,1111d072-383c-4879-a649-e62890df6801.jpg', '品牌： 冰海精灵\n商品名称：冰海精灵 鸟活体虎皮鹦鹉活体鸟手玩鸟学舌牡丹鸟宠物小鸟玄风鹦鹉活幼鸟 绿色虎皮+蓝色虎皮商品编号：10024242328628店铺： 冰海精灵旗舰店商品毛重：35.00g商品产地：中国大陆类别：小型鹦鹉是否有视频：有视频\n包装清单\n暂无', '1', '0', '10', '2023-04-27 23:08:08', '2023-04-27 23:08:08', '1', '1', '0');
INSERT INTO `pet_sale` VALUES ('104', '柴墨 金毛幼犬宠物狗狗活体大型寻回犬智商高聪明温顺导盲犬小狗仔 金毛幼犬', '1', '149900.00', '420de016-29ed-45f9-9963-ab39b8e38058.png,03a1886f-c7bd-419b-ab0c-8a6e066b7d11.avif,6e1791c1-77f5-4833-bf5f-d2ac77a3a5c9.png,c63191ef-db7c-4fdf-9754-01a91a281489.png', '品牌： 柴墨\n商品名称：柴墨 金毛幼犬宠物狗狗活体大型寻回犬智商高聪明温顺导盲犬小狗仔 金毛幼犬商品编号：10071481419379店铺： 柴墨官方旗舰店商品毛重：3.0kg商品产地：中国大陆换毛程度：正常换毛宠物级别：血统级宠物性别：公品种：金毛体型：大型犬宠物年龄：幼年是否注射芯片：未注射芯片疫苗：1针驱虫次数：1次\n包装清单\n暂无', '1', '0', '2', '2023-04-28 16:03:45', '2023-05-28 15:24:08', '1', null, '0');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(20) NOT NULL COMMENT '用户名',
  `password` char(32) NOT NULL COMMENT '密码',
  `salt` char(36) DEFAULT NULL COMMENT '盐值',
  `phone` varchar(20) DEFAULT NULL COMMENT '电话号码',
  `email` varchar(30) DEFAULT NULL COMMENT '电子邮箱',
  `gender` int(11) DEFAULT NULL COMMENT '性别:0-女，1-男',
  `avatar` varchar(250) DEFAULT NULL COMMENT '头像',
  `is_delete` int(11) DEFAULT NULL COMMENT '是否删除：0-未删除，1-已删除',
  `create_time` datetime DEFAULT NULL COMMENT '日志-创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '日志-最后修改时间',
  `create_user` varchar(20) DEFAULT NULL COMMENT '日志-创建人',
  `update_user` varchar(20) DEFAULT NULL COMMENT '日志-最后修改执行人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'controller', '731C96D302C74CB621E72035B0003814', '22279771-0C5D-4503-8A9F-F6516181B19F', '1919114514810', 'a1141514@163.com', '1', '409a617a-9b30-485f-a587-14c200f16b2a.jpeg', '0', '2023-04-06 11:18:20', '2023-04-28 23:27:40', null, null);
INSERT INTO `user` VALUES ('2', 'controller1', '2DE1F26B98C289FF7A4CE0123D14A9CA', '9599EA2A-25FA-4E3D-884E-7EA76D6AAFCB', '1919114514810', '', '1', null, '0', '2023-04-19 22:07:46', '2023-04-19 22:08:07', null, null);
INSERT INTO `user` VALUES ('3', '张十', 'BD38D8DD1C7BA5F8E7339C0DD51222AF', '11A0B4BA-5A05-48EB-A3B1-A6A7C334DEA5', '1919114514810', null, null, null, '0', '2023-04-28 22:50:20', '2023-04-28 22:50:20', null, null);
INSERT INTO `user` VALUES ('4', 'controller111', '110006CA5FA19B643009E3B28A0DBCAB', 'C8E64630-5D6A-4C68-B88C-0DA6D3CD6774', '1919114514810', null, null, null, '0', '2023-05-28 09:46:03', '2023-05-28 09:46:03', null, null);
