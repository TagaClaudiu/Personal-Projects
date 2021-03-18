--orase
insert into Orase(denumire_oras) values ('Iasi');
insert into Orase(denumire_oras) values ('Pascani');
insert into Orase(denumire_oras) values ('Roman');
insert into Orase(denumire_oras) values ('Suceava');
insert into Orase(denumire_oras) values ('Ruginoasa');

--magazine
insert into Magazine(orase_id_oras, denumire_magazin) values ((select id_oras from Orase where denumire_oras = 'Iasi'), 'Fix My PC');
insert into Magazine(orase_id_oras, denumire_magazin) values ((select id_oras from Orase where denumire_oras = 'Iasi'), 'PC Repair');
insert into Magazine(orase_id_oras, denumire_magazin) values ((select id_oras from Orase where denumire_oras = 'Pascani'), 'Helping Hand PC');
insert into Magazine(orase_id_oras, denumire_magazin) values ((select id_oras from Orase where denumire_oras = 'Roman'), 'Reparatii PC'); 
insert into Magazine(orase_id_oras, denumire_magazin) values ((select id_oras from Orase where denumire_oras = 'Suceava'), 'Service Tech'); 


--joburi
insert into Joburi(denumire_job, salariu_baza) values ('Manager', 4000);
insert into Joburi(denumire_job, salariu_baza) values ('Depanator', 2500);

--echipe
insert into Echipe(id_manager, nume_manager) values (1, 'Niculita Silviu');
insert into Echipe(id_manager, nume_manager) values (2, 'Dodoaia Alexandru');
insert into Echipe(id_manager, nume_manager) values (3, 'Berbece Sabin');
insert into Echipe(id_manager, nume_manager) values (4, 'Amariei Ana');
insert into Echipe(id_manager, nume_manager) values (5, 'Giorno Giovanna');

--angajati

--managerii
insert into Angajati(nume_angajat, joburi_id_job, echipe_id_echipa, salariu) values ('Silviu Niculita', (select id_job from Joburi where denumire_job = 'Manager'), 1, (select salariu_baza from Joburi where denumire_job = 'Manager')); 
insert into Detalii_Angajati values ('MX 125969', 'buletin', TO_DATE('02.04.1974', 'DD.MM.YYYY'), '0742222111', 'str Alexandru cel Mare , nr 24, Pascani', 'RO', TO_DATE('05.06.2010', 'DD.MM.YYYY'),  'full-time', (select id_angajat from Angajati where nume_angajat = 'Silviu Niculita'), (select joburi_id_job from Angajati where nume_angajat = 'Silviu Niculita'));
insert into situatie_concedii values (14, 31, 0, (select id_angajat from Angajati where nume_angajat = 'Silviu Niculita'), (select joburi_id_job from Angajati where nume_angajat = 'Silviu Niculita'));


insert into Angajati(nume_angajat, joburi_id_job, echipe_id_echipa, salariu) values ('Dodoaia Alexandru', (select id_job from Joburi where denumire_job = 'Manager'), 2, (select salariu_baza from Joburi where denumire_job = 'Manager')); 
insert into Detalii_Angajati values ('MX 141278', 'buletin', TO_DATE('02.04.1964', 'DD.MM.YYYY'), '0745555111', 'str Botezului , nr 16, Iasi', 'RO', TO_DATE('09.10.2004', 'DD.MM.YYYY'),  'full-time', (select id_angajat from Angajati where nume_angajat = 'Dodoaia Alexandru'), (select joburi_id_job from Angajati where nume_angajat = 'Dodoaia Alexandru'));
insert into situatie_concedii values (21, 11, 0, (select id_angajat from Angajati where nume_angajat = 'Dodoaia Alexandru'), (select joburi_id_job from Angajati where nume_angajat = 'Dodoaia Alexandru'));


insert into Angajati(nume_angajat, joburi_id_job, echipe_id_echipa, salariu) values ('Berbece Sabin', (select id_job from Joburi where denumire_job = 'Manager'), 3, (select salariu_baza from Joburi where denumire_job = 'Manager')); 
insert into Detalii_Angajati values ('MX 111222', 'buletin', TO_DATE('02.04.1989', 'DD.MM.YYYY'), '0742222414', 'str Gradinitei , nr 23, Pascani', 'RO', TO_DATE('09.10.2016', 'DD.MM.YYYY'),  'full-time', (select id_angajat from Angajati where nume_angajat = 'Berbece Sabin'), (select joburi_id_job from Angajati where nume_angajat = 'Berbece Sabin'));
insert into situatie_concedii values (14, 31, 2, (select id_angajat from Angajati where nume_angajat = 'Berbece Sabin'), (select joburi_id_job from Angajati where nume_angajat = 'Berbece Sabin'));


