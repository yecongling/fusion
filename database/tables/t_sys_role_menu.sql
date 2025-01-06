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

 Date: 04/01/2025 16:23:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role_menu`;
CREATE TABLE `t_sys_role_menu`
(
    `id`          bigint unsigned                                              NOT NULL COMMENT '主键ID',
    `role_id`     bigint unsigned                                              NOT NULL COMMENT '角色ID',
    `menu_id`     bigint unsigned                                              NOT NULL COMMENT '菜单id',
    `operate_ip`  varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '操作IP',
    `create_by`   bigint unsigned                                              NOT NULL COMMENT '创建人',
    `create_time` datetime                                                     NOT NULL COMMENT '创建时间',
    `update_by`   bigint unsigned                                              NOT NULL COMMENT '更新人',
    `update_time` datetime                                                     NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='系统角色菜单关联表';

SET FOREIGN_KEY_CHECKS = 1;
