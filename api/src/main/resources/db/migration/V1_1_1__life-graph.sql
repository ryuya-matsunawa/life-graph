INSERT INTO `users` (
`email`,        
`username`,     
`password`      
) VALUE
("manato@example.com","manato","$2a$10$EsxzbKMm1nFK6K/LVdVIxeDHhgWwo9GS5DSUj241yughANYD00MZS"),
("ryuya@example.com","ryuya","$2a$10$EsxzbKMm1nFK6K/LVdVIxeDHhgWwo9GS5DSUj241yughANYD00MZS"),
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
(2, 22, 50, "まあまあってとこですね");


INSERT INTO `user_roles`(
`user_id`,
`role_id`
) VALUE
(1,1),
(2,2),
(3,3);
