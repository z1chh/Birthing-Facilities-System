# Birthing Facilities System

## Relational Model
<p align="center">
  <img src="Midwife ER.jpg">
</p>

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



