-- Change this statement to connect to your database (I used my school's database).
CONNECT TO cs421;

-- Mothers
INSERT INTO mother
VALUES (1, 'Victoria Gutierrez', '(514)747-1111', 'Dentist', 'victoriag@outlook.com', DATE'1989-02-02', '1415, Chomedey', DATE'2022-06-01', 'AB+')
;

INSERT INTO mother
VALUES (2, 'Maria Lopez', '(514)747-2222', 'Professor', 'someone@outlook.com', DATE'1984-10-22', '15, Adolphe', DATE'2022-11-13', 'A+')
;

INSERT INTO mother
VALUES (3, 'Juliette', '(514)747-3333', 'Cat Owner', 'someonetoo@gmail.com', DATE'2001-09-08', '1356, Maisonneuve', DATE'2022-10-28', 'B-')
;

INSERT INTO mother (qchcardid, name, phone_number, email, profession, dob, address, estimated_birth_date)
VALUES
(4, 'Olivia', '(514)747-4444', 'olivia@gmail.com', 'Nanny', DATE'1993-09-25', '3800, Queen-Mary', DATE'2022-04-28'),
(5, 'Daphne', '(514)747-5555', 'daphdaph@gmail.com', 'Pharmacist', DATE'1986-01-27', '3175, Cote-Sainte-Cath', DATE'2022-09-08'),
(6, 'Phoebe', '(514)747-6666', 'phoebs@gmail.com', 'Plumber', DATE'1988-05-23', '329, Melville', DATE'2022-05-01'),
(7, 'Agatha', '(514)747-7777', 'aaa@gmail.com', 'Baker', DATE'1991-06-05', '1909, Canadiens', DATE'2022-08-09'),
(8, 'Naomi', '(514)747-8888', 'naonoe@gmail.com', 'Lifeguard', DATE'1985-07-07', '1297, Foret', DATE'2022-11-27'),
(9, 'Charlotte', '(514)747-9999', '5205144@gmail.com', 'Receptionist', DATE'1987-10-19', '475, Maisonneuve', DATE'2022-03-11')
;


-- Babies
INSERT INTO baby (bid, qchcardid)
VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4)
;


INSERT INTO baby (bid, qchcardid, dob) -- 8(11), 5(1), 6(6), 2(12)
VALUES
(5, 5, DATE'2020-11-01'),
(6, 6, DATE'2020-06-01'),
(7, 7, DATE'2020-11-01'),
(8, 8, DATE'2020-11-01'),
(9, 9, DATE'2020-06-01'),
(10, 1, DATE'2020-06-01'),
(11, 2, DATE'2020-12-02'),
(12, 3, DATE'2020-12-01'),
(13, 4, DATE'2020-06-01'),
(14, 4, DATE'2020-11-01'),
(15, 4, DATE'2020-06-01'),
(16, 4, DATE'2020-01-01'),
(17, 4, DATE'2020-11-01'),
(18, 4, DATE'2020-06-01'),
(19, 4, DATE'2020-11-01'),
(20, 4, DATE'2020-01-01'),
(21, 4, DATE'2020-01-01'),
(22, 4, DATE'2020-11-01'),
(23, 4, DATE'2020-01-01'),
(24, 4, DATE'2020-01-01'),
(25, 4, DATE'2020-11-01')
;


-- Fathers
INSERT INTO father (name, phone_number, profession, dob, address)
VALUES
('Luke', '(514)748-1111', 'Photographer', DATE'1991-01-11', '3800, Queen-Mary'),
('Leo', '(514)748-2222', 'Taxi Driver', DATE'1998-12-02', '5900, Cote-des-Neiges'),
('Isaac', '(514)748-3333', 'Travel Guide', DATE'1988-08-22', '3175, Cote-Sainte-Cath'),
('Thomas', '(514)748-4444', 'Salesman', DATE'1977-03-27', '1909, Canadiens')
;

-- Couples
INSERT INTO couple (cid, qchcardid)
VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5)
;

INSERT INTO couple
VALUES
(6, 6, '(514)748-1111'),
(7, 7, '(514)748-2222'),
(8, 8, '(514)748-3333'),
(9, 9, '(514)748-4444')
;

