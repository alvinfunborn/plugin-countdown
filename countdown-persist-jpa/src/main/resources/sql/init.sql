CREATE TABLE `countdown_states` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '倒计时的名字',
  `millis` bigint(20) DEFAULT NULL COMMENT '最近记录的剩余时间',
  `action` tinyint(2) DEFAULT NULL COMMENT '操作',
  `action_result` text COMMENT '操作结果',
  `state` tinyint(2) DEFAULT NULL COMMENT '当前状态',
  `timestamp` bigint(20) DEFAULT NULL COMMENT '操作的时间戳',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=88 DEFAULT CHARSET=utf8mb4 COMMENT='倒计时器状态记录表';

CREATE TABLE `countdown_observers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '倒计时名称',
  `observer` varchar(50) DEFAULT NULL COMMENT '观察者名称',
  `result` text COMMENT '观察者执行结果',
  `status` int(11) DEFAULT NULL COMMENT '观察者启用状态',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COMMENT='倒计时的观察者记录表';