insert into Angajati(nume_angajat, joburi_id_job, echipe_id_echipa, salariu) values ('Amariei Ana', (select id_job from Joburi where denumire_job = 'Manager'), 4, (select salariu_baza from Joburi where denumire_job = 'Manager')); 
insert into Detalii_Angajati values ('MX 215222', 'buletin', TO_DATE('01.01.2000', 'DD.MM.YYYY'), '0735111111', 'str Gradinitei , nr 24, Pascani', 'RO', TO_DATE('08.08.2018', 'DD.MM.YYYY'),  'full-time', (select id_angajat from Angajati where nume_angajat = 'Amariei Ana'), (select joburi_id_job from Angajati where nume_angajat = 'Amariei Ana'));
insert into situatie_concedii values (21, 4, 0, (select id_angajat from Angajati where nume_angajat = 'Amariei Ana'), (select joburi_id_job from Angajati where nume_angajat = 'Amariei Ana'));


insert into Angajati(nume_angajat, joburi_id_job, echipe_id_echipa, salariu) values ('Giorno Giovanna', (select id_job from Joburi where denumire_job = 'Manager'), 5, (select salariu_baza from Joburi where denumire_job = 'Manager')); 
insert into Detalii_Angajati values ('126412', 'pasaport', TO_DATE('06.06.1996', 'DD.MM.YYYY'), '0232400401', 'str Libelulelor, nr 6, Roman', 'ITA', TO_DATE('12.02.2019', 'DD.MM.YYYY'),  'full-time', (select id_angajat from Angajati where nume_angajat = 'Giorno Giovanna'), (select joburi_id_job from Angajati where nume_angajat = 'Giorno Giovanna'));
insert into situatie_concedii values (0, 31, 5, (select id_angajat from Angajati where nume_angajat = 'Giorno Giovanna'), (select joburi_id_job from Angajati where nume_angajat = 'Giorno Giovanna'));


--angajatii
insert into Angajati(nume_angajat, joburi_id_job, echipe_id_echipa, salariu) values ('Boris Vladivich', (select id_job from Joburi where denumire_job = 'Depanator'), 1, (select salariu_baza from Joburi where denumire_job = 'Depanator')); 
insert into Detalii_Angajati values ('125900', 'pasaport', TO_DATE('02.04.1954', 'DD.MM.YYYY'), '0232100100', 'str Vasile Alexandri, nr 4, Suceava', 'RUS', TO_DATE('05.09.2005', 'DD.MM.YYYY'),  'full-time', (select id_angajat from Angajati where nume_angajat = 'Boris Vladivich'), (select joburi_id_job from Angajati where nume_angajat = 'Boris Vladivich'));
insert into situatie_concedii values (21, 31, 0, (select id_angajat from Angajati where nume_angajat = 'Boris Vladivich'), (select joburi_id_job from Angajati where nume_angajat = 'Boris Vladivich'));


insert into Angajati(nume_angajat, joburi_id_job, echipe_id_echipa, salariu) values ('Babes Alexandra', (select id_job from Joburi where denumire_job = 'Depanator'), 1, (select salariu_baza from Joburi where denumire_job = 'Depanator')); 
insert into Detalii_Angajati values ('165900', 'pasaport', TO_DATE('02.04.1994', 'DD.MM.YYYY'), '0232160100', 'str Floriror, nr 15, Pascani', 'MOL', TO_DATE('05.09.2019', 'DD.MM.YYYY'),  'part-time', (select id_angajat from Angajati where nume_angajat = 'Babes Alexandra'), (select joburi_id_job from Angajati where nume_angajat = 'Babes Alexandra'));
insert into situatie_concedii values (21, 31, 0, (select id_angajat from Angajati where nume_angajat = 'Babes Alexandra'), (select joburi_id_job from Angajati where nume_angajat = 'Babes Alexandra'));


