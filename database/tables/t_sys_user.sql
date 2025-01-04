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

 Date: 04/01/2025 16:07:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user`;
CREATE TABLE `t_sys_user`
(
    `id`             varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '主键id',
    `username`       varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '登录账号',
    `real_name`      varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '真实姓名',
    `password`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '密码',
    `salt`           varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT 'md5密码盐',
    `avatar`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '头像',
    `birthday`       datetime                                                      DEFAULT NULL COMMENT '生日',
    `sex`            tinyint(1)                                                    DEFAULT '0' COMMENT '性别(0-默认未知,1-男,2-女)',
    `email`          varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '电子邮件',
    `phone`          varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '电话',
    `org_code`       varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '登录会话的机构编码',
    `status`         tinyint(1)                                                    DEFAULT '1' COMMENT '状态(1-正常,2-冻结)',
    `del_flag`       tinyint(1)                                                    DEFAULT '0' COMMENT '删除状态(0-正常,1-已删除)',
    `third_id`       varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '第三方登录的唯一标识',
    `third_type`     varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '第三方类型',
    `activity_sync`  tinyint(1)                                                    DEFAULT '0' COMMENT '同步工作流引擎(1-同步,0-不同步)',
    `work_no`        varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '工号，唯一键',
    `post`           varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '职务，关联职务表',
    `telephone`      varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '座机号',
    `create_by`      varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '创建人',
    `create_time`    datetime                                                      NOT NULL COMMENT '创建日期',
    `update_by`      varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '更新人',
    `update_time`    datetime                                                      NOT NULL COMMENT '更新日期',
    `user_identity`  tinyint(1)                                                    DEFAULT '1' COMMENT '身份（1普通成员 2上级）',
    `depart_ids`     longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '负责部门',
    `rel_tenant_ids` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '多租户标识',
    `client_id`      varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '设备ID',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `uniq_sys_user_username` (`username`) USING BTREE,
    UNIQUE KEY `uniq_sys_user_work_no` (`work_no`) USING BTREE,
    UNIQUE KEY `uniq_sys_user_phone` (`phone`) USING BTREE,
    UNIQUE KEY `uniq_sys_user_email` (`email`) USING BTREE,
    KEY `idx_su_username` (`username`) USING BTREE,
    KEY `idx_su_status` (`status`) USING BTREE,
    KEY `idx_su_del_flag` (`del_flag`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC COMMENT ='用户表';

SET FOREIGN_KEY_CHECKS = 1;
