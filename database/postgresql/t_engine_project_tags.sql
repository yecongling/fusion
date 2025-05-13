create table t_engine_project_tags
(
    project_id bigint not null,
    tag_id     bigint not null,
    constraint t_project_tags_pk_projectid
        unique (project_id, tag_id)
);

comment on table t_engine_project_tags is '项目、标签关联表';

comment on column t_engine_project_tags.project_id is '项目ID';

comment on column t_engine_project_tags.tag_id is '标签id';

alter table t_engine_project_tags
    owner to fusion;

