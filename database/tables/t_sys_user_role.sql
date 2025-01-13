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

 Date: 13/01/2025 11:38:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user_role`;
CREATE TABLE `t_sys_user_role`
(
    `id`          bigint                                 NOT NULL COMMENT '主键',
    `user_id`     bigint                                 NOT NULL COMMENT '用户ID',
    `role_id`     bigint                                 NOT NULL COMMENT '用户ID',
    `operate_ip`  varchar(20) COLLATE utf8mb4_general_ci NOT NULL COMMENT '操作IP',
    `create_by`   bigint unsigned                        NOT NULL COMMENT '创建人',
    `create_time` datetime                               NOT NULL COMMENT '创建时间',
    `update_by`   bigint                                 NOT NULL COMMENT '更新人',
    `update_time` datetime                               NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_sp_user_id` (`user_id`) USING BTREE,
    KEY `idx_sp_role_id` (`role_id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

SET FOREIGN_KEY_CHECKS = 1;