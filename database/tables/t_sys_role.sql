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

 Date: 04/01/2025 09:53:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role`;
CREATE TABLE `t_sys_role`
(
    `id`          bigint unsigned                                              NOT NULL COMMENT '主键id',
    `role_code`   varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '角色编码（用户自定义）',
    `role_name`   varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '角色名称',
    `role_type`   tinyint                                                      NOT NULL DEFAULT '1' COMMENT '角色类型(1、普通角色 0、管理员角色)',
    `status`      tinyint                                                      NOT NULL DEFAULT '0' COMMENT '状态(0、正常 1、冻结)',
    `remark`      varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci          DEFAULT NULL COMMENT '备注',
    `create_by`   bigint                                                       NOT NULL COMMENT '创建人ID',
    `create_time` datetime                                                     NOT NULL COMMENT '创建时间',
    `update_by`   bigint                                                       NOT NULL COMMENT '更新人ID',
    `update_time` datetime                                                     NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `t_sys_role_pk` (`role_code`),
    KEY `t_sys_role_name_index` (`role_name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='系统角色表';

SET FOREIGN_KEY_CHECKS = 1;
