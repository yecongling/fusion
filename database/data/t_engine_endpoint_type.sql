/*
 Navicat Premium Dump SQL

 Source Server         : integration
 Source Server Type    : MySQL
 Source Server Version : 80039 (8.0.39-0ubuntu0.22.04.1)
 Source Host           : localhost:3306
 Source Schema         : integration

 Target Server Type    : MySQL
 Target Server Version : 80039 (8.0.39-0ubuntu0.22.04.1)
 File Encoding         : 65001

 Date: 09/08/2024 11:09:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_engine_endpoint_type
-- ----------------------------
DROP TABLE IF EXISTS `t_engine_endpoint_type`;
CREATE TABLE `t_engine_endpoint_type`
(
    `id`              varchar(16)                                                  NOT NULL COMMENT '端点类型唯一ID',
    `name`            varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '类型名',
    `supported_modes` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '支持的模式',
    `create_by`       varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
    `create_time`     datetime                                                     NOT NULL COMMENT '创建时间',
    `update_by`       varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人',
    `update_time`     datetime                                                     NOT NULL COMMENT '更新时间',
    `parent_id`       varchar(16)                                                  DEFAULT NULL COMMENT '父节点',
    `status`          int                                                          NOT NULL COMMENT '状态 1、正常 2、停用',
    `title`           varchar(255)                                                 DEFAULT NULL COMMENT '中文名描述',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_endpoint_id` (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC COMMENT ='端点类型表';

SET FOREIGN_KEY_CHECKS = 1;
