/*
 Navicat Premium Dump SQL

 Source Server         : fusion
 Source Server Type    : MySQL
 Source Server Version : 80041 (8.0.41)
 Source Host           : localhost:3306
 Source Schema         : fusion

 Target Server Type    : MySQL
 Target Server Version : 80041 (8.0.41)
 File Encoding         : 65001

 Date: 08/02/2025 15:13:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_engine_project
-- ----------------------------
DROP TABLE IF EXISTS `t_engine_project`;
CREATE TABLE `t_engine_project`  (
  `id` bigint NOT NULL COMMENT '主键ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '项目名称',
  `type` tinyint NOT NULL COMMENT '项目类型',
  `status` tinyint NOT NULL COMMENT '项目状态（1、停止 2、运行中 3、警告）',
  `priority` tinyint NOT NULL COMMENT '优先级（默认1）',
  `log_level` tinyint NOT NULL COMMENT '日志级别（1、无日志 2、精简日志 3、详细日志）',
  `background` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '背景图',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by` bigint NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` bigint NOT NULL COMMENT '更新人',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_name`(`name` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '项目表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_engine_project
-- ----------------------------
INSERT INTO `t_engine_project` VALUES (245396888135135232, '查询服务调用', 1, 0, 5, 1, NULL, NULL, 235123826202185728, '2025-02-07 17:00:37', 235123826202185728, '2025-02-07 17:00:37');

SET FOREIGN_KEY_CHECKS = 1;
