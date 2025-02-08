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

 Date: 08/02/2025 15:12:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_menu`;
CREATE TABLE `t_sys_menu`  (
  `id` bigint NOT NULL COMMENT 'id',
  `parent_id` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父id',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单标题',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '路径',
  `component` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组件',
  `component_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组件名字',
  `redirect` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '一级菜单跳转地址',
  `menu_type` int NULL DEFAULT NULL COMMENT '菜单类型(0:一级菜单; 1:子菜单:2:按钮权限)',
  `perms` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单权限编码',
  `perms_type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '权限策略1显示2禁用',
  `sort_no` double(8, 2) NOT NULL COMMENT '菜单排序',
  `always_show` tinyint(1) NULL DEFAULT NULL COMMENT '聚合子路由: 1是0否',
  `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `is_route` tinyint(1) NULL DEFAULT 1 COMMENT '是否路由菜单: 0:不是  1:是（默认值1）',
  `is_leaf` tinyint(1) NULL DEFAULT NULL COMMENT '是否叶子节点:    1:是   0:不是',
  `keep_alive` tinyint(1) NULL DEFAULT NULL COMMENT '是否缓存该页面:    1:是   0:不是',
  `hidden` tinyint(1) NULL DEFAULT 0 COMMENT '是否隐藏路由: 0否,1是',
  `hide_tab` tinyint(1) NULL DEFAULT NULL COMMENT '是否隐藏tab: 0否,1是',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `create_by` bigint NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建日期',
  `update_by` bigint NOT NULL COMMENT '更新人',
  `update_time` datetime NOT NULL COMMENT '更新日期',
  `del_flag` int NULL DEFAULT 0 COMMENT '删除状态 0正常 1已删除',
  `rule_flag` int NULL DEFAULT 0 COMMENT '是否添加数据权限1是0否',
  `status` int NULL DEFAULT NULL COMMENT '按钮权限状态(0无效1有效)',
  `internal_or_external` tinyint(1) NULL DEFAULT NULL COMMENT '外链菜单打开方式 0/内部打开 1/外部打开',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_sp_parent_id`(`parent_id` ASC) USING BTREE,
  INDEX `idx_sp_is_route`(`is_route` ASC) USING BTREE,
  INDEX `idx_sp_is_leaf`(`is_leaf` ASC) USING BTREE,
  INDEX `idx_sp_sort_no`(`sort_no` ASC) USING BTREE,
  INDEX `idx_sp_del_flag`(`del_flag` ASC) USING BTREE,
  INDEX `idx_sp_menu_type`(`menu_type` ASC) USING BTREE,
  INDEX `idx_sp_hidden`(`hidden` ASC) USING BTREE,
  INDEX `idx_sp_status`(`status` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单权限表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_sys_menu
-- ----------------------------
INSERT INTO `t_sys_menu` VALUES (11231, NULL, '首页', '/home', 'Home', '首页', NULL, 1, NULL, '0', 1.00, 1, 'HomeOutlined', 1, 1, 1, 0, 0, NULL, 235123826202185728, '2024-01-03 11:24:00', 235123826202185728, '2024-01-03 11:24:00', 0, 0, 1, NULL);
INSERT INTO `t_sys_menu` VALUES (11232, NULL, '数据统计', '/statics', NULL, '数据统计', NULL, 0, NULL, '0', 2.00, 1, 'LineChartOutlined', 0, 0, 0, 0, 0, NULL, 235123826202185728, '2024-01-03 11:24:00', 235123826202185728, '2024-01-03 11:24:00', 0, 0, 1, 0);
INSERT INTO `t_sys_menu` VALUES (11234, '11232', '消息检索', '/statics/messageSearch', 'statics/MessageSearch', '消息检索', NULL, 1, NULL, '0', 3.00, 1, 'FileSearchOutlined', 1, 1, 1, 0, 0, NULL, 235123826202185728, '2024-01-03 11:24:00', 235123826202185728, '2024-01-03 11:24:00', 0, 0, 1, NULL);
INSERT INTO `t_sys_menu` VALUES (11235, '11232', '错误统计', '/statics/errorStatics', 'statics/ErrorStatics', '错误统计', NULL, 1, NULL, '0', 4.00, 1, 'CloseCircleOutlined', 1, 1, 1, 0, 0, NULL, 235123826202185728, '2024-01-03 11:24:00', 235123826202185728, '2024-01-03 11:24:00', 0, 0, 1, NULL);
INSERT INTO `t_sys_menu` VALUES (11236, '11232', '终端监控', '/statics/endpoint', 'statics/Terminal', '终端监控', NULL, 1, NULL, '0', 5.00, 1, 'MonitorOutlined', 1, 1, 1, 0, 0, NULL, 235123826202185728, '2024-01-03 11:24:00', 235123826202185728, '2024-01-03 11:24:00', 0, 0, 1, NULL);
INSERT INTO `t_sys_menu` VALUES (11237, '11232', '测试消息', '/statics/testMessage', 'statics/TestMessage', '测试消息', NULL, 1, NULL, '0', 6.00, 1, 'MessageOutlined', 1, 1, 1, 0, 0, NULL, 235123826202185728, '2024-01-03 11:24:00', 235123826202185728, '2024-01-03 11:24:00', 0, 0, 1, NULL);
INSERT INTO `t_sys_menu` VALUES (11238, NULL, '项目维护', '/project', NULL, '项目维护', NULL, 0, NULL, '0', 7.00, 1, 'ClusterOutlined', 0, 0, 0, 0, 0, NULL, 235123826202185728, '2024-01-03 11:24:00', 235123826202185728, '2024-01-03 11:24:00', 0, 0, 1, NULL);
INSERT INTO `t_sys_menu` VALUES (11239, '11238', '项目设计', '/project/design', 'project/Design', '项目设计', NULL, 1, NULL, '0', 8.00, 1, 'ContainerOutlined', 1, 1, 1, 0, 0, NULL, 235123826202185728, '2024-01-03 11:24:00', 235123826202185728, '2024-01-03 11:24:00', 0, 0, 1, NULL);
INSERT INTO `t_sys_menu` VALUES (11240, '11238', '端点管理', '/project/endpoint', 'project/Endpoint', '端点管理', NULL, 1, NULL, '0', 9.00, 1, 'HomeOutlined', 1, 1, 1, 0, 0, NULL, 235123826202185728, '2024-01-03 11:24:00', 235123826202185728, '2024-01-03 11:24:00', 0, 0, 1, NULL);
INSERT INTO `t_sys_menu` VALUES (11241, NULL, '资源管理', '/resource', NULL, '资源管理', NULL, 0, NULL, '0', 10.00, 1, 'DeploymentUnitOutlined', 0, 0, 1, 0, 0, NULL, 235123826202185728, '2024-01-03 11:24:00', 235123826202185728, '2024-01-03 11:24:00', 0, 0, 1, NULL);
INSERT INTO `t_sys_menu` VALUES (11242, '11241', '数据库资源', '/resource/database', 'resource/Database', '数据库资源', NULL, 1, NULL, '0', 11.00, 1, 'DatabaseOutlined', 1, 1, 1, 0, 0, NULL, 235123826202185728, '2024-01-03 11:24:00', 235123826202185728, '2024-01-03 11:24:00', 0, 0, 1, NULL);
INSERT INTO `t_sys_menu` VALUES (11243, '11241', '数据模式', '/resource/dataMode', 'resource/DataMode', '数据模式', NULL, 1, NULL, '0', 12.00, 1, 'FundOutlined', 1, 1, 1, 0, 0, NULL, 235123826202185728, '2024-01-03 11:24:00', 235123826202185728, '2024-01-03 11:24:00', 0, 0, 1, NULL);
INSERT INTO `t_sys_menu` VALUES (11244, '11241', '数据转换', '/resource/transfer', 'resource/Transfer', '数据转换', NULL, 1, NULL, '0', 13.00, 1, 'SwapOutlined', 1, 1, 1, 0, 0, NULL, 235123826202185728, '2024-01-03 11:24:00', 235123826202185728, '2024-01-03 11:24:00', 0, 0, 1, NULL);
INSERT INTO `t_sys_menu` VALUES (11245, '11241', 'SSL', '/resource/ssl', 'resource/SSL', 'SSL', NULL, 1, NULL, '0', 14.00, 1, 'HomeOutlined', 1, 1, 1, 0, 0, NULL, 235123826202185728, '2024-01-03 11:24:00', 235123826202185728, '2024-01-03 11:24:00', 0, 0, 1, NULL);
INSERT INTO `t_sys_menu` VALUES (11246, '11241', 'Web服务', '/resource/web', 'resource/Web', 'Web服务', NULL, 1, NULL, '0', 15.00, 1, 'HomeOutlined', 1, 1, 1, 0, 0, NULL, 235123826202185728, '2024-01-03 11:24:00', 235123826202185728, '2024-01-03 11:24:00', 0, 0, 1, NULL);
INSERT INTO `t_sys_menu` VALUES (11247, '11241', '原生库', '/resource/dll', 'resource/Dll', '原生库', NULL, 1, NULL, '0', 16.00, 1, 'HomeOutlined', 1, 1, 1, 0, 0, NULL, 235123826202185728, '2024-01-03 11:24:00', 235123826202185728, '2024-01-03 11:24:00', 0, 0, 1, NULL);
INSERT INTO `t_sys_menu` VALUES (11248, '', '连接管理', '/connection', NULL, '连接管理', NULL, 1, NULL, '0', 17.00, 1, 'ApartmentOutlined', 0, 0, 0, 0, 0, NULL, 235123826202185728, '2024-01-03 11:24:00', 235123826202185728, '2024-01-03 11:24:00', 0, 0, 1, NULL);
INSERT INTO `t_sys_menu` VALUES (11249, '11248', '数据库', '/connection/database', 'connection/Database', '数据库', NULL, 1, NULL, '0', 18.00, 1, 'HomeOutlined', 1, 1, 1, 0, 0, NULL, 235123826202185728, '2024-01-03 11:24:00', 235123826202185728, '2024-01-03 11:24:00', 0, 0, 1, NULL);
INSERT INTO `t_sys_menu` VALUES (11250, '11248', 'JMS', '/connection/jms', 'connection/Jms', 'JMS', NULL, 1, NULL, '0', 19.00, 1, 'HomeOutlined', 1, 1, 1, 0, 0, NULL, 235123826202185728, '2024-01-03 11:24:00', 235123826202185728, '2024-01-03 11:24:00', 0, 0, 1, NULL);
INSERT INTO `t_sys_menu` VALUES (11251, NULL, '数据处理', '/dataHandle', NULL, '数据处理', NULL, 1, NULL, '0', 20.00, 1, 'HeatMapOutlined', 0, 0, 0, 0, 0, NULL, 235123826202185728, '2024-01-03 11:24:00', 235123826202185728, '2024-01-03 11:24:00', 0, 0, 1, NULL);
INSERT INTO `t_sys_menu` VALUES (11252, '11251', '数据转换', '/dataHandle/dataTransfer', 'dataHandle/DataTransfer', '数据转换', NULL, 1, NULL, '0', 21.00, 1, 'HomeOutlined', 1, 1, 1, 0, 0, NULL, 235123826202185728, '2024-01-03 11:24:00', 235123826202185728, '2024-01-03 11:24:00', 0, 0, 1, NULL);
INSERT INTO `t_sys_menu` VALUES (11253, '11251', '变量配置', '/dataHandle/variable', 'dataHandle/Variable', '变量配置', NULL, 1, NULL, '0', 22.00, 1, 'HomeOutlined', 1, 1, 1, 0, 0, NULL, 235123826202185728, '2024-01-03 11:24:00', 235123826202185728, '2024-01-03 11:24:00', 0, 0, 1, NULL);
INSERT INTO `t_sys_menu` VALUES (11254, '11251', '编码集', '/dataHandle/codeSet', 'dataHandle/CodeSet', '编码集', NULL, 1, NULL, '0', 23.00, 1, 'HomeOutlined', 1, 1, 1, 0, 0, NULL, 235123826202185728, '2024-01-03 11:24:00', 235123826202185728, '2024-01-03 11:24:00', 0, 0, 1, NULL);
INSERT INTO `t_sys_menu` VALUES (11255, '11251', '共享脚本', '/dataHandle/script', 'dataHandle/Script', '共享脚本', NULL, 1, NULL, '0', 24.00, 1, 'HomeOutlined', 1, 1, 1, 0, 0, NULL, 235123826202185728, '2024-01-03 11:24:00', 235123826202185728, '2024-01-03 11:24:00', 0, 0, 1, NULL);
INSERT INTO `t_sys_menu` VALUES (11256, NULL, '系统管理', '/system', NULL, '系统管理', NULL, 0, NULL, '0', 25.00, 1, 'SettingOutlined', 0, 0, 1, 0, 0, NULL, 235123826202185728, '2024-01-03 11:24:00', 235123826202185728, '2024-01-03 11:24:00', 0, 0, 1, NULL);
INSERT INTO `t_sys_menu` VALUES (11257, '11256', '用户管理', '/system/user', 'system/User', '用户管理', NULL, 1, NULL, '0', 26.00, 1, 'UserOutlined', 1, 1, 1, 0, 0, NULL, 235123826202185728, '2024-01-03 11:24:00', 235123826202185728, '2024-01-03 11:24:00', 0, 0, 1, NULL);
INSERT INTO `t_sys_menu` VALUES (11258, '11256', '角色管理', '/system/role', 'system/Role', '角色管理', NULL, 1, NULL, '0', 27.00, 1, 'UsergroupDeleteOutlined', 1, 1, 1, 0, 0, NULL, 235123826202185728, '2024-01-03 11:24:00', 235123826202185728, '2024-01-03 11:24:00', 0, 0, 1, NULL);
INSERT INTO `t_sys_menu` VALUES (11259, '11256', '菜单管理', '/system/menu', 'system/Menu', '菜单管理', NULL, 1, NULL, '0', 28.00, 1, 'MenuOutlined', 1, 1, 1, 0, 0, NULL, 235123826202185728, '2024-01-03 11:24:00', 235123826202185728, '2024-01-03 11:24:00', 0, 0, 1, NULL);
INSERT INTO `t_sys_menu` VALUES (11260, '11256', '权限分配', '/system/permission', 'system/Permission', '权限分配', NULL, 1, NULL, '0', 29.00, 1, 'HomeOutlined', 1, 1, 1, 0, 0, NULL, 235123826202185728, '2024-01-03 11:24:00', 235123826202185728, '2024-01-03 11:24:00', 0, 0, 1, NULL);
INSERT INTO `t_sys_menu` VALUES (11261, '11256', '数据字典', '/system/dictionary', 'system/Dictionary', '数据字典', NULL, 1, NULL, '0', 30.00, 1, 'HomeOutlined', 1, 1, 1, 0, 0, NULL, 235123826202185728, '2024-01-03 11:24:00', 235123826202185728, '2024-01-03 11:24:00', 0, 0, 1, NULL);
INSERT INTO `t_sys_menu` VALUES (11262, '11256', '字典分类', '/system/dictionaryCategory', 'system/DictionaryCategory', '字典分类', NULL, 1, NULL, '0', 31.00, 1, 'HomeOutlined', 1, 1, 1, 0, 0, NULL, 235123826202185728, '2024-01-03 11:24:00', 235123826202185728, '2024-01-03 11:24:00', 0, 0, 1, NULL);
INSERT INTO `t_sys_menu` VALUES (11263, '11256', '系统公告', '/system/announcement', 'system/Announcement', '系统公告', NULL, 1, NULL, '0', 32.00, 1, 'HomeOutlined', 1, 1, 1, 0, 0, NULL, 235123826202185728, '2024-01-03 11:24:00', 235123826202185728, '2024-01-03 11:24:00', 0, 0, 1, NULL);
INSERT INTO `t_sys_menu` VALUES (11264, NULL, '系统监控', '/monitor', NULL, '系统监控', NULL, 0, NULL, '0', 33.00, 1, 'MonitorOutlined', 0, 0, 1, 0, 0, NULL, 235123826202185728, '2024-01-03 11:24:00', 235123826202185728, '2024-01-03 11:24:00', 0, 0, 1, NULL);
INSERT INTO `t_sys_menu` VALUES (11265, '11264', '定时器', '/monitor/timer', 'monitor/Timer', '定时器', NULL, 1, NULL, '0', 34.00, 1, 'FieldTimeOutlined', 1, 1, 1, 0, 0, NULL, 235123826202185728, '2024-01-03 11:24:00', 235123826202185728, '2024-01-03 11:24:00', 0, 0, 1, NULL);
INSERT INTO `t_sys_menu` VALUES (11266, '11264', '数据日志', '/monitor/dataLog', 'monitor/DataLog', '数据日志', NULL, 1, NULL, '0', 35.00, 1, 'HomeOutlined', 1, 1, 1, 0, 0, NULL, 235123826202185728, '2024-01-03 11:24:00', 235123826202185728, '2024-01-03 11:24:00', 0, 0, 1, NULL);
INSERT INTO `t_sys_menu` VALUES (11267, '11264', '日志管理', '/monitor/log', 'monitor/Log', '日志管理', NULL, 1, NULL, '0', 36.00, 1, 'HomeOutlined', 1, 1, 1, 0, 0, NULL, 235123826202185728, '2024-01-03 11:24:00', 235123826202185728, '2024-01-03 11:24:00', 0, 0, 1, NULL);
INSERT INTO `t_sys_menu` VALUES (11268, '11264', 'SQL监控', '/monitor/sql', 'monitor/Sql', 'SQL监控', NULL, 1, NULL, '0', 37.00, 1, 'ConsoleSqlOutlined', 1, 1, 1, 0, 0, NULL, 235123826202185728, '2024-01-03 11:24:00', 235123826202185728, '2024-01-03 11:24:00', 0, 0, 1, NULL);
INSERT INTO `t_sys_menu` VALUES (11269, '11264', '性能监控', '/monitor/performance', 'monitor/Performance', '性能监控', NULL, 1, NULL, '0', 38.00, 1, 'HomeOutlined', 1, 1, 1, 0, 0, NULL, 235123826202185728, '2024-01-03 11:24:00', 235123826202185728, '2024-01-03 11:24:00', 0, 0, 1, NULL);
INSERT INTO `t_sys_menu` VALUES (11270, '11264', '网关路由', '/monitor/gateway', 'monitor/Gateway', '网关路由', NULL, 1, NULL, '0', 39.00, 1, 'GatewayOutlined', 1, 1, 1, 0, 0, NULL, 235123826202185728, '2024-01-03 11:24:00', 235123826202185728, '2024-01-03 11:24:00', 0, 0, 1, NULL);
INSERT INTO `t_sys_menu` VALUES (11271, NULL, '消息中心', '/message', NULL, '消息中心', NULL, 1, NULL, '0', 40.00, 1, 'CommentOutlined', 0, 0, 1, 0, 0, NULL, 235123826202185728, '2024-01-03 11:24:00', 235123826202185728, '2024-01-03 11:24:00', 0, 0, 1, NULL);
INSERT INTO `t_sys_menu` VALUES (11272, '11271', '消息管理', '/message/messageManager', 'message/MessageManager', '消息管理', NULL, 1, NULL, '0', 41.00, 1, 'HomeOutlined', 1, 1, 1, 0, 0, NULL, 235123826202185728, '2024-01-03 11:24:00', 235123826202185728, '2024-01-03 11:24:00', 0, 0, 1, NULL);
INSERT INTO `t_sys_menu` VALUES (11273, '11271', '消息模板', '/message/template', 'message/Template', '消息模板', NULL, 1, NULL, '0', 42.00, 1, 'HomeOutlined', 1, 1, 1, 0, 0, NULL, 235123826202185728, '2024-01-03 11:24:00', 235123826202185728, '2024-01-03 11:24:00', 0, 0, 1, NULL);
INSERT INTO `t_sys_menu` VALUES (11274, NULL, '编辑器', '/editor', NULL, '编辑器', NULL, 1, NULL, '0', 43.00, 1, 'FileDoneOutlined', 0, 0, 0, 0, 0, NULL, 235123826202185728, '2024-01-03 11:24:00', 235123826202185728, '2024-01-03 11:24:00', 0, 0, 1, NULL);
INSERT INTO `t_sys_menu` VALUES (11275, '11274', '文档编辑器', '/editor/docEditor', 'editor/DocEditor', '文档编辑器', NULL, 1, NULL, '0', 44.00, 1, 'EditOutlined', 1, 1, 1, 0, 0, NULL, 235123826202185728, '2024-01-03 11:24:00', 235123826202185728, '2024-01-03 11:24:00', 0, 0, 1, NULL);
INSERT INTO `t_sys_menu` VALUES (11276, '11238', '端点配置维护', '/project/endpointConfig', 'project/EndpointConfig', '端点配置维护', NULL, 1, NULL, '0', 9.00, 1, 'ProductOutlined', 1, 1, 1, 0, 0, NULL, 235123826202185728, '2024-01-03 11:24:00', 235123826202185728, '2024-01-03 11:24:00', 0, 0, 1, NULL);
INSERT INTO `t_sys_menu` VALUES (245278318260645888, '11238', '流程设计', '/project/designer/:id', 'project/Designer', '项目流程设计', NULL, 1, NULL, NULL, 8.50, 0, 'DeploymentUnitOutlined', 1, 0, 0, 1, 0, NULL, 235123826202185728, '2025-02-07 09:09:28', 235123826202185728, '2025-02-07 10:30:47', 0, 0, 1, 0);

SET FOREIGN_KEY_CHECKS = 1;
