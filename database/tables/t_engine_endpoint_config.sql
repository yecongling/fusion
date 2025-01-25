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

 Date: 20/01/2025 15:31:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_engine_endpoint_config
-- ----------------------------
DROP TABLE IF EXISTS `t_engine_endpoint_config`;
CREATE TABLE `t_engine_endpoint_config`
(
    `id`             bigint                                                       NOT NULL COMMENT '端点配置id',
    `config_name`    varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '端点配置名称',
    `supported_mode` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '支持的模式',
    `type_id`        bigint                                                       NOT NULL COMMENT '所属分类的id',
    `icon`           varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci          DEFAULT NULL COMMENT '图标',
    `support_retry`  tinyint                                                      NOT NULL DEFAULT '0' COMMENT '支持重试（0，不支持-默认，1-支持）',
    `create_by`      varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
    `create_time`    datetime                                                     NOT NULL COMMENT '创建时间',
    `update_by`      varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '更新人',
    `update_time`    datetime                                                     NOT NULL COMMENT '更新时间',
    `description`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci         DEFAULT NULL COMMENT '描述',
    `strategy`       tinyint                                                      NOT NULL DEFAULT '0' COMMENT '调用策略（0、顺序处理-默认、1、并行处理）',
    PRIMARY KEY (`id`, `type_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='端点配置表';

SET FOREIGN_KEY_CHECKS = 1;
