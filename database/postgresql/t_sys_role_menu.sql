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

 Date: 12/03/2025 15:13:06
*/


-- ----------------------------
-- Table structure for t_sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_sys_role_menu";
CREATE TABLE "public"."t_sys_role_menu" (
  "id" int8 NOT NULL,
  "role_id" int8 NOT NULL,
  "menu_id" int8 NOT NULL,
  "operate_ip" varchar(32) COLLATE "pg_catalog"."default" NOT NULL,
  "create_by" int8 NOT NULL,
  "create_time" timestamp(6) NOT NULL,
  "update_by" int8 NOT NULL,
  "update_time" timestamp(6) NOT NULL
)
;
COMMENT ON COLUMN "public"."t_sys_role_menu"."id" IS '主键';
COMMENT ON COLUMN "public"."t_sys_role_menu"."role_id" IS '角色ID';
COMMENT ON COLUMN "public"."t_sys_role_menu"."menu_id" IS '菜单ID';
COMMENT ON COLUMN "public"."t_sys_role_menu"."operate_ip" IS '操作IP';
COMMENT ON COLUMN "public"."t_sys_role_menu"."create_by" IS '创建人';
COMMENT ON COLUMN "public"."t_sys_role_menu"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."t_sys_role_menu"."update_by" IS '更新人';
COMMENT ON COLUMN "public"."t_sys_role_menu"."update_time" IS '更新时间';

-- ----------------------------
-- Records of t_sys_role_menu
-- ----------------------------
INSERT INTO "public"."t_sys_role_menu" VALUES (235123826202185721, 235123826202185723, 235123826202185728, '127.0.0.1', 235123826202185728, '2025-03-12 15:09:46', 235123826202185728, '2025-03-12 15:09:49');
INSERT INTO "public"."t_sys_role_menu" VALUES (235123826202185722, 235123826202185723, 235123826202185729, '127.0.0.1', 235123826202185728, '2025-03-12 15:10:33', 235123826202185728, '2025-03-12 15:10:37');

-- ----------------------------
-- Primary Key structure for table t_sys_role_menu
-- ----------------------------
ALTER TABLE "public"."t_sys_role_menu" ADD CONSTRAINT "t_sys_role_menu_pkey" PRIMARY KEY ("id");
