/* 更高效的mysql8中的分页查询
   SELECT * FROM (
    SELECT *, ROW_NUMBER() OVER (ORDER BY id) AS row_num FROM t_sys_permission tsp
) AS temp
WHERE row_num BETWEEN 0 AND 10;
 */

/*  新建用户信息表 */
DROP TABLE IF EXISTS `t_sys_user`;
CREATE TABLE `t_sys_user`
(
    `id`             varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '主键id',
    `username`       varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '登录账号',
    `real_name`      varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '真实姓名',
    `password`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
    `salt`           varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT NULL COMMENT 'md5密码盐',
    `avatar`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
    `birthday`       datetime                                                      NULL DEFAULT NULL COMMENT '生日',
    `sex`            tinyint(1)                                                    NULL DEFAULT 0 COMMENT '性别(0-默认未知,1-男,2-女)',
    `email`          varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT NULL COMMENT '电子邮件',
    `phone`          varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT NULL COMMENT '电话',
    `org_code`       varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT NULL COMMENT '登录会话的机构编码',
    `status`         tinyint(1)                                                    NULL DEFAULT 1 COMMENT '状态(1-正常,2-冻结)',
    `del_flag`       tinyint(1)                                                    NULL DEFAULT 0 COMMENT '删除状态(0-正常,1-已删除)',
    `third_id`       varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '第三方登录的唯一标识',
    `third_type`     varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '第三方类型',
    `activity_sync`  tinyint(1)                                                    NULL DEFAULT 0 COMMENT '同步工作流引擎(1-同步,0-不同步)',
    `work_no`        varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '工号，唯一键',
    `post`           varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '职务，关联职务表',
    `telephone`      varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT NULL COMMENT '座机号',
    `create_by`      varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '创建人',
    `create_time`    datetime                                                      NOT NULL COMMENT '创建日期',
    `update_by`      varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '更新人',
    `update_time`    datetime                                                      NOT NULL COMMENT '更新日期',
    `user_identity`  tinyint(1)                                                    NULL DEFAULT 1 COMMENT '身份（1普通成员 2上级）',
    `depart_ids`     longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci     NULL COMMENT '负责部门',
    `rel_tenant_ids` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '多租户标识',
    `client_id`      varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT NULL COMMENT '设备ID',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `uniq_sys_user_work_no` (`work_no`) USING BTREE,
    UNIQUE INDEX `uniq_sys_user_username` (`username`) USING BTREE,
    UNIQUE INDEX `uniq_sys_user_phone` (`phone`) USING BTREE,
    UNIQUE INDEX `uniq_sys_user_email` (`email`) USING BTREE,
    INDEX `idx_su_username` (`username`) USING BTREE,
    INDEX `idx_su_status` (`status`) USING BTREE,
    INDEX `idx_su_del_flag` (`del_flag`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '用户表'
  ROW_FORMAT = Dynamic;

/* 新建系统角色表 */
DROP TABLE IF EXISTS `t_sys_role`;
CREATE TABLE `t_sys_role`
(
    `role_id`     varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '主键id',
    `role_name`   varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '角色名称',
    `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '描述',
    `create_by`   varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '创建人',
    `create_time` datetime                                                      NOT NULL COMMENT '创建日期',
    `update_by`   varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '更新人',
    `update_time` datetime                                                      NOT NULL COMMENT '更新日期',
    `status`      tinyint(1)                                                    NOT NULL DEFAULT 1 comment '角色状态',
    PRIMARY KEY (`role_id`) USING BTREE,
    UNIQUE INDEX `uniq_sys_role_name` (`role_name`) USING BTREE,
    INDEX `idx_sr_role_name` (`role_name`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '角色表'
  ROW_FORMAT = Dynamic;

/* 新建用户角色关联表 */
DROP TABLE IF EXISTS `t_sys_user_role`;
CREATE TABLE `t_sys_user_role`
(
    `id`      varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
    `user_id` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户id',
    `role_id` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色id',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `idx_sur_user_id` (`user_id`) USING BTREE,
    INDEX `idx_sur_role_id` (`role_id`) USING BTREE,
    INDEX `idx_sur_user_role_id` (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '用户角色表'
  ROW_FORMAT = Dynamic;


-- ----------------------------
-- 系统菜单表（2023-01-30新加）
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_permission`;
CREATE TABLE `t_sys_permission`
(
    `id`                   varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '主键id',
    `parent_id`            varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT NULL COMMENT '父id',
    `name`                 varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单标题',
    `url`                  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '路径',
    `component`            varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组件',
    `component_name`       varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组件名字',
    `redirect`             varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '一级菜单跳转地址',
    `menu_type`            int(11)                                                       NULL DEFAULT NULL COMMENT '菜单类型(0:一级菜单; 1:子菜单:2:按钮权限)',
    `perms`                varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单权限编码',
    `perms_type`           varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT '0' COMMENT '权限策略1显示2禁用',
    `sort_no`              double(8, 2)                                                  NULL DEFAULT NULL COMMENT '菜单排序',
    `always_show`          tinyint(1)                                                    NULL DEFAULT NULL COMMENT '聚合子路由: 1是0否',
    `icon`                 varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
    `is_route`             tinyint(1)                                                    NULL DEFAULT 1 COMMENT '是否路由菜单: 0:不是  1:是（默认值1）',
    `is_leaf`              tinyint(1)                                                    NULL DEFAULT NULL COMMENT '是否叶子节点:    1:是   0:不是',
    `keep_alive`           tinyint(1)                                                    NULL DEFAULT NULL COMMENT '是否缓存该页面:    1:是   0:不是',
    `hidden`               tinyint(1)                                                    NULL DEFAULT 0 COMMENT '是否隐藏路由: 0否,1是',
    `hide_tab`             tinyint(1)                                                    NULL DEFAULT NULL COMMENT '是否隐藏tab: 0否,1是',
    `description`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
    `create_by`            varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '创建人',
    `create_time`          datetime                                                      NOT NULL COMMENT '创建日期',
    `update_by`            varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '更新人',
    `update_time`          datetime                                                      NOT NULL COMMENT '更新日期',
    `del_flag`             int(1)                                                        NULL DEFAULT 0 COMMENT '删除状态 0正常 1已删除',
    `rule_flag`            int(3)                                                        NULL DEFAULT 0 COMMENT '是否添加数据权限1是0否',
    `status`               varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci   NULL DEFAULT NULL COMMENT '按钮权限状态(0无效1有效)',
    `internal_or_external` tinyint(1)                                                    NULL DEFAULT NULL COMMENT '外链菜单打开方式 0/内部打开 1/外部打开',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `idx_sp_parent_id` (`parent_id`) USING BTREE,
    INDEX `idx_sp_is_route` (`is_route`) USING BTREE,
    INDEX `idx_sp_is_leaf` (`is_leaf`) USING BTREE,
    INDEX `idx_sp_sort_no` (`sort_no`) USING BTREE,
    INDEX `idx_sp_del_flag` (`del_flag`) USING BTREE,
    INDEX `idx_sp_menu_type` (`menu_type`) USING BTREE,
    INDEX `idx_sp_hidden` (`hidden`) USING BTREE,
    INDEX `idx_sp_status` (`status`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '菜单权限表'
  ROW_FORMAT = Dynamic;


/*  系统角色和菜单关联表  */
-- ----------------------------
-- Table structure for t_sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role_permission`;
CREATE TABLE `t_sys_role_permission`
(
    `create_by`     varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci   NOT NULL COMMENT '创建人',
    `create_time`   datetime                                                       NOT NULL COMMENT '创建日期',
    `update_by`     varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci   NOT NULL COMMENT '更新人',
    `update_time`   datetime                                                       NOT NULL COMMENT '更新日期',
    `id`            varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci   NOT NULL,
    `role_id`       varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci   NULL DEFAULT NULL COMMENT '角色id',
    `permission_id` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci   NULL DEFAULT NULL COMMENT '权限id',
    `data_rule_ids` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '数据权限ids',
    `operate_date`  datetime                                                       NULL DEFAULT NULL COMMENT '操作时间',
    `operate_ip`    varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT NULL COMMENT '操作ip',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `idx_srp_role_per_id` (`role_id`, `permission_id`) USING BTREE,
    INDEX `idx_srp_role_id` (`role_id`) USING BTREE,
    INDEX `idx_srp_permission_id` (`permission_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '角色权限表'
  ROW_FORMAT = Dynamic;

/*  ============== 以下是引擎部分需要的=================*/


/* 资源类型表 */

DROP DATABASE IF EXISTS `t_engine_resource_category`;
CREATE TABLE `t_engine_resource_category`
(
    `create_by`   varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '创建人',
    `create_time` datetime                                                      NOT NULL COMMENT '创建日期',
    `update_by`   varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '更新人',
    `update_time` datetime                                                      NOT NULL COMMENT '更新日期',
    `id`          varchar(16) character set utf8mb4 collate utf8mb4_general_ci  not null,
    `parent_id`   varchar(16) character set utf8mb4 collate utf8mb4_general_ci  NULL default null comment '上级id',
    `name`        varchar(100) character set utf8mb4 collate utf8mb4_general_ci NOT NULL comment '分类名称',
    primary key (`id`) using btree
) engine = InnoDB
  character set = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '引擎资源类型表'
  ROW_FORMAT = Dynamic;


/* 资源表 包括众多资源，如JDBC驱动、JSON数据模式、XSD数据模式、XSLT、SSL秘钥、证书、对称加密秘钥、WSDL、DLL */
DROP DATABASE IF EXISTS `t_engine_resource`;
CREATE TABLE `t_engine_resource`
(
    `create_by`   varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '创建人',
    `create_time` datetime                                                      NOT NULL COMMENT '创建日期',
    `update_by`   varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '更新人',
    `update_time` datetime                                                      NOT NULL COMMENT '更新日期',
    `id`          varchar(16) character set utf8mb4 collate utf8mb4_general_ci  not null,
    `category_id` varchar(16) character set utf8mb4 collate utf8mb4_general_ci  not null comment '分类id',
    `name`        varchar(100) character set utf8mb4 collate utf8mb4_general_ci not null comment '资源名称',
    primary key (`id`) using btree
) engine = InnoDB
  character set = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '引擎资源表'
  ROW_FORMAT = Dynamic;

/* 数据库资源 */
DROP TABLE IF EXISTS `t_engine_datasource`;
CREATE TABLE `t_engine_datasource`
(
    `id`                 varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL,
    `name`               varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '数据源名称',
    `remark`             varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
    `datasource_type`    varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT NULL COMMENT '数据库类型',
    `datasource_url`     varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '数据源地址',
    `test_query`         varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '测试语句',
    `username`           varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
    `password`           varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
    `variable_password`  bit                                                           NULL DEFAULT NULL COMMENT '使用变量',
    `connection_timeout` int                                                                default 30 NOT NULL COMMENT '连接超时时间',
    `idle_timeout`       int                                                                default 30 NOT NULL COMMENT '闲置超时时间',
    `max_lifetime`       int                                                                default 30 not null COMMENT '连接存活时间',
    `min_idle`           int                                                                default 8 not null COMMENT '最小闲置连接数',
    `max_pool_size`      int                                                                default 20 not null COMMENT '最大连接池数量',
    `create_by`          varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '创建人',
    `create_time`        datetime                                                      NOT NULL COMMENT '创建日期',
    `update_by`          varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '更新人',
    `update_time`        datetime                                                      NOT NULL COMMENT '更新日期',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic;

/* 新建项目表 */
drop table if exists `t_engine_project`;
create table `t_engine_project`
(
    `id`          varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci not null comment '项目ID',
    `create_by`   varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
    `create_time` datetime                                                     NOT NULL COMMENT '创建日期',
    `update_by`   varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '更新人',
    `update_time` datetime                                                     NOT NULL COMMENT '更新日期',
    `name`        varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '项目名',
    `type`        tinyint(1)                                                   NULL     DEFAULT NULL COMMENT '项目类型（1-集成项目 2-接口项目）',
    `log_type`    tinyint(1)                                                   NULL     DEFAULT NULL COMMENT '日志类型（1、全部记录 2、仅记录失败 3、精简日志 4、不记录）',
    `status`      tinyint(1)                                                   NULL     DEFAULT NULL COMMENT '项目状态（0-全部停止 1-部分启动 2-全部启动）',
    `priority`    tinyint(1)                                                   not NULL DEFAULT 1 COMMENT '项目优先级（1-10）',
    `del_flag`    tinyint(1)                                                   not null default 0 comment '删除标记（1、删除状态 0、未删除状态）针对是否放置在回收站',
    `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci        NULL     DEFAULT NULL COMMENT '项目描述',
    `routes`      text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci        NULL     DEFAULT NULL COMMENT '项目包含的路由ID',
    `endpoints`   text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci        NULL     DEFAULT NULL COMMENT '项目包含的终端ID',
    `links`       text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci        NULL     DEFAULT NULL COMMENT '项目包含的连线ID（第一个编辑器内部）',
    primary key (`id`) using btree,
    index `idx_project_id` (`id`) using btree
) ENGINE = InnoDB
  character set = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '项目信息'
  ROW_FORMAT = Dynamic;

/* 新建变量表 */
drop table if exists `t_engine_variable`;
create table `t_engine_variable`
(

    `id`          varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  not null comment '变量ID',
    `create_by`   varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '创建人',
    `create_time` datetime                                                      NOT NULL COMMENT '创建日期',
    `update_by`   varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '更新人',
    `update_time` datetime                                                      NOT NULL COMMENT '更新日期',
    `name`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT NULL COMMENT '变量名字',
    `encrypt`     bit                                                           NULL DEFAULT NULL COMMENT '是否加密',
    `encryption`  varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT NULL COMMENT '加密方式',
    `value`       varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '变量值',
    primary key (`id`) using BTREE,
    index `idx_variable_id` (`id`) using btree
) ENGINE = InnoDB
  character set = utf8mb4
  collate = utf8mb4_general_ci comment = '变量表'
  row_format = DYNAMIC;

/* 新建终端属性配置表 */
drop table if exists t_engine_endpoint_properties;
create table `t_engine_endpoint_properties`
(
    id               varchar(16)                                                  not null comment '端点类型唯一ID',
    `create_by`      varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
    `create_time`    datetime                                                     NOT NULL COMMENT '创建日期',
    `update_by`      varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '更新人',
    `update_time`    datetime                                                     NOT NULL COMMENT '更新日期',
    `endpoint_type`  varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci not null COMMENT '端点类型',
    `name`           varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci not null COMMENT '属性名',
    `title`          varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci null     default null COMMENT '标题',
    `type`           varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci null     default null COMMENT '类型',
    `required`       tinyint(1)                                                   not null default 0 COMMENT '属性名',
    `allowed_values` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci        null     default NULL COMMENT '允许的属性值',
    `default_value`  varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci null     default NULL COMMENT '默认值',
    `endpoint_mode`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci not null NULL COMMENT '端点模式',
    `masked`         tinyint(1)                                                   not null default 0 COMMENT '标记',
    `mode_required`  tinyint(1)                                                   not null default 0 COMMENT '模式必填',
    `applies_to`     varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  not null COMMENT '用于哪一端 生产-PRODUCER（IN/IN_OUT） 消费-CONSUMER（OUT/OUT_IN）',
    `tips`           text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci        NUll COMMENT '提示信息',
    primary key (`id`) using BTREE,
    index `idx_variable_ep_id` (`endpoint_type`) using btree,
    index `idx_variable_ep_type_name` (`endpoint_type`) using btree,
    index `idx_variable_applies_to` (`applies_to`) using btree,
    index `idx_variable_name` (`name`) using btree
) ENGINE = InnoDB
  character set = utf8mb4
  collate = utf8mb4_general_ci comment = '终端属性配置表'
  row_format = DYNAMIC;

/* 新建路由表 */
drop table if exists `t_engine_route`;
create table t_engine_route
(
    `create_by`         varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
    `create_time`       datetime                                                     NOT NULL COMMENT '创建日期',
    `update_by`         varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '更新人',
    `update_time`       datetime                                                     NOT NULL COMMENT '更新日期',
    id                  varchar(16)                                                  not null comment '路由唯一ID',
    project_id          varchar(16)                                                  null comment '所属项目',
    name                varchar(128)                                                 not null comment '路由名字',
    description         varchar(256)                                                 null comment '描述',
    debug               tinyint     default 0                                        not null comment '启用调试模式',
    deep_clone          tinyint     default 0                                        not null comment '启用深度克隆',
    processing_strategy varchar(12) default 'SEQUENTIAL'                             not null comment '处理策略',
    thread_count        int         default 1                                        not null comment '处理线程数',
    x                   int         default 0                                        not null comment '路由x位置',
    y                   int         default 0                                        not null comment '路由y位置',
    nodes               text                                                         null comment '路由包含的节点',
    links               text                                                         null comment '路由包含的连线',
    PRIMARY KEY (`id`) USING BTREE,
    index `idx_route_id` (`id`) using btree
) engine = InnoDB
  character set = utf8mb4
  collate = utf8mb4_general_ci comment '引擎路由表'
  row_format = DYNAMIC;

/* 新建端点表 字段还未添加完毕，后续还需要添加更多的其它字段 */
drop table if exists `t_engine_endpoint`;
create table t_engine_endpoint
(
    `create_by`   varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '创建人',
    `create_time` datetime                                                      NOT NULL COMMENT '创建日期',
    `update_by`   varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '更新人',
    `update_time` datetime                                                      NOT NULL COMMENT '更新日期',
    `id`          varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  not null comment '端点唯一ID',
    `name`        varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci not null comment '端点名字',
    `description` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci null comment '描述',
    `project_id`  varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci comment '所属项目ID（也可能同时属于多个项目，也可能不属于任何项目，提前配置的）',
    `type`        varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci not null comment '类型（如http、soap、DB、FTP……）',
    `mode`        varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  not null comment '模式，进出或进、出模式',
    `status`      int                                                           not null default 1 comment '状态 1-正常 2-部分异常 3-异常',
    `del_flag`    bit                                                           not null default 0 comment '删除标记 1 - 删除 0 - 未删除',
    `configs`     text                                                          null comment '配置数据',
    PRIMARY KEY (`id`, `type`, `mode`) USING BTREE,
    index `idx_endpoint_id` (`id`) using btree,
    index `idx_endpoint_type` (`type`) using btree,
    index `idx_endpoint_mode` (`mode`) using btree
) engine = InnoDB
  character set = utf8mb4
  collate = utf8mb4_general_ci comment '引擎端点表'
  row_format = DYNAMIC;

/* 端点类型表 */
drop table if exists `t_engine_endpoint_type`;
create table if not exists integration.t_engine_endpoint_type
(
    id              varchar(16)                                                  not null comment '端点类型唯一ID',
    name            varchar(16) charset utf8mb4 COLLATE utf8mb4_general_ci       not null comment '类型名',
    supported_modes varchar(32) charset utf8mb4 COLLATE utf8mb4_general_ci       not null comment '支持的模式',
    create_by       varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci not null comment '创建人',
    create_time     datetime                                                     not null comment '创建时间',
    update_by       varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci null comment '更新人',
    update_time     datetime                                                     not null comment '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    index `idx_endpoint_id` (`id`) using btree
) engine = InnoDB
  character set = utf8mb4
    comment '端点类型表'
  row_format = DYNAMIC;