insert into Angajati(nume_angajat, joburi_id_job, echipe_id_echipa, salariu) values ('Anitoaiei Daniel', (select id_job from Joburi where denumire_job = 'Depanator'), 1, (select salariu_baza from Joburi where denumire_job = 'Depanator')); 
insert into Detalii_Angajati values ('MZ 656562', 'buletin', TO_DATE('02.04.1999', 'DD.MM.YYYY'), '0751231234', 'str Dealu Mare, nr 15, Ruginoasa', 'RO', TO_DATE('05.09.2015', 'DD.MM.YYYY'),  'full-time', (select id_angajat from Angajati where nume_angajat = 'Anitoaiei Daniel'), (select joburi_id_job from Angajati where nume_angajat = 'Anitoaiei Daniel'));
insert into situatie_concedii values (20, 30, 3, (select id_angajat from Angajati where nume_angajat = 'Anitoaiei Daniel'), (select joburi_id_job from Angajati where nume_angajat = 'Anitoaiei Daniel'));


insert into Angajati(nume_angajat, joburi_id_job, echipe_id_echipa, salariu) values ('Ion Ela', (select id_job from Joburi where denumire_job = 'Depanator'), 2, (select salariu_baza from Joburi where denumire_job = 'Depanator')); 
insert into Detalii_Angajati values ('MZ 475966', 'buletin', TO_DATE('03.06.2001', 'DD.MM.YYYY'), '0771231234', 'str Soarelui, nr 10, Iasi', 'RO', TO_DATE('05.09.2017', 'DD.MM.YYYY'),  'part-time', (select id_angajat from Angajati where nume_angajat = 'Ion Ela'), (select joburi_id_job from Angajati where nume_angajat = 'Ion Ela'));
insert into situatie_concedii values (7, 21, 0, (select id_angajat from Angajati where nume_angajat = 'Ion Ela'), (select joburi_id_job from Angajati where nume_angajat = 'Ion Ela'));


insert into Angajati(nume_angajat, joburi_id_job, echipe_id_echipa, salariu) values ('Ionescu Andrei', (select id_job from Joburi where denumire_job = 'Depanator'), 2, (select salariu_baza from Joburi where denumire_job = 'Depanator')); 
insert into Detalii_Angajati values ('MZ 125951', 'buletin', TO_DATE('02.04.1970', 'DD.MM.YYYY'), NULL, NULL, 'RO', TO_DATE('15.09.2018', 'DD.MM.YYYY'),  'full-time', (select id_angajat from Angajati where nume_angajat = 'Ionescu Andrei'), (select joburi_id_job from Angajati where nume_angajat = 'Ionescu Andrei'));
insert into situatie_concedii values (7, 7, 0, (select id_angajat from Angajati where nume_angajat = 'Ionescu Andrei'), (select joburi_id_job from Angajati where nume_angajat = 'Ionescu Andrei'));

insert into Angajati(nume_angajat, joburi_id_job, echipe_id_echipa, salariu) values ('Martin Louis', (select id_job from Joburi where denumire_job = 'Depanator'), 2, (select salariu_baza from Joburi where denumire_job = 'Depanator')); 
insert into Detalii_Angajati values ('125952', 'pasaport', TO_DATE('02.11.1997', 'DD.MM.YYYY'), NULL, NULL, 'FRA', TO_DATE('25.10.2010', 'DD.MM.YYYY'),  'full-time', (select id_angajat from Angajati where nume_angajat = 'Martin Louis'), (select joburi_id_job from Angajati where nume_angajat = 'Martin Louis'));
insert into situatie_concedii values (14, 21, 0, (select id_angajat from Angajati where nume_angajat = 'Martin Louis'), (select joburi_id_job from Angajati where nume_angajat = 'Martin Louis'));


insert into Angajati(nume_angajat, joburi_id_job, echipe_id_echipa, salariu) values ('Partag Axinte', (select id_job from Joburi where denumire_job = 'Depanator'), 3, (select salariu_baza from Joburi where denumire_job = 'Depanator')); 
insert into Detalii_Angajati values ('MX 124252', 'buletin', TO_DATE('02.11.1975', 'DD.MM.YYYY'), NULL, NULL, 'RO', TO_DATE('25.10.2010', 'DD.MM.YYYY'),  'full-time', (select id_angajat from Angajati where nume_angajat = 'Partag Axinte'), (select joburi_id_job from Angajati where nume_angajat = 'Partag Axinte'));
insert into situatie_concedii values (21, 0, 10, (select id_angajat from Angajati where nume_angajat = 'Partag Axinte'), (select joburi_id_job from Angajati where nume_angajat = 'Partag Axinte'));


