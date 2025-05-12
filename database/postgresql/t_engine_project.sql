create table t_engine_project
(
    id          bigint      not null
        constraint t_engine_project_pk
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

comment on table t_engine_project is '系统项目表';

comment on column t_engine_project.id is '主键ID';

comment on column t_engine_project.name is '项目名称';

comment on column t_engine_project.type is '项目类型';

comment on column t_engine_project.status is '状态';

comment on column t_engine_project.priority is '优先级';

comment on column t_engine_project.log_level is '日志级别';

comment on column t_engine_project.background is '背景图';

comment on column t_engine_project.remark is '备注';

comment on column t_engine_project.create_by is '创建人';

comment on column t_engine_project.create_time is '创建时间';

comment on column t_engine_project.update_by is '更新人';

comment on column t_engine_project.update_time is '更新时间';

alter table t_engine_project
    owner to fusion;

create index t_engine_project_name_index
    on t_engine_project (name);

