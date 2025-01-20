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

 Date: 20/01/2025 13:46:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_engine_project
-- ----------------------------
DROP TABLE IF EXISTS `t_engine_project`;
CREATE TABLE `t_engine_project`
(
    `id`          bigint                                  NOT NULL COMMENT '主键ID',
    `name`        varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT '项目名称',
    `type`        tinyint                                 NOT NULL COMMENT '项目类型',
    `status`      tinyint                                 NOT NULL COMMENT '项目状态（1、停止 2、运行中 3、警告）',
    `priority`    tinyint                                 NOT NULL COMMENT '优先级（默认1）',
    `log_level`   tinyint                                 NOT NULL COMMENT '日志级别（1、无日志 2、精简日志 3、详细日志）',
    `mark`        varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
    `create_by`   bigint                                  NOT NULL COMMENT '创建人',
    `create_time` datetime                                NOT NULL COMMENT '创建时间',
    `update_by`   bigint                                  NOT NULL COMMENT '更新人',
    `update_time` datetime                                NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_name` (`name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='项目表';

SET FOREIGN_KEY_CHECKS = 1;
