insert into USERS(id, username, password, role) values (100, 'ana@email.com', '$2a$10$Z7/3dclbuOLEOKUt5EMdG.lzK4bYlbUtVjAehj2Y45QxTJ/mwAg3.', 'ROLE_ADMIN');
insert into USERS(id, username, password, role) values (101, 'bia@email.com', '$2a$10$Z7/3dclbuOLEOKUt5EMdG.lzK4bYlbUtVjAehj2Y45QxTJ/mwAg3.', 'ROLE_CLIENT');
insert into USERS(id, username, password, role) values (102, 'bob@email.com', '$2a$10$Z7/3dclbuOLEOKUt5EMdG.lzK4bYlbUtVjAehj2Y45QxTJ/mwAg3.', 'ROLE_CLIENT');

INSERT INTO MESSAGES (id, sender_user, message_text, recipient_name, opening_date_time, narrative_theme, status)
VALUES (100, 100, 'Oi', 'Bob', '2025-10-10 10:00:00', 'Geral', 'PENDING');

