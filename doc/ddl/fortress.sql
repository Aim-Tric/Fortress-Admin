/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80031
 Source Host           : localhost:3306
 Source Schema         : fortress

 Target Server Type    : MySQL
 Target Server Version : 80031
 File Encoding         : 65001

 Date: 24/12/2022 20:41:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for f_auth
-- ----------------------------
DROP TABLE IF EXISTS `f_auth`;
CREATE TABLE `f_auth`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `parent` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父级主键',
  `name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限名称',
  `identify` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限标识',
  `order_num` int(0) NULL DEFAULT NULL COMMENT '排序',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除; 0 正常; 1 删除',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of f_auth
-- ----------------------------
INSERT INTO `f_auth` VALUES ('1591808304991027202', '', '权限测试父级1号', 'test:parent:one', 0, 0, NULL, '2022-11-13 23:00:49', NULL, NULL);
INSERT INTO `f_auth` VALUES ('1591808368928997378', '1591808304991027202', '权限测试子级1号', 'test:child:one', 0, 0, NULL, '2022-11-13 23:01:04', NULL, NULL);
INSERT INTO `f_auth` VALUES ('1591808438965485570', '1591808304991027202', '权限测试子级2号', 'test:child:two', 0, 0, NULL, '2022-11-13 23:01:21', NULL, NULL);

-- ----------------------------
-- Table structure for f_menu
-- ----------------------------
DROP TABLE IF EXISTS `f_menu`;
CREATE TABLE `f_menu`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `parent` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父级主键',
  `name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '显示名称',
  `icon_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图标名称',
  `route_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '路由名称',
  `page_title` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '页面标题',
  `page_path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '页面路径',
  `type` int(0) NULL DEFAULT NULL COMMENT '菜单类型; 0 目录; 1 页面; 2 按钮; 3 链接; ',
  `page_type` int(0) NULL DEFAULT NULL COMMENT '页面类型; 0 模板; 1 自定义;',
  `component_path` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '页面组件路径',
  `status` int(0) NULL DEFAULT NULL COMMENT '状态; 0 隐藏; 1 正常;',
  `description` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `order_num` int(0) NULL DEFAULT NULL COMMENT '排序',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(0) NULL DEFAULT 0 COMMENT '逻辑删除; 0 正常; 1 删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of f_menu
-- ----------------------------
INSERT INTO `f_menu` VALUES ('1588356705580748801', '', '菜单1', 'Basketball', 'Menu1', '菜单页面', '/menuPage', 0, NULL, '', 1, '目录菜单1', 3, NULL, '2022-11-04 10:25:24', NULL, '2022-11-10 21:07:24', 0);
INSERT INTO `f_menu` VALUES ('1588398843383255042', '1588356705580748801', '子菜单1', NULL, 'Child1', '子菜单页面', '/child', 1, NULL, '/sample/One', 1, '第一个子菜单页面', 0, NULL, '2022-11-04 13:12:50', NULL, NULL, 0);
INSERT INTO `f_menu` VALUES ('1590333128902291458', '1588356705580748801', '子菜单2', 'AddLocation', 'Child2', 'Child2', 'Child2', 1, NULL, 'Child', 1, '', -1, NULL, '2022-11-09 21:19:00', NULL, '2022-11-09 21:19:06', 0);
INSERT INTO `f_menu` VALUES ('1595709766426988545', '', '首页', 'HomeFilled', 'Index', '首页', '/home', 1, NULL, 'Index', 1, '正常情况登录后跳转的页面', 0, NULL, '2022-11-24 17:23:50', NULL, '2022-11-24 17:25:03', 0);
INSERT INTO `f_menu` VALUES ('1595710051622883329', '', '系统管理', 'Menu', 'SystemManager', '系统管理', '/system', 0, NULL, '', 1, '系统管理目录菜单', 1, NULL, '2022-11-24 17:24:58', NULL, NULL, 0);
INSERT INTO `f_menu` VALUES ('1595710441827373058', '1595710051622883329', '用户管理', 'UserFilled', 'UserManager', '用户管理', '/user', 1, NULL, 'system/user/Index', 1, '用户管理页面', 0, NULL, '2022-11-24 17:26:31', NULL, NULL, 0);
INSERT INTO `f_menu` VALUES ('1595711027146690561', '1595710051622883329', '角色管理', 'Ticket', 'Role', '角色管理', '/role', 1, NULL, 'system/role/Index', 1, '角色信息管理', 1, NULL, '2022-11-24 17:28:50', NULL, '2022-11-24 17:28:57', 0);
INSERT INTO `f_menu` VALUES ('1595711445608206337', '1595710051622883329', '权限管理', 'Lock', 'AuthManager', '权限管理', '/auth', 1, NULL, 'system/auth/Index', 1, '权限管理', 2, NULL, '2022-11-24 17:30:30', NULL, '2022-11-24 17:38:40', 0);
INSERT INTO `f_menu` VALUES ('1595713469724790786', '1595710051622883329', '菜单管理', 'Grid', 'MenuManager', '菜单管理', '/menu', 1, NULL, 'system/menu/Index', 1, '菜单管理页面', 3, NULL, '2022-11-24 17:38:33', NULL, '2022-11-24 17:38:35', 0);

-- ----------------------------
-- Table structure for f_role
-- ----------------------------
DROP TABLE IF EXISTS `f_role`;
CREATE TABLE `f_role`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色名',
  `identify` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色标识',
  `order_num` int(0) NULL DEFAULT NULL COMMENT '排序',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除; 0 正常; 1 删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of f_role
-- ----------------------------
INSERT INTO `f_role` VALUES ('1584930984296464385', '普通用户', 'commonUser', 0, NULL, '2022-10-25 23:32:48', NULL, NULL, 0);
INSERT INTO `f_role` VALUES ('1592362395500601345', '角色2', 'role2', 0, NULL, '2022-11-15 11:42:34', NULL, NULL, 0);
INSERT INTO `f_role` VALUES ('1595803347191894017', '超级管理员', 'super:admin', 0, NULL, '2022-11-24 23:35:41', NULL, NULL, 0);

-- ----------------------------
-- Table structure for f_role_auth
-- ----------------------------
DROP TABLE IF EXISTS `f_role_auth`;
CREATE TABLE `f_role_auth`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `role_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色id',
  `auth_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of f_role_auth
-- ----------------------------
INSERT INTO `f_role_auth` VALUES ('539e9ce61b224bd4927fcc691538de71', '1584930984296464385', '1591808438965485570');
INSERT INTO `f_role_auth` VALUES ('57d311cb89ac42d0b5069ed78f81337e', '1584930984296464385', '1591808368928997378');
INSERT INTO `f_role_auth` VALUES ('5a92ccd73efb438bbcaf0b9b9adee8ed', '1584930984296464385', '1591808304991027202');

-- ----------------------------
-- Table structure for f_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `f_role_menu`;
CREATE TABLE `f_role_menu`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `role_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色主键',
  `menu_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单主键',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of f_role_menu
-- ----------------------------
INSERT INTO `f_role_menu` VALUES ('2b20600f060945aa860f70f00371974a', '1595803347191894017', '1595709766426988545');
INSERT INTO `f_role_menu` VALUES ('5b3d4e784c2b4bdeabb019287338f3fe', '1595803347191894017', '1595710441827373058');
INSERT INTO `f_role_menu` VALUES ('7dc5342100314c27b61ad532ab3238c9', '1595803347191894017', '1595711445608206337');
INSERT INTO `f_role_menu` VALUES ('7ef8ea3f77ba4c4db404bf5fb9ca99f8', '1595803347191894017', '1588398843383255042');
INSERT INTO `f_role_menu` VALUES ('93c882ca9d8e4113b76135df1a8586df', '1595803347191894017', '1595710051622883329');
INSERT INTO `f_role_menu` VALUES ('a995042524cc4e29be3b054134ff7252', '1595803347191894017', '1595713469724790786');
INSERT INTO `f_role_menu` VALUES ('b2f958c62bbe4732816fdd7c29d66753', '1595803347191894017', '1590333128902291458');
INSERT INTO `f_role_menu` VALUES ('ea7a697dc92847f79d31c81d402612ad', '1595803347191894017', '1595711027146690561');
INSERT INTO `f_role_menu` VALUES ('ff074b705e534962a8fe335880b3528f', '1595803347191894017', '1588356705580748801');

-- ----------------------------
-- Table structure for f_system
-- ----------------------------
DROP TABLE IF EXISTS `f_system`;
CREATE TABLE `f_system`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `system_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `initialized` tinyint(1) NULL DEFAULT NULL,
  `initialize_time` datetime(0) NULL DEFAULT NULL,
  `version` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of f_system
-- ----------------------------
INSERT INTO `f_system` VALUES ('538b871d528c47758fab16eec7b98060', 'Fortress Admin', 1, '2022-12-23 20:10:55', 1);

-- ----------------------------
-- Table structure for f_user
-- ----------------------------
DROP TABLE IF EXISTS `f_user`;
CREATE TABLE `f_user`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `username` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '账号',
  `password` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `nickname` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `sex` int(0) NULL DEFAULT NULL COMMENT '性别: 0 男; 1 女; 2 未知',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `email` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱地址',
  `post` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '岗位',
  `dept` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '部门',
  `avatar` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像地址',
  `status` int(0) NULL DEFAULT 1 COMMENT '账号状态: 0 冻结; 1 正常',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除; 0 正常; 1 删除',
  `order_num` int(0) NULL DEFAULT NULL COMMENT '排序',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of f_user
-- ----------------------------
INSERT INTO `f_user` VALUES ('1582963233648062465', 'Tric', '4QrcOUm6Wau+VuBX8g+IPg==', 'Tric', 2, '138****2212', '473074764@qq.com', NULL, NULL, NULL, NULL, 1, NULL, '2022-10-20 13:13:40', NULL, '2022-10-23 17:53:14', NULL);
INSERT INTO `f_user` VALUES ('1584715940854018049', 'Tric2', '4QrcOUm6Wau+VuBX8g+IPg==', 'Tric2', 2, '138****1234', '138****1234', '', '', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `f_user` VALUES ('1584891393266995201', 'Tric3', '4QrcOUm6Wau+VuBX8g+IPg==', 'Tric3', 2, '138****1234', '138****1234@163.com', '', '', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `f_user` VALUES ('1595803508836175874', 'admin', '4QrcOUm6Wau+VuBX8g+IPg==', '系统管理员', 2, '13821331231', '13821331231@163.com', '', '', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `f_user` VALUES ('1603756525522784258', 'Tric4', '4QrcOUm6Wau+VuBX8g+IPg==', '131313', 2, '1313131313', '131313', '', '', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for f_user_role
-- ----------------------------
DROP TABLE IF EXISTS `f_user_role`;
CREATE TABLE `f_user_role`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `role_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色id',
  `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of f_user_role
-- ----------------------------
INSERT INTO `f_user_role` VALUES ('0c948f8bc30b441a8767d8766bc51c6d', '1584930984296464385', '1582963233648062465');
INSERT INTO `f_user_role` VALUES ('9d91e1be8905453784a34d624f58aa84', '1584930984296464385', '1603756525522784258');
INSERT INTO `f_user_role` VALUES ('b2ba6ce00ea443f2bb6d0f301e338b90', '1595803347191894017', '1595803508836175874');

SET FOREIGN_KEY_CHECKS = 1;
