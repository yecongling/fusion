/*
 Navicat Premium Dump SQL

 Source Server         : fusion
 Source Server Type    : MySQL
 Source Server Version : 80040 (8.0.40-0ubuntu0.24.04.1)
 Source Host           : localhost:3306
 Source Schema         : fusion

 Target Server Type    : MySQL
 Target Server Version : 80040 (8.0.40-0ubuntu0.24.04.1)
 File Encoding         : 65001

 Date: 28/11/2024 11:25:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_engine_endpoint_properties
-- ----------------------------
DROP TABLE IF EXISTS `t_engine_endpoint_properties`;
CREATE TABLE `t_engine_endpoint_properties` (
  `id` bigint unsigned NOT NULL COMMENT '主键id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '配置名',
  `config_id` bigint unsigned NOT NULL COMMENT '所属端点配置',
  `tips` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '提示信息',
  `title` varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT '配置title',
  `type` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'input' COMMENT '配置字段使用控件类型（checkbox、input、select等）',
  `required` tinyint DEFAULT NULL COMMENT '是否必填',
  `default_value` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '默认值',
  `allowed_values` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '允许的值',
  `applies_to` varchar(32) COLLATE utf8mb4_general_ci NOT NULL COMMENT '应用于生产端还是消费端(producer|consumer)',
  `create_by` varchar(32) COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(32) COLLATE utf8mb4_general_ci NOT NULL COMMENT '更新人',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`,`config_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

SET FOREIGN_KEY_CHECKS = 1;
