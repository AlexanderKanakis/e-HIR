-- Entity Tables

CREATE TABLE ehir_user_internal (
    id UUID NOT NULL PRIMARY KEY,
    name VARCHAR(100) NULL,
    email VARCHAR(100) NULL,
    password VARCHAR(100) NULL,
    privileges int NULL
);

CREATE TABLE ehir_user_external(
    id UUID NOT NULL PRIMARY KEY,
    country int NULL,
    department int NULL,
    occupation int NULL
);

CREATE TABLE ehir_tag (
    id SERIAL PRIMARY KEY ,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE ehir_country (
    id SERIAL PRIMARY KEY ,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE ehir_department (
    id SERIAL PRIMARY KEY ,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE ehir_occupation (
    id SERIAL PRIMARY KEY ,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE ehir_item (
    id SERIAL PRIMARY KEY ,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(1000000) NOT NULL,
    links VARCHAR(1000) NOT NULL,
    image_id int
);

-----------------------------------------------------------------------------

-- Relation Tables

CREATE TABLE ehir_item_image (
    id SERIAL PRIMARY KEY ,
    name VARCHAR(100) NOT NULL,
    type VARCHAR(1000000) NOT NULL,
    bytes bytea NOT NULL
);

CREATE TABLE ehir_item_tag (
    tag_id int,
    item_id int,
    PRIMARY KEY (tag_id, item_id),
    FOREIGN KEY (tag_id) REFERENCES ehir_tag (id) on delete cascade,
    FOREIGN KEY (item_id) REFERENCES ehir_item (id) on delete cascade
);

CREATE TABLE ehir_country_tag (
    country_id int,
    tag_id int,
    PRIMARY KEY (country_id,tag_id),
    FOREIGN KEY (country_id) REFERENCES  ehir_country (id) on delete cascade,
    FOREIGN KEY (tag_id) REFERENCES  ehir_tag (id) on delete cascade
);

CREATE TABLE ehir_department_tag (
    department_id int,
    tag_id int,
    PRIMARY KEY (department_id,tag_id),
    FOREIGN KEY (tag_id) REFERENCES  ehir_tag (id) on delete cascade ,
    FOREIGN KEY (department_id) REFERENCES  ehir_department (id) on delete cascade
);

CREATE TABLE ehir_occupation_tag (
    occupation_id int,
    tag_id int,
    PRIMARY KEY (occupation_id,tag_id),
    FOREIGN KEY (tag_id) REFERENCES  ehir_tag (id) on delete cascade ,
    FOREIGN KEY (occupation_id) REFERENCES  ehir_occupation (id) on delete cascade
);

CREATE TABLE ehir_department_occupation (
    department_id int,
    occupation_id int,
    PRIMARY KEY (department_id,occupation_id),
    FOREIGN KEY (occupation_id) REFERENCES  ehir_occupation (id) on delete cascade ,
    FOREIGN KEY (department_id) REFERENCES  ehir_department (id) on delete cascade
);

-----------------------------------------------------------------------------

-- Initial Input

insert into ehir_user_internal (id, name, email, password, privileges) values
    ('0ee08214-9bec-4cb2-a6db-a28ce960f33b',
    'admin',
    'kanaxbusiness@gmail.com',
    'root',
    '1');

INSERT INTO ehir_country (name) VALUES
    ('Austria'),
    ('Belgium'),
    ('Bulgaria'),
    ('Croatia'),
    ('Cyprus'),
    ('Czechia'),
    ('Denmark'),
    ('Estonia'),
    ('Finland'),
    ('France'),
    ('Germany'),
    ('Greece'),
    ('Hungary'),
    ('Ireland'),
    ('Italy'),
    ('Latvia'),
    ('Lithuania'),
    ('Luxembourg'),
    ('Malta'),
    ('Netherlands'),
    ('Poland'),
    ('Portugal'),
    ('Romania'),
    ('Slovakia'),
    ('Slovenia'),
    ('Spain'),
    ('Sweden'),
    ('UK');

INSERT INTO ehir_department (name) VALUES
    ('Ambulatory Care'),
    ('Community Health'),
    ('Dental Care'),
    ('Health Insurance Administration '),
    ('Health Insurance Administration '),
    ('Health Center'),
    ('Home Care'),
    ('Hospital '),
    ('Hospital - Training / University'),
    ('Laboratory - Medicine'),
    ('Laboratory - Clinic'),
    ('Ministry of Health Administration'),
    ('Ministry of Finance Administration'),
    ('Medical Device Retail'),
    ('Nursing Home'),
    ('Medicine / Pharmaceutical'),
    ('Physiotherapy'),
    ('Private Specialized Practitioner '),
    ('Psychology'),
    ('Social Care');

INSERT INTO ehir_occupation (name) VALUES
    ('Anesthesiologist'),
    ('Anesthesiologist Assistant'),
    ('Chiropractor'),
    ('Clinical Laboratory Technician'),
    ('Clinical Laboratory Technologist'),
    ('Dental Assistant'),
    ('Dentist'),
    ('Diagnostic Medical Sonographer'),
    ('Dietician'),
    ('Emergency Medical Technician'),
    ('Health Information Technician'),
    ('Home Health Aide'),
    ('Magnetic Resonance Imaging Technologist'),
    ('Medical Assistant'),
    ('Medical Equipment Preparer'),
    ('Nursing Assistant'),
    ('Nurse'),
    ('Obstetrician'),
    ('Occupational Health and Safety Specialist'),
    ('Occupational Therapist'),
    ('Occupational Therapy Aide'),
    ('Optician'),
    ('Optometrist'),
    ('Physician'),
    ('Physician Assistant'),
    ('Pharmacist'),
    ('Pharmacy Technician'),
    ('Physical Therapist'),
    ('Psychiatrist'),
    ('Psychological Therapist'),
    ('Radiologic Technologist'),
    ('Speech-Language Pathologist'),
    ('Surgeon'),
    ('Surgeon Assistant'),
    ('Surgical Technologist');

INSERT INTO ehir_tag (name) VALUES
    ('Anesthesiologist'),
    ('Anesthesiologist Assistant'),
    ('Chiropractor'),
    ('Clinical Laboratory Technician'),
    ('Clinical Laboratory Technologist'),
    ('Dental Assistant'),
    ('Dentist'),
    ('Diagnostic Medical Sonographer'),
    ('Dietician'),
    ('Emergency Medical Technician'),
    ('Health Information Technician'),
    ('Home Health Aide'),
    ('Magnetic Resonance Imaging Technologist'),
    ('Medical Assistant'),
    ('Medical Equipment Preparer'),
    ('Nursing Assistant'),
    ('Nurse'),
    ('Obstetrician'),
    ('Occupational Health and Safety Specialist'),
    ('Occupational Therapist'),
    ('Occupational Therapy Aide'),
    ('Optician'),
    ('Optometrist'),
    ('Physician'),
    ('Physician Assistant'),
    ('Pharmacist'),
    ('Pharmacy Technician'),
    ('Physical Therapist'),
    ('Psychiatrist'),
    ('Psychological Therapist'),
    ('Radiologic Technologist'),
    ('Speech-Language Pathologist'),
    ('Surgeon'),
    ('Surgeon Assistant'),
    ('Surgical Technologist'),
    ('Ambulatory Care'),
    ('Community Health'),
    ('Dental Care'),
    ('Health Insurance Administration '),
    ('Health Insurance Administration '),
    ('Health Center'),
    ('Home Care'),
    ('Hospital '),
    ('Hospital - Training / University'),
    ('Laboratory - Medicine'),
    ('Laboratory - Clinic'),
    ('Ministry of Health Administration'),
    ('Ministry of Finance Administration'),
    ('Medical Device Retail'),
    ('Nursing Home'),
    ('Medicine / Pharmaceutical'),
    ('Physiotherapy'),
    ('Private Specialized Practitioner '),
    ('Psychology'),
    ('Social Care'),
    ('Austria'),
    ('Belgium'),
    ('Bulgaria'),
    ('Croatia'),
    ('Cyprus'),
    ('Czechia'),
    ('Denmark'),
    ('Estonia'),
    ('Finland'),
    ('France'),
    ('Germany'),
    ('Greece'),
    ('Hungary'),
    ('Ireland'),
    ('Italy'),
    ('Latvia'),
    ('Lithuania'),
    ('Luxembourg'),
    ('Malta'),
    ('Netherlands'),
    ('Poland'),
    ('Portugal'),
    ('Romania'),
    ('Slovakia'),
    ('Slovenia'),
    ('Spain'),
    ('Sweden'),
    ('UK');

INSERT INTO ehir_country_tag (country_id, tag_id) VALUES (1, 56);
INSERT INTO ehir_country_tag (country_id, tag_id) VALUES (2, 57);
INSERT INTO ehir_country_tag (country_id, tag_id) VALUES (3, 58);
INSERT INTO ehir_country_tag (country_id, tag_id) VALUES (4, 59);
INSERT INTO ehir_country_tag (country_id, tag_id) VALUES (5, 60);
INSERT INTO ehir_country_tag (country_id, tag_id) VALUES (6, 61);
INSERT INTO ehir_country_tag (country_id, tag_id) VALUES (7, 62);
INSERT INTO ehir_country_tag (country_id, tag_id) VALUES (8, 63);
INSERT INTO ehir_country_tag (country_id, tag_id) VALUES (9, 64);
INSERT INTO ehir_country_tag (country_id, tag_id) VALUES (10, 65);
INSERT INTO ehir_country_tag (country_id, tag_id) VALUES (11, 66);
INSERT INTO ehir_country_tag (country_id, tag_id) VALUES (12, 67);
INSERT INTO ehir_country_tag (country_id, tag_id) VALUES (13, 68);
INSERT INTO ehir_country_tag (country_id, tag_id) VALUES (14, 69);
INSERT INTO ehir_country_tag (country_id, tag_id) VALUES (15, 70);
INSERT INTO ehir_country_tag (country_id, tag_id) VALUES (16, 71);
INSERT INTO ehir_country_tag (country_id, tag_id) VALUES (17, 72);
INSERT INTO ehir_country_tag (country_id, tag_id) VALUES (18, 73);
INSERT INTO ehir_country_tag (country_id, tag_id) VALUES (19, 74);
INSERT INTO ehir_country_tag (country_id, tag_id) VALUES (20, 75);
INSERT INTO ehir_country_tag (country_id, tag_id) VALUES (21, 76);
INSERT INTO ehir_country_tag (country_id, tag_id) VALUES (22, 77);
INSERT INTO ehir_country_tag (country_id, tag_id) VALUES (23, 78);
INSERT INTO ehir_country_tag (country_id, tag_id) VALUES (24, 79);
INSERT INTO ehir_country_tag (country_id, tag_id) VALUES (25, 80);
INSERT INTO ehir_country_tag (country_id, tag_id) VALUES (26, 81);
INSERT INTO ehir_country_tag (country_id, tag_id) VALUES (27, 82);
INSERT INTO ehir_country_tag (country_id, tag_id) VALUES (28, 83);

INSERT INTO ehir_department_tag (department_id, tag_id) VALUES (1, 36);
INSERT INTO ehir_department_tag (department_id, tag_id) VALUES (2, 37);
INSERT INTO ehir_department_tag (department_id, tag_id) VALUES (3, 38);
INSERT INTO ehir_department_tag (department_id, tag_id) VALUES (6, 41);
INSERT INTO ehir_department_tag (department_id, tag_id) VALUES (5, 40);
INSERT INTO ehir_department_tag (department_id, tag_id) VALUES (5, 39);
INSERT INTO ehir_department_tag (department_id, tag_id) VALUES (4, 40);
INSERT INTO ehir_department_tag (department_id, tag_id) VALUES (4, 39);
INSERT INTO ehir_department_tag (department_id, tag_id) VALUES (7, 42);
INSERT INTO ehir_department_tag (department_id, tag_id) VALUES (8, 43);
INSERT INTO ehir_department_tag (department_id, tag_id) VALUES (9, 44);
INSERT INTO ehir_department_tag (department_id, tag_id) VALUES (11, 46);
INSERT INTO ehir_department_tag (department_id, tag_id) VALUES (10, 45);
INSERT INTO ehir_department_tag (department_id, tag_id) VALUES (14, 49);
INSERT INTO ehir_department_tag (department_id, tag_id) VALUES (16, 51);
INSERT INTO ehir_department_tag (department_id, tag_id) VALUES (13, 48);
INSERT INTO ehir_department_tag (department_id, tag_id) VALUES (12, 47);
INSERT INTO ehir_department_tag (department_id, tag_id) VALUES (15, 50);
INSERT INTO ehir_department_tag (department_id, tag_id) VALUES (17, 52);
INSERT INTO ehir_department_tag (department_id, tag_id) VALUES (18, 53);
INSERT INTO ehir_department_tag (department_id, tag_id) VALUES (19, 54);
INSERT INTO ehir_department_tag (department_id, tag_id) VALUES (20, 55);

INSERT INTO ehir_occupation_tag (occupation_id, tag_id) VALUES (1, 1);
INSERT INTO ehir_occupation_tag (occupation_id, tag_id) VALUES (2, 2);
INSERT INTO ehir_occupation_tag (occupation_id, tag_id) VALUES (3, 3);
INSERT INTO ehir_occupation_tag (occupation_id, tag_id) VALUES (4, 4);
INSERT INTO ehir_occupation_tag (occupation_id, tag_id) VALUES (5, 5);
INSERT INTO ehir_occupation_tag (occupation_id, tag_id) VALUES (6, 6);
INSERT INTO ehir_occupation_tag (occupation_id, tag_id) VALUES (7, 7);
INSERT INTO ehir_occupation_tag (occupation_id, tag_id) VALUES (8, 8);
INSERT INTO ehir_occupation_tag (occupation_id, tag_id) VALUES (9, 9);
INSERT INTO ehir_occupation_tag (occupation_id, tag_id) VALUES (10, 10);
INSERT INTO ehir_occupation_tag (occupation_id, tag_id) VALUES (11, 11);
INSERT INTO ehir_occupation_tag (occupation_id, tag_id) VALUES (12, 12);
INSERT INTO ehir_occupation_tag (occupation_id, tag_id) VALUES (13, 13);
INSERT INTO ehir_occupation_tag (occupation_id, tag_id) VALUES (14, 14);
INSERT INTO ehir_occupation_tag (occupation_id, tag_id) VALUES (15, 15);
INSERT INTO ehir_occupation_tag (occupation_id, tag_id) VALUES (17, 17);
INSERT INTO ehir_occupation_tag (occupation_id, tag_id) VALUES (16, 16);
INSERT INTO ehir_occupation_tag (occupation_id, tag_id) VALUES (18, 18);
INSERT INTO ehir_occupation_tag (occupation_id, tag_id) VALUES (19, 19);
INSERT INTO ehir_occupation_tag (occupation_id, tag_id) VALUES (20, 20);
INSERT INTO ehir_occupation_tag (occupation_id, tag_id) VALUES (21, 21);
INSERT INTO ehir_occupation_tag (occupation_id, tag_id) VALUES (22, 22);
INSERT INTO ehir_occupation_tag (occupation_id, tag_id) VALUES (23, 23);
INSERT INTO ehir_occupation_tag (occupation_id, tag_id) VALUES (26, 26);
INSERT INTO ehir_occupation_tag (occupation_id, tag_id) VALUES (27, 27);
INSERT INTO ehir_occupation_tag (occupation_id, tag_id) VALUES (28, 28);
INSERT INTO ehir_occupation_tag (occupation_id, tag_id) VALUES (24, 24);
INSERT INTO ehir_occupation_tag (occupation_id, tag_id) VALUES (25, 25);
INSERT INTO ehir_occupation_tag (occupation_id, tag_id) VALUES (29, 29);
INSERT INTO ehir_occupation_tag (occupation_id, tag_id) VALUES (30, 30);
INSERT INTO ehir_occupation_tag (occupation_id, tag_id) VALUES (31, 31);
INSERT INTO ehir_occupation_tag (occupation_id, tag_id) VALUES (32, 32);
INSERT INTO ehir_occupation_tag (occupation_id, tag_id) VALUES (33, 33);
INSERT INTO ehir_occupation_tag (occupation_id, tag_id) VALUES (34, 34);
INSERT INTO ehir_occupation_tag (occupation_id, tag_id) VALUES (35, 35);



























