CREATE TABLE `user` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(32) COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `email` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '邮箱',
  `status` tinyint NOT NULL COMMENT '状态: 0-disabled，1-enabled',
  `password` char(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `salt` char(16) COLLATE utf8mb4_general_ci NOT NULL COMMENT '盐',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_by` bigint NOT NULL COMMENT '创建人',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `update_by` bigint NOT NULL COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户表'


INSERT INTO `user` (
  `name`, `email`, `status`, `password`, `salt`,
  `create_time`, `create_by`, `update_time`, `update_by`
) VALUES
('Alice', 'alice@example.com', 1, '5f4dcc3b5aa765d61d8327deb882cf99aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa', 'abcd1234efgh5678', NOW(), 1, NOW(), 1),
('Bob', 'bob@example.com', 1, 'e99a18c428cb38d5f260853678922e03aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa', 'ijkl9012mnop3456', NOW(), 1, NOW(), 1),
('Charlie', 'charlie@example.com', 0, '098f6bcd4621d373cade4e832627b4f6aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa', 'qrst6789uvwx0123', NOW(), 1, NOW(), 1),
('Diana', 'diana@example.com', 1, 'd8578edf8458ce06fbc5bb76a58c5ca4aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa', 'yzab4567cdef8901', NOW(), 1, NOW(), 1),
('Ethan', 'ethan@example.com', 0, '25d55ad283aa400af464c76d713c07adaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa', 'ghij2345klmn6789', NOW(), 1, NOW(), 1);

