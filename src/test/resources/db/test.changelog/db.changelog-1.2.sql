------------------ user password is pass encoded using BCryptPasswordEncoder(11)
--liquibase formatted sql

--changelog mightyloot:1
INSERT INTO users (id, user_icon, first_name, last_name, username, email, phone_number, password, role, is_non_expired,
                   is_account_non_locked, is_credentials_non_expired, is_enabled)
VALUES ( 1, 'no icon', 'John', 'Silverhand', 'JohnySilver', 'test@mail.com', '+322488491751312'
       , '$2a$11$vYLIFtejpDKZCbXlS6nsV.aq7DpMfFwp02XCmBkCiE2ZlwGL6R/sO', 'USER', true, true, true, true),
       (2, 'no icon', 'William', 'Garcia', 'WilliamG', 'william@example.com', '+15557890123',
        '$2a$11$vYLIFtejpDKZCbXlS6nsV.aq7DpMfFwp02XCmBkCiE2ZlwGL6R/sO', 'USER', true, true, true, true),
       ( 3, 'no icon', 'Isabella', 'Rodriguez', 'IsabellaR', 'isabella@example.com', '+15558901234'
       , '$2a$11$vYLIFtejpDKZCbXlS6nsV.aq7DpMfFwp02XCmBkCiE2ZlwGL6R/sO', 'USER', true, true, true, true),
       ( 4, 'no icon', 'Alexander', 'Lopez', 'AlexanderL', 'alexander@example.com', '+15559012345'
       , '$2a$11$vYLIFtejpDKZCbXlS6nsV.aq7DpMfFwp02XCmBkCiE2ZlwGL6R/sO', 'USER', true, true, true, true),
       ( 5, 'no icon', 'Emily', 'Hernandez', 'EmilyH', 'emily@example.com', '+15550123456'
       , '$2a$11$vYLIFtejpDKZCbXlS6nsV.aq7DpMfFwp02XCmBkCiE2ZlwGL6R/sO', 'USER', true, true, true, true);

SELECT SETVAL('users_id_seq', '5');

INSERT INTO notes (id, title, content, user_id)
VALUES (1, 'title 1', 'content 1', 1),
       (2, 'title 2', 'content 2', 1),
       (3, 'title 3', 'content 3', 1),
       (4, 'title 4', 'content 4', 1),
       (5, 'title 5', 'content 5', 1),
       (6, 'title 1', 'content 1', 2),
       (7, 'title 2', 'content 2', 2),
       (8, 'title 3', 'content 3', 2),
       (9, 'title 4', 'content 4', 2),
       (10, 'title 5', 'content 5', 2),
       (11, 'title 1', 'content 1', 3),
       (12, 'title 2', 'content 2', 3),
       (13, 'title 3', 'content 3', 3),
       (14, 'title 4', 'content 4', 3),
       (15, 'title 5', 'content 5', 3),
       (16, 'title 1', 'content 1', 4),
       (17, 'title 2', 'content 2', 4),
       (18, 'title 3', 'content 3', 4),
       (19, 'title 4', 'content 4', 4),
       (20, 'title 5', 'content 5', 4),
       (21, 'title 1', 'content 1', 5),
       (22, 'title 2', 'content 2', 5),
       (23, 'title 3', 'content 3', 5),
       (24, 'title 4', 'content 4', 5),
       (25, 'title 5', 'content 5', 5);

SELECT SETVAL('notes_id_seq', '25');
