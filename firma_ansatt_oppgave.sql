-- Drop the existing schema to start fresh
DROP SCHEMA IF EXISTS firma_ansatt_oppgave CASCADE;
CREATE SCHEMA firma_ansatt_oppgave;
SET search_path TO firma_ansatt_oppgave;


CREATE TABLE avdeling (
                          avd_id SERIAL PRIMARY KEY,
                          navn VARCHAR(50) NOT NULL,
                          sjef_id INT
);


CREATE TABLE ansatt (
                        id_ansatt SERIAL PRIMARY KEY,
                        brukernavn VARCHAR(4) NOT NULL UNIQUE,
                        fornavn VARCHAR(30) NOT NULL,
                        etternavn VARCHAR(30) NOT NULL,
                        ans_dato DATE NOT NULL,
                        stilling VARCHAR(30) NOT NULL,
                        lonn_mnd DECIMAL(10, 2) NOT NULL,
                        avd_id INT NOT NULL,
                        FOREIGN KEY (avd_id) REFERENCES avdeling(avd_id)
);


ALTER TABLE avdeling
    ADD CONSTRAINT fk_sjef_id
        FOREIGN KEY (sjef_id) REFERENCES ansatt(id_ansatt);


INSERT INTO avdeling (navn)
VALUES
    ('Utvikling'),
    ('Design'),
    ('Markedsføring'),
    ('IT'),
    ('Økonomi');


INSERT INTO ansatt (brukernavn, fornavn, etternavn, ans_dato, stilling, lonn_mnd, avd_id)
VALUES
    ('pnm', 'Per', 'Nordmann', '2023-01-15', 'Sjef', 50000.00, 1),
    ('ola', 'Ola', 'Hansen', '2023-02-20', 'Utvikler', 45000.00, 1),
    ('kar', 'Kari', 'Jensen', '2023-03-10', 'Designer', 42000.00, 2),
    ('lar', 'Lars', 'Berg', '2023-04-05', 'Prosjektleder', 48000.00, 2),
    ('ann', 'Anna', 'Svendsen', '2023-05-01', 'Markedsfører', 40000.00, 3),
    ('sve', 'Svein', 'Olsen', '2023-06-12', 'Analytiker', 46000.00, 3),
    ('mar', 'Marit', 'Lunde', '2023-07-25', 'Tester', 41000.00, 4),
    ('tom', 'Tom', 'Eriksen', '2023-08-08', 'Systemadministrator', 47000.00, 4),
    ('ing', 'Ingvild', 'Moen', '2023-09-18', 'HR-rådgiver', 43000.00, 5),
    ('knu', 'Knut', 'Gran', '2023-10-30', 'Økonom', 44000.00, 5);


UPDATE avdeling
SET sjef_id = 1
WHERE avd_id = 1;

UPDATE avdeling
SET sjef_id = 4
WHERE avd_id = 2;

UPDATE avdeling
SET sjef_id = 5
WHERE avd_id = 3;

UPDATE avdeling
SET sjef_id = 8
WHERE avd_id = 4;

UPDATE avdeling
SET sjef_id = 10
WHERE avd_id = 5;


ALTER TABLE avdeling
    ALTER COLUMN sjef_id SET NOT NULL;