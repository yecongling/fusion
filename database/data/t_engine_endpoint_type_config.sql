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

 Date: 13/08/2024 11:08:53
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_engine_endpoint_type_config
-- ----------------------------
DROP TABLE IF EXISTS `t_engine_endpoint_type_config`;
CREATE TABLE `t_engine_endpoint_type_config`
(
    `id`               varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '类型配置项id',
    `type_id`          varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '类型id',
    `name`             varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '配置项名（英文的），代码中使用的配置名',
    `title`            varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用于表单显示，字段标签',
    `description`      varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '描述（配置框下面的描述性信息）',
    `type`             varchar(128) COLLATE utf8mb4_general_ci                       NOT NULL COMMENT '配置项类型（如： text、input、select等）',
    `default_value`    varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '默认值',
    `options`          text                                    DEFAULT NULL COMMENT '可选字段，用于存储select类型的选项（以json格式存储）',
    `validation_rules` text                                    DEFAULT NULL COMMENT '字段验证规则',
    `applies_to`       varchar(32) COLLATE utf8mb4_general_ci                        NOT NULL COMMENT '应用于哪一端（PRODUCER 还是CONSUMER，针对camel做出的配置）',
    `create_time`      datetime                                                      NOT NULL COMMENT '创建时间',
    `create_by`        varchar(32) COLLATE utf8mb4_general_ci                        NOT NULL COMMENT '创建人',
    `update_time`      datetime                                                      NOT NULL COMMENT '更新时间',
    `update_by`        varchar(32) COLLATE utf8mb4_general_ci                        NOT NULL COMMENT '更新人',
    PRIMARY KEY (`id`, `type_id`, `applies_to`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

SET FOREIGN_KEY_CHECKS = 1;