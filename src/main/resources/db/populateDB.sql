DELETE
FROM meals;
DELETE
FROM user_roles;
DELETE
FROM users;

ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

INSERT INTO meals (user_id, date_time, description, calories)
VALUES (100000, '01.02.2021 07:00', 'Breakfast USER', 350),
       (100000, '01.02.2021 12:00', 'Lunch USER', 550),
       (100000, '01.02.2021 21:10', 'Dinner USER', 350),
       (100000, '02.02.2021 06:00', 'Breakfast USER', 400),
       (100000, '02.02.2021 12:30', 'Lunch USER', 700),
       (100000, '02.02.2021 19:30', 'Dinner USER', 600),
       (100000, '03.02.2021 06:00', 'Breakfast USER', 360),
       (100000, '03.02.2021 12:30', 'Lunch USER', 550),
       (100000, '03.02.2021 19:30', 'Dinner USER', 500),
       (100000, '31.01.2021 00:00', 'Еда на граничное значение USER', 100),

       (100001, '01.02.2021 07:00', 'Breakfast ADMIN', 300),
       (100001, '01.02.2021 13:15', 'Lunch ADMIN', 600),
       (100001, '01.02.2021 20:00', 'Dinner ADMIN', 550),
       (100001, '02.02.2021 06:00', 'Breakfast ADMIN', 400),
       (100001, '02.02.2021 12:30', 'Lunch ADMIN', 700),
       (100001, '02.02.2021 19:30', 'Dinner ADMIN', 600),
       (100001, '03.02.2021 06:00', 'Breakfast ADMIN', 360),
       (100001, '03.02.2021 12:30', 'Lunch ADMIN', 550),
       (100001, '03.02.2021 19:30', 'Dinner ADMIN', 500),
       (100001, '31.01.2021 00:00', 'Еда на граничное значение ADMIN', 100);

INSERT INTO meals (id, user_id, date_time, description, calories)
VALUES (100, 100000, '01.02.2021 13:13', 'Reference meal USER', 350);
