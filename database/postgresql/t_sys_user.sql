/*
 Navicat Premium Dump SQL

 Source Server         : fusion
 Source Server Type    : PostgreSQL
 Source Server Version : 170004 (170004)
 Source Host           : localhost:5432
 Source Catalog        : fusion
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 170004 (170004)
 File Encoding         : 65001

 Date: 12/03/2025 15:12:31
*/


-- ----------------------------
-- Table structure for t_sys_user
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_sys_user";
CREATE TABLE "public"."t_sys_user" (
  "id" int8 NOT NULL,
  "username" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "real_name" varchar(50) COLLATE "pg_catalog"."default",
  "password" varchar(255) COLLATE "pg_catalog"."default",
  "salt" varchar(45) COLLATE "pg_catalog"."default",
  "avatar" varchar(255) COLLATE "pg_catalog"."default",
  "birthday" timestamp(6),
  "sex" int2,
  "email" varchar(45) COLLATE "pg_catalog"."default",
  "phone" varchar(16) COLLATE "pg_catalog"."default",
  "org_code" int8,
  "status" int2,
  "del_flag" bool DEFAULT false,
  "third_id" varchar(64) COLLATE "pg_catalog"."default",
  "third_type" varchar(16) COLLATE "pg_catalog"."default",
  "activity_sync" bool DEFAULT false,
  "work_no" varchar(32) COLLATE "pg_catalog"."default",
  "post" varchar(32) COLLATE "pg_catalog"."default",
  "telephone" varchar(16) COLLATE "pg_catalog"."default",
  "user_identity" int2,
  "depart_ids" varchar(32) COLLATE "pg_catalog"."default",
  "rel_tenant_ids" varchar(32) COLLATE "pg_catalog"."default",
  "client_id" varchar(64) COLLATE "pg_catalog"."default",
  "create_by" varchar(16) COLLATE "pg_catalog"."default" NOT NULL,
  "create_time" timestamp(6) NOT NULL,
  "update_by" varchar(16) COLLATE "pg_catalog"."default" NOT NULL,
  "update_time" timestamp(6) NOT NULL
)
;
COMMENT ON COLUMN "public"."t_sys_user"."id" IS '主键ID';
COMMENT ON COLUMN "public"."t_sys_user"."username" IS '用户名';
COMMENT ON COLUMN "public"."t_sys_user"."real_name" IS '真实姓名';
COMMENT ON COLUMN "public"."t_sys_user"."password" IS '密码';
COMMENT ON COLUMN "public"."t_sys_user"."salt" IS '密码盐';
COMMENT ON COLUMN "public"."t_sys_user"."avatar" IS '头像';
COMMENT ON COLUMN "public"."t_sys_user"."birthday" IS '生日';
COMMENT ON COLUMN "public"."t_sys_user"."sex" IS '性别';
COMMENT ON COLUMN "public"."t_sys_user"."email" IS '邮箱';
COMMENT ON COLUMN "public"."t_sys_user"."phone" IS '电话';
COMMENT ON COLUMN "public"."t_sys_user"."org_code" IS '机构编码';
COMMENT ON COLUMN "public"."t_sys_user"."status" IS '状态（1、正常 2、冻结）';
COMMENT ON COLUMN "public"."t_sys_user"."del_flag" IS '删除标记';
COMMENT ON COLUMN "public"."t_sys_user"."third_id" IS '三方登录ID';
COMMENT ON COLUMN "public"."t_sys_user"."third_type" IS '三方登录类型';
COMMENT ON COLUMN "public"."t_sys_user"."activity_sync" IS '同步工作流引擎';
COMMENT ON COLUMN "public"."t_sys_user"."work_no" IS '工号';
COMMENT ON COLUMN "public"."t_sys_user"."post" IS '职务';
COMMENT ON COLUMN "public"."t_sys_user"."telephone" IS '座机号';
COMMENT ON COLUMN "public"."t_sys_user"."user_identity" IS '身份';
COMMENT ON COLUMN "public"."t_sys_user"."depart_ids" IS '负责部门';
COMMENT ON COLUMN "public"."t_sys_user"."rel_tenant_ids" IS '多租户标识';
COMMENT ON COLUMN "public"."t_sys_user"."client_id" IS '设备ID';
COMMENT ON COLUMN "public"."t_sys_user"."create_by" IS '创建人';
COMMENT ON COLUMN "public"."t_sys_user"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."t_sys_user"."update_by" IS '更新人';
COMMENT ON COLUMN "public"."t_sys_user"."update_time" IS '更新时间';

-- ----------------------------
-- Records of t_sys_user
-- ----------------------------
INSERT INTO "public"."t_sys_user" VALUES (235123826202185728, 'admin', 'admin', '9d151a6bee2bbfe0', '9Pp+b+oPTws=', NULL, NULL, 0, NULL, NULL, NULL, 1, 'f', NULL, NULL, 'f', NULL, NULL, NULL, 1, NULL, NULL, NULL, 'admin', '2024-11-09 08:48:54', 'admin', '2024-11-09 08:49:05');

-- ----------------------------
-- Indexes structure for table t_sys_user
-- ----------------------------
CREATE INDEX "uniq_sys_user_username" ON "public"."t_sys_user" USING btree (
  "username" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table t_sys_user
-- ----------------------------
ALTER TABLE "public"."t_sys_user" ADD CONSTRAINT "t_sys_user_pkey" PRIMARY KEY ("id");