insert into Angajati(nume_angajat, joburi_id_job, echipe_id_echipa, salariu) values ('Partag Leana', (select id_job from Joburi where denumire_job = 'Depanator'), 3, (select salariu_baza from Joburi where denumire_job = 'Depanator')); 
insert into Detalii_Angajati values ('MX 124653', 'buletin', TO_DATE('02.11.1955', 'DD.MM.YYYY'), NULL, NULL, 'RO', TO_DATE('02.12.2005', 'DD.MM.YYYY'),  'full-time', (select id_angajat from Angajati where nume_angajat = 'Partag Leana'), (select joburi_id_job from Angajati where nume_angajat = 'Partag Leana'));
insert into situatie_concedii values (0, 31, 5, (select id_angajat from Angajati where nume_angajat = 'Partag Leana'), (select joburi_id_job from Angajati where nume_angajat = 'Partag Leana'));

insert into Angajati(nume_angajat, joburi_id_job, echipe_id_echipa, salariu) values ('Partag Costel', (select id_job from Joburi where denumire_job = 'Depanator'), 3, (select salariu_baza from Joburi where denumire_job = 'Depanator')); 
insert into Detalii_Angajati values ('MX 999999', 'buletin', TO_DATE('02.11.1954', 'DD.MM.YYYY'), NULL, NULL, 'RO', TO_DATE('01.12.2005', 'DD.MM.YYYY'),  'full-time', (select id_angajat from Angajati where nume_angajat = 'Partag Costel'), (select joburi_id_job from Angajati where nume_angajat = 'Partag Costel'));
insert into situatie_concedii values (14, 0, 0, (select id_angajat from Angajati where nume_angajat = 'Partag Costel'), (select joburi_id_job from Angajati where nume_angajat = 'Partag Costel'));


insert into Angajati(nume_angajat, joburi_id_job, echipe_id_echipa, salariu) values ('Joseph Joestar', (select id_job from Joburi where denumire_job = 'Depanator'), 4, (select salariu_baza from Joburi where denumire_job = 'Depanator')); 
insert into Detalii_Angajati values ('989676', 'pasaport', TO_DATE('02.11.1984', 'DD.MM.YYYY'), NULL, NULL, 'ENG', TO_DATE('01.06.2013', 'DD.MM.YYYY'),  'full-time', (select id_angajat from Angajati where nume_angajat = 'Joseph Joestar'), (select joburi_id_job from Angajati where nume_angajat = 'Joseph Joestar'));
insert into situatie_concedii values (21, 31, 0, (select id_angajat from Angajati where nume_angajat = 'Joseph Joestar'), (select joburi_id_job from Angajati where nume_angajat = 'Joseph Joestar'));


insert into Angajati(nume_angajat, joburi_id_job, echipe_id_echipa, salariu) values ('Jonathan Joestar', (select id_job from Joburi where denumire_job = 'Depanator'), 4, (select salariu_baza from Joburi where denumire_job = 'Depanator')); 
insert into Detalii_Angajati values ('653523', 'pasaport', TO_DATE('12.12.1967', 'DD.MM.YYYY'), NULL, NULL, 'ENG', TO_DATE('01.12.2005', 'DD.MM.YYYY'),  'full-time', (select id_angajat from Angajati where nume_angajat = 'Jonathan Joestar'), (select joburi_id_job from Angajati where nume_angajat = 'Jonathan Joestar'));
insert into situatie_concedii values (21, 31, 0, (select id_angajat from Angajati where nume_angajat = 'Jonathan Joestar'), (select joburi_id_job from Angajati where nume_angajat = 'Jonathan Joestar'));


insert into Angajati(nume_angajat, joburi_id_job, echipe_id_echipa, salariu) values ('Branescu Stan', (select id_job from Joburi where denumire_job = 'Depanator'), 5, (select salariu_baza from Joburi where denumire_job = 'Depanator')); 
insert into Detalii_Angajati values ('MX 126512', 'buletin', TO_DATE('30.11.1994', 'DD.MM.YYYY'), NULL, NULL, 'RO', TO_DATE('21.10.2014', 'DD.MM.YYYY'),  'full-time', (select id_angajat from Angajati where nume_angajat = 'Branescu Stan'), (select joburi_id_job from Angajati where nume_angajat = 'Branescu Stan'));
insert into situatie_concedii values (14, 0, 5, (select id_angajat from Angajati where nume_angajat = 'Branescu Stan'), (select joburi_id_job from Angajati where nume_angajat = 'Branescu Stan'));


