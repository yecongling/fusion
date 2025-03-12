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

 Date: 12/03/2025 15:12:57
*/


-- ----------------------------
-- Table structure for t_sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_sys_user_role";
CREATE TABLE "public"."t_sys_user_role" (
  "id" int8 NOT NULL,
  "user_id" int8 NOT NULL,
  "role_id" int8 NOT NULL,
  "operate_ip" varchar(32) COLLATE "pg_catalog"."default" NOT NULL,
  "create_by" int8 NOT NULL,
  "create_time" timestamp(6) NOT NULL,
  "update_by" int8 NOT NULL,
  "update_time" timestamp(6) NOT NULL
)
;
COMMENT ON COLUMN "public"."t_sys_user_role"."id" IS '主键';
COMMENT ON COLUMN "public"."t_sys_user_role"."user_id" IS '用户ID';
COMMENT ON COLUMN "public"."t_sys_user_role"."role_id" IS '角色ID';
COMMENT ON COLUMN "public"."t_sys_user_role"."operate_ip" IS '操作IP';
COMMENT ON COLUMN "public"."t_sys_user_role"."create_by" IS '创建人';
COMMENT ON COLUMN "public"."t_sys_user_role"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."t_sys_user_role"."update_by" IS '更新人';
COMMENT ON COLUMN "public"."t_sys_user_role"."update_time" IS '更新时间';

-- ----------------------------
-- Records of t_sys_user_role
-- ----------------------------
INSERT INTO "public"."t_sys_user_role" VALUES (235123826202185722, 235123826202185728, 235123826202185723, '127.0.0.1', 235123826202185728, '2025-03-12 15:09:03', 235123826202185728, '2025-03-12 15:09:05');

-- ----------------------------
-- Indexes structure for table t_sys_user_role
-- ----------------------------
CREATE INDEX "idx_sp_role_id" ON "public"."t_sys_user_role" USING btree (
  "role_id" "pg_catalog"."int8_ops" ASC NULLS LAST
);
CREATE INDEX "idx_sp_user_id" ON "public"."t_sys_user_role" USING btree (
  "user_id" "pg_catalog"."int8_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table t_sys_user_role
-- ----------------------------
ALTER TABLE "public"."t_sys_user_role" ADD CONSTRAINT "t_sys_user_role_pkey" PRIMARY KEY ("id");
