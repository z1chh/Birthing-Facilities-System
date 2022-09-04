-- Change this statement to connect to your database (I used my school's database).
CONNECT TO cs421;

CREATE TABLE mother
(
qchcardid INTEGER NOT NULL,
name VARCHAR(30) NOT NULL,
phone_number VARCHAR(15) NOT NULL,
email VARCHAR(30) NOT NULL,
profession VARCHAR(30) NOT NULL,
dob DATE NOT NULL,
address VARCHAR(50) NOT NULL,
estimated_birth_date DATE NOT NULL,
blood_type VARCHAR(3),
PRIMARY KEY (qchcardid)
)
;

CREATE TABLE baby
(
bid INTEGER NOT NULL,
qchcardid INTEGER NOT NULL,
dob DATE,
birth_time TIME,
name VARCHAR(30),
gender VARCHAR(1),
blood_type VARCHAR(3),
FOREIGN KEY (qchcardid) REFERENCES mother,
PRIMARY KEY (bid)
)
;

CREATE TABLE father
(
name VARCHAR(30) NOT NULL,
phone_number VARCHAR(15) NOT NULL,
profession VARCHAR(30) NOT NULL,
dob DATE NOT NULL,
qchcard INTEGER,
email VARCHAR(30),
address VARCHAR(50),
blood_type VARCHAR(3),
PRIMARY KEY (phone_number)
)
;

CREATE TABLE couple
(
cid INTEGER NOT NULL,
qchcardid INTEGER NOT NULL,
phone_number VARCHAR(15),
FOREIGN KEY(qchcardid) REFERENCES mother,
FOREIGN KEY(phone_number) REFERENCES father,
PRIMARY KEY (cid)
)
;

CREATE TABLE health_institution
(
email VARCHAR(30) NOT NULL,
name VARCHAR(30) NOT NULL,
address VARCHAR(50) NOT NULL,
phone_number VARCHAR(15) NOT NULL,
PRIMARY KEY (email)
)
;

CREATE TABLE community_clinic
(
email VARCHAR(30) NOT NULL,
FOREIGN KEY (email) REFERENCES health_institution,
PRIMARY KEY (email)
)
;

CREATE TABLE birthing_center
(
email VARCHAR(30) NOT NULL,
FOREIGN KEY (email) REFERENCES health_institution,
PRIMARY KEY (email)
)
;

CREATE TABLE midwife
(
pid INTEGER NOT NULL,
name VARCHAR(30) NOT NULL,
email VARCHAR(30) NOT NULL,
phone_number VARCHAR(15) NOT NULL,
hi_email VARCHAR(30) NOT NULL,
FOREIGN KEY (hi_email) REFERENCES health_institution,
PRIMARY KEY (pid)
)
;

CREATE TABLE pregnancy
(
pid INTEGER NOT NULL,
cid INTEGER NOT NULL,
interested CHAR(1),
first_m INTEGER,
second_m INTEGER,
location VARCHAR(50),
pregnancy_number INTEGER NOT NULL,
expected_birth_date DATE NOT NULL,
babies INTEGER,
birthed CHAR(1) DEFAULT '0',
final_date_set CHAR(1) DEFAULT '0',
FOREIGN KEY(cid) REFERENCES couple,
FOREIGN KEY (first_m) REFERENCES midwife,
FOREIGN KEY (second_m) REFERENCES midwife,
PRIMARY KEY (pid)
)
;

CREATE TABLE technician
(
tid INTEGER NOT NULL,
name VARCHAR(30) NOT NULL,
phone_number VARCHAR(15) NOT NULL,
PRIMARY KEY (tid)
)
;

CREATE TABLE test
(
tid INTEGER NOT NULL,
techid INTEGER NOT NULL,
qchcardid INTEGER,
bid INTEGER,
pid INTEGER NOT NULL,
type VARCHAR(30) NOT NULL,
results VARCHAR(150),
sample_date DATE,
lab_date DATE,
prescription_date DATE NOT NULL,
FOREIGN KEY (techid) REFERENCES technician,
FOREIGN KEY (qchcardid) REFERENCES mother,
FOREIGN KEY (bid) REFERENCES baby,
FOREIGN KEY (pid) REFERENCES pregnancy,
PRIMARY KEY (tid)
)
;

CREATE TABLE appointment
(
aid INTEGER NOT NULL,
pid INTEGER NOT NULL,
app_date DATE NOT NULL,
app_time TIME NOT NULL,
FOREIGN KEY (pid) REFERENCES pregnancy,
PRIMARY KEY (aid)
)
;

CREATE TABLE birth_test_appointment
(
aid INTEGER NOT NULL,
expected_birth_date DATE NOT NULL,
FOREIGN KEY (aid) REFERENCES appointment,
PRIMARY KEY (aid)
)
;

CREATE TABLE note
(
aid INTEGER NOT NULL,
date DATE NOT NULL,
time TIME NOT NULL,
observations VARCHAR(150) NOT NULL,
FOREIGN KEY (aid) REFERENCES appointment,
PRIMARY KEY (aid, date, time)
)
;

CREATE TABLE online_information_session
(
sid INTEGER NOT NULL,
host INTEGER NOT NULL,
date DATE NOT NULL,
time TIME NOT NULL,
language VARCHAR(20) NOT NULL,
FOREIGN KEY(host) REFERENCES midwife,
PRIMARY KEY (sid)
)
;

CREATE TABLE registration
(
sid INTEGER NOT NULL,
cid INTEGER NOT NULL,
FOREIGN KEY (sid) REFERENCES online_information_session,
FOREIGN KEY (cid) REFERENCES couple,
PRIMARY KEY (sid, cid)
)
;

CREATE TABLE attendance
(
sid INTEGER NOT NULL,
cid INTEGER NOT NULL,
FOREIGN KEY (sid) REFERENCES online_information_session,
FOREIGN KEY (cid) REFERENCES couple,
PRIMARY KEY (sid, cid)
)
;