insert into Angajati(nume_angajat, joburi_id_job, echipe_id_echipa, salariu) values ('Stanescu Bran', (select id_job from Joburi where denumire_job = 'Depanator'), 5, (select salariu_baza from Joburi where denumire_job = 'Depanator')); 
insert into Detalii_Angajati values ('MX 126521', 'buletin', TO_DATE('29.11.1984', 'DD.MM.YYYY'), NULL, NULL, 'RO', TO_DATE('01.12.2014', 'DD.MM.YYYY'),  'full-time', (select id_angajat from Angajati where nume_angajat = 'Stanescu Bran'), (select joburi_id_job from Angajati where nume_angajat = 'Stanescu Bran'));
insert into situatie_concedii values (0, 14, 5, (select id_angajat from Angajati where nume_angajat = 'Stanescu Bran'), (select joburi_id_job from Angajati where nume_angajat = 'Stanescu Bran'));


--echipe magazine fk
insert into echipe_magazine_fk values (1, 1, (select orase_id_oras from magazine where id_magazin = 1), 1); --1 din echipa 1 in magazin 1
insert into echipe_magazine_fk values (1, 2, (select orase_id_oras from magazine where id_magazin = 2), 2); --2 din echipa 1 in magazin 2
insert into echipe_magazine_fk values (2, 1, (select orase_id_oras from magazine where id_magazin = 1), 1); --1 din echipa 2 in magazin 1
insert into echipe_magazine_fk values (2, 5, (select orase_id_oras from magazine where id_magazin = 5), 2); --2 din echipa 2 in magazin 5
insert into echipe_magazine_fk values (3, 3, (select orase_id_oras from magazine where id_magazin = 3), 2); --2 din echipa 3 in magazin 3
insert into echipe_magazine_fk values (3, 1, (select orase_id_oras from magazine where id_magazin = 1), 1); --1 din echipa 3 in magazin 1
insert into echipe_magazine_fk values (4, 4, (select orase_id_oras from magazine where id_magazin = 4), 2); --2 din echipa 4 in magazin 4
insert into echipe_magazine_fk values (5, 2, (select orase_id_oras from magazine where id_magazin = 2), 1); --1 din echipa 5 in magazin 2
insert into echipe_magazine_fk values (5, 3, (select orase_id_oras from magazine where id_magazin = 3), 1); --1 din echipa 5 in magazin 3



--reparatii efectuate
insert into reparatii_efectuate values( TO_DATE('1.02.2020', 'DD.MM.YYYY'),  TO_DATE('6.02.2020', 'DD.MM.YYYY'), 630.50, (select ID_angajat from Angajati where nume_angajat = 'Branescu Stan'), (select joburi_id_job from Angajati where nume_angajat = 'Branescu Stan'));
update angajati set salariu = salariu + (select pret_reparatie from reparatii_efectuate r, angajati a where a.nume_angajat = 'Branescu Stan' and r.angajati_id_angajat = a.id_angajat and r.data_incepere_reparatii = TO_DATE('1.02.2020', 'DD.MM.YYYY'))*0.25 where nume_angajat = 'Branescu Stan';

insert into reparatii_efectuate values( TO_DATE('1.02.2020', 'DD.MM.YYYY'),  TO_DATE('3.02.2020', 'DD.MM.YYYY'), 230.25, (select ID_angajat from Angajati where nume_angajat = 'Stanescu Bran'), (select joburi_id_job from Angajati where nume_angajat = 'Stanescu Bran'));
update angajati set salariu = salariu + (select pret_reparatie from reparatii_efectuate r, angajati a where a.nume_angajat = 'Stanescu Bran' and r.angajati_id_angajat = a.id_angajat and r.data_incepere_reparatii = TO_DATE('1.02.2020', 'DD.MM.YYYY'))*0.25 where nume_angajat = 'Stanescu Bran';

