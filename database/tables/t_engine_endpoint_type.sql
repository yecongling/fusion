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

 Date: 26/11/2024 10:33:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_engine_endpoint_type
-- ----------------------------
DROP TABLE IF EXISTS `t_engine_endpoint_type`;
CREATE TABLE `t_engine_endpoint_type`
(
    `id`          varchar(16) COLLATE utf8mb4_general_ci  NOT NULL COMMENT '主键，类型id\n',
    `type_name`   varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT '类型名称',
    `parent_id`   varchar(16) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '上级分类id',
    `create_by`   varchar(16) COLLATE utf8mb4_general_ci  NOT NULL COMMENT '创建人',
    `create_time` datetime                                NOT NULL COMMENT '创建时间',
    `update_by`   varchar(16) COLLATE utf8mb4_general_ci  NOT NULL COMMENT '更新人',
    `update_time` datetime                                NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `endpointTypeId` (`id`) USING BTREE COMMENT '唯一id索引',
    KEY `endpointTypeName` (`type_name`) USING BTREE COMMENT '类型名称索引'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

SET FOREIGN_KEY_CHECKS = 1;
