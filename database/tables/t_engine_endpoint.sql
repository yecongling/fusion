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

 Date: 20/01/2025 13:47:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_engine_endpoint
-- ----------------------------
DROP TABLE IF EXISTS `t_engine_endpoint`;
CREATE TABLE `t_engine_endpoint`
(
    `id`          bigint                                                       NOT NULL COMMENT '主键ID',
    `name`        varchar(255) COLLATE utf8mb4_general_ci                      NOT NULL COMMENT '端点名称',
    `type`        varchar(32) COLLATE utf8mb4_general_ci                       NOT NULL COMMENT '端点类型（HTTP、SOAP等）',
    `mode`        varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '模式（IN, IN_OUT, OUT, OUT_IN）',
    `create_by`   bigint                                                       NOT NULL COMMENT '创建人',
    `create_time` datetime                                                     NOT NULL COMMENT '创建时间',
    `update_by`   bigint                                                       NOT NULL COMMENT '更新人',
    `update_time` datetime                                                     NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='端点表';

SET FOREIGN_KEY_CHECKS = 1;
