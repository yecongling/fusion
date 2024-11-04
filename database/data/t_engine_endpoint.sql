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

 Date: 12/08/2024 10:52:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_engine_endpoint
-- ----------------------------
DROP TABLE IF EXISTS `t_engine_endpoint`;
CREATE TABLE `t_engine_endpoint`
(
    `create_by`           varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '创建人',
    `create_time`         datetime                                                      NOT NULL COMMENT '创建日期',
    `update_by`           varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '更新人',
    `update_time`         datetime                                                      NOT NULL COMMENT '更新日期',
    `id`                  varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '端点唯一ID',
    `name`                varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '端点名字',
    `description`         varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci          DEFAULT NULL COMMENT '描述',
    `project_id`          varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci          DEFAULT NULL COMMENT '所属项目ID（也可能同时属于多个项目，多个项目id|分隔，也可能不属于任何项目，提前配置的）',
    `type_id`             varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '类型id - 关联t_engine_endpoint_type表（如http、soap、DB、FTP……）',
    `mode`                varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '模式，进出或进、出模式',
    `status`              int                                                           NOT NULL DEFAULT '1' COMMENT '状态 1-正常 2-部分异常 3-异常',
    `del_flag`            bit(1)                                                        NOT NULL DEFAULT b'0' COMMENT '删除标记 1 - 删除 0 - 未删除',
    `uri`                 varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci          DEFAULT NULL COMMENT '完整的uri，用于配置Camel的endpoint',
    `host`                varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci           DEFAULT NULL COMMENT 'endpoint的主机名或IP地址',
    `port`                int                                                                    DEFAULT NULL COMMENT '连接端口',
    `path`                varchar(255) COLLATE utf8mb4_general_ci                                DEFAULT NULL COMMENT '路径或资源标识符，如文件路径、队列名称',
    `query_params`        text                                                                   DEFAULT NULL COMMENT '查询参数，以json的形式存储',
    `username`            varchar(32) COLLATE utf8mb4_general_ci                                 DEFAULT NULL COMMENT '用户名',
    `password`            varchar(255) COLLATE utf8mb4_general_ci                                DEFAULT NULL COMMENT '密码（加密存储）',
    `authentication_type` varchar(32) COLLATE utf8mb4_general_ci                                 DEFAULT NULL COMMENT '认证类型',
    `ssl_enabled`         tinyint                                                                DEFAULT NULL COMMENT '是否启用SSL/TLS安全链接',
    `ssl_cert`            varchar(255) COLLATE utf8mb4_general_ci                                DEFAULT NULL COMMENT 'SSL证书路径或ID',
    `options`             text                                                                   DEFAULT NULL COMMENT 'endpoint的特定的配置选项',
    `headers`             text                                                                   DEFAULT NULL COMMENT 'HTTP或JMS等协议的请求头信息',
    `timeout`             int                                                           NOT NULL DEFAULT '0' COMMENT '连接或操作的超时时间',
    `retry_policy`        text                                                                   DEFAULT NULL COMMENT '重试策略配置，如重试次数、重试间隔等',
    `tags`                varchar(255) COLLATE utf8mb4_general_ci                                DEFAULT NULL COMMENT '为endpoint添加标签，便于分类和搜索',
    PRIMARY KEY (`id`, `type_id`, `mode`) USING BTREE,
    KEY `idx_endpoint_id` (`id`) USING BTREE,
    KEY `idx_endpoint_type` (`type_id`) USING BTREE,
    KEY `idx_endpoint_mode` (`mode`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC COMMENT ='引擎端点表';

SET FOREIGN_KEY_CHECKS = 1;