-- Health Institutions
INSERT INTO health_institution
VALUES
('admin@birthingcdn.ca', 'Birthing Cote-des-Neiges', '6560, Cote-des-Neiges', '(514)736-2323'),
('admin@birthingstlouis.ca', 'Lac-Saint-Louis', '180, Cartier', '(514)697-1199'),
('admin@mcgillhealth.ca', 'McGill', '4110, Decarie', '(514)843-2090'),
('admin@randohi.ca', 'Random', '3810, Decarie', '(514)585-3947')
;


-- Community Clinics
INSERT INTO community_clinic
VALUES
('admin@mcgillhealth.ca'),
('admin@randohi.ca')
;


-- Birthing Centers
INSERT INTO birthing_center
VALUES
('admin@birthingstlouis.ca'),
('admin@birthingcdn.ca')
;


-- Midwives
INSERT INTO midwife
VALUES
(1, 'Marion Girard', 'm.g@birthingstlouis.ca', '(514)888-4567', 'admin@birthingstlouis.ca'),
(2, 'Eleanor Johnson', 'e.j@birthingstlouis.ca', '(514)888-4568', 'admin@birthingstlouis.ca'),
(3, 'Grace Lamoureux', 'g.l@mcgillhealth.ca', '(514)888-4569', 'admin@mcgillhealth.ca'),
(4, 'Zoey Chemali', 'z.c@randohi.ca', '(514)888-4570', 'admin@randohi.ca'),
(5, 'Riley Ambroise', 'r.a@birthingcdn.ca', '(514)888-4571', 'admin@birthingcdn.ca'),
(6, 'Useless Midwife', 'u.m@birthingcdn.ca', '(514)101-0101', 'admin@birthingcdn.ca')
;


-- Pregnancies
INSERT INTO pregnancy (pid, cid, first_m, pregnancy_number, expected_birth_date, babies)
VALUES
(1, 1, 1, 1, DATE'2022-07-01', 1),
(3, 3, 3, 1, DATE'2022-07-10', 2),
(4, 4, 4, 1, DATE'2022-07-22', 2),
(6, 6, 1, 1, DATE'2022-08-01', 3),
(7, 7, 2, 1, DATE'2022-06-30', 1),
(8, 8, 3, 1, DATE'2022-07-15', 1),
(11, 4, 1, 2, DATE'2022-07-24', 2),
(12, 4, 1, 3, DATE'2011-07-03', 1),
(13, 1, 2, 3, DATE'2011-07-03', 1)
;

INSERT INTO pregnancy (pid, cid, first_m, second_m, pregnancy_number, expected_birth_date, babies)
VALUES
(2, 2, 2, 3, 1, DATE'2022-07-31', 1),
(5, 5, 5, 2, 1, DATE'2022-08-01', 2),
(9, 9, 4, 2, 1, DATE'2022-08-01', 1),
(14, 1, 1, 2, 4, DATE'2023-08-01', 1),
(10, 1, 1, 5, 2, DATE'2022-07-15', 1)
;


-- Technicians
INSERT INTO technician
VALUES
(1, 'Juice WRLD', '(713)999-6031'),
(2, 'Lil TJay', '+1(936)247-0168'),
(3, 'Avicii', '+4(676)943-6383'),
(4, 'Polo G', '(212)645-0555')
;


-- Tests
INSERT INTO test (tid, techid, pid, type, results, prescription_date)
VALUES
(5, 2, 5, 'blood iron', 'blood iron okay', DATE'2022-03-11'),
(6, 3, 8, 'dating ultrasound', 'nothing to add', DATE'2022-05-12'),
(7, 4, 4, 'dating ultrasound', 'nothing to add', DATE'2022-04-22')
;

INSERT INTO test (tid, techid, qchcardid, pid, type, results, lab_date, prescription_date)
VALUES
(1, 1, 1, 10, 'blood iron', 'blood iron low', DATE'2022-03-01', DATE'2022-02-01'),
(2, 1, 1, 10, 'blood iron', 'blood iron regular', DATE'2022-08-01', DATE'2022-02-02'),
(3, 1, 1, 1, 'blood iron', 'blood iron high', DATE'2012-03-15', DATE'2012-02-03'),
(4, 1, 1, 10, 'first trimester ultrasound', 'as planned', DATE'2022-02-28', DATE'2022-02-04')
;

INSERT INTO test (tid, techid, bid, pid, type, results, lab_date, prescription_date)
VALUES
(8, 1, 1, 10, 'blood iron', 'blood iron correct', DATE'2022-03-02', DATE'2022-02-01')
;

