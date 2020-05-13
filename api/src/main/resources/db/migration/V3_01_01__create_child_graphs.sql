CREATE TABLE `child_graphs` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `parent_id` bigint NOT NULL,
  `age` int NOT NULL,
  `score` int NOT NULL,
  `comment` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `parent_id` (`parent_id`,`age`),
  CONSTRAINT `child_graphs_ibfk_1` FOREIGN KEY (`parent_id`) REFERENCES `parent_graphs` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;
---UTF8MB4を検討してっていう警告が出るが無視でいい？
