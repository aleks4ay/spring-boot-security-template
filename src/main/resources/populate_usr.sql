insert into usr (id, email, name, password) values (1, 'user@ya.com', 'user', '$2a$12$sJYXgNNJqbDViGgICheAr.041czjxFACtUPJoP5JSj7DKmAqlfgIK'),
                                                   (2, 'admin@ya.com', 'adm', '$2a$12$F7vAr7nBkQNx/npIXsCQuuET4d8J/8XLr3fdGi.6Um0fi.l/addNC');
insert into role (id, role_name) values (3, 'ROLE_USER'), (4, 'ROLE_ADMIN');
insert into user_role (user_id, role_id) values (1, 3), (2, 3), (2, 4);