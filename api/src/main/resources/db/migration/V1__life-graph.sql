CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `mail_address` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `authority` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `mail_address` (`mail_address`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;

INSERT INTO users(
`mail_address`,`name`,`password`)VALUES
('manato@example.com','manato','password'),
('ryuya@example.com','ryuya','password'),
('eisuke@example.com','eisuke','password'),
('kaji@example.com','kaji','password');

CREATE TABLE `parent_graphs` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id` (`user_id`),
  CONSTRAINT `parent_graphs_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8MB4;

INSERT INTO parent_graphs(
`user_id`)VALUE
('1'),
('2'),
('3');

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

INSERT INTO child_graphs( 
`parent_id`,`age`,`score`,`comment`)VALUE
('1','18','30','まあまあ'),
('1','15','50','いい感じ'),
('2','18','30','まあまあ'),
('3','18','30','まあまあ');

