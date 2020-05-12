CREATE   TABLE `user` (
`id` bigint NOT NULL AUTO_INCREMENT,
`mail_address` varchar(255) NOT NULL UNIQUE,
`name` varchar(255) NOT NULL,
`password` varchar(255) NOT NULL,
`authority` int NOT NULL DEFAULT 1,
PRIMARY KEY (`id`)
) ENGINE=InnoDB   AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;
