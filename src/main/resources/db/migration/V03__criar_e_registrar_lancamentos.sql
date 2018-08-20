CREATE TABLE releases (
	id INT(11) PRIMARY KEY AUTO_INCREMENT,
	description VARCHAR(50) NOT NULL,
	due_date DATE NOT NULL,
	payday DATE,
	value DECIMAL(10,2) NOT NULL,
	note VARCHAR(100),
	type VARCHAR(20) NOT NULL,
	category_id INT(11) NOT NULL,
	person_id INT(11) NOT NULL,
	FOREIGN KEY (category_id) REFERENCES categories(id),
	FOREIGN KEY (person_id) REFERENCES people(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO releases (description, due_date, payday, value, note, type, category_id, person_id) values ('Salário mensal', '2017-06-10', null, 6500.00, 'Distribuição de lucros', 'RECIPE', 1, 1);
INSERT INTO releases (description, due_date, payday, value, note, type, category_id, person_id) values ('Bahamas', '2017-02-10', '2017-02-10', 100.32, null, 'EXPENSE', 2, 2);
INSERT INTO releases (description, due_date, payday, value, note, type, category_id, person_id) values ('Top Club', '2017-06-10', null, 120, null, 'RECIPE', 3, 3);
INSERT INTO releases (description, due_date, payday, value, note, type, category_id, person_id) values ('CEMIG', '2017-02-10', '2017-02-10', 110.44, 'Geração', 'RECIPE', 3, 4);
INSERT INTO releases (description, due_date, payday, value, note, type, category_id, person_id) values ('DMAE', '2017-06-10', null, 200.30, null, 'EXPENSE', 3, 5);
INSERT INTO releases (description, due_date, payday, value, note, type, category_id, person_id) values ('Extra', '2017-03-10', '2017-03-10', 1010.32, null, 'RECIPE', 4, 6);
INSERT INTO releases (description, due_date, payday, value, note, type, category_id, person_id) values ('Bahamas', '2017-06-10', null, 500, null, 'RECIPE', 1, 7);
INSERT INTO releases (description, due_date, payday, value, note, type, category_id, person_id) values ('Top Club', '2017-03-10', '2017-03-10', 400.32, null, 'EXPENSE', 4, 8);
INSERT INTO releases (description, due_date, payday, value, note, type, category_id, person_id) values ('Despachante', '2017-06-10', null, 123.64, 'Multas', 'EXPENSE', 3, 9);
INSERT INTO releases (description, due_date, payday, value, note, type, category_id, person_id) values ('Pneus', '2017-04-10', '2017-04-10', 665.33, null, 'RECIPE', 5, 10);
INSERT INTO releases (description, due_date, payday, value, note, type, category_id, person_id) values ('Café', '2017-06-10', null, 8.32, null, 'EXPENSE', 1, 5);
INSERT INTO releases (description, due_date, payday, value, note, type, category_id, person_id) values ('Eletrônicos', '2017-04-10', '2017-04-10', 2100.32, null, 'EXPENSE', 5, 4);
INSERT INTO releases (description, due_date, payday, value, note, type, category_id, person_id) values ('Instrumentos', '2017-06-10', null, 1040.32, null, 'EXPENSE', 4, 3);
INSERT INTO releases (description, due_date, payday, value, note, type, category_id, person_id) values ('Café', '2017-04-10', '2017-04-10', 4.32, null, 'EXPENSE', 4, 2);
INSERT INTO releases (description, due_date, payday, value, note, type, category_id, person_id) values ('Lanche', '2017-06-10', null, 10.20, null, 'EXPENSE', 4, 1);