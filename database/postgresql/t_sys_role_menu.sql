create table t_sys_role_menu
(
    id          bigint      not null
        primary key,
    role_id     bigint      not null,
    menu_id     bigint      not null,
    operate_ip  varchar(32) not null,
    create_by   bigint      not null,
    create_time timestamp   not null,
    update_by   bigint      not null,
    update_time timestamp   not null
);

comment on column t_sys_role_menu.id is '主键';

comment on column t_sys_role_menu.role_id is '角色ID';

comment on column t_sys_role_menu.menu_id is '菜单ID';

comment on column t_sys_role_menu.operate_ip is '操作IP';

comment on column t_sys_role_menu.create_by is '创建人';

comment on column t_sys_role_menu.create_time is '创建时间';

comment on column t_sys_role_menu.update_by is '更新人';

comment on column t_sys_role_menu.update_time is '更新时间';

alter table t_sys_role_menu
    owner to postgres;

INSERT INTO public.t_sys_role_menu (id, role_id, menu_id, operate_ip, create_by, create_time, update_by, update_time) VALUES (262134882401153024, 235123826202185723, 235123826202185728, '0:0:0:0:0:0:0:1', 235123826202185728, '2025-03-25 21:31:26.200317', 235123826202185728, '2025-03-25 21:31:26.200325');
INSERT INTO public.t_sys_role_menu (id, role_id, menu_id, operate_ip, create_by, create_time, update_by, update_time) VALUES (262134882401153025, 235123826202185723, 235123826202185729, '0:0:0:0:0:0:0:1', 235123826202185728, '2025-03-25 21:31:26.202932', 235123826202185728, '2025-03-25 21:31:26.202937');
INSERT INTO public.t_sys_role_menu (id, role_id, menu_id, operate_ip, create_by, create_time, update_by, update_time) VALUES (262134882401153026, 235123826202185723, 259950204508307456, '0:0:0:0:0:0:0:1', 235123826202185728, '2025-03-25 21:31:26.205240', 235123826202185728, '2025-03-25 21:31:26.205245');
INSERT INTO public.t_sys_role_menu (id, role_id, menu_id, operate_ip, create_by, create_time, update_by, update_time) VALUES (262134882401153027, 235123826202185723, 259950660651450368, '0:0:0:0:0:0:0:1', 235123826202185728, '2025-03-25 21:31:26.207532', 235123826202185728, '2025-03-25 21:31:26.207536');
INSERT INTO public.t_sys_role_menu (id, role_id, menu_id, operate_ip, create_by, create_time, update_by, update_time) VALUES (262134882401153028, 235123826202185723, 257681744906833920, '0:0:0:0:0:0:0:1', 235123826202185728, '2025-03-25 21:31:26.210001', 235123826202185728, '2025-03-25 21:31:26.210006');
