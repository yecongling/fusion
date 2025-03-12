create table if not exists t_sys_user
(
    id             bigint      not null
        primary key,
    username       varchar(50) not null,
    real_name      varchar(50),
    password       varchar(255),
    salt           varchar(45),
    avatar         varchar(255),
    birthday       timestamp,
    sex            smallint,
    email          varchar(45),
    phone          varchar(16),
    org_code       bigint,
    status         smallint,
    del_flag       boolean default false,
    third_id       varchar(64),
    third_type     varchar(16),
    activity_sync  boolean default false,
    work_no        varchar(32),
    post           varchar(32),
    telephone      varchar(16),
    user_identity  smallint,
    depart_ids     varchar(32),
    rel_tenant_ids varchar(32),
    client_id      varchar(64),
    create_by      varchar(16) not null,
    create_time    timestamp   not null,
    update_by      varchar(16) not null,
    update_time    timestamp   not null
);

comment on column t_sys_user.id is '主键ID';

comment on column t_sys_user.username is '用户名';

comment on column t_sys_user.real_name is '真实姓名';

comment on column t_sys_user.password is '密码';

comment on column t_sys_user.salt is '密码盐';

comment on column t_sys_user.avatar is '头像';

comment on column t_sys_user.birthday is '生日';

comment on column t_sys_user.sex is '性别';

comment on column t_sys_user.email is '邮箱';

comment on column t_sys_user.phone is '电话';

comment on column t_sys_user.org_code is '机构编码';

comment on column t_sys_user.status is '状态（1、正常 2、冻结）';

comment on column t_sys_user.del_flag is '删除标记';

comment on column t_sys_user.third_id is '三方登录ID';

comment on column t_sys_user.third_type is '三方登录类型';

comment on column t_sys_user.activity_sync is '同步工作流引擎';

comment on column t_sys_user.work_no is '工号';

comment on column t_sys_user.post is '职务';

comment on column t_sys_user.telephone is '座机号';

comment on column t_sys_user.user_identity is '身份';

comment on column t_sys_user.depart_ids is '负责部门';

comment on column t_sys_user.rel_tenant_ids is '多租户标识';

comment on column t_sys_user.client_id is '设备ID';

comment on column t_sys_user.create_by is '创建人';

comment on column t_sys_user.create_time is '创建时间';

comment on column t_sys_user.update_by is '更新人';

comment on column t_sys_user.update_time is '更新时间';

alter table t_sys_user
    owner to postgres;

create index uniq_sys_user_username
    on t_sys_user (username);

INSERT INTO public.t_sys_user (id, username, real_name, password, salt, avatar, birthday, sex, email, phone, org_code, status, del_flag, third_id, third_type, activity_sync, work_no, post, telephone, user_identity, depart_ids, rel_tenant_ids, client_id, create_by, create_time, update_by, update_time) VALUES (235123826202185728, 'admin', 'admin', '9d151a6bee2bbfe0', '9Pp+b+oPTws=', null, null, 0, null, null, null, 1, false, null, null, false, null, null, null, 1, null, null, null, 'admin', '2024-11-09 08:48:54.000000', 'admin', '2024-11-09 08:49:05.000000');