insert into reparatii_efectuate values( TO_DATE('2.02.2020', 'DD.MM.YYYY'),  TO_DATE('5.02.2020', 'DD.MM.YYYY'), 250, (select ID_angajat from Angajati where nume_angajat = 'Joseph Joestar'), (select joburi_id_job from Angajati where nume_angajat = 'Joseph Joestar'));
update angajati set salariu = salariu + (select pret_reparatie from reparatii_efectuate r, angajati a where a.nume_angajat = 'Joseph Joestar' and r.angajati_id_angajat = a.id_angajat and r.data_incepere_reparatii = TO_DATE('2.02.2020', 'DD.MM.YYYY'))*0.25 where nume_angajat = 'Joseph Joestar';

insert into reparatii_efectuate values( TO_DATE('05.02.2020', 'DD.MM.YYYY'),  TO_DATE('15.02.2020', 'DD.MM.YYYY'), 750.75, (select ID_angajat from Angajati where nume_angajat = 'Boris Vladivich'), (select joburi_id_job from Angajati where nume_angajat = 'Boris Vladivich'));
update angajati set salariu = salariu + (select pret_reparatie from reparatii_efectuate r, angajati a where a.nume_angajat = 'Boris Vladivich' and r.angajati_id_angajat = a.id_angajat and r.data_incepere_reparatii = TO_DATE('05.02.2020', 'DD.MM.YYYY'))*0.25 where nume_angajat = 'Boris Vladivich';

insert into reparatii_efectuate values( TO_DATE('06.02.2020', 'DD.MM.YYYY'),  TO_DATE('07.02.2020', 'DD.MM.YYYY'), 75, (select ID_angajat from Angajati where nume_angajat = 'Martin Louis'), (select joburi_id_job from Angajati where nume_angajat = 'Martin Louis'));
update angajati set salariu = salariu + (select pret_reparatie from reparatii_efectuate r, angajati a where a.nume_angajat = 'Martin Louis' and r.angajati_id_angajat = a.id_angajat and r.data_incepere_reparatii = TO_DATE('6.02.2020', 'DD.MM.YYYY'))*0.25 where nume_angajat = 'Martin Louis';

insert into reparatii_efectuate values( TO_DATE('07.02.2020', 'DD.MM.YYYY'),  TO_DATE('12.02.2020', 'DD.MM.YYYY'), 450, (select ID_angajat from Angajati where nume_angajat = 'Ion Ela'), (select joburi_id_job from Angajati where nume_angajat = 'Ion Ela'));
update angajati set salariu = salariu + (select pret_reparatie from reparatii_efectuate r, angajati a where a.nume_angajat = 'Ion Ela' and r.angajati_id_angajat = a.id_angajat and r.data_incepere_reparatii = TO_DATE('7.02.2020', 'DD.MM.YYYY'))*0.15 where nume_angajat = 'Ion Ela';

insert into reparatii_efectuate values( TO_DATE('07.02.2020', 'DD.MM.YYYY'),  TO_DATE('08.02.2020', 'DD.MM.YYYY'), 75, (select ID_angajat from Angajati where nume_angajat = 'Anitoaiei Daniel'), (select joburi_id_job from Angajati where nume_angajat = 'Anitoaiei Daniel'));
update angajati set salariu = salariu + (select pret_reparatie from reparatii_efectuate r, angajati a where a.nume_angajat = 'Anitoaiei Daniel' and r.angajati_id_angajat = a.id_angajat and r.data_incepere_reparatii = TO_DATE('7.02.2020', 'DD.MM.YYYY'))*0.25 where nume_angajat = 'Anitoaiei Daniel';

insert into reparatii_efectuate values( TO_DATE('07.02.2020', 'DD.MM.YYYY'),  TO_DATE('10.02.2020', 'DD.MM.YYYY'), 125.25, (select ID_angajat from Angajati where nume_angajat = 'Boris Vladivich'), (select joburi_id_job from Angajati where nume_angajat = 'Boris Vladivich'));
update angajati set salariu = salariu + (select pret_reparatie from reparatii_efectuate r, angajati a where a.nume_angajat = 'Boris Vladivich' and r.angajati_id_angajat = a.id_angajat and r.data_incepere_reparatii = TO_DATE('7.02.2020', 'DD.MM.YYYY'))*0.25 where nume_angajat = 'Boris Vladivich';

