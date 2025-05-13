create table t_tags
(
    id          bigint      not null
        constraint t_engine_tags_pk
            primary key,
    name        varchar(30) not null,
    type        smallint    not null,
    remark      varchar(50),
    create_by   bigint      not null,
    create_time timestamp   not null,
    update_by   bigint      not null,
    update_time timestamp   not null
);

comment on table t_tags is '引擎标签';

comment on column t_tags.id is '主键id';

comment on column t_tags.name is '标签名称';

comment on column t_tags.type is '标签类型';

comment on column t_tags.remark is '备注';

comment on column t_tags.create_by is '创建人';

comment on column t_tags.create_time is '创建时间';

comment on column t_tags.update_by is '更新人';

comment on column t_tags.update_time is '更新时间';

alter table t_tags
    owner to fusion;

create index t_engine_tags_name_index
    on t_tags (name);

comment on index t_engine_tags_name_index is 'name索引';

