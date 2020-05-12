CREATE   TABLE `child_graphs` (
`id` bigint NOT NULL AUTO_INCREMENT,
`parent_id` bigint NOT NULL,
`age` int NOT NULL ,
`score` int NOT NULL,
`comment` varchar(255) default null,
UNIQUE (parent_id,age),PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

