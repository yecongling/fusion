create table t_engine_app
(
    id          bigint      not null
        constraint t_engine_app_pk
            primary key,
    name        varchar(32) not null,
    type        smallint    not null ,
    status      smallint    not null,
    priority    smallint    not null,
    log_level   smallint    not null,
    background  varchar(255),
    remark      varchar(50),
    create_by   bigint      not null,
    create_time timestamp   not null,
    update_by   bigint      not null,
    update_time timestamp   not null
);

comment on table t_engine_app is '系统项目表';

comment on column t_engine_app.id is '主键ID';

comment on column t_engine_app.name is '项目名称';

comment on column t_engine_app.type is '项目类型';

comment on column t_engine_app.status is '状态';

comment on column t_engine_app.priority is '优先级';

comment on column t_engine_app.log_level is '日志级别';

comment on column t_engine_app.background is '背景图';

comment on column t_engine_app.remark is '备注';

comment on column t_engine_app.create_by is '创建人';

comment on column t_engine_app.create_time is '创建时间';

comment on column t_engine_app.update_by is '更新人';

comment on column t_engine_app.update_time is '更新时间';

alter table t_engine_app
    owner to fusion;

create index t_engine_app_name_index
    on t_engine_app (name);