INSERT INTO test (tid, techid, qchcardid, pid, type, results, prescription_date)
VALUES
(9, 1, 2, 2, 'blood iron', 'there is iron in the blood', DATE'2022-03-21'),
(10, 1, 2, 2, 'blood iron', 'this result text is super long so not everything shows up whenever this note is promted rip deal with it', DATE'2022-03-23'),
(11, 1, 2, 2, 'first trimester ultrasound', 'good first trimester', DATE'2022-03-11'),
(12, 1, 2, 2, 'second trimester ultrasound', 'already second trimester wow', DATE'2022-03-13')
;

INSERT INTO test (tid, techid, pid, type, results, prescription_date)
VALUES
(13, 1, 2, 'blood iron', 'if this outputs then i failed my test for 03-25', DATE'2022-03-21')
;

INSERT INTO test (tid, techid, qchcardid, pid, type, prescription_date)
VALUES
(15, 1, 1, 13, 'blood iron', DATE'2022-03-13'),
(14, 1, 2, 2, 'blood iron', DATE'2022-03-21')
;


INSERT INTO test (tid, techid, qchcardid, pid, type, results, prescription_date)
VALUES
(16, 1, 1, 13, 'blood iron', 'iron in blood duh', DATE'2022-02-21')
;


-- Appointments
INSERT INTO appointment
VALUES
(1, 1, DATE'2022-03-20', TIME'9:00:00'),
(2, 6, DATE'2022-03-26', TIME'9:00:00'),
(3, 10, DATE'2022-03-21', TIME'9:00:00'),
(4, 11, DATE'2022-03-25', TIME'10:00:00'),
(5, 12, DATE'2022-03-23', TIME'11:00:00'),
(6, 2, DATE'2022-03-22', TIME'9:00:00'),
(7, 3, DATE'2022-03-23', TIME'9:00:00'),
(8, 3, DATE'2022-03-24', TIME'9:00:00'),
(9, 7, DATE'2022-03-21', TIME'9:00:00'),
(10, 2, DATE'2022-03-25', TIME'9:00:00'),
(11, 9, DATE'2022-03-21', TIME'10:00:00'),
(14, 9, DATE'2022-03-25', TIME'10:00:00'),
(12, 5, DATE'2022-03-23', TIME'11:00:00'),
(13, 13, DATE'2022-03-25', TIME'12:00:00')
;


-- Birth Test Appointments
INSERT INTO birth_test_appointment
VALUES (8, DATE'2022-10-15')
;


-- Notes
INSERT INTO note
VALUES
(1, DATE'2022-03-20', TIME'9:45:00', 'pregnancy 1'),
(2, DATE'2022-03-26', TIME'9:45:00', 'pregnancy 6'),
(3, DATE'2022-02-21', TIME'9:45:00', 'First Note'),
(3, DATE'2022-02-26', TIME'9:45:00', 'Second Note'),
(10, DATE'2022-03-26', TIME'9:45:00', 'First note'),
(10, DATE'2022-03-27', TIME'11:45:00', 'Second note'),
(10, DATE'2022-03-27', TIME'8:45:00', 'Fourth note'),
(11, DATE'2022-03-26', TIME'9:45:00', 'pregnancy 66'),
(6, DATE'2022-03-22', TIME'9:00:00', 'Note from other app'),
(13, DATE'2022-03-22', TIME'9:00:00', 'First Note from app 13'),
(13, DATE'2022-03-21', TIME'12:00:00', 'Second Note from app 13'),
(8, DATE'2022-03-24', TIME'9:45:00', 'pregnancy 3')
;

INSERT INTO note
VALUES
(10, DATE'2022-03-28', TIME'11:45:00', 'Third note that contains way more than 50 characters but GoBabbyApp program should only output the first 50 characters.')
;


-- Online Information Sessions
INSERT INTO online_information_session
VALUES
(1, 1, DATE'2022-02-28', TIME'9:00:00', 'English'),
(2, 2, DATE'2022-02-28', TIME'9:00:00', 'French'),
(3, 3, DATE'2022-03-14', TIME'9:00:00', 'English'),
(4, 4, DATE'2022-03-14', TIME'9:00:00', 'French')
;


-- Registrations
INSERT INTO registration
VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(1, 5),
(2, 6),
(3, 7),
(4, 8)
;


-- Attendances
INSERT INTO attendance
VALUES
(1, 1),
(1, 2),
(2, 3),
(3, 4),
(4, 8)
;

