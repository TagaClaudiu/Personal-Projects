--afisare oraselor cu magazine
select distinct denumire_oras from orase o, magazine m
where o.id_oras = m.orase_id_oras;


--afisarea magazinelor din iasi
select denumire_magazin from magazine m, orase o
where m.orase_id_oras = o.id_oras and o.denumire_oras = 'Iasi';


--afisarea membrilor echipei 2, mentionandu-le si numele joburilor
select id_angajat, nume_angajat, echipe_id_echipa as id_echipa, denumire_job from angajati a, joburi j
where echipe_id_echipa = 2 and joburi_id_job = id_job;


--afisarea echipelor, nr de angajati din echipa din fiecare magazin, ordonate dupa id-ul magazinelor, afisand si orasul in care se afla magazinul
select em.id_echipa, em.id_magazin, nr_angajati_in_magazin, denumire_magazin, denumire_oras from magazine m, orase o, echipe_magazine_fk em
where em.id_magazin = m.id_magazin and m.orase_id_oras = o.id_oras order by em.id_magazin;


--afisare nr total de angajati din fiecare magazin
WITH
 suma1
 as ( select sum(nr_angajati_in_magazin) as Magazin_1 from echipe_magazine_fk
where id_magazin = 1),
 suma2
 as ( select sum(nr_angajati_in_magazin) as Magazin_2 from echipe_magazine_fk
where id_magazin = 2),
 suma3
 as ( select sum(nr_angajati_in_magazin) as Magazin_3 from echipe_magazine_fk
where id_magazin = 3),
 suma4
 as ( select sum(nr_angajati_in_magazin) as Magazin_4 from echipe_magazine_fk
where id_magazin = 4),
 suma5
 as ( select sum(nr_angajati_in_magazin) as Magazin_5 from echipe_magazine_fk
where id_magazin = 5)
select Magazin_1, Magazin_2, Magazin_3, Magazin_4, Magazin_5 from suma1, suma2, suma3, suma4, suma5;


--afisarea echipelor cu managerii romani
select id_echipa, id_manager, nume_manager, nationalitate as nationalitate_manager from 
echipe, detalii_angajati
where nationalitate = 'RO' and id_manager = angajati_id_angajat;


--afisarea numelor si salariilor angajatilor depanatori care lucreaza full-time si au fost angajati dupa 01.01.2010
select a.nume_angajat, a.salariu, j.denumire_job, d.data_angajare, d.tip_contract_munca from angajati a, joburi j, detalii_angajati d
where
d.data_angajare > TO_DATE('01.01.2010', 'DD.MM.YYYY') and d.tip_contract_munca = 'full-time' and j.denumire_job = 'Depanator'
and j.id_job = a.joburi_id_job and d.angajati_id_angajat = a.id_angajat and d.angajati_id_job = a.joburi_id_job;


--afisarea angajatilor care au mai putin de o saptamana de concediu medical ramasa si sunt mai in varsta decat 30 de ani
select nume_angajat, zile_concediu_medical_ramase, cast((TO_DATE('2020-12-31', 'YYYY-MM-DD') - data_nastere)/365 as INT) as varsta_in_ani from angajati a, situatie_concedii s, detalii_angajati d
where s.zile_concediu_medical_ramase < 7 and a.id_angajat = d.angajati_id_angajat and a.id_angajat = s.angajati_id_angajat
and cast((TO_DATE('2020-12-31', 'YYYY-MM-DD') - data_nastere)/365 as INT)>30;


