INSERT INTO `users` (
`email`,        
`username`,     
`password`      
) VALUE
("manato@example.com","manato","$2a$10$EsxzbKMm1nFK6K/LVdVIxeDHhgWwo9GS5DSUj241yughANYD00MZS"),
("ryuya@example.com","ryuya","$2a$10$pfys1Nmpx7g8yoZ3Xi4louBIA7hrUI0uSt4JDwW9xf/IK9SKuSdEm"),
("eisuke@example.com","eisuke","$2a$10$5zIuKszAyHF3rK6PIKfy7ezRk7SIaA4i.qs.l46jOBhFllvBzYJ3u");

INSERT INTO `parent_graphs` (
`user_id`
) VALUE
(2),
(1),
(3);

INSERT INTO `child_graphs`
(`parent_id`,`age`,`score`,`comment`)
VALUE
(1, 22, 50, "まあまあってとこですね"),
(1, 25, 70, "きてるわ"),
(1, 30, 40, "なるほどね"),
(1, 40, 80,);

INSERT INTO `user_roles`(
`user_id`,
`role_id`
) VALUE
(1,1),
(2,2),
(3,3);
