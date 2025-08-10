CREATE TABLE `t_device` (
  `Findex` int(11) NOT NULL COMMENT '主键',
  `Fdevice_id` varchar(32) NOT NULL DEFAULT '' COMMENT '设备id',
  `Fdevice_name` varchar(128) NOT NULL DEFAULT '' COMMENT '设备名称',
  `Fimei1` varchar(64) NOT NULL DEFAULT '' COMMENT '设备imei标识1',
  `Fimei2` varchar(64) NOT NULL DEFAULT '' COMMENT '设备imei标识2',
  `Ficcid1` varchar(64) NOT NULL DEFAULT '' COMMENT 'sim卡1 iccid',
  `Ficcid2` varchar(64) NOT NULL DEFAULT '' COMMENT 'sim卡2 iccid',
  `Fphone1` varchar(32) NOT NULL DEFAULT '' COMMENT 'sim卡1 号码',
  `Fphone2` varchar(32) NOT NULL DEFAULT '' COMMENT 'sim卡2 号码',
  `Fzzy_device_status` char(2) NOT NULL DEFAULT '0' COMMENT '设备状态，取值为：\r\n1=正常，3=预注册，4=已丢失，5=待删除，6=闲置，7=已擦除，8=待擦除，9=已删除(仅用于接口，实际设备无此状态)',
  `Fstatus` tinyint(4) NOT NULL DEFAULT '0' COMMENT '系统设备状态，0-已停用,1-启用,2-失效,',
  `Ftenant_id` int(11) NOT NULL DEFAULT '0' COMMENT '所属租户ID',
  `Fowner` varchar(128) NOT NULL DEFAULT '' COMMENT '设备拥有者,系统用户账号(英文名)',
  `Foperator` varchar(128) NOT NULL DEFAULT '' COMMENT '操作人',
  `Fversion` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  `Fcreate_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '记录创建时间',
  `Fmodify_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '记录修改时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='设备列表';