--afisarea nr de reparatii pt fiecare angajat
WITH
 angajat1 as (
select count(r.angajati_id_angajat) as nr_reparatii1 from angajati a, reparatii_efectuate r
where a.id_angajat = r.angajati_id_angajat and a.id_angajat = 6),
 angajat2 as (
select count(r.angajati_id_angajat) as nr_reparatii2 from angajati a, reparatii_efectuate r
where a.id_angajat = r.angajati_id_angajat and a.id_angajat = 7),
 angajat3 as (
select count(r.angajati_id_angajat) as nr_reparatii3 from angajati a, reparatii_efectuate r
where a.id_angajat = r.angajati_id_angajat and a.id_angajat = 8),
 angajat4 as (
select count(r.angajati_id_angajat) as nr_reparatii4 from angajati a, reparatii_efectuate r
where a.id_angajat = r.angajati_id_angajat and a.id_angajat = 9),
 angajat5 as (
select count(r.angajati_id_angajat) as nr_reparatii5 from angajati a, reparatii_efectuate r
where a.id_angajat = r.angajati_id_angajat and a.id_angajat = 10),
 angajat6 as (
select count(r.angajati_id_angajat) as nr_reparatii6 from angajati a, reparatii_efectuate r
where a.id_angajat = r.angajati_id_angajat and a.id_angajat = 11),
 angajat7 as (
select count(r.angajati_id_angajat) as nr_reparatii7 from angajati a, reparatii_efectuate r
where a.id_angajat = r.angajati_id_angajat and a.id_angajat = 12),
 angajat8 as (
select count(r.angajati_id_angajat) as nr_reparatii8 from angajati a, reparatii_efectuate r
where a.id_angajat = r.angajati_id_angajat and a.id_angajat = 13),
 angajat9 as (
select count(r.angajati_id_angajat) as nr_reparatii9 from angajati a, reparatii_efectuate r
where a.id_angajat = r.angajati_id_angajat and a.id_angajat = 14),
 angajat10 as (
select count(r.angajati_id_angajat) as nr_reparatii10 from angajati a, reparatii_efectuate r
where a.id_angajat = r.angajati_id_angajat and a.id_angajat = 15),
 angajat11 as (
select count(r.angajati_id_angajat) as nr_reparatii11 from angajati a, reparatii_efectuate r
where a.id_angajat = r.angajati_id_angajat and a.id_angajat = 16),
 angajat12 as (
select count(r.angajati_id_angajat) as nr_reparatii12 from angajati a, reparatii_efectuate r
where a.id_angajat = r.angajati_id_angajat and a.id_angajat = 17),
 angajat13 as (
select count(r.angajati_id_angajat) as nr_reparatii13 from angajati a, reparatii_efectuate r
where a.id_angajat = r.angajati_id_angajat and a.id_angajat = 18)
select nr_reparatii1, nr_reparatii2, nr_reparatii3, nr_reparatii4, nr_reparatii5, nr_reparatii6, nr_reparatii7, nr_reparatii8, nr_reparatii9, nr_reparatii10, nr_reparatii11, nr_reparatii12, nr_reparatii13
from angajat1, angajat2, angajat3, angajat4, angajat5, angajat6, angajat7, angajat8, angajat9, angajat10, angajat11, angajat12, angajat13;
 
 
 --afisarea depanatorului cu salariul cel mai mare, adaosul la salariul sau din raparatii, si nr de reparatii facute de acesta;
 with
 counterman as (
 select count(angajati_id_angajat) as nr_lucrari from reparatii_efectuate, angajati where salariu = (select max(salariu) from angajati where joburi_id_job = 2) and angajati_id_angajat = id_angajat)
 select id_angajat, nume_angajat, salariu, salariu - salariu_baza as adaos_la_salar, c.nr_lucrari from angajati a, joburi j, counterman c
 where a.salariu = (select max(salariu) from angajati where joburi_id_job = 2) and a.joburi_id_job = j.id_job; 
 
 
 
 --introduceri eronate (nu respecta constrangerile)
 
 --constraint la denumiri/nume
 insert into orase(denumire_oras) values ('2151256123');
 insert into magazine(ORASE_ID_ORAS, denumire_magazin) values ( 1, '12512512ba');
 insert into joburi(denumire_job) values ('asd412gasv6%$#');
 insert into echipe(id_manager, nume_manager) values (6, '125211gggggggg');
 insert into angajati(joburi_id_job, echipe_id_echipa, nume_angajat) values (2, 2, 'dadada123');
 
 --constraint la salarii; sa fie mai mari decat minimul pe economie
 insert into joburi(denumire_job, salariu_baza) values ('inginer', 500);
insert into angajati(joburi_id_job, echipe_id_echipa, nume_angajat, salariu) values (2, 2, 'dan', 500);

 --constraint la costul reparatiilor; nu vrem sa platim noi pe client
insert into reparatii_efectuate values( TO_DATE('16.02.2020', 'DD.MM.YYYY'),  TO_DATE('20.02.2020', 'DD.MM.YYYY'), -100, (select ID_angajat from Angajati where nume_angajat = 'Boris Vladivich'), (select joburi_id_job from Angajati where nume_angajat = 'Boris Vladivich'));


