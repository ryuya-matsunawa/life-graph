CREATE   TABLE `parent_graphs` (
`id` bigint NOT NULL AUTO_INCREMENT,
`user_id` int NOT NULL,
`created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
`updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY (`id`)
) ENGINE=InnoDB   AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;
