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

 Date: 12/03/2025 15:12:48
*/


-- ----------------------------
-- Table structure for t_sys_menu
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_sys_menu";
CREATE TABLE "public"."t_sys_menu" (
  "id" int8 NOT NULL,
  "parent_id" int8,
  "name" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "url" varchar(64) COLLATE "pg_catalog"."default",
  "component" varchar(32) COLLATE "pg_catalog"."default",
  "component_name" varchar(64) COLLATE "pg_catalog"."default",
  "redirect" varchar(64) COLLATE "pg_catalog"."default",
  "menu_type" int2 NOT NULL,
  "perms" varchar(32) COLLATE "pg_catalog"."default",
  "perms_type" int2,
  "sort_no" numeric(4,2),
  "always_show" bool,
  "icon" varchar(32) COLLATE "pg_catalog"."default",
  "is_route" bool,
  "is_leaf" bool,
  "keep_alive" bool,
  "hidden" bool,
  "hide_tab" bool,
  "description" varchar(64) COLLATE "pg_catalog"."default",
  "del_flag" bool,
  "status" bool,
  "internal_or_external" bool,
  "rule_flag" bool,
  "create_by" int8 NOT NULL,
  "create_time" timestamp(6) NOT NULL,
  "update_by" int8 NOT NULL,
  "update_time" timestamp(6) NOT NULL
)
;
COMMENT ON COLUMN "public"."t_sys_menu"."id" IS '主键';
COMMENT ON COLUMN "public"."t_sys_menu"."parent_id" IS '上级菜单';
COMMENT ON COLUMN "public"."t_sys_menu"."name" IS '菜单名称';
COMMENT ON COLUMN "public"."t_sys_menu"."url" IS '路径';
COMMENT ON COLUMN "public"."t_sys_menu"."component" IS '组件';
COMMENT ON COLUMN "public"."t_sys_menu"."component_name" IS '组件名字';
COMMENT ON COLUMN "public"."t_sys_menu"."redirect" IS '一级菜单跳转地址';
COMMENT ON COLUMN "public"."t_sys_menu"."menu_type" IS '菜单类型（0、一级菜单、1、子菜单 2、子路由 3、按钮权限）';
COMMENT ON COLUMN "public"."t_sys_menu"."perms" IS '菜单权限编码';
COMMENT ON COLUMN "public"."t_sys_menu"."perms_type" IS '权限策略 1、显示 2、禁用';
COMMENT ON COLUMN "public"."t_sys_menu"."sort_no" IS '排序';
COMMENT ON COLUMN "public"."t_sys_menu"."always_show" IS '始终显示';
COMMENT ON COLUMN "public"."t_sys_menu"."icon" IS '菜单图标';
COMMENT ON COLUMN "public"."t_sys_menu"."is_route" IS '是否路由';
COMMENT ON COLUMN "public"."t_sys_menu"."is_leaf" IS '是否叶子节点';
COMMENT ON COLUMN "public"."t_sys_menu"."keep_alive" IS '缓存该页面';
COMMENT ON COLUMN "public"."t_sys_menu"."hidden" IS '隐藏';
COMMENT ON COLUMN "public"."t_sys_menu"."hide_tab" IS '隐藏tab';
COMMENT ON COLUMN "public"."t_sys_menu"."description" IS '描述';
COMMENT ON COLUMN "public"."t_sys_menu"."del_flag" IS '删除标记';
COMMENT ON COLUMN "public"."t_sys_menu"."status" IS '权限按钮状态';
COMMENT ON COLUMN "public"."t_sys_menu"."internal_or_external" IS '内置还是外挂';
COMMENT ON COLUMN "public"."t_sys_menu"."rule_flag" IS '添加数据权限';
COMMENT ON COLUMN "public"."t_sys_menu"."create_by" IS '创建人';
COMMENT ON COLUMN "public"."t_sys_menu"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."t_sys_menu"."update_by" IS '更新人';
COMMENT ON COLUMN "public"."t_sys_menu"."update_time" IS '更新时间';

-- ----------------------------
-- Records of t_sys_menu
-- ----------------------------
INSERT INTO "public"."t_sys_menu" VALUES (235123826202185728, NULL, '系统管理', '/system', NULL, '系统管理', NULL, 0, NULL, 0, 27.00, 't', 'SettingOutlined', 'f', 'f', 'f', 'f', 'f', NULL, 'f', 't', 'f', 'f', 235123826202185728, '2025-03-12 15:01:50', 235123826202185728, '2025-03-12 15:01:54');
INSERT INTO "public"."t_sys_menu" VALUES (235123826202185729, 235123826202185728, '菜单管理', '/system/menu', 'system/Menu', '菜单管理', NULL, 1, NULL, 0, 28.00, 't', 'MenuOutlined', 't', 't', 'f', 'f', 'f', NULL, 'f', 't', 'f', 'f', 235123826202185728, '2025-03-12 15:05:30', 235123826202185728, '2025-03-12 15:05:32');

-- ----------------------------
-- Indexes structure for table t_sys_menu
-- ----------------------------
CREATE INDEX "idx_sp_name" ON "public"."t_sys_menu" USING btree (
  "name" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);
CREATE INDEX "idx_sp_sort_no" ON "public"."t_sys_menu" USING btree (
  "sort_no" "pg_catalog"."numeric_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table t_sys_menu
-- ----------------------------
ALTER TABLE "public"."t_sys_menu" ADD CONSTRAINT "t_sys_menu_pkey" PRIMARY KEY ("id");
