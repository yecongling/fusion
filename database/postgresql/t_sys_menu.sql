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
    owner to postgres;

create index idx_sp_sort_no
    on t_sys_menu (sort_no);

create index idx_sp_name
    on t_sys_menu (name);

INSERT INTO public.t_sys_menu (id, parent_id, name, url, component, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_route, is_leaf, keep_alive, hidden, hide_tab, description, del_flag, status, internal_or_external, rule_flag, create_by, create_time, update_by, update_time) VALUES (235123826202185728, null, '系统管理', '/system', null, '系统管理', null, 0, null, 0, 27.00, true, 'SettingOutlined', false, false, false, false, false, null, false, true, false, false, 235123826202185728, '2025-03-12 15:01:50.000000', 235123826202185728, '2025-03-12 15:01:54.000000');
INSERT INTO public.t_sys_menu (id, parent_id, name, url, component, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_route, is_leaf, keep_alive, hidden, hide_tab, description, del_flag, status, internal_or_external, rule_flag, create_by, create_time, update_by, update_time) VALUES (235123826202185729, 235123826202185728, '菜单管理', '/system/menu', 'system/Menu', '菜单管理', null, 1, null, 0, 28.00, true, 'MenuOutlined', true, true, false, false, false, null, false, true, false, false, 235123826202185728, '2025-03-12 15:05:30.000000', 235123826202185728, '2025-03-12 15:05:32.000000');
