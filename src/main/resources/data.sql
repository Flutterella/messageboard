INSERT INTO messageboarddb.role (name) VALUES('ROLE_ADMIN');
INSERT INTO messageboarddb.role (name) VALUES('ROLE_USER');
INSERT INTO messageboarddb.user (password, username) VALUES('admin', 'admin');
INSERT INTO messageboarddb.user_roles (users_id, roles_id) VALUES ('1', '1');
INSERT INTO messageboarddb.user_roles (users_id, roles_id) VALUES ('1', '2');
INSERT INTO messageboarddb.user (password, username) VALUES('test', 'Lena');
INSERT INTO messageboarddb.user_roles (users_id, roles_id) VALUES ('2', '2');