create table if not exists t_sys_role
(
    id          bigint                not null
        primary key,
    role_code   varchar(20)           not null
        constraint t_sys_role_pk
            unique,
    role_name   varchar(32)           not null,
    role_type   smallint              not null,
    status      smallint              not null,
    del_flag    boolean default false not null,
    remark      varchar(64),
    create_by   bigint                not null,
    create_time timestamp             not null,
    update_by   bigint                not null,
    update_time timestamp             not null
);

comment on column t_sys_role.id is '主键';

comment on column t_sys_role.role_code is '角色编码';

comment on column t_sys_role.role_name is '角色名称';

comment on column t_sys_role.role_type is '角色类型';

comment on column t_sys_role.status is '角色状态';

comment on column t_sys_role.del_flag is '删除标记';

comment on column t_sys_role.remark is '备注';

comment on column t_sys_role.create_by is '创建人';

comment on column t_sys_role.create_time is '创建时间';

comment on column t_sys_role.update_by is '更新人';

comment on column t_sys_role.update_time is '更新时间';

alter table t_sys_role
    owner to postgres;

INSERT INTO public.t_sys_role (id, role_code, role_name, role_type, status, del_flag, remark, create_by, create_time, update_by, update_time) VALUES (235123826202185723, 'admin', '管理员', 1, 1, false, null, 235123826202185728, '2025-03-12 15:08:27.000000', 235123826202185728, '2025-03-12 15:08:30.000000');
