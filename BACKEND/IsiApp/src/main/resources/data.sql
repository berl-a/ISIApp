INSERT INTO sql7334356.roles (name, status) VALUES
  ('ADMIN', 'ACTIVE'),
  ('USER_APP', 'ACTIVE'),
  ('DRIVER_APP', 'ACTIVE');

INSERT INTO sql7334356.users (username, status, password) VALUES
  ('admin', 'ACTIVE', '$2a$04$Hv/npCF.beaBK43pDE/cLOUISnx./xNbEZJ6aOwskmcsvtfunlECu');

INSERT INTO sql7334356.user_roles (user_id, role_id) VALUES
  (1, 1);