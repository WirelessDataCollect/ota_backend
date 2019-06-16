/*
 Navicat Premium Data Transfer

 Source Server         : ruili
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : 47.111.9.209:3306
 Source Schema         : fev_dev_dev

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 15/06/2019 22:33:53
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for fota_images
-- ----------------------------
DROP TABLE IF EXISTS `fota_images`;
CREATE TABLE `fota_images` (
  `gid` int(255) NOT NULL AUTO_INCREMENT,
  `firmware_id` varchar(32) NOT NULL,
  `mcu_type` varchar(32) DEFAULT NULL COMMENT '下发的设备类型，单通道，双通道，中央控制器',
  `file_name` varchar(32) DEFAULT NULL COMMENT '固件的文件名',
  `uploader` varchar(32) DEFAULT NULL COMMENT '上传者姓名',
  `uploadertel` varchar(32) DEFAULT NULL COMMENT '上传者手机号',
  `firm_version` varchar(32) DEFAULT NULL COMMENT '固件版本号',
  `content` varchar(255) DEFAULT NULL COMMENT '信息说明',
  `gmtcreate` datetime(6) DEFAULT NULL,
  `gmtupdate` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`gid`,`firmware_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fota_images
-- ----------------------------
BEGIN;
INSERT INTO `fota_images` VALUES (19, '3f3c0ae6c77442a4aa84800e6ae500f6', '单通道控制器', 'helloworld', '我波哥波哥', '123123', '1.0.0', 'info', '2019-06-13 19:28:14.186000', '2019-06-13 19:28:14.186000');
INSERT INTO `fota_images` VALUES (20, 'db22096599a041fa86e6ae46b7f7299c', '单通道控制器', 'helloworld', '我波哥波哥', '123123', '1.2.3', 'info', '2019-06-13 19:34:32.692000', '2019-06-13 19:34:32.692000');
INSERT INTO `fota_images` VALUES (21, '733410bc40764578bc4bd89b627b2a21', '双通道控制器', 'LedbLinker', '我波哥波哥', '123123', '1.0.0', 'ceshi', '2019-06-14 13:21:23.027000', '2019-06-14 13:21:23.027000');
COMMIT;

-- ----------------------------
-- Table structure for fota_load_history
-- ----------------------------
DROP TABLE IF EXISTS `fota_load_history`;
CREATE TABLE `fota_load_history` (
  `gid` int(255) NOT NULL AUTO_INCREMENT,
  `imei` varchar(32) DEFAULT NULL,
  `request_id` varchar(32) DEFAULT NULL COMMENT '请求的唯一查询id',
  `firmware_id` varchar(32) DEFAULT NULL,
  `load_process` varchar(255) DEFAULT NULL COMMENT '状态，0无状态，23失败，66成功',
  `config_bo` varchar(1023) DEFAULT NULL COMMENT 'configBO的序列化',
  `gmtcreate` datetime(6) DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(6),
  `gmtupdate` datetime(6) DEFAULT NULL,
  `gmtmodified` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`gid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fota_load_history
-- ----------------------------
BEGIN;
INSERT INTO `fota_load_history` VALUES (9, '865192040010133', '6ba49f021d1c46489911a1dce7a6b11f', '3f3c0ae6c77442a4aa84800e6ae500f6', '{\"status\":\"下载成功\",\"code\":66}', '{\"RecID\":13,\"SendID\":13,\"imei\":\"865192040010133\",\"cannum\":1,\"measure\":23,\"firmwareId\":\"3f3c0ae6c77442a4aa84800e6ae500f6\",\"mcuType\":\"单通道控制器\",\"fotaImages\":{\"gid\":1,\"firmwareId\":\"3f3c0ae6c77442a4aa84800e6ae500f6\",\"mcuType\":\"单通道控制器\",\"fileName\":\"helloworld\",\"uploader\":\"我波哥波哥\",\"uploadertel\":\"123123\",\"firmVersion\":\"1.0.0\",\"content\":\"info\",\"gmtcreate\":\"2019-06-14 19:34:37.224776\",\"gmtupdate\":\"2019-06-14 19:34:37.224776\"}}', '2019-06-14 19:35:20.259133', '2019-06-14 13:40:55.000000', '2019-06-14 13:43:04.000000');
INSERT INTO `fota_load_history` VALUES (20, '865192040010133', 'bc03b340624249a69037753b1ac2208b', 'db22096599a041fa86e6ae46b7f7299c', '{\"status\":\"下载成功\",\"code\":66}', '{\"RecID\":13,\"SendID\":13,\"imei\":\"865192040010133\",\"cannum\":1,\"measure\":23,\"firmwareId\":\"3f3c0ae6c77442a4aa84800e6ae500f6\",\"mcuType\":\"单通道控制器\",\"fotaImages\":{\"gid\":1,\"firmwareId\":\"3f3c0ae6c77442a4aa84800e6ae500f6\",\"mcuType\":\"单通道控制器\",\"fileName\":\"helloworld\",\"uploader\":\"我波哥波哥\",\"uploadertel\":\"123123\",\"firmVersion\":\"1.0.0\",\"content\":\"info\",\"gmtcreate\":\"2019-06-14 19:34:37.224776\",\"gmtupdate\":\"2019-06-14 19:34:37.224776\"}}', '2019-06-14 19:35:23.566782', '2019-06-14 19:29:58.427000', '2019-06-14 19:30:57.687000');
INSERT INTO `fota_load_history` VALUES (21, '865192040010133', '8d9de2bfa85a4d0298a102f8e97a1749', '3f3c0ae6c77442a4aa84800e6ae500f6', '{\"status\":\"下载成功\",\"code\":66}', '{\"RecID\":11,\"SendID\":22,\"imei\":\"865192040010133\",\"cannum\":1,\"measure\":23,\"firmwareId\":\"3f3c0ae6c77442a4aa84800e6ae500f6\",\"mcuType\":\"单通道控制器\",\"fotaImages\":{\"gid\":19,\"firmwareId\":\"3f3c0ae6c77442a4aa84800e6ae500f6\",\"mcuType\":\"单通道控制器\",\"fileName\":\"helloworld\",\"uploader\":\"我波哥波哥\",\"uploadertel\":\"123123\",\"firmVersion\":\"1.0.0\",\"content\":\"info\",\"gmtcreate\":\"Thu Jun 13 19:28:14 CST 2019\",\"gmtupdate\":\"Thu Jun 13 19:28:14 CST 2019\"}}', '2019-06-14 19:58:48.335000', '2019-06-14 19:57:43.054000', '2019-06-14 19:58:48.334000');
INSERT INTO `fota_load_history` VALUES (22, '865192040010133', '9e6ba307796c4b918ecae55b5fd63343', '3f3c0ae6c77442a4aa84800e6ae500f6', '{\"status\":\"升级成功\",\"code\":106}', '{\"RecID\":11,\"SendID\":22,\"imei\":\"865192040010133\",\"cannum\":1,\"measure\":66,\"firmwareId\":\"3f3c0ae6c77442a4aa84800e6ae500f6\",\"mcuType\":\"单通道控制器\",\"fotaImages\":{\"gid\":19,\"firmwareId\":\"3f3c0ae6c77442a4aa84800e6ae500f6\",\"mcuType\":\"单通道控制器\",\"fileName\":\"helloworld\",\"uploader\":\"我波哥波哥\",\"uploadertel\":\"123123\",\"firmVersion\":\"1.0.0\",\"content\":\"info\",\"gmtcreate\":\"Thu Jun 13 19:28:14 CST 2019\",\"gmtupdate\":\"Thu Jun 13 19:28:14 CST 2019\"}}', '2019-06-14 20:09:10.589000', '2019-06-14 20:07:51.057000', NULL);
COMMIT;

-- ----------------------------
-- Table structure for fota_loaders
-- ----------------------------
DROP TABLE IF EXISTS `fota_loaders`;
CREATE TABLE `fota_loaders` (
  `gid` int(255) NOT NULL AUTO_INCREMENT,
  `imei` varchar(32) NOT NULL,
  `imsi` varchar(32) DEFAULT NULL,
  `request_id` varchar(32) DEFAULT NULL COMMENT '用来判断设备升级状态',
  `csq` int(4) DEFAULT NULL,
  `load_status` int(1) DEFAULT NULL,
  `online_status` int(1) DEFAULT NULL,
  `gmtcreate` datetime(6) DEFAULT NULL COMMENT '采集器第一次上线时间',
  `gmtupdate` datetime(6) DEFAULT NULL COMMENT '采集器刷新时间',
  `gmtmodified` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`gid`,`imei`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fota_loaders
-- ----------------------------
BEGIN;
INSERT INTO `fota_loaders` VALUES (1, '12345678', '12345678', NULL, 21, 1, 66, '2019-06-08 15:20:10.000000', '2019-06-08 15:20:12.000000', '2019-06-08 15:20:14.000000');
INSERT INTO `fota_loaders` VALUES (2, '123123123', '123123123', NULL, 21, 1, 23, '2019-06-08 15:20:32.000000', '2019-06-08 15:20:34.000000', '2019-06-08 15:20:35.000000');
INSERT INTO `fota_loaders` VALUES (3, '865192040010133', '460045106803480', '9e6ba307796c4b918ecae55b5fd63343', 25, 0, 23, '2019-06-10 16:30:38.919000', '2019-06-14 20:39:10.605000', '2019-06-14 20:09:10.597000');
INSERT INTO `fota_loaders` VALUES (4, '123456789012345', '123456789012345', NULL, 22, 0, 23, '2019-06-11 23:30:36.466000', '2019-06-11 23:32:56.480000', '2019-06-11 23:30:36.466000');
COMMIT;

-- ----------------------------
-- Table structure for fota_permission
-- ----------------------------
DROP TABLE IF EXISTS `fota_permission`;
CREATE TABLE `fota_permission` (
  `gid` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL COMMENT '菜单编码',
  `p_code` varchar(255) DEFAULT NULL COMMENT '菜单父编码',
  `p_id` int(11) DEFAULT NULL COMMENT '父菜单ID',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `url` varchar(255) DEFAULT NULL COMMENT '请求地址',
  `is_menu` int(11) DEFAULT NULL COMMENT '是否是菜单 0不是  1是菜单',
  `level` int(11) DEFAULT NULL COMMENT '菜单层级',
  `sort` int(11) DEFAULT NULL COMMENT '菜单排序',
  `status` int(5) DEFAULT NULL COMMENT '状态 0禁用 1启用',
  `gmtcreate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gmtupdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `type` int(11) DEFAULT NULL COMMENT '0微信 1PC',
  PRIMARY KEY (`gid`) USING BTREE,
  UNIQUE KEY `FK_CODE` (`code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fota_permission
-- ----------------------------
BEGIN;
INSERT INTO `fota_permission` VALUES (1, 'deviceManage/**', '/**', 0, '设备管理', 'deviceManage/**', 1, 1, 1, 1, '2018-12-24 13:46:51', '2019-06-03 09:21:29', 1);
INSERT INTO `fota_permission` VALUES (2, 'uploadOtaFile/**', '/**', 0, 'ota文件管理', 'uploadOtaFile/**', 1, 1, 5, 1, '2018-12-24 13:47:01', '2019-06-03 09:21:29', 1);
INSERT INTO `fota_permission` VALUES (3, 'issuanceOtaFile/**', '/**', 0, 'ota下发', 'issuanceOtaFile/**', 1, 1, 9, 1, '2018-12-24 13:47:07', '2019-06-03 09:21:29', 1);
INSERT INTO `fota_permission` VALUES (4, 'otaHistory/**', '/**', 0, '升级历史', 'otaHistory/**', 1, 1, 11, 1, '2018-12-24 13:47:13', '2019-06-03 09:21:29', 1);
COMMIT;

-- ----------------------------
-- Table structure for fota_role
-- ----------------------------
DROP TABLE IF EXISTS `fota_role`;
CREATE TABLE `fota_role` (
  `gid` int(32) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL COMMENT '角色中文，展示',
  `value` varchar(255) NOT NULL COMMENT '角色英文',
  `info` varchar(255) DEFAULT NULL COMMENT '备注',
  `status` int(1) DEFAULT NULL COMMENT '是否启用',
  `gmtcreate` datetime DEFAULT NULL,
  `gmtupdate` datetime DEFAULT NULL,
  PRIMARY KEY (`gid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fota_role
-- ----------------------------
BEGIN;
INSERT INTO `fota_role` VALUES (3, '设备管理员', 'DEVICEMANAGE', '对设备进行管理', 1, '2019-06-01 16:00:19', '2019-06-01 16:00:19');
INSERT INTO `fota_role` VALUES (5, '超级管理员', 'ADMIN', '超级管理员', 1, '2019-06-03 01:22:15', '2019-06-03 01:22:17');
COMMIT;

-- ----------------------------
-- Table structure for fota_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `fota_role_permission`;
CREATE TABLE `fota_role_permission` (
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `permission_id` int(11) NOT NULL COMMENT '权限id',
  `gmtcreate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`role_id`,`permission_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fota_role_permission
-- ----------------------------
BEGIN;
INSERT INTO `fota_role_permission` VALUES (3, 1, '2019-06-03 01:24:00');
INSERT INTO `fota_role_permission` VALUES (3, 4, '2019-06-03 01:24:01');
INSERT INTO `fota_role_permission` VALUES (5, 1, '2019-06-03 01:22:43');
INSERT INTO `fota_role_permission` VALUES (5, 2, '2019-06-03 01:22:52');
INSERT INTO `fota_role_permission` VALUES (5, 3, '2019-06-03 01:23:05');
INSERT INTO `fota_role_permission` VALUES (5, 4, '2019-06-03 01:23:15');
COMMIT;

-- ----------------------------
-- Table structure for fota_users
-- ----------------------------
DROP TABLE IF EXISTS `fota_users`;
CREATE TABLE `fota_users` (
  `gid` int(64) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `openid` varchar(16) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `realname` varchar(32) DEFAULT NULL,
  `phone` varchar(16) NOT NULL,
  `email` varchar(32) DEFAULT NULL,
  `status` int(1) DEFAULT NULL COMMENT '是否启用',
  `info` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `lastloginip` varchar(32) DEFAULT NULL COMMENT '上次登陆ip',
  `gmtcreate` datetime DEFAULT NULL,
  `gmtupdate` datetime DEFAULT NULL,
  PRIMARY KEY (`gid`,`username`,`phone`) USING BTREE,
  UNIQUE KEY `username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fota_users
-- ----------------------------
BEGIN;
INSERT INTO `fota_users` VALUES (11, 'admin', '31233', '123', '我波哥波哥', '123123', 'xxx@11.com', 1, '我波哥的info111', NULL, '2019-05-30 22:41:50', '2019-05-30 22:41:50');
COMMIT;

-- ----------------------------
-- Table structure for fota_users_role
-- ----------------------------
DROP TABLE IF EXISTS `fota_users_role`;
CREATE TABLE `fota_users_role` (
  `gid` int(11) NOT NULL AUTO_INCREMENT,
  `admin_id` int(11) DEFAULT NULL COMMENT '用户id',
  `role_id` int(11) DEFAULT NULL COMMENT '角色id',
  `gmtcreate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`gid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fota_users_role
-- ----------------------------
BEGIN;
INSERT INTO `fota_users_role` VALUES (17, 1, 3, '2019-06-01 23:20:43');
INSERT INTO `fota_users_role` VALUES (18, 11, 5, '2019-06-03 01:32:28');
INSERT INTO `fota_users_role` VALUES (20, 12, 2, '2019-06-13 14:58:28');
INSERT INTO `fota_users_role` VALUES (21, 13, 7, '2019-06-13 15:09:07');
COMMIT;

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(48) NOT NULL,
  `resource_ids` varchar(256) DEFAULT NULL,
  `client_secret` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `authorized_grant_types` varchar(256) DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) DEFAULT NULL,
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(4096) DEFAULT NULL,
  `autoapprove` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`client_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
BEGIN;
INSERT INTO `oauth_client_details` VALUES ('app', NULL, 'app', 'app', 'password,refresh_token', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `oauth_client_details` VALUES ('webApp', NULL, 'webApp', 'app', 'authorization_code,password,refresh_token,client_credentials', NULL, NULL, NULL, NULL, NULL, NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
