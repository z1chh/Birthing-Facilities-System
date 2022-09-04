# Birthing Facilities System

## Relational Model
<p align="center">
  <img src="Midwife ER.jpg">
</p>

## Project Overview
I first carefully designed a relational model, as shown above, to represent the entities, their attributes, and the relations between entity sets.

I then modelled the relational translation with its pending constraints (constraints that cannot be enforced within the relational translation).

The next step was to implement the database by creating tables and populating them with some values.

Finally, I developed a simple console-based application program with a simple and friendly user-interface for midwives to use. This JDBC program allows midwives to interact with the system via a menu to perform some actions, such as managing notes for their clients or viewing their appointments.


## Relational Translation
* Mother(qchcardid, name, phone_number, email, profession, dob, address, estimated_birth_date, blood_type)
* Baby(bid, qchcardid, dob, birth_time, name, gender, blood_type), qchcardid references mother
* Father(name, phone_number, profession, dob, qchcardid, email, address, blood_type)
* Couple(cid, qchcardid, phone_number), qchcardid references mother, phone_number references father
* Health_institution(email, name, address, phone_number)
*	Community_clinic(email), email references health_institution
*	Birthing_center(email), email references health_institution
*	Midwife(pid, name, email, phone_number, hi_email), hi_email references health_institution
*	Pregnancy(pid, cid, first_m, second_m, location, pregnancy_number, expected_birth_date, birthed, final_date_set, interested, babies), cid references couple, first_m references midwife, second_m references midwife
*	Technician(tid, name, phone_number)
*	Test(tid, techid, qchcardid, bid, pid, type, results, sample_date, lab_date, prescription_date), techid references technician, qchcardid references mother, bid references baby, pid references pregnancy
*	Appointment(aid, pid, date, time), pid references pregnancy
*	Birth_test_appointment(aid, expected_birth_date), aid references appointment
*	Note(aid, date, time, observations), aid references appointment
*	Online_information_session(sid, host, date, time, language), host references midwife
*	Registration(sid, cid), sid references online_information_session, cid references couple
*	Attendance(sid, cid), sid references online_information_session, cid references couple

## Application Interaction Examples
<p align="center">
  No midwife has the practitioner ID 0 (starts at 1) (database query):
  <img src="pid 0.JPG">
</p>
<p align="center">
  No midwife with practioner ID 0 (application interaction):
  <img src="pid 0 - app.JPG">
</p>
<p align="center">
  Pregnancies of mother M1 (database query):
  <img src="m1 pregnancies.JPG">
</p>
<p align="center">
  Midwife with ID 2 views information on M1 (application interaction):
  <img src="pid 2 with m1.JPG">
</p>

## Additional Notes
The GoBabbyApp.java file imports other java files from a getters package (the other .java files are in the getters folder). Without this, the code will not compile and run.
