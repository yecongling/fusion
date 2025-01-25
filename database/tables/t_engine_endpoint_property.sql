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

 Date: 20/01/2025 13:46:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_engine_endpoint_property
-- ----------------------------
DROP TABLE IF EXISTS `t_engine_endpoint_property`;
CREATE TABLE `t_engine_endpoint_property`
(
    `endpoint_id`    bigint   NOT NULL COMMENT '端点ID',
    `property_id`    bigint   NOT NULL COMMENT '属性ID',
    `property_value` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '属性值',
    `create_by`      bigint   NOT NULL COMMENT '创建人',
    `create_time`    datetime NOT NULL COMMENT '创建时间',
    `update_by`      bigint   NOT NULL COMMENT '更新人',
    `update_time`    datetime NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`endpoint_id`, `property_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='项目属性表';

SET FOREIGN_KEY_CHECKS = 1;
