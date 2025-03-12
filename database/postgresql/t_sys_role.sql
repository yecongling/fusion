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

 Date: 12/03/2025 15:12:40
*/


-- ----------------------------
-- Table structure for t_sys_role
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_sys_role";
CREATE TABLE "public"."t_sys_role" (
  "id" int8 NOT NULL,
  "role_code" varchar(20) COLLATE "pg_catalog"."default" NOT NULL,
  "role_name" varchar(32) COLLATE "pg_catalog"."default" NOT NULL,
  "role_type" int2 NOT NULL,
  "status" int2 NOT NULL,
  "del_flag" bool NOT NULL DEFAULT false,
  "remark" varchar(64) COLLATE "pg_catalog"."default",
  "create_by" int8 NOT NULL,
  "create_time" timestamp(6) NOT NULL,
  "update_by" int8 NOT NULL,
  "update_time" timestamp(6) NOT NULL
)
;
COMMENT ON COLUMN "public"."t_sys_role"."id" IS '主键';
COMMENT ON COLUMN "public"."t_sys_role"."role_code" IS '角色编码';
COMMENT ON COLUMN "public"."t_sys_role"."role_name" IS '角色名称';
COMMENT ON COLUMN "public"."t_sys_role"."role_type" IS '角色类型';
COMMENT ON COLUMN "public"."t_sys_role"."status" IS '角色状态';
COMMENT ON COLUMN "public"."t_sys_role"."del_flag" IS '删除标记';
COMMENT ON COLUMN "public"."t_sys_role"."remark" IS '备注';
COMMENT ON COLUMN "public"."t_sys_role"."create_by" IS '创建人';
COMMENT ON COLUMN "public"."t_sys_role"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."t_sys_role"."update_by" IS '更新人';
COMMENT ON COLUMN "public"."t_sys_role"."update_time" IS '更新时间';

-- ----------------------------
-- Records of t_sys_role
-- ----------------------------
INSERT INTO "public"."t_sys_role" VALUES (235123826202185723, 'admin', '管理员', 1, 1, 'f', NULL, 235123826202185728, '2025-03-12 15:08:27', 235123826202185728, '2025-03-12 15:08:30');

-- ----------------------------
-- Uniques structure for table t_sys_role
-- ----------------------------
ALTER TABLE "public"."t_sys_role" ADD CONSTRAINT "t_sys_role_pk" UNIQUE ("role_code");

-- ----------------------------
-- Primary Key structure for table t_sys_role
-- ----------------------------
ALTER TABLE "public"."t_sys_role" ADD CONSTRAINT "t_sys_role_pkey" PRIMARY KEY ("id");
