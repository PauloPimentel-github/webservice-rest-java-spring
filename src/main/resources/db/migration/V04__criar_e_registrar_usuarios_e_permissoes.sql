CREATE TABLE users (
	user_id INT(20) PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	email VARCHAR(50) NOT NULL,
	password VARCHAR(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE permissions (
	permission_id INT(20) PRIMARY KEY,
	description VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE user_permissions (
	user_id INT(20) NOT NULL,
	permission_id INT(20) NOT NULL,
	PRIMARY KEY (user_id, permission_id),
	FOREIGN KEY (user_id) REFERENCES users(user_id),
	FOREIGN KEY (permission_id) REFERENCES permissions(permission_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO users (user_id, name, email, password) values (1, 'Administrador', 'admin@admin.com', '$2a$10$X607ZPhQ4EgGNaYKt3n4SONjIv9zc.VMWdEuhCuba7oLAL5IvcL5.');
INSERT INTO users (user_id, name, email, password) values (2, 'Paulo Pimentel', 'paulo.h.g.pimentel@gmail.com', '$2a$10$Zc3w6HyuPOPXamaMhh.PQOXvDnEsadztbfi6/RyZWJDzimE8WQjaq');

INSERT INTO permissions (permission_id, description) values (1, 'ROLE_CADASTRAR_CATEGORIA');
INSERT INTO permissions (permission_id, description) values (2, 'ROLE_PESQUISAR_CATEGORIA');

INSERT INTO permissions (permission_id, description) values (3, 'ROLE_CADASTRAR_PESSOA');
INSERT INTO permissions (permission_id, description) values (4, 'ROLE_REMOVER_PESSOA');
INSERT INTO permissions (permission_id, description) values (5, 'ROLE_PESQUISAR_PESSOA');

INSERT INTO permissions (permission_id, description) values (6, 'ROLE_CADASTRAR_LANCAMENTO');
INSERT INTO permissions (permission_id, description) values (7, 'ROLE_REMOVER_LANCAMENTO');
INSERT INTO permissions (permission_id, description) values (8, 'ROLE_PESQUISAR_LANCAMENTO');

-- admin
INSERT INTO user_permissions (user_id, permission_id) values (1, 1);
INSERT INTO user_permissions (user_id, permission_id) values (1, 2);
INSERT INTO user_permissions (user_id, permission_id) values (1, 3);
INSERT INTO user_permissions (user_id, permission_id) values (1, 4);
INSERT INTO user_permissions (user_id, permission_id) values (1, 5);
INSERT INTO user_permissions (user_id, permission_id) values (1, 6);
INSERT INTO user_permissions (user_id, permission_id) values (1, 7);
INSERT INTO user_permissions (user_id, permission_id) values (1, 8);

-- paulo
INSERT INTO user_permissions (user_id, permission_id) values (2, 2);
INSERT INTO user_permissions (user_id, permission_id) values (2, 5);
INSERT INTO user_permissions (user_id, permission_id) values (2, 8);