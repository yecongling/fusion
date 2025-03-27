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

INSERT INTO public.t_sys_role_menu (id, role_id, menu_id, operate_ip, create_by, create_time, update_by, update_time) VALUES (235123826202185721, 235123826202185723, 235123826202185728, '127.0.0.1', 235123826202185728, '2025-03-12 15:09:46.000000', 235123826202185728, '2025-03-27 16:25:42.932738');
INSERT INTO public.t_sys_role_menu (id, role_id, menu_id, operate_ip, create_by, create_time, update_by, update_time) VALUES (235123826202185722, 235123826202185723, 235123826202185729, '127.0.0.1', 235123826202185728, '2025-03-12 15:10:33.000000', 235123826202185728, '2025-03-27 16:25:42.942323');
INSERT INTO public.t_sys_role_menu (id, role_id, menu_id, operate_ip, create_by, create_time, update_by, update_time) VALUES (235123826202185723, 235123826202185723, 257681744906833920, '127.0.0.1', 235123826202185728, '2025-03-13 14:39:11.000000', 235123826202185728, '2025-03-27 16:25:42.952404');
INSERT INTO public.t_sys_role_menu (id, role_id, menu_id, operate_ip, create_by, create_time, update_by, update_time) VALUES (235123826202185724, 235123826202185723, 259950204508307456, '127.0.0.1', 235123826202185728, '2025-03-19 21:05:19.000000', 235123826202185728, '2025-03-27 16:25:42.961142');
INSERT INTO public.t_sys_role_menu (id, role_id, menu_id, operate_ip, create_by, create_time, update_by, update_time) VALUES (235123826202185725, 235123826202185723, 259950660651450368, '127.0.0.1', 235123826202185728, '2025-03-19 21:05:55.000000', 235123826202185728, '2025-03-27 16:25:42.969414');
INSERT INTO public.t_sys_role_menu (id, role_id, menu_id, operate_ip, create_by, create_time, update_by, update_time) VALUES (262708144566890496, 235123826202185723, 262707554793222144, '127.0.0.1', 235123826202185728, '2025-03-27 11:29:22.549716', 235123826202185728, '2025-03-27 16:25:42.977539');
INSERT INTO public.t_sys_role_menu (id, role_id, menu_id, operate_ip, create_by, create_time, update_by, update_time) VALUES (262708144566890497, 235123826202185723, 262708109523480576, '127.0.0.1', 235123826202185728, '2025-03-27 11:29:22.554033', 235123826202185728, '2025-03-27 16:25:42.985396');
INSERT INTO public.t_sys_role_menu (id, role_id, menu_id, operate_ip, create_by, create_time, update_by, update_time) VALUES (262782721141698560, 235123826202185723, 262780659381235712, '127.0.0.1', 235123826202185728, '2025-03-27 16:25:42.995395', 235123826202185728, '2025-03-27 16:25:42.995410');
INSERT INTO public.t_sys_role_menu (id, role_id, menu_id, operate_ip, create_by, create_time, update_by, update_time) VALUES (262782721141698561, 235123826202185723, 262781066388107264, '127.0.0.1', 235123826202185728, '2025-03-27 16:25:43.000946', 235123826202185728, '2025-03-27 16:25:43.000958');
INSERT INTO public.t_sys_role_menu (id, role_id, menu_id, operate_ip, create_by, create_time, update_by, update_time) VALUES (262782721141698562, 235123826202185723, 262781545507647488, '127.0.0.1', 235123826202185728, '2025-03-27 16:25:43.006817', 235123826202185728, '2025-03-27 16:25:43.006837');
INSERT INTO public.t_sys_role_menu (id, role_id, menu_id, operate_ip, create_by, create_time, update_by, update_time) VALUES (262782721141698563, 235123826202185723, 262781914170191872, '127.0.0.1', 235123826202185728, '2025-03-27 16:25:43.011816', 235123826202185728, '2025-03-27 16:25:43.011831');
INSERT INTO public.t_sys_role_menu (id, role_id, menu_id, operate_ip, create_by, create_time, update_by, update_time) VALUES (262782721141698564, 235123826202185723, 262782638253862912, '127.0.0.1', 235123826202185728, '2025-03-27 16:25:43.017010', 235123826202185728, '2025-03-27 16:25:43.017023');
