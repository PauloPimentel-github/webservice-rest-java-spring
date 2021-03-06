CREATE TABLE people (
	id INT(11) PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL,
	street VARCHAR(30),
	number VARCHAR(30),
	complement VARCHAR(30),
	neighborhood VARCHAR(30),
	zipcode VARCHAR(30),
	city VARCHAR(30),
	state VARCHAR(30),
	active BOOLEAN NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO people (name, street, number, complement, neighborhood, zipcode, city, state, active) Values ('João Silva', 'Rua do Abacaxi', '10', null, 'Brasil', '38.400-121', 'Uberlândia', 'MG', true);
INSERT INTO people (name, street, number, complement, neighborhood, zipcode, city, state, active) Values ('Maria Rita', 'Rua do Sabiá', '110', 'Apto 101', 'Colina', '11.400-121', 'Ribeirão Preto', 'SP', true);
INSERT INTO people (name, street, number, complement, neighborhood, zipcode, city, state, active) Values ('Pedro Santos', 'Rua da Bateria', '23', null, 'Morumbi', '54.212-121', 'Goiânia', 'GO', true);
INSERT INTO people (name, street, number, complement, neighborhood, zipcode, city, state, active) Values ('Ricardo Pereira', 'Rua do Motorista', '123', 'Apto 302', 'Aparecida', '38.400-12', 'Salvador', 'BA', true);
INSERT INTO people (name, street, number, complement, neighborhood, zipcode, city, state, active) Values ('Josué Mariano', 'Av Rio Branco', '321', null, 'Jardins', '56.400-121', 'Natal', 'RN', true);
INSERT INTO people (name, street, number, complement, neighborhood, zipcode, city, state, active) Values ('Pedro Barbosa', 'Av Brasil', '100', null, 'Tubalina', '77.400-121', 'Porto Alegre', 'RS', true);
INSERT INTO people (name, street, number, complement, neighborhood, zipcode, city, state, active) Values ('Henrique Medeiros', 'Rua do Sapo', '1120', 'Apto 201', 'Centro', '12.400-121', 'Rio de Janeiro', 'RJ', true);
INSERT INTO people (name, street, number, complement, neighborhood, zipcode, city, state, active) Values ('Carlos Santana', 'Rua da Manga', '433', null, 'Centro', '31.400-121', 'Belo Horizonte', 'MG', true);
INSERT INTO people (name, street, number, complement, neighborhood, zipcode, city, state, active) Values ('Leonardo Oliveira', 'Rua do Músico', '566', null, 'Segismundo Pereira', '38.400-00', 'Uberlândia', 'MG', true);
INSERT INTO people (name, street, number, complement, neighborhood, zipcode, city, state, active) Values ('Isabela Martins', 'Rua da Terra', '1233', 'Apto 10', 'Vigilato', '99.400-12', 'Manaus', 'AM', true);