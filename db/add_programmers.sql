INSERT INTO users (login, name, password, enabled, authority_id)
VALUES ('polyanskiy', 'Полянский Д.Д.', '$2a$10$vo3Lbmv4pATB2c4c67xK.u2WL6zvJHYza9XL0YEeSs3eeEfub.dlm', true,
        (select id from authorities where authority = 'ROLE_ADMIN'));
