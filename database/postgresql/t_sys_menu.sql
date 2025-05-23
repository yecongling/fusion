create table if not exists t_sys_menu
(
    id                   bigint      not null
        primary key,
    parent_id            bigint,
    name                 varchar(64) not null,
    url                  varchar(64),
    component            varchar(32),
    component_name       varchar(64),
    redirect             varchar(64),
    menu_type            smallint    not null,
    perms                varchar(32),
    perms_type           smallint,
    sort_no              numeric(4, 2),
    always_show          boolean,
    icon                 varchar(32),
    is_route             boolean,
    is_leaf              boolean,
    keep_alive           boolean,
    hidden               boolean,
    hide_tab             boolean,
    description          varchar(64),
    del_flag             boolean,
    status               boolean,
    internal_or_external boolean,
    rule_flag            boolean,
    create_by            bigint      not null,
    create_time          timestamp   not null,
    update_by            bigint      not null,
    update_time          timestamp   not null
);

comment on column t_sys_menu.id is '主键';

comment on column t_sys_menu.parent_id is '上级菜单';

comment on column t_sys_menu.name is '菜单名称';

comment on column t_sys_menu.url is '路径';

comment on column t_sys_menu.component is '组件';

comment on column t_sys_menu.component_name is '组件名字';

comment on column t_sys_menu.redirect is '一级菜单跳转地址';

comment on column t_sys_menu.menu_type is '菜单类型（0、一级菜单、1、子菜单 2、子路由 3、按钮权限）';

comment on column t_sys_menu.perms is '菜单权限编码';

comment on column t_sys_menu.perms_type is '权限策略 1、显示 2、禁用';

comment on column t_sys_menu.sort_no is '排序';

comment on column t_sys_menu.always_show is '始终显示';

comment on column t_sys_menu.icon is '菜单图标';

comment on column t_sys_menu.is_route is '是否路由';

comment on column t_sys_menu.is_leaf is '是否叶子节点';

comment on column t_sys_menu.keep_alive is '缓存该页面';

comment on column t_sys_menu.hidden is '隐藏';

comment on column t_sys_menu.hide_tab is '隐藏tab';

comment on column t_sys_menu.description is '描述';

comment on column t_sys_menu.del_flag is '删除标记';

comment on column t_sys_menu.status is '权限按钮状态';

comment on column t_sys_menu.internal_or_external is '内置还是外挂';

comment on column t_sys_menu.rule_flag is '添加数据权限';

comment on column t_sys_menu.create_by is '创建人';

comment on column t_sys_menu.create_time is '创建时间';

comment on column t_sys_menu.update_by is '更新人';

comment on column t_sys_menu.update_time is '更新时间';

alter table t_sys_menu
    owner to fusion;

create index idx_sp_sort_no
    on t_sys_menu (sort_no);

create index idx_sp_name
    on t_sys_menu (name);

