CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `mail_address` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `authority` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `mail_address` (`mail_address`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
---UTF8MB4を検討してっていう警告が出るが無視でいい？