insert into reparatii_efectuate values( TO_DATE('10.02.2020', 'DD.MM.YYYY'),  TO_DATE('12.02.2020', 'DD.MM.YYYY'), 165, (select ID_angajat from Angajati where nume_angajat = 'Martin Louis'), (select joburi_id_job from Angajati where nume_angajat = 'Martin Louis'));
update angajati set salariu = salariu + (select pret_reparatie from reparatii_efectuate r, angajati a where a.nume_angajat = 'Martin Louis' and r.angajati_id_angajat = a.id_angajat and r.data_incepere_reparatii = TO_DATE('10.02.2020', 'DD.MM.YYYY'))*0.25 where nume_angajat = 'Martin Louis';

insert into reparatii_efectuate values( TO_DATE('12.02.2020', 'DD.MM.YYYY'),  TO_DATE('21.02.2020', 'DD.MM.YYYY'), 1090.75, (select ID_angajat from Angajati where nume_angajat = 'Ionescu Andrei'), (select joburi_id_job from Angajati where nume_angajat = 'Ionescu Andrei'));
update angajati set salariu = salariu + (select pret_reparatie from reparatii_efectuate r, angajati a where a.nume_angajat = 'Ionescu Andrei' and r.angajati_id_angajat = a.id_angajat and r.data_incepere_reparatii = TO_DATE('12.02.2020', 'DD.MM.YYYY'))*0.25 where nume_angajat = 'Ionescu Andrei';

insert into reparatii_efectuate values( TO_DATE('13.02.2020', 'DD.MM.YYYY'),  TO_DATE('14.02.2020', 'DD.MM.YYYY'), 125, (select ID_angajat from Angajati where nume_angajat = 'Jonathan Joestar'), (select joburi_id_job from Angajati where nume_angajat = 'Jonathan Joestar'));
update angajati set salariu = salariu + (select pret_reparatie from reparatii_efectuate r, angajati a where a.nume_angajat = 'Jonathan Joestar' and r.angajati_id_angajat = a.id_angajat and r.data_incepere_reparatii = TO_DATE('13.02.2020', 'DD.MM.YYYY'))*0.25 where nume_angajat = 'Jonathan Joestar';

insert into reparatii_efectuate values( TO_DATE('13.02.2020', 'DD.MM.YYYY'),  TO_DATE('14.02.2020', 'DD.MM.YYYY'), 125, (select ID_angajat from Angajati where nume_angajat = 'Babes Alexandra'), (select joburi_id_job from Angajati where nume_angajat = 'Babes Alexandra'));
update angajati set salariu = salariu + (select pret_reparatie from reparatii_efectuate r, angajati a where a.nume_angajat = 'Babes Alexandra' and r.angajati_id_angajat = a.id_angajat and r.data_incepere_reparatii = TO_DATE('13.02.2020', 'DD.MM.YYYY'))*0.15 where nume_angajat = 'Babes Alexandra';

insert into reparatii_efectuate values( TO_DATE('15.02.2020', 'DD.MM.YYYY'),  TO_DATE('16.02.2020', 'DD.MM.YYYY'), 65, (select ID_angajat from Angajati where nume_angajat = 'Jonathan Joestar'), (select joburi_id_job from Angajati where nume_angajat = 'Jonathan Joestar'));
update angajati set salariu = salariu + (select pret_reparatie from reparatii_efectuate r, angajati a where a.nume_angajat = 'Jonathan Joestar' and r.angajati_id_angajat = a.id_angajat and r.data_incepere_reparatii = TO_DATE('15.02.2020', 'DD.MM.YYYY'))*0.25 where nume_angajat = 'Jonathan Joestar';

insert into reparatii_efectuate values( TO_DATE('15.02.2020', 'DD.MM.YYYY'),  TO_DATE('20.02.2020', 'DD.MM.YYYY'), 345.75, (select ID_angajat from Angajati where nume_angajat = 'Boris Vladivich'), (select joburi_id_job from Angajati where nume_angajat = 'Boris Vladivich'));
update angajati set salariu = salariu + (select pret_reparatie from reparatii_efectuate r, angajati a where a.nume_angajat = 'Boris Vladivich' and r.angajati_id_angajat = a.id_angajat and r.data_incepere_reparatii = TO_DATE('15.02.2020', 'DD.MM.YYYY'))*0.25 where nume_angajat = 'Boris Vladivich';

--delete from reparatii_efectuate;
--select * from magazine;
--select * from echipe;
--select * from angajati;
--select * from detalii_angajati;
--select * from situatie_concedii;
--select * from echipe_magazine_fk;
--select * from reparatii_efectuate;


