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

 Date: 26/11/2024 15:51:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_sys_permission 菜单表
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_permission`;
CREATE TABLE `t_sys_permission`
(
    `id`                   varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
    `parent_id`            varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '父id',
    `name`                 varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '菜单标题',
    `url`                  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '路径',
    `component`            varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '组件',
    `component_name`       varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '组件名字',
    `redirect`             varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '一级菜单跳转地址',
    `menu_type`            int                                                           DEFAULT NULL COMMENT '菜单类型(0:一级菜单; 1:子菜单:2:按钮权限)',
    `perms`                varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '菜单权限编码',
    `perms_type`           varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT 0 COMMENT '权限策略1显示2禁用',
    `sort_no`              int                                                          NOT NULL COMMENT '菜单排序',
    `always_show`          tinyint(1)                                                    DEFAULT NULL COMMENT '聚合子路由: 1是0否',
    `icon`                 varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '菜单图标',
    `is_route`             tinyint(1)                                                    DEFAULT 1 COMMENT '是否路由菜单: 0:不是  1:是（默认值1）',
    `is_leaf`              tinyint(1)                                                    DEFAULT NULL COMMENT '是否叶子节点:    1:是   0:不是',
    `keep_alive`           tinyint(1)                                                    DEFAULT NULL COMMENT '是否缓存该页面:    1:是   0:不是',
    `hidden`               tinyint(1)                                                    DEFAULT 0 COMMENT '是否隐藏路由: 0否,1是',
    `hide_tab`             tinyint(1)                                                    DEFAULT NULL COMMENT '是否隐藏tab: 0否,1是',
    `description`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '描述',
    `create_by`            varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
    `create_time`          datetime                                                     NOT NULL COMMENT '创建日期',
    `update_by`            varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '更新人',
    `update_time`          datetime                                                     NOT NULL COMMENT '更新日期',
    `del_flag`             int                                                           DEFAULT 0 COMMENT '删除状态 0正常 1已删除',
    `rule_flag`            int                                                           DEFAULT 0 COMMENT '是否添加数据权限1是0否',
    `status`               int                                                           DEFAULT NULL COMMENT '按钮权限状态(0无效1有效)',
    `internal_or_external` tinyint(1)                                                    DEFAULT NULL COMMENT '外链菜单打开方式 0/内部打开 1/外部打开',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_sp_parent_id` (`parent_id`) USING BTREE,
    KEY `idx_sp_is_route` (`is_route`) USING BTREE,
    KEY `idx_sp_is_leaf` (`is_leaf`) USING BTREE,
    KEY `idx_sp_sort_no` (`sort_no`) USING BTREE,
    KEY `idx_sp_del_flag` (`del_flag`) USING BTREE,
    KEY `idx_sp_menu_type` (`menu_type`) USING BTREE,
    KEY `idx_sp_hidden` (`hidden`) USING BTREE,
    KEY `idx_sp_status` (`status`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC COMMENT ='菜单权限表';

SET FOREIGN_KEY_CHECKS = 1;

insert into t_sys_permission (id, parent_id, name, url, component, component_name, redirect, menu_type, perms,
                              perms_type, sort_no, always_show, icon, is_route, is_leaf, keep_alive, hidden, hide_tab,
                              description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status,
                              internal_or_external)
values ('11231', null, '首页', '/home', 'Home', '首页', null, 1, null, '0', 1, 1, 'HomeOutlined', 1, 1, 1, 0, 0, null,
        '叶丛林', '2024-01-03 11:24:00', '叶丛林', '2024-01-03 11:24:00', 0, 0, 1, null),
       ('11232', null, '数据统计', '/statics', null, '数据统计', null, 0, null, '0', 2, 1, 'LineChartOutlined', 0, 0, 0,
        0, 0, null, '叶丛林', '2024-01-03 11:24:00', '叶丛林', '2024-01-03 11:24:00', 0, 0, 1, 0),
       ('11234', '11232', '消息检索', '/statics/messageSearch', 'statics/MessageSearch', '消息检索', null, 1, null, '0',
        3, 1, 'FileSearchOutlined', 1, 1, 1, 0, 0, null, '叶丛林', '2024-01-03 11:24:00', '叶丛林',
        '2024-01-03 11:24:00', 0, 0, 1, null),
       ('11235', '11232', '错误统计', '/statics/errorStatics', 'statics/ErrorStatics', '错误统计', null, 1, null, '0',
        4, 1, 'CloseCircleOutlined', 1, 1, 1, 0, 0, null, '叶丛林', '2024-01-03 11:24:00', '叶丛林',
        '2024-01-03 11:24:00', 0, 0, 1, null),
       ('11236', '11232', '终端监控', '/statics/endpoint', 'statics/Terminal', '终端监控', null, 1, null, '0', 5, 1,
        'MonitorOutlined', 1, 1, 1, 0, 0, null, '叶丛林', '2024-01-03 11:24:00', '叶丛林', '2024-01-03 11:24:00', 0, 0,
        1, null),
       ('11237', '11232', '测试消息', '/statics/testMessage', 'statics/TestMessage', '测试消息', null, 1, null, '0', 6,
        1, 'MessageOutlined', 1, 1, 1, 0, 0, null, '叶丛林', '2024-01-03 11:24:00', '叶丛林', '2024-01-03 11:24:00', 0,
        0, 1, null),
       ('11238', null, '项目维护', '/project', null, '项目维护', null, 0, null, '0', 7, 1, 'ClusterOutlined', 0, 0, 0,
        0, 0, null, '叶丛林', '2024-01-03 11:24:00', '叶丛林', '2024-01-03 11:24:00', 0, 0, 1, null),
       ('11239', '11238', '项目设计', '/project/design', 'project/Design', '项目设计', null, 1, null, '0', 8, 1,
        'ContainerOutlined', 1, 1, 1, 0, 0, null, '叶丛林', '2024-01-03 11:24:00', '叶丛林', '2024-01-03 11:24:00', 0,
        0, 1, null),
       ('11240', '11238', '端点管理', '/project/endpoint', 'project/Endpoint', '端点管理', null, 1, null, '0', 9, 1,
        'HomeOutlined', 1, 1, 1, 0, 0, null, '叶丛林', '2024-01-03 11:24:00', '叶丛林', '2024-01-03 11:24:00', 0, 0, 1,
        null),
       ('11276', '11238', '端点配置维护', '/project/endpointConfig', 'project/EndpointConfig', '端点配置维护', null, 1,
        null, '0', 9, 1, 'ProductOutlined', 1, 1, 1, 0, 0, null, '叶丛林', '2024-01-03 11:24:00', '叶丛林',
        '2024-01-03 11:24:00', 0, 0, 1, null),
       ('11241', null, '资源管理', '/resource', null, '资源管理', null, 0, null, '0', 10, 1, 'DeploymentUnitOutlined',
        0, 0, 1, 0, 0, null, '叶丛林', '2024-01-03 11:24:00', '叶丛林', '2024-01-03 11:24:00', 0, 0, 1, null),
       ('11242', '11241', '数据库资源', '/resource/database', 'resource/Database', '数据库资源', null, 1, null, '0', 11,
        1, 'DatabaseOutlined', 1, 1, 1, 0, 0, null, '叶丛林', '2024-01-03 11:24:00', '叶丛林', '2024-01-03 11:24:00', 0,
        0, 1, null),
       ('11243', '11241', '数据模式', '/resource/dataMode', 'resource/DataMode', '数据模式', null, 1, null, '0', 12, 1,
        'FundOutlined', 1, 1, 1, 0, 0, null, '叶丛林', '2024-01-03 11:24:00', '叶丛林', '2024-01-03 11:24:00', 0, 0, 1,
        null),
       ('11244', '11241', '数据转换', '/resource/transfer', 'resource/Transfer', '数据转换', null, 1, null, '0', 13, 1,
        'SwapOutlined', 1, 1, 1, 0, 0, null, '叶丛林', '2024-01-03 11:24:00', '叶丛林', '2024-01-03 11:24:00', 0, 0, 1,
        null),
       ('11245', '11241', 'SSL', '/resource/ssl', 'resource/SSL', 'SSL', null, 1, null, '0', 14, 1, 'HomeOutlined', 1,
        1, 1, 0, 0, null, '叶丛林', '2024-01-03 11:24:00', '叶丛林', '2024-01-03 11:24:00', 0, 0, 1, null),
       ('11246', '11241', 'Web服务', '/resource/web', 'resource/Web', 'Web服务', null, 1, null, '0', 15, 1,
        'HomeOutlined', 1, 1, 1, 0, 0, null, '叶丛林', '2024-01-03 11:24:00', '叶丛林', '2024-01-03 11:24:00', 0, 0, 1,
        null),
       ('11247', '11241', '原生库', '/resource/dll', 'resource/Dll', '原生库', null, 1, null, '0', 16, 1,
        'HomeOutlined', 1, 1, 1, 0, 0, null, '叶丛林', '2024-01-03 11:24:00', '叶丛林', '2024-01-03 11:24:00', 0, 0, 1,
        null),
       ('11248', '', '连接管理', '/connection', null, '连接管理', null, 1, null, '0', 17, 1, 'ApartmentOutlined', 0, 0,
        0, 0, 0, null, '叶丛林', '2024-01-03 11:24:00', '叶丛林', '2024-01-03 11:24:00', 0, 0, 1, null),
       ('11249', '11248', '数据库', '/connection/database', 'connection/Database', '数据库', null, 1, null, '0', 18, 1,
        'HomeOutlined', 1, 1, 1, 0, 0, null, '叶丛林', '2024-01-03 11:24:00', '叶丛林', '2024-01-03 11:24:00', 0, 0, 1,
        null),
       ('11250', '11248', 'JMS', '/connection/jms', 'connection/Jms', 'JMS', null, 1, null, '0', 19, 1, 'HomeOutlined',
        1, 1, 1, 0, 0, null, '叶丛林', '2024-01-03 11:24:00', '叶丛林', '2024-01-03 11:24:00', 0, 0, 1, null),
       ('11251', null, '数据处理', '/dataHandle', null, '数据处理', null, 1, null, '0', 20, 1, 'HeatMapOutlined', 0, 0,
        0, 0, 0, null, '叶丛林', '2024-01-03 11:24:00', '叶丛林', '2024-01-03 11:24:00', 0, 0, 1, null),
       ('11252', '11251', '数据转换', '/dataHandle/dataTransfer', 'dataHandle/DataTransfer', '数据转换', null, 1, null,
        '0', 21, 1, 'HomeOutlined', 1, 1, 1, 0, 0, null, '叶丛林', '2024-01-03 11:24:00', '叶丛林',
        '2024-01-03 11:24:00', 0, 0, 1, null),
       ('11253', '11251', '变量配置', '/dataHandle/variable', 'dataHandle/Variable', '变量配置', null, 1, null, '0', 22,
        1, 'HomeOutlined', 1, 1, 1, 0, 0, null, '叶丛林', '2024-01-03 11:24:00', '叶丛林', '2024-01-03 11:24:00', 0, 0,
        1, null),
       ('11254', '11251', '编码集', '/dataHandle/codeSet', 'dataHandle/CodeSet', '编码集', null, 1, null, '0', 23, 1,
        'HomeOutlined', 1, 1, 1, 0, 0, null, '叶丛林', '2024-01-03 11:24:00', '叶丛林', '2024-01-03 11:24:00', 0, 0, 1,
        null),
       ('11255', '11251', '共享脚本', '/dataHandle/script', 'dataHandle/Script', '共享脚本', null, 1, null, '0', 24, 1,
        'HomeOutlined', 1, 1, 1, 0, 0, null, '叶丛林', '2024-01-03 11:24:00', '叶丛林', '2024-01-03 11:24:00', 0, 0, 1,
        null),
       ('11256', null, '系统管理', '/system', null, '系统管理', null, 0, null, '0', 25, 1, 'SettingOutlined', 0, 0, 1,
        0, 0, null, '叶丛林', '2024-01-03 11:24:00', '叶丛林', '2024-01-03 11:24:00', 0, 0, 1, null),
       ('11257', '11256', '用户管理', '/system/user', 'system/User', '用户管理', null, 1, null, '0', 26, 1,
        'UserOutlined', 1, 1, 1, 0, 0, null, '叶丛林', '2024-01-03 11:24:00', '叶丛林', '2024-01-03 11:24:00', 0, 0, 1,
        null),
       ('11258', '11256', '角色管理', '/system/role', 'system/Role', '角色管理', null, 1, null, '0', 27, 1,
        'UsergroupDeleteOutlined', 1, 1, 1, 0, 0, null, '叶丛林', '2024-01-03 11:24:00', '叶丛林',
        '2024-01-03 11:24:00', 0, 0, 1, null),
       ('11259', '11256', '菜单管理', '/system/menu', 'system/Menu', '菜单管理', null, 1, null, '0', 28, 1,
        'MenuOutlined', 1, 1, 1, 0, 0, null, '叶丛林', '2024-01-03 11:24:00', '叶丛林', '2024-01-03 11:24:00', 0, 0, 1,
        null),
       ('11260', '11256', '权限分配', '/system/permission', 'system/Permission', '权限分配', null, 1, null, '0', 29, 1,
        'HomeOutlined', 1, 1, 1, 0, 0, null, '叶丛林', '2024-01-03 11:24:00', '叶丛林', '2024-01-03 11:24:00', 0, 0, 1,
        null),
       ('11261', '11256', '数据字典', '/system/dictionary', 'system/Dictionary', '数据字典', null, 1, null, '0', 30, 1,
        'HomeOutlined', 1, 1, 1, 0, 0, null, '叶丛林', '2024-01-03 11:24:00', '叶丛林', '2024-01-03 11:24:00', 0, 0, 1,
        null),
       ('11262', '11256', '字典分类', '/system/dictionaryCategory', 'system/DictionaryCategory', '字典分类', null, 1,
        null, '0', 31, 1, 'HomeOutlined', 1, 1, 1, 0, 0, null, '叶丛林', '2024-01-03 11:24:00', '叶丛林',
        '2024-01-03 11:24:00', 0, 0, 1, null),
       ('11263', '11256', '系统公告', '/system/announcement', 'system/Announcement', '系统公告', null, 1, null, '0', 32,
        1, 'HomeOutlined', 1, 1, 1, 0, 0, null, '叶丛林', '2024-01-03 11:24:00', '叶丛林', '2024-01-03 11:24:00', 0, 0,
        1, null),
       ('11264', null, '系统监控', '/monitor', null, '系统监控', null, 0, null, '0', 33, 1, 'MonitorOutlined', 0, 0, 1,
        0, 0, null, '叶丛林', '2024-01-03 11:24:00', '叶丛林', '2024-01-03 11:24:00', 0, 0, 1, null),
       ('11265', '11264', '定时器', '/monitor/timer', 'monitor/Timer', '定时器', null, 1, null, '0', 34, 1,
        'FieldTimeOutlined', 1, 1, 1, 0, 0, null, '叶丛林', '2024-01-03 11:24:00', '叶丛林', '2024-01-03 11:24:00', 0,
        0, 1, null),
       ('11266', '11264', '数据日志', '/monitor/dataLog', 'monitor/DataLog', '数据日志', null, 1, null, '0', 35, 1,
        'HomeOutlined', 1, 1, 1, 0, 0, null, '叶丛林', '2024-01-03 11:24:00', '叶丛林', '2024-01-03 11:24:00', 0, 0, 1,
        null),
       ('11267', '11264', '日志管理', '/monitor/log', 'monitor/Log', '日志管理', null, 1, null, '0', 36, 1,
        'HomeOutlined', 1, 1, 1, 0, 0, null, '叶丛林', '2024-01-03 11:24:00', '叶丛林', '2024-01-03 11:24:00', 0, 0, 1,
        null),
       ('11268', '11264', 'SQL监控', '/monitor/sql', 'monitor/Sql', 'SQL监控', null, 1, null, '0', 37, 1,
        'ConsoleSqlOutlined', 1, 1, 1, 0, 0, null, '叶丛林', '2024-01-03 11:24:00', '叶丛林', '2024-01-03 11:24:00', 0,
        0, 1, null),
       ('11269', '11264', '性能监控', '/monitor/performance', 'monitor/Performance', '性能监控', null, 1, null, '0', 38,
        1, 'HomeOutlined', 1, 1, 1, 0, 0, null, '叶丛林', '2024-01-03 11:24:00', '叶丛林', '2024-01-03 11:24:00', 0, 0,
        1, null),
       ('11270', '11264', '网关路由', '/monitor/gateway', 'monitor/Gateway', '网关路由', null, 1, null, '0', 39, 1,
        'GatewayOutlined', 1, 1, 1, 0, 0, null, '叶丛林', '2024-01-03 11:24:00', '叶丛林', '2024-01-03 11:24:00', 0, 0,
        1, null),
       ('11271', null, '消息中心', '/message', null, '消息中心', null, 1, null, '0', 40, 1, 'CommentOutlined', 0, 0, 1,
        0, 0, null, '叶丛林', '2024-01-03 11:24:00', '叶丛林', '2024-01-03 11:24:00', 0, 0, 1, null),
       ('11272', '11271', '消息管理', '/message/messageManager', 'message/MessageManager', '消息管理', null, 1, null,
        '0', 41, 1, 'HomeOutlined', 1, 1, 1, 0, 0, null, '叶丛林', '2024-01-03 11:24:00', '叶丛林',
        '2024-01-03 11:24:00', 0, 0, 1, null),
       ('11273', '11271', '消息模板', '/message/template', 'message/Template', '消息模板', null, 1, null, '0', 42, 1,
        'HomeOutlined', 1, 1, 1, 0, 0, null, '叶丛林', '2024-01-03 11:24:00', '叶丛林', '2024-01-03 11:24:00', 0, 0, 1,
        null),
       ('11274', null, '编辑器', '/editor', null, '编辑器', null, 1, null, '0', 43, 1, 'FileDoneOutlined', 0, 0, 0, 0,
        0, null, '叶丛林', '2024-01-03 11:24:00', '叶丛林', '2024-01-03 11:24:00', 0, 0, 1, null),
       ('11275', '11274', '文档编辑器', '/editor/docEditor', 'editor/DocEditor', '文档编辑器', null, 1, null, '0', 44,
        1, 'EditOutlined', 1, 1, 1, 0, 0, null, '叶丛林', '2024-01-03 11:24:00', '叶丛林', '2024-01-03 11:24:00', 0, 0,
        1, null);
