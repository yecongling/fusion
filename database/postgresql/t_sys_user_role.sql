create table t_sys_user_role
(
    id          bigint      not null
        primary key,
    user_id     bigint      not null,
    role_id     bigint      not null,
    operate_ip  varchar(32) not null,
    create_by   bigint      not null,
    create_time timestamp   not null,
    update_by   bigint      not null,
    update_time timestamp   not null
);

comment on column t_sys_user_role.id is '主键';

comment on column t_sys_user_role.user_id is '用户ID';

comment on column t_sys_user_role.role_id is '角色ID';

comment on column t_sys_user_role.operate_ip is '操作IP';

comment on column t_sys_user_role.create_by is '创建人';

comment on column t_sys_user_role.create_time is '创建时间';

comment on column t_sys_user_role.update_by is '更新人';

comment on column t_sys_user_role.update_time is '更新时间';

alter table t_sys_user_role
    owner to postgres;

create index idx_sp_user_id
    on t_sys_user_role (user_id);

create index idx_sp_role_id
    on t_sys_user_role (role_id);

INSERT INTO public.t_sys_user_role (id, user_id, role_id, operate_ip, create_by, create_time, update_by, update_time) VALUES (235123826202185722, 235123826202185728, 235123826202185723, '127.0.0.1', 235123826202185728, '2025-03-12 15:09:03.000000', 235123826202185728, '2025-03-12 15:09:05.000000');
