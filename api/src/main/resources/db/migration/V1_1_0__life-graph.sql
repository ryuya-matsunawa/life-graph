CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;

CREATE TABLE `roles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;

CREATE TABLE `user_roles` (
  `user_id` bigint not null,
  `role_id` int not null,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_id` (`user_id`,`role_id`),
  FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;

INSERT INTO `roles` (
  `name`
)VALUES
('ROLE_USER'),
('ROLE_MODERATOR'),
('ROLE_ADMIN');

CREATE TABLE `parent_graphs` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id` (`user_id`),
  CONSTRAINT `parent_graphs_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;



CREATE TABLE `child_graphs` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `parent_id` bigint NOT NULL,
  `age` int NOT NULL,
  `score` int NOT NULL,
  `comment` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `parent_id` (`parent_id`,`age`),
  CONSTRAINT `child_graphs_ibfk_1` FOREIGN KEY (`parent_id`) REFERENCES `parent_graphs` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

-- INSERT `users` (
-- `email`,		
-- `username`,		
-- `password`		
-- ) VALUE
-- ("manato@example.com","manato","$2a$10$EsxzbKMm1nFK6K/LVdVIxeDHhgWwo9GS5DSUj241yughANYD00MZS"),
-- ("ryuya@example.com","ryuya","$2a$10$pfys1Nmpx7g8yoZ3Xi4louBIA7hrUI0uSt4JDwW9xf/IK9SKuSdEm"),
-- ("eisuke@example.com","eisuke","$2a$10$5zIuKszAyHF3rK6PIKfy7ezRk7SIaA4i.qs.l46jOBhFllvBzYJ3u");


-- INSERT `parent_graphs` (
-- `user_id`
-- ) VALUE
-- (2),
-- (1),
-- (3);

-- INSERT `user_roles`(
-- `user_id`,
-- `role_id`
-- ) VALUE
-- (1,3),
-- (2,2),
-- (3,1);
