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

INSERT INTO public.t_sys_role_menu (id, role_id, menu_id, operate_ip, create_by, create_time, update_by, update_time) VALUES (235123826202185721, 235123826202185723, 235123826202185728, '127.0.0.1', 235123826202185728, '2025-03-12 15:09:46.000000', 235123826202185728, '2025-03-27 11:29:22.527374');
INSERT INTO public.t_sys_role_menu (id, role_id, menu_id, operate_ip, create_by, create_time, update_by, update_time) VALUES (235123826202185722, 235123826202185723, 235123826202185729, '127.0.0.1', 235123826202185728, '2025-03-12 15:10:33.000000', 235123826202185728, '2025-03-27 11:29:22.532705');
INSERT INTO public.t_sys_role_menu (id, role_id, menu_id, operate_ip, create_by, create_time, update_by, update_time) VALUES (235123826202185723, 235123826202185723, 257681744906833920, '127.0.0.1', 235123826202185728, '2025-03-13 14:39:11.000000', 235123826202185728, '2025-03-27 11:29:22.537284');
INSERT INTO public.t_sys_role_menu (id, role_id, menu_id, operate_ip, create_by, create_time, update_by, update_time) VALUES (235123826202185724, 235123826202185723, 259950204508307456, '127.0.0.1', 235123826202185728, '2025-03-19 21:05:19.000000', 235123826202185728, '2025-03-27 11:29:22.542028');
INSERT INTO public.t_sys_role_menu (id, role_id, menu_id, operate_ip, create_by, create_time, update_by, update_time) VALUES (235123826202185725, 235123826202185723, 259950660651450368, '127.0.0.1', 235123826202185728, '2025-03-19 21:05:55.000000', 235123826202185728, '2025-03-27 11:29:22.545095');
INSERT INTO public.t_sys_role_menu (id, role_id, menu_id, operate_ip, create_by, create_time, update_by, update_time) VALUES (262708144566890496, 235123826202185723, 262707554793222144, '127.0.0.1', 235123826202185728, '2025-03-27 11:29:22.549716', 235123826202185728, '2025-03-27 11:29:22.549726');
INSERT INTO public.t_sys_role_menu (id, role_id, menu_id, operate_ip, create_by, create_time, update_by, update_time) VALUES (262708144566890497, 235123826202185723, 262708109523480576, '127.0.0.1', 235123826202185728, '2025-03-27 11:29:22.554033', 235123826202185728, '2025-03-27 11:29:22.554044');