--constraint la datele de nastere, angajare, incepere a reparatiilor. sa nu se intample maine
insert into angajati values (50, 'daniel buzdugan', 2, 3, 2500);
insert into detalii_angajati values ('123456', 'pasaport', TO_DATE('20.10.2050', 'DD.MM.YYYY'), NULL, NULL, NULL, NULL, NULL, 50, 2);
insert into detalii_angajati values ('123456', 'pasaport', NULL, NULL, NULL, NULL, TO_DATE('20.10.2050', 'DD.MM.YYYY'), NULL, 50, 2);
insert into reparatii_efectuate values (TO_DATE('20.10.2050', 'DD.MM.YYYY'), TO_DATE('20.10.2051', 'DD.MM.YYYY'), 500, 6, 2);


--constraint la data terminarii reparatiilor; treb sa fie mai mare decat data inceperii reparatiilor
insert into reparatii_efectuate values (TO_DATE('20.10.2010', 'DD.MM.YYYY'), TO_DATE('20.10.2009', 'DD.MM.YYYY'), 500, 6, 2);

--constraint-uri la detalii

--tip_act_identitate e ori buletin ori pasaport
insert into detalii_angajati values ('123456', 'certificat de nastere', TO_DATE('20.10.2050', 'DD.MM.YYYY'), NULL, NULL, NULL, NULL, NULL, 50, 2);

--nationalitatea poate fi doar un nr specific de tari din zona Europei
insert into detalii_angajati values ('123456', 'pasaport', NULL, NULL, NULL, 'JAP', NULL, 'part-time', 50, 2);

--tipul contractului de munca nu e full-time sau part-time
insert into detalii_angajati values ('123456', 'pasaport', NULL, NULL, NULL, NULL, NULL, 'no time', 50, 2);

--nr de telefon trebuie sa aibe macar 10 cifre
insert into detalii_angajati values ('123456', 'pasaport', NULL, '123', NULL, NULL, NULL, 'part-time', 50, 2);

--nr de zile de concediu fara plata nu pot fi mai mult de 50 sau mai putine de 0
insert into situatie_concedii values (0, 0, 51, 50, 2);

--aceleasi erori le primim si cand facem modify
update angajati
set salariu = 500
where id_angajat = 50;

--daca 'stergem' valori din tabele mandatory
update angajati
set nume_angajat = NULL
where id_angajat = 50;

--incerc sa inserez/updatez nr de angajati dintr-o echipa dintr-un magazin cu valori mai mari decat nr total de angajati din echipa
update echipe_magazine_fk
set nr_angajati_in_magazin = 6
where id_echipa = 2;

--insert o valoare intr-un tabel care nu respecta foreign key-ul
insert into angajati values (50, 'daniel buzdugan', 7, 3, 2500);

--update o valoare dintr-un tabel care nu respecta foreign key-ul
update angajati
set echipe_id_echipa = 10
where id_angajat = 50;

--stergere valori
delete from angajati 
where id_angajat = 50;

--adaugarea unui noui tip de job, si angajarea unui nou lucrator
insert into Joburi(denumire_job, salariu_baza) values ('Inginer Electro', 3000);
insert into angajati values ( angajati_id_angajati_seq.nextval, 'Daniel Buzdugan', 3, 5, 3000);
select * from angajati where joburi_id_job = 3;


--stergerea unui angajat din echipa 1 si transferarea noului inginer in echipa 1;
delete from detalii_angajati
where angajati_id_angajat = (select id_angajat from angajati where nume_angajat = 'Anitoaiei Daniel');
delete from situatie_concedii
where angajati_id_angajat = (select id_angajat from angajati where nume_angajat = 'Anitoaiei Daniel');
delete from reparatii_efectuate
where angajati_id_angajat = (select id_angajat from angajati where nume_angajat = 'Anitoaiei Daniel');
delete from angajati
where nume_angajat = 'Anitoaiei Daniel';
update angajati
set echipe_id_echipa = 1
where joburi_id_job = (select id_job from joburi where denumire_job = 'Inginer Electro') and nume_angajat = 'Daniel Buzdugan';



--stergerea tabelelor
drop table Situatie_Concedii;

drop table reparatii_efectuate;

drop table echipe_magazine_FK;

drop table magazine;

drop table orase;

drop table angajati;

drop table joburi;

drop table detalii_angajati;

drop table echipe;

drop sequence detalii_angajati_detalii_angaj;
drop sequence angajati_id_angajat_seq;
drop sequence echipe_id_echipa_seq;
drop sequence joburi_id_job_seq;
drop sequence magazine_id_magazin_seq;
drop sequence orase_id_oras_seq;