INSERT INTO public.t_sys_menu (id, parent_id, name, url, component, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_route, is_leaf, keep_alive, hidden, hide_tab, description, del_flag, status, internal_or_external, rule_flag, create_by, create_time, update_by, update_time) VALUES (257681744906833920, null, 'menu.home', '/home', 'Home', '首页', null, 1, null, null, 1.00, false, 'HomeOutlined', true, false, false, false, false, null, false, true, false, false, 235123826202185728, '2025-03-13 14:36:15.500940', 235123826202185728, '2025-03-13 14:36:15.500940');
INSERT INTO public.t_sys_menu (id, parent_id, name, url, component, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_route, is_leaf, keep_alive, hidden, hide_tab, description, del_flag, status, internal_or_external, rule_flag, create_by, create_time, update_by, update_time) VALUES (262781066388107264, 262780659381235712, 'menu.integration.apps', '/integrated/apps', 'integrated/Apps', '应用中心', null, 1, null, null, 21.00, false, 'ProjectOutlined', true, false, false, false, false, null, false, true, false, false, 235123826202185728, '2025-03-27 16:19:08.467428', 235123826202185728, '2025-03-27 16:19:25.059057');
INSERT INTO public.t_sys_menu (id, parent_id, name, url, component, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_route, is_leaf, keep_alive, hidden, hide_tab, description, del_flag, status, internal_or_external, rule_flag, create_by, create_time, update_by, update_time) VALUES (259950204508307456, 235123826202185728, 'menu.system.user', '/system/user', 'system/User', null, null, 1, null, null, 26.00, false, 'UserOutlined', true, false, false, false, false, null, false, true, false, false, 235123826202185728, '2025-03-19 20:50:18.414244', 235123826202185728, '2025-03-19 20:50:31.814702');
INSERT INTO public.t_sys_menu (id, parent_id, name, url, component, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_route, is_leaf, keep_alive, hidden, hide_tab, description, del_flag, status, internal_or_external, rule_flag, create_by, create_time, update_by, update_time) VALUES (262708109523480576, 235123826202185728, 'menu.system.param', '/system/params', 'system/Params', '系统参数', null, 1, null, null, 30.00, false, 'ToolOutlined', true, false, false, false, false, null, false, true, false, false, 235123826202185728, '2025-03-27 11:29:14.197218', 235123826202185728, '2025-03-27 11:29:14.197229');
INSERT INTO public.t_sys_menu (id, parent_id, name, url, component, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_route, is_leaf, keep_alive, hidden, hide_tab, description, del_flag, status, internal_or_external, rule_flag, create_by, create_time, update_by, update_time) VALUES (262780659381235712, null, 'menu.integration.management', '/integrated', null, null, null, 0, null, null, 20.00, false, 'DeploymentUnitOutlined', false, false, false, false, false, null, false, true, false, false, 235123826202185728, '2025-03-27 16:17:31.429385', 235123826202185728, '2025-03-27 16:17:31.429394');
INSERT INTO public.t_sys_menu (id, parent_id, name, url, component, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_route, is_leaf, keep_alive, hidden, hide_tab, description, del_flag, status, internal_or_external, rule_flag, create_by, create_time, update_by, update_time) VALUES (262782638253862912, 262780659381235712, 'menu.integration.workflow', '/integrated/app/:appId/workflow', 'integrated/Workflow', '应用编排', null, 2, null, null, 24.00, false, 'ClusterOutlined', true, false, false, false, false, null, false, true, false, false, 235123826202185728, '2025-03-27 16:25:23.228787', 235123826202185728, '2025-03-27 16:25:23.228798');
INSERT INTO public.t_sys_menu (id, parent_id, name, url, component, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_route, is_leaf, keep_alive, hidden, hide_tab, description, del_flag, status, internal_or_external, rule_flag, create_by, create_time, update_by, update_time) VALUES (235123826202185729, 235123826202185728, 'menu.system.menu', '/system/menu', 'system/Menu', '菜单管理', null, 1, null, 0, 28.00, true, 'MenuOutlined', true, true, false, false, false, null, false, true, false, false, 235123826202185728, '2025-03-12 15:05:30.000000', 235123826202185728, '2025-03-12 15:05:32.000000');
INSERT INTO public.t_sys_menu (id, parent_id, name, url, component, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_route, is_leaf, keep_alive, hidden, hide_tab, description, del_flag, status, internal_or_external, rule_flag, create_by, create_time, update_by, update_time) VALUES (262781914170191872, 262780659381235712, 'menu.integration.endpointConfig', '/integrated/endpointCfg', 'integrated/EndpointCfg', '端点的配置选项维护', null, 1, null, null, 23.00, false, 'AppstoreAddOutlined', true, false, false, false, false, null, false, true, false, false, 235123826202185728, '2025-03-27 16:22:30.594776', 235123826202185728, '2025-03-27 16:22:30.594806');
INSERT INTO public.t_sys_menu (id, parent_id, name, url, component, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_route, is_leaf, keep_alive, hidden, hide_tab, description, del_flag, status, internal_or_external, rule_flag, create_by, create_time, update_by, update_time) VALUES (235123826202185728, null, 'menu.system.management', '/system', null, '系统管理', null, 0, null, 0, 27.00, true, 'SettingOutlined', false, false, false, false, false, null, false, true, false, false, 235123826202185728, '2025-03-12 15:01:50.000000', 235123826202185728, '2025-03-12 15:01:54.000000');
INSERT INTO public.t_sys_menu (id, parent_id, name, url, component, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_route, is_leaf, keep_alive, hidden, hide_tab, description, del_flag, status, internal_or_external, rule_flag, create_by, create_time, update_by, update_time) VALUES (262707554793222144, 235123826202185728, 'menu.system.dict', '/system/dict', 'system/Dict', '数据字典', null, 1, null, null, 29.00, false, 'BookOutlined', true, false, false, false, false, null, false, true, false, false, 235123826202185728, '2025-03-27 11:27:01.939482', 235123826202185728, '2025-03-27 11:27:01.939496');
INSERT INTO public.t_sys_menu (id, parent_id, name, url, component, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_route, is_leaf, keep_alive, hidden, hide_tab, description, del_flag, status, internal_or_external, rule_flag, create_by, create_time, update_by, update_time) VALUES (259950660651450368, 235123826202185728, 'menu.system.role', '/system/role', 'system/Role', '角色管理', null, 1, null, null, 27.00, false, 'UsergroupDeleteOutlined', true, false, false, false, false, null, false, true, false, false, 235123826202185728, '2025-03-19 20:52:07.159059', 235123826202185728, '2025-03-21 16:46:24.830336');
INSERT INTO public.t_sys_menu (id, parent_id, name, url, component, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_route, is_leaf, keep_alive, hidden, hide_tab, description, del_flag, status, internal_or_external, rule_flag, create_by, create_time, update_by, update_time) VALUES (262781545507647488, 262780659381235712, 'menu.integration.endpoint', '/integrated/endpoint', 'integrated/Endpoint', '端点维护模块', null, 1, null, null, 22.00, false, 'ApartmentOutlined', true, false, false, false, false, null, false, true, false, false, 235123826202185728, '2025-03-27 16:21:02.697883', 235123826202185728, '2025-03-27 16:21:02.697894');
