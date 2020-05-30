INSERT INTO roles (name, status) VALUES
  ('ADMIN', 'ACTIVE'),
  ('USER_APP', 'ACTIVE'),
  ('DRIVER_APP', 'ACTIVE');

INSERT INTO users (username, status, password) VALUES
  ('admin', 'ACTIVE', '$2a$04$Hv/npCF.beaBK43pDE/cLOUISnx./xNbEZJ6aOwskmcsvtfunlECu'),
  ('user_app_user', 'ACTIVE', '$2a$04$hOk6AZ8Z.hjZ6NjdBV3Ij.WOJvYIEQCIzT0.LBwWaQMgHPK5IYABC'),
  ('driver_app_user', 'ACTIVE', '$2a$04$kOke/PY00ikR9dka3c1f.eyCLv1yfCTL6yyJV50Y5ptMx1NLSuZMC');

INSERT INTO user_roles (user_id, role_id) VALUES
  (1, 1),
  (2, 2),
  (3, 